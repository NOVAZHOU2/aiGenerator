package springweb.aiGenerator.entity;

import lombok.Data;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;

@Data
public class QuestionQueryParams {
    private QuestionType type;
    private DifficultyLevel difficulty;
    private String language;
    private String keyword;
    private int page = 0;
    private int size = 10;
}