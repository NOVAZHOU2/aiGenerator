package springweb.aiGenerator.dto.response;

import lombok.Data;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;

import java.util.Map;

@Data
public class QuestionStatisticsResponse {
    private long totalQuestions;
    private Map<QuestionType, Long> countByType;
    private Map<DifficultyLevel, Long> countByDifficulty;
    private Map<String, Long> countByLanguage;
    private Map<String, Long> countByTypeAndDifficulty;

    public long getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(long totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Map<QuestionType, Long> getCountByType() {
        return countByType;
    }

    public void setCountByType(Map<QuestionType, Long> countByType) {
        this.countByType = countByType;
    }

    public Map<DifficultyLevel, Long> getCountByDifficulty() {
        return countByDifficulty;
    }

    public void setCountByDifficulty(Map<DifficultyLevel, Long> countByDifficulty) {
        this.countByDifficulty = countByDifficulty;
    }

    public Map<String, Long> getCountByLanguage() {
        return countByLanguage;
    }

    public void setCountByLanguage(Map<String, Long> countByLanguage) {
        this.countByLanguage = countByLanguage;
    }

    public Map<String, Long> getCountByTypeAndDifficulty() {
        return countByTypeAndDifficulty;
    }

    public void setCountByTypeAndDifficulty(Map<String, Long> countByTypeAndDifficulty) {
        this.countByTypeAndDifficulty = countByTypeAndDifficulty;
    }
}