package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.UnlockAccForm;
import in.ashokit.service.UserMgmtService;

@RestController
public class ForgetPwdRestController {

	@Autowired
	private UserMgmtService service;

	@GetMapping("/forgotPwd/{emailId}")
	public String forgetPwd(@PathVariable String emailId) {
		return service.forgetPwd(emailId);
	}
}
