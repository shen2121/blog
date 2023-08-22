package blog.ex.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.ex.model.entity.AccountEntity;

public interface AccountDao extends JpaRepository<AccountEntity, Long> {
	// AccountEntityを保存します
	AccountEntity save(AccountEntity accountEntity);

	// AccountEntityの中からemail一致するものを検索して返します
	AccountEntity findByAccountEmail(String accountEmail);

	// AccountEntityの中から、そのemailアドレスとpasswordと一致するものを検索して返します
	AccountEntity findByAccountEmailAndPassword(String accountEmail, String password);
}
