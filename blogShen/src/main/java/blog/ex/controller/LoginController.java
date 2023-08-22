package blog.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.ex.model.entity.AccountEntity;
import blog.ex.service.AccountService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class LoginController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private HttpSession session;

	// ログイン画面を表示
	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}

	// 送信されたログインデータの処理
	@PostMapping("/login/process")
	public String login(@RequestParam String email, @RequestParam String password) {
		// accountListのなか対応するメールアドレスとパスワードを検索します
		AccountEntity accountList = accountService.loginAccount(email, password);
		// なかった場合このページのまま
		if (accountList == null) {
			return "redirect:/user/login";
			// それ以外ブログ一覧ページを飛びます
		} else {
			session.setAttribute("account", accountList);
			return "redirect:/user/blog/list";
		}
	}
}
