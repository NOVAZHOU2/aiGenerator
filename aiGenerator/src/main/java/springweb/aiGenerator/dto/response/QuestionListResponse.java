package springweb.aiGenerator.dto.response;

import lombok.Data;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.Question;
import springweb.aiGenerator.entity.QuestionType;

import java.time.LocalDateTime;

@Data
public class QuestionListResponse {
    private Long id;
    private QuestionType type;
    private String content;
    private DifficultyLevel difficulty;
    private String options; // 添加options字段
    private LocalDateTime createdAt;
    private String language;

    public QuestionListResponse(Question question) {
        this.id = question.getId();
        this.type = question.getType();
        this.content = question.getContent();
        this.difficulty = question.getDifficulty();
        this.createdAt = question.getCreatedAt();
        this.options = question.getOptions();
        this.language = question.getLanguage();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}