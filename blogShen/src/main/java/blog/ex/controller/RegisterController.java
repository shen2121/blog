package blog.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.ex.service.AccountService;

//全てのurlの前に/user/を追加
@RequestMapping("/user")
@Controller
public class RegisterController {
	@Autowired
	private AccountService accountService;

	// 登録画面を表示
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
	}

	// ユーザー情報の登録
	@PostMapping("/register/process")
	public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		accountService.createAccount(name, email, password);
		return "redirect:/user/login";
	}

}
