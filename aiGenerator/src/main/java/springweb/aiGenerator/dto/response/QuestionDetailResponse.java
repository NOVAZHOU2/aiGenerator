package springweb.aiGenerator.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import springweb.aiGenerator.entity.Question;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
public class QuestionDetailResponse {
    private Long id;
    private QuestionType type;
    private String content;
    private String options; // 选项列表
    private String answer;
    private DifficultyLevel difficulty;
    private String language;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    private Long userId;

    public QuestionDetailResponse(Question question) {
        this.id = question.getId();
        this.type = question.getType();
        this.content = question.getContent();
        this.options = question.getOptions();
        this.answer = question.getAnswer();
        this.difficulty = question.getDifficulty();
        this.language = question.getLanguage();
        this.createdAt = question.getCreatedAt();
        this.userId = question.getUserId();
    }
    public QuestionDetailResponse() {}
    private List<String> convertOptions(String optionsJson) {
        if (optionsJson == null || optionsJson.isEmpty()) return null;
        try {
            return new ObjectMapper().readValue(optionsJson, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            return Collections.emptyList();
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}