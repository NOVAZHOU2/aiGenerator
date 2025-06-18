package springweb.aiGenerator.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import springweb.aiGenerator.entity.BaseEntity;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "questions")
@Data
@EqualsAndHashCode(callSuper = true)

public class Question extends BaseEntity {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT")
    private String options;

    @Column(length = 50)
    private String answer; // 选择题答案，如 "A" 或 "AB"

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty;
    private LocalDateTime createdAt;
    @Column(length = 50)
    private String language; // 编程语言
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Transient
    private String userName; // 用户名，非持久化字段

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", options='" + options + '\'' +
                ", answer='" + answer + '\'' +
                ", difficulty=" + difficulty +
                ", createdAt=" + createdAt +
                ", language='" + language + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}