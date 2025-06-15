package springweb.aiGenerator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;
import springweb.aiGenerator.entity.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>,
        JpaSpecificationExecutor<Question>, // 添加此接口
        PagingAndSortingRepository<Question, Long> {

    // 根据题目类型查找
    Page<Question> findByType(QuestionType type, Pageable pageable);

    // 根据难度查找
    Page<Question> findByDifficulty(DifficultyLevel difficulty, Pageable pageable);

    // 根据编程语言查找
    Page<Question> findByLanguage(String language, Pageable pageable);

    // 组合查询：类型和难度
    Page<Question> findByTypeAndDifficulty(QuestionType type, DifficultyLevel difficulty, Pageable pageable);

    // 组合查询：类型、难度和语言
    Page<Question> findByTypeAndDifficultyAndLanguage(QuestionType type, DifficultyLevel difficulty, String language, Pageable pageable);

    // 全文搜索（忽略大小写）
    @Query("SELECT q FROM Question q WHERE LOWER(q.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Question> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // 动态条件查询
    @Query("SELECT q FROM Question q WHERE " +
            "(:type IS NULL OR q.type = :type) AND " +
            "(:difficulty IS NULL OR q.difficulty = :difficulty) AND " +
            "(:language IS NULL OR q.language = :language) AND " +
            "(:keyword IS NULL OR LOWER(q.content) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Question> findByCriteria(
            @Param("type") QuestionType type,
            @Param("difficulty") DifficultyLevel difficulty,
            @Param("language") String language,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    // 批量删除
    @Modifying
    @Query("DELETE FROM Question q WHERE q.id IN :ids")
    void deleteByIds(@Param("ids") List<Long> ids);


    // 查找所有题目
    @Override
    List<Question> findAllById(Iterable<Long> ids);

    // 统计总数
    @Override
    long count();

    // 按问题类型分组统计
    @Query("SELECT q.type, COUNT(q) FROM Question q GROUP BY q.type")
    List<Object[]> countGroupedByType();

    // 按难度级别分组统计
    @Query("SELECT q.difficulty, COUNT(q) FROM Question q GROUP BY q.difficulty")
    List<Object[]> countGroupedByDifficulty();

    // 按编程语言分组统计
    @Query("SELECT q.language, COUNT(q) FROM Question q " +
            "WHERE q.language IS NOT NULL " + // 移除 deleted 条件
            "GROUP BY q.language")
    List<Object[]> countGroupedByLanguage();
}