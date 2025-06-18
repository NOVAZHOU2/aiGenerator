package springweb.aiGenerator.dto.request;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;
import org.springframework.data.domain.Pageable;

@Data
public class QuestionSearchRequest {
    private QuestionType type;
    private DifficultyLevel difficulty;
    private String language;
    private String keyword;
    private Long userId;

    // 分页信息
    private int page = 0;
    private int size = 10;

    // 排序字段
    private String sortBy = "createdAt";
    private String direction = "DESC";

    public Pageable toPageable() {
        return PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
    }
}