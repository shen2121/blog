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
	//すべてのブログ投稿を取得します
	public List<BlogEntity> findAllBlogPost(Long accountId){
		//なかった場合はnullを返します
		if(accountId == null) {
			return null;
			//あった場合
		}else {
			return blogDao.findByAccountId(accountId);
		}
	}
	//ブログ記事を保存します
	public boolean createBlogPost(String blogTitle,LocalDate registerDate,String category,String fileName,String article,Long accountId) {
		//同じタイトルと登録日の記事が存在するかを検索します
		BlogEntity blogList = blogDao.findByBlogTitleAndRegisterDate(blogTitle, registerDate);
		//なかった場合新しい記事作成します
		if(blogList == null) {
			blogDao.save(new BlogEntity(blogTitle,registerDate,category,fileName,article,accountId));
			//
			return true;
		}else {
			//
			return false;
		}
	}
	/**getBlogPostメソッドは、与えられたblogIdに基づいて、blogDaoから該当する
	 * ブログエンティティを取得して返します。blogIdがnullである場合は、nullを返します**/
	public BlogEntity getBlogPost(Long blogId) {
		if(blogId == null) {
			return null;
		}else {
			return blogDao.findByBlogId(blogId);
		}
	}
	/**
	 * editBlogPostメソッド
	 * ブログ記事のタイトル、登録日、詳細などの情報を受け取り、指定されたblogIdに対応するブログ記事を更新します。
	 * blogDao.findByBlogId(blogId)によって、指定されたblogIdに対応するブログ記事を取得し、更新します。
	 * userIdがnullの場合は、falseを返します。更新に成功した場合は、trueを返します。**/
	public boolean editBlogPost(String blogTitle,LocalDate registerDate,String category,String article,Long accountId,Long blogId) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if(accountId == null) {
			return false;
		}else {
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
	/**
	 * public boolean editBlogImage(Long blogId, String fileName, Long accountId): 
	 * メソッド名と引数を定義しています。ブログID、ファイル名、ユーザーIDを引数として受け取ります。戻り値はboolean型です。**/
	public boolean editBlogImage(Long blogId,String fileName,Long accountId) {
		/**
		 * BlogEntity blogList = blogDao.findByBlogId(blogId);
		 *  引数で渡されたブログIDから、ブログ情報を取得します。ブログIDに該当するブログが存在しない場合はnullが返ります。**/
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		/**
		 * if (fileName == null || blogList.getBlogImage().equals(fileName)) { ... }: 
		 * ファイル名がnullであるか、既に設定されている画像ファイル名と同じである場合は、処理を中断してfalseを返します。**/
		if(fileName == null || blogList.getBlogImage().equals(fileName)) {
			return false;
		}else {/**
			 * blogList.setBlogId(blogId);: ブログIDを設定します。
			 * blogList.setBlogImage(fileName);: 新しい画像ファイル名を設定します。
			 * blogList.setAccountId(accountId);: ユーザーIDを設定します。
			 * blogDao.save(blogList);: ブログ情報を更新して保存します。
			 * return true;: 画像の編集に成功した場合、trueを返します。**/
			blogList.setBlogId(blogId);
			blogList.setBlogImage(fileName);
			blogList.setAccountId(accountId);
			blogDao.save(blogList);
			return true;
		}
	}
	/**deleteByBlogIdメソッドを呼び出すことで、
	 * 指定されたIDのブログ記事を削除するdeleteBlogメソッドを定義しています。
	 * メソッドの戻り値の型はbooleanであり、メソッドの実行結果を呼び出し元に返します。
	 * このメソッドは、引数として渡されたblogIdがnullである場合はfalseを返します。
	 * そうでない場合は、BlogDaoインターフェースのdeleteByBlogIdメソッドを呼び出して、
	 * 削除処理を実行し、結果としてtrueを返します。**/
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
