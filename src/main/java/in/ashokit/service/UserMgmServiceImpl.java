package in.ashokit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccForm;
import in.ashokit.binding.UserRegForm;
import in.ashokit.entities.CityMasterEntity;
import in.ashokit.entities.CountryMasterEntity;
import in.ashokit.entities.StateMasterEntity;
import in.ashokit.entities.UserAccountEntity;
import in.ashokit.repository.CityMasterRepo;
import in.ashokit.repository.CountryMasterRepo;
import in.ashokit.repository.StateMasterRepo;
import in.ashokit.repository.UserDtlsRepo;
import in.ashokit.utils.EmailUtils;

@Service
public class UserMgmServiceImpl implements UserMgmtService {

	@Autowired
	private UserDtlsRepo userRepo;
	@Autowired
	private CityMasterRepo cityRepo;
	@Autowired
	private StateMasterRepo stateRepo;
	@Autowired
	private CountryMasterRepo countryRepo;
	@Autowired
	private EmailUtils emailUtil;

	@Override
	public String emailCheck(String emailId) {
		UserAccountEntity entity = userRepo.findByEmail(emailId);
		if (entity == null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}

	@Override
	public String forgetPwd(String emailId) {
		UserAccountEntity entity = userRepo.findByEmail(emailId);
		if (entity == null) {
			return "User Not Register";
		}
		// send mail with pwd
		String subject = "Recover Password -Ashok IT";

		String fileName = "RECOVER-PASSWORD-EMAIL-BODY-TEMPLATE.txt";
		String body = readMailBody(fileName, entity);
		boolean isSent = emailUtil.sendEmail(emailId, subject, body);
		if (isSent) {
			return "Password sent to Register Email";
		}
		return "ERROR";
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		List<CityMasterEntity> entity = cityRepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		for (CityMasterEntity city : entity) {
			cityMap.put(city.getCityId(), city.getCityName());
		}
		return cityMap;
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<CountryMasterEntity> countries = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		for (CountryMasterEntity country : countries) {
			countryMap.put(country.getCountryID(), country.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {
		List<StateMasterEntity> entity = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		for (StateMasterEntity state : entity) {
			stateMap.put(state.getStateId(), state.getStateName());
		}
		return stateMap;
	}

	@Override
	public String login(LoginForm loginForm) {
		UserAccountEntity findByEmailAndPazzword = userRepo.findByEmailAndPazzword(loginForm.getEmail(),
				loginForm.getPwd());
		if (findByEmailAndPazzword == null) {
			return "Invalid Account ";
		}
		if (findByEmailAndPazzword != null && findByEmailAndPazzword.getAccStatus().equals("LOCKED")) {
			return "Your  Account Locked";
		}
		return "Valid Account";
	}

	@Override
	public String registerUser(UserRegForm form) {

		UserAccountEntity entity = new UserAccountEntity();
		BeanUtils.copyProperties(form, entity);
		entity.setAccStatus("LOCKED");
		entity.setPazzword(generatePwd());

		// TODO write to send mail

		UserAccountEntity saveEntity = userRepo.save(entity);
		String email = form.getEmail();
		String subject = "User Registration -Ashok IT";
		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		String body = readMailBody(fileName, entity);
		boolean isSent = emailUtil.sendEmail(email, subject, body);
		if (saveEntity.getUserId() != null && isSent) {
			return "SuccessFul";
		}
		return "Failed!!";
	}

	@Override
	public String unlockAccout(UnlockAccForm unlockForm) {
		if (!unlockForm.getNewPwd().equals(unlockForm.getConfirmNewPwd())) {
			return "New Password and Confirm Password should be same here.";
		}
		UserAccountEntity entity = userRepo.findByEmailAndPazzword(unlockForm.getEmail(), unlockForm.getTempPwd());
		if (entity == null) {
			return "Temp password incorrect ";
		}
		entity.setPazzword(unlockForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");
		userRepo.save(entity);
		return "Account Unlocked ";
	}

	private String generatePwd() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}

	private String readMailBody(String fileName, UserAccountEntity entity) {
		String mailBody = null;
		try {
			StringBuffer sb = new StringBuffer();
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);

			String readLine = br.readLine();
			while (readLine != null) {
				sb.append(readLine);
				readLine = br.readLine();
			}
			mailBody = sb.toString();
			mailBody = mailBody.replace("{FNAME}", entity.getFname());
			mailBody = mailBody.replace("{LNAME}", entity.getLname());
			mailBody = mailBody.replace("{TEMP-PWD}", entity.getPazzword());
			mailBody = mailBody.replace("{EMAIL}", entity.getEmail());
			mailBody = mailBody.replace("{PWD}", entity.getPazzword());
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}
}
