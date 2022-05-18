package in.ashokit.service;

import java.util.Map;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccForm;
import in.ashokit.binding.UserRegForm;

public interface UserMgmtService {

	
	//login form fuctionality
	public String login(LoginForm loginForm);
	
	//registration functionality
	public String emailCheck(String emailId);
	public Map<Integer,String> loadCountries();
	public Map<Integer,String>loadStates(Integer countryId);
	public Map<Integer,String>loadCities(Integer stateId);
	public String registerUser(UserRegForm form);
	
	//unlock functionality
	public String unlockAccout(UnlockAccForm unlockForm);
	//forget functionality
	
	public String forgetPwd(String emailId);
}
