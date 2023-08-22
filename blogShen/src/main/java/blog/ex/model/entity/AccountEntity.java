package blog.ex.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
//空のコンストラクタの作成
@NoArgsConstructor
//全ての引数を持つコンストラクタの作成
@AllArgsConstructor
//全ての引数を持つコンストラクタを自動生成します。
@RequiredArgsConstructor
@Entity
//tableを指名します
@Table(name = "account")

public class AccountEntity {
	@Id
    //フィールドとテーブルのカラムをマッピングします。
	@Column(name = "account_id")
	//プライマリーキーを自動生成する方法を指定します。
	@GeneratedValue(strategy = GenerationType.AUTO)
	//accountId
	private Long accountId;
	//名前
	@NonNull
	@Column(name = "account_name")
	private String accountName;
    //メールアドレス
	@NonNull
	@Column(name = "account_email")
	private String accountEmail;
    //パスワード
	@NonNull
	@Column(name = "password")
	private String password;
    //日時
	@NonNull
	@Column(name = "register_date")
	private LocalDateTime registerDate;
}
