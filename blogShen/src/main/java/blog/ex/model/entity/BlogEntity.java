package blog.ex.model.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
* このエンティティクラスは、blogsというテーブルを表しています。テーブルのカラムは、以下の通りです。
* JPAを使用することで、このエンティティクラスはデータベースとのマッピングを簡単に行うことができます。
* このエンティティクラスのインスタンスをデータベースに保存することができます。
* さらに、JPAを使用することで、データベースからエンティティクラスのインスタンスを取得することもできます。
*/

/**
* @Data Lombokのアノテーションで、クラスに対してGetter、 Setter、toString、 equals、hashCodeメソッドを
*       自動生成します。
*/
@Data
/**
* @NoArgsConstructor Lombokのアノテーションで、引数なしのデフォルトコンストラクタを自動生成します。
*/
@NoArgsConstructor
/**
* @AllArgsConstructor Lombokのアノテーションで、全ての引数を持つコンストラクタを自動生成します。
*/
@AllArgsConstructor
/**
* @RequiredArgsConstructor Lombokのアノテーションで、全ての引数を持つコンストラクタを自動生成します。
*/
@RequiredArgsConstructor
/**
* @Entity JPAのアノテーションで、エンティティクラスであることを示します。
*/
@Entity
/**
* @Table() JPAのアノテーションで、テーブル名を指定します。
**/
public class BlogEntity {
	/**
	 * JPAのアノテーションで、プライマリーキーであることを示します
	 */
	@Id
	/**
	 * @Column(name="blog_id") JPAのアノテーションで、フィールドとテーブルのカラムをマッピングします。
	 */
	@Column(name = "blog_id")
	/**
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 *                          JPAのアノテーションで、プライマリーキーを自動生成する方法を指定します。
	 */
	@GeneratedValue(strategy = GenerationType.AUTO)
	/**
	 * フィールド変数で、エンティティのプライマリーキーとして使用されます。。
	 */
	private Long blogId;
	/**
	 * @NonNull Lombokのアノテーションで、nullを許容しないことを示します。
	 */
	@NonNull
	@Column(name = "blog_title")
	/**
	 * private String blogTitle; フィールド変数で、ブログのタイトルを表します。
	 */
	private String blogTitle;

	/**
	 * @DateTimeFormat(pattern = "yyyy-MM-dd") Spring
	 * Frameworkアノテーションで、このフィールドが日付/時間の形式を持つことを示します。
	 */
	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "register_date")
	/**
	 * フィールド変数で、登録日時を表します。 private LocalDateTime registerDate
	 */
	private LocalDate registerDate;

	@NonNull
	@Column(name = "category_name")
	/**
	 * フィールド変数で、カテゴリーを表します。 private String category
	 */
	private String category;
	
	@NonNull
	@Column(name = "blog_image")
	/**
	 * フィールド変数で、画像名を表します。 private String blogImage
	 */
	private String blogImage;
	
	@NonNull
	@Column(name = "article")
	/**
	 * フィールド変数で、ブログ詳細文を表します。 private String article
	 */
	private String article;
	@Column(name = "account_id")
	/**
	 * フィールド変数で、accountIdを表します。 private Long accountId
	 */
	private Long accountId;
	
	/**
	 * コンストラクターは、6つのパラメータを取ります。それぞれのパラメーターは以下の通りです。
	 * @NonNull String blogTitle: ブログ記事のタイトルを表す文字列
	 * @NonNull LocalDate registerDate: ブログ記事の登録日を表す日付
	 * @NonNull String blogImage: ブログ記事の画像を表す文字列
	 * @NonNull String blogDetail: ブログ記事の詳細な内容を表す文字列
	 * @NonNull String category: ブログ記事のカテゴリーを表す文字列
	 * Long userId: ブログ記事を投稿したユーザーのIDを表すLong型の数値。
	 * ただし、投稿者が不明な場合にはnullが渡されることがあります。
	 * コンストラクターは、パラメーターから取得した値を、クラスのフィールドに代入することで、
	 * 新しいブログ記事エンティティを作成します。具体的には、以下のフィールドが定義されています。
	 **/
	
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
