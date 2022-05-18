package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.LoginForm;
import in.ashokit.service.UserMgmtService;

@RestController
public class LoginRestController {
	
	@Autowired
	private UserMgmtService service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		return service.login(loginForm);
	}

}
