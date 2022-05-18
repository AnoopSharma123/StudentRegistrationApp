package in.ashokit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.UserRegForm;
import in.ashokit.service.UserMgmtService;

@RestController
public class RegistrationRestController {
	
	@Autowired
	private UserMgmtService service;
	
	@GetMapping("/email/{emailId}")
	public String emailCheck(@PathVariable String emailId) {
		return service.emailCheck(emailId);
	}
	
	@GetMapping("/countries")
	public Map<Integer,String>getCounties(){
	  return service.loadCountries();	
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer,String>getStates(@PathVariable Integer countryId){
		return service.loadStates(countryId);
	}
	@GetMapping("/cities/{stateId}")
	public Map<Integer,String>getCities(@PathVariable Integer stateId){
		return service.loadCities(stateId);
	}
	
	@PostMapping("/user")
	public String getUserDetails(@RequestBody UserRegForm form) {
		return service.registerUser(form);
	}
}
