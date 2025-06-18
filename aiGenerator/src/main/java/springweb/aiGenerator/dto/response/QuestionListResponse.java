package springweb.aiGenerator.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    private String language;
    private Long userId;
    private String userName;

    public QuestionListResponse(Question question) {
        this.id = question.getId();
        this.type = question.getType();
        this.content = question.getContent();
        this.difficulty = question.getDifficulty();
        this.createdAt = question.getCreatedAt();
        this.options = question.getOptions();
        this.language = question.getLanguage();
        this.userId = question.getUserId();
        this.userName = question.getUserName();
    }

    public QuestionListResponse(Question question, String userName) {
        this.id = question.getId();
        this.content = question.getContent();
        this.type = question.getType();
        this.difficulty = question.getDifficulty();
        this.language = question.getLanguage();
        this.userId = question.getUserId();
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}