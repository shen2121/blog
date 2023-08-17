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
/**
* @Controllerアノテーションは、Spring Frameworkがコンポーネントスキャン機能
* によってこのクラスをDIコンテナに登録するために使用されます。
* これにより、UserRegisterControllerクラスは、他のコンポーネントに注入されることができ,
* システム内で一元的に管理されることができます。
* コンポーネントスキャン機能は、Spring Frameworkにおいて、アノテーションで構成されたクラスを自動的に検出し、
* DIコンテナに登録するための機能です
* DI（Dependency Injection）コンテナは、Spring Frameworkが提供する機能の一つで、アプリケーションに必要なオブジェクトを管理する仕組みです。
*通常、アプリケーションに必要なオブジェクトは、new演算子を使用してインスタンス化していますが、
*DIコンテナを使用すると、オブジェクトの生成や注入を自動的に行うことができます。
**/
@Controller
public class LoginController {
	/**
	 * @Autowiredアノテーションは、DIコンテナが自動的にUserServiceインスタンスを
	 * 注入するために使用されます。これにより、UserRegisterControllerクラスは、
	 * UserServiceクラスのメソッドを呼び出すことができ、ログインの処理を実行することができます。
	 **/
	@Autowired
	private AccountService accountService;
	@Autowired
	private HttpSession session;
	/**
	 * @GetMappingアノテーションは、HTTP GETリクエストに対するマッピングを設定するために使用されます。
	 * "/login"と指定されているので、このエンドポイントは"/user/login"でアクセス可能です。
	 * getLoginPage()メソッドは、"login.html"を返すことで、ログイン画面を表示します。**/
	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}
	/**
	 * @PostMappingアノテーションは、HTTP POSTリクエストを処理するためのメソッドであることを示します。/login/processは、
	 * リクエストのURLの"/user"の下にある、このメソッドが処理するリクエストを指します。
	 * public String login(@RequestParam String email,@RequestParam String password)は、
	 * このメソッドがリクエストパラメータを受け取ることを示します。
	 * @RequestParamアノテーションは、メソッドの引数に対応するリクエストパラメータの値を受け取ることを示します。
	 * この場合、emailとpasswordがリクエストパラメータとして渡されます。**/
	@PostMapping("/login/process")
	public String login(@RequestParam String email,@RequestParam String password) {
		/**
		 * accountService.loginAccount(email, password)は、AccuntServiceのloginAccountメソッドを呼び出して、
		 * 指定されたメールアドレスとパスワードでアカウントにログインできるかどうかを確認します。
		 * もしログインに成功した場合は、セッションにログイン情報を格納し、"redirect:/user/blog"にリダイレクトされ、
		 * それ以外の場合は"redirect:/user/login"にリダイレクトされる**/
		AccountEntity accountList =accountService.loginAccount(email, password);
		if(accountList == null) {
			return "redirect:/user/login";
		}else {
			session.setAttribute("account", accountList);
			return "blog.html";
		}
	}
}
