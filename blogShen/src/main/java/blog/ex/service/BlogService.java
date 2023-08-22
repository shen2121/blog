package blog.ex.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.ex.model.dao.BlogDao;
import blog.ex.model.entity.BlogEntity;

@Service
public class BlogService {
	// AccountDaoクインタフェースを使います
	@Autowired
	private BlogDao blogDao;

	// すべてのブログ投稿を取得します
	public List<BlogEntity> findAllBlogPost(Long accountId) {
		// なかった場合はnullを返します
		if (accountId == null) {
			return null;
			// あった場合
		} else {
			return blogDao.findByAccountId(accountId);
		}
	}

	// ブログ記事を保存します
	public boolean createBlogPost(String blogTitle, LocalDate registerDate, String category, String fileName,
			String article, Long accountId) {
		// 同じタイトルと登録日の記事が存在するかを検索します
		BlogEntity blogList = blogDao.findByBlogTitleAndRegisterDate(blogTitle, registerDate);
		// なかった場合新しい記事作成します
		if (blogList == null) {
			blogDao.save(new BlogEntity(blogTitle, registerDate, category, fileName, article, accountId));
			// 保存します
			return true;
		} else {
			// あった場合は作成失敗しました
			return false;
		}
	}

	// blogIdを基準でblogDaoから内容を取得します
	public BlogEntity getBlogPost(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	// blogIdに対応するブログ内容を取得して更新します
	public boolean editBlogPost(String blogTitle, LocalDate registerDate, String category, String article,
			Long accountId, Long blogId) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if (accountId == null) {
			return false;
		} else {
			blogList.setBlogId(blogId);
			blogList.setBlogTitle(blogTitle);
			blogList.setRegisterDate(registerDate);
			blogList.setCategory(category);
			blogList.setArticle(article);
			blogList.setAccountId(accountId);
			blogDao.save(blogList);
			return true;
		}
	}

	// ブログの画像を更新します
	public boolean editBlogImage(Long blogId, String fileName, Long accountId) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if (fileName == null || blogList.getBlogImage().equals(fileName)) {
			return false;
		} else {
			blogList.setBlogId(blogId);
			blogList.setBlogImage(fileName);
			blogList.setAccountId(accountId);
			blogDao.save(blogList);
			return true;
		}
	}

	// blogIdを基づいてblogDaoから対応の内容を削除します。
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
