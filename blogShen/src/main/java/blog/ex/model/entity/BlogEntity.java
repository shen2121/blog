package blog.ex.model.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "blog")
public class BlogEntity {
	@Id
	 //フィールドとテーブルのカラムをマッピングします。
	@Column(name = "blog_id")
	//プライマリーキーを自動生成する方法を指定します。
	@GeneratedValue(strategy = GenerationType.AUTO)
	//blogId
	private Long blogId;
	//タイトル
	@NonNull
	@Column(name = "blog_title")
	private String blogTitle;
    //日時
	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "register_date")
	private LocalDate registerDate;
    //カテゴリ
	@NonNull
	@Column(name = "category_name")
	private String category;
	//ブログ画像
	@NonNull
	@Column(name = "blog_image")
	private String blogImage;
	//ブログ詳細
	@NonNull
	@Column(name = "article")
	private String article;
	//accountId
	@Column(name = "account_id")
	private Long accountId;
	//コンストラクター
	public BlogEntity( @NonNull String blogTitle, @NonNull LocalDate registerDate, @NonNull String category,
			@NonNull String blogImage, @NonNull String article, Long accountId) {
		this.blogTitle = blogTitle;
		this.registerDate = registerDate;
		this.category = category;
		this.blogImage = blogImage;
		this.article = article;
		this.accountId = accountId;
	}
	
}
