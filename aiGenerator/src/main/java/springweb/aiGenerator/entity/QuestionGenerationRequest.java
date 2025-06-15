package springweb.aiGenerator.entity;

import lombok.Data;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;

@Data
public class QuestionGenerationRequest {
    private QuestionType type;
    private int count = 5;
    private DifficultyLevel difficulty;
    private String language;
}