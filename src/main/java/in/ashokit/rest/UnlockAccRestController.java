package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.UnlockAccForm;
import in.ashokit.service.UserMgmtService;

@RestController
public class UnlockAccRestController {
	@Autowired
	private UserMgmtService service;
	
	@PostMapping("/unlock")
	public String getUserUnlock(@RequestBody UnlockAccForm unlockForm) {
		return service.unlockAccout(unlockForm);
	}

}
