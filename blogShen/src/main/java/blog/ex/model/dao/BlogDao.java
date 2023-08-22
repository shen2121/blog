package blog.ex.model.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.ex.model.entity.BlogEntity;
import jakarta.transaction.Transactional;

public interface BlogDao extends JpaRepository<BlogEntity, Long> {
	// 指定されたaccountIdに基づいてデータベース内のBlogEntityを検索します
	List<BlogEntity> findByAccountId(Long accountId);

	// BlogEntityのオブジェクトを引数として受け取ってDBに保存します。
	BlogEntity save(BlogEntity blogEntity);

	// blogTitleとregisterDateを使用して、BlogEntityのなかから一致するものを検索して返します
	BlogEntity findByBlogTitleAndRegisterDate(String blogTitle, LocalDate registerDate);

	// 指定されたblogIdに基づいて、データベース内のBlogEntityを検索して返します
	BlogEntity findByBlogId(Long blogId);

	// 指定されたaccountIdに基づいてデータベース内のBlogEntityの内容を削除します
	@Transactional
	List<BlogEntity> deleteByBlogId(Long blogId);
}
