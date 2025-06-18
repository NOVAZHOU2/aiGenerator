package springweb.aiGenerator.dto.request;

import lombok.Data;
import javax.validation.constraints.NotNull;
import springweb.aiGenerator.entity.DifficultyLevel;
import springweb.aiGenerator.entity.QuestionType;
import javax.validation.constraints.NotBlank;
import java.util.Map;


@Data
public class QuestionCreateUpdateDTO {
    @NotNull(message = "题目类型不能为空")
    private QuestionType type;

    @NotBlank(message = "题目内容不能为空")
    private String content;

    private String  options; // JSON格式的选项字符串

    private String answer; // 选择题答案

    @NotNull(message = "题目难度不能为空")
    private DifficultyLevel difficulty;

    private String language; // 编程语言

    @NotNull(message = "题目创建者不能为空")
    private Long userId;

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
}