package blog.ex.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.ex.model.dao.AccountDao;
import blog.ex.model.entity.AccountEntity;

@Service
public class AccountService {
	// AccountDaoクインタフェースを使います
	@Autowired
	private AccountDao accountDao;
	// ユーザの情報を保存します
	public boolean createAccount(String accountName, String accountEmail, String password) {
		// 現在の日時を取得します
		LocalDateTime registerDate = LocalDateTime.now();
		// RegisterControllerから渡されるユーザ情報（ユーザーメールアドレス）を条件にDB検索で検索します
		AccountEntity accountEntity = accountDao.findByAccountEmail(accountEmail);
		// RegisterControllerから渡されるユーザ情報（ユーザ名、パスワード）を条件にDB検索で検索した結果
		// なかった場合には、保存
		if (accountEntity == null) {
			accountDao.save(new AccountEntity(accountName, accountEmail, password, registerDate));
			return true;
		} else {
			return false;
		}
	}
	// idを見つけるために
	// コントローラーでわたってきたaccountEmailを基にしてDBを検索
	public AccountEntity loginAccount(String accountEmail, String password) {
		AccountEntity accountEntity = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		// なかった場合はログイン失敗
		if (accountEntity == null) {
			return null;
		} else {
			// あった場合はログイン成功
			return accountEntity;
		}
	}
}
