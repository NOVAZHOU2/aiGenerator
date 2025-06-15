package springweb.aiGenerator.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String path;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private Object errors; // 用于字段级错误详情

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus status, String message) {
        this();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message, String path) {
        this(status, message);
        this.path = path;
    }

    public ErrorResponse(HttpStatus status, String message, String path, Object errors) {
        this(status, message, path);
        this.errors = errors;
    }

    public ErrorResponse(int value, String notFound, String message, String description, LocalDateTime now) {
    }

    // 静态工厂方法
    public static ErrorResponse of(HttpStatus status, String message) {
        return new ErrorResponse(status, message);
    }

    public static ErrorResponse of(HttpStatus status, String message, String path) {
        return new ErrorResponse(status, message, path);
    }

    public static ErrorResponse withFieldErrors(HttpStatus status, String message, String path, Object errors) {
        return new ErrorResponse(status, message, path, errors);
    }
}