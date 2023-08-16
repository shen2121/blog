package blog.ex.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.ex.model.entity.AccountEntity;


public interface AccountDao extends JpaRepository<AccountEntity,Long>{
	/**
	 *saveメソッドは、UserEntityを引数として受け取り、
	 *UserEntityを保存し、保存したUserEntityを返します
	 */
	AccountEntity save(AccountEntity accountEntity);
	/**
	 findByEmailは、String型の引数を受け取り、
	 その引数と一致するemailを持つAccountEntityを返します
	 */
	AccountEntity findByAccountEmail(String accountEmail);
	/**
	 * findByEmailAndPassword(String accountEmail,String password)：
	 * このメソッドは、String型のemailアドレスとpasswordを引数に取り、
	 * データベース内のAccountEntityの中から、そのemailアドレスとpasswordと一致するものを検索し、
	 * それを返します。
	 ***/
	AccountEntity findByAccountEmailAndPassword(String accountEmail,String password);
}
