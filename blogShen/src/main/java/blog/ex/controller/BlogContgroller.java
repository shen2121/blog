package blog.ex.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.ex.model.entity.AccountEntity;
import blog.ex.model.entity.BlogEntity;
import blog.ex.service.BlogService;
import jakarta.servlet.http.HttpSession;

//全てのurlの前に/user/blogを追加
@RequestMapping("/user/blog")

@Controller
public class BlogContgroller {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	// ブログ一覧画面を取得します
	@GetMapping("/list")
	// コントローラーからビューに渡すためのデータを格納します
	public String getBlogListPage(Model model) {
		// アカウント情報を取得
		// accountIdを取得します
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		// アカウント名を取得
		String accountName = accountList.getAccountName();
		// ユーザーに関連するブログ投稿を取得します
		List<BlogEntity> blogList = blogService.findAllBlogPost(accountId);
		// ページでaccountNameとblogListを表示します
		model.addAttribute("accountName", accountName);
		model.addAttribute("blogList", blogList);
		return "blog.html";
	}

	// ブログ登録画面を取得
	@GetMapping("/register")
	public String getBlogRegisterPage(Model model) {
		// アカウント情報を取得
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		// アカウント名を取得
		String accountName = accountList.getAccountName();
		model.addAttribute("accountName", accountName);
		model.addAttribute("registerMessage", "新規記事追加");
		return "blog-register.html";
	}

	// 送信されたデータの処理
	@PostMapping("/register/process")

	public String blogRegister(@RequestParam String blogTitle, @RequestParam LocalDate registerDate,
			@RequestParam String category, @RequestParam MultipartFile blogImage, @RequestParam String article,
			Model model) {
		// アカウント情報を取得
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		// 日時とファイルの名前を取得
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-ss-").format(new Date())
				+ blogImage.getOriginalFilename();
		try {
			// 保存先を指定します
			Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 登録した記事を保存します
		if (blogService.createBlogPost(blogTitle, registerDate, category, fileName, article, accountId)) {
			return "blog-register-fix.html";
		} else {
			// 登録出来なかった場合は画面に既に登録済みですと表示します
			model.addAttribute("registerMessage", "既に登録済みです");
			return "blog-register.html";
		}
	}

	// 編集画面の取得
	@GetMapping("/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		// アカウント情報を取得
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		// アカウント名を取得
		String accountName = accountList.getAccountName();
		model.addAttribute("accountName", accountName);
		// 指定されたblogId対応ブログ内容取得します
		BlogEntity blogList = blogService.getBlogPost(blogId);
		// ブログがない場合は一覧画面のまま
		if (blogList == null) {
			return "redirecr:/user/blog/list";
		} else {
			// ブログと画面メッセージで記事編集の表示
			model.addAttribute("blogList", blogList);
			model.addAttribute("editMessage", "記事編集");
			return "blog-edit.html";
		}
	}

	// 編集更新の処理
	@PostMapping("/update")

	public String blogUpdate(@RequestParam String blogTitle, @RequestParam LocalDate registerDate,
			@RequestParam String category, @RequestParam String article, @RequestParam Long blogId, Model model) {
		// アカウント情報を取得
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		// アカウントidを取得
		Long accountId = accountList.getAccountId();
		// 指定されたblogIdに対応するブログを更新します
		if (blogService.editBlogPost(blogTitle, registerDate, category, article, accountId, blogId)) {
			// 成功の場合blog-edit-fix.htmlに飛びます
			return "blog-edit-fix.html";
		} else {
			// 失敗の場合画面に更新に失敗しましたを表示
			model.addAttribute("registerMessage", "更新に失敗しました");
			return "blog-edit.html";
		}
	}

	// 画像編集処理
	@GetMapping("/image/edit/{blogId}")
	public String getBlogEditImagePage(@PathVariable Long blogId, Model model) {
		// アカウント情報を取得
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		// アカウント名前を取得
		String accountName = accountList.getAccountName();
		model.addAttribute("accountName", accountName);
		// blogId対応のブログ内容所得して、blogListで代入
		BlogEntity blogList = blogService.getBlogPost(blogId);
		// なかった場合は一覧画面のまま
		if (blogList == null) {
			return "redirecr:/user/blog/list";
		} else {
			// あった場合は画像編集画面にとびます
			model.addAttribute("blogList", blogList);
			model.addAttribute("editImageMessage", "画像編集");
			return "blog-edit-img.html";
		}
	}

	// 画像の更新処理
	@PostMapping("/image/update")

	public String blogImgUpdate(@RequestParam MultipartFile blogImage, @RequestParam Long blogId, Model model) {
		// アカウント情報を取得
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		// アカウントidを取得
		Long accountId = accountList.getAccountId();
		// 日時とファイルの名前をfileName変数に代入
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
				+ blogImage.getOriginalFilename();
		try {
			// ファイルを保存
			Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 成功した場合blog-edit-fix.htmlにとびます
		if (blogService.editBlogImage(blogId, fileName, accountId)) {
			return "blog-edit-fix.html";
		} else {
			// 失敗した場合画面に失敗を表示します
			BlogEntity blogList = blogService.getBlogPost(blogId);
			model.addAttribute("blogList", blogList);
			model.addAttribute("editImageMessage", "更新失敗です");
			return "blog-edit-img.html";
		}
	}

	// 削除画面の所得
	@GetMapping("/delete/list")
	public String getBlogDeleteListPage(Model model) {
		// アカウントの情報、idを取得
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		// アカウントの名前を取得
		String accountName = accountList.getAccountName();
		// このアカウントに関するぶろぐ内容を取得
		List<BlogEntity> blogList = blogService.findAllBlogPost(accountId);

		model.addAttribute("accountName", accountName);
		model.addAttribute("blogList", blogList);
		model.addAttribute("deleteMessage", "削除一覧");
		return "blog-delete.html";

	}

	@GetMapping("/delete/detail/{blogId}")
	public String getBlogDeleteDetailPage(@PathVariable Long blogId, Model model) {
		// アカウントの情報、idを取得
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		// アカウントの名前を取得
		String accountName = accountList.getAccountName();
		// blogId対応のブログ内容所得して、blogListで代入
		BlogEntity blogList = blogService.getBlogPost(blogId);
		// ブログはなかった場合はブログ一覧のまま
		if (blogList == null) {
			return "redirecr:/user/blog/list";
		} else {
			// あった場合は削除記事詳細を表示blog-delete-detail.htmlにとびます
			model.addAttribute("blogList", blogList);
			model.addAttribute("DeleteDetailMessage", "削除記事詳細");
			return "blog-delete-detail.html";
		}
	}

	// 削除の処理
	@PostMapping("/delete")
	public String blogDelete(@RequestParam Long blogId, Model model) {
		if (blogService.deleteBlog(blogId)) {
			// 成功した場合はblog-delete-fix.htmlをとびます
			return "blog-delete-fix.html";
		} else {
			// 失敗した場合は画面に記事削除に失敗しましたを表示
			model.addAttribute("DeleteDetailMessage", "記事削除に失敗しました");
			return "blog-delete.html";
		}
	}

	// ログアウトしたらログイン画面に戻ります
	@GetMapping("/logout")
	public String Logout() {

		session.invalidate();
		return "redirect:/user/login";
	}
}
