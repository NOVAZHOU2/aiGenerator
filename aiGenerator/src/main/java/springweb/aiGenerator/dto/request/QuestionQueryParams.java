package springweb.aiGenerator.dto.request;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;

@Data
public class QuestionQueryParams {
    private QuestionType type;
    private DifficultyLevel difficulty;
    private String language;
    private String keyword;
    private Long userId;
    private int page = 0;
    private int size = 10;
    private String sortBy = "createdAt";
    private Sort.Direction direction = Sort.Direction.DESC;

    // 转换为Spring Data的Pageable对象
    public Pageable toPageable() {
        if (sortBy != null && !sortBy.isEmpty()) {
            return PageRequest.of(page, size, direction, sortBy);
        } else {
            return PageRequest.of(page, size);
        }
    }

    // 获取排序方向字符串表示
    public String getDirectionString() {
        return direction.name();
    }

    // 检查是否为空查询（无任何查询条件）
    public boolean isEmptyQuery() {
        return type == null &&
                difficulty == null &&
                (language == null || language.isEmpty()) &&
                (keyword == null || keyword.isEmpty());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }
}