package blog.ex.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.ex.model.dao.AccountDao;
import blog.ex.model.entity.AccountEntity;

@Service
public class AccountService {
	/**
	 * @Autowired private AccountDao accountDao; accountテーブルにアクセスして操作するため、AccountDaoクインタフェース
	 *            を使えるようにしておきます。
	 */
	@Autowired
	private AccountDao accountDao;
	
	/**
	 * @param accountName ユーザー名
	 * @param accountEmail    メールアドレス
	 * @param password パスワード
	 * @return
	 */

	/**
	 * createAccountソッドは、ユーザーアカウントを作成するためのメソッドで、引数としてaccountName、accountEmail、passwordを受け取ります。
	 */
	
	public boolean createAccount(String accountName,String accountEmail,String password) {
		/**
		 * 現在の日時を取得して、registerDateに保存します
		 */
		LocalDateTime registerDate = LocalDateTime.now();
		/**
		 * AccountDaoインターフェースのfindByEmailメソッドを使用して、 指定されたaccountEmailアドレスを持つAccountEntityを検索し,
		 * 結果をaccountEntityに格納する。
		 */
		AccountEntity accountEntity = accountDao.findByAccountEmail(accountEmail);
		/**
		 * AccountEntityが見つからなかった場合、
		 */
		if(accountEntity == null) {
			/**
			 * 新しいAccountEntityオブジェクトを作成し、
			 * 引数として受け取ったaccountName、accountEmail、password、registerDateを設定します。
			 * の後、AccountDaoのsaveメソッドを呼び出して、 新しいAccountEntityオブジェクトを保存します。そして、trueを返します。
			 **/
			accountDao.save(new AccountEntity(accountName,accountEmail,password,registerDate));
			return true;
		}else {
			/** UserEntityが見つかった場合、falseを返します。 **/
			return false;
		}
	}
	/**
	 * loginAccountソッドは、ユーザーアカウントのログイン機能を実装するためのメソッドで、引数としてemailとpasswordを受け取ります
	 **/
	/**
	 * @param accountEmail    メールアドレス
	 * @param password パスワード
	 * @return
	 */
	public AccountEntity loginAccount(String accountEmail,String password) {
		/**
		 * 引数として受け取ったaccountEmailとpasswordを使用して、
		 * UserDaoインタフェースのfindByEmailAndPasswordメソッドを呼び出して、 該当するAccountEntityを検索します。
		 **/
		AccountEntity accountEntity = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		/**
		 * 検索結果として取得したAccountEntityがnullであるかどうかを確認し、
		 * nullである場合はログインに失敗したことを示すためにnullを返します。
		 **/
		if(accountEntity == null) {
			return null;
		}else {
			/** 検索結果として取得したAccountEntityがnullでない場合は、
			 * ログインに成功したことを示すために検索結果のAccountEntityを返します **/
			return accountEntity;
		}
		/**
		 * つまり、このメソッドは、ユーザーアカウントのログインが成功したかどうかを判断するために使用されます。
		 * UserDaoインタフェースのfindByEmailAndPasswordメソッドが、
		 * emailとpasswordを使用してデータベース内のユーザーエンティティを検索するために使用されます。
		 **/
	}
	
}
