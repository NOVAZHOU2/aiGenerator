package springweb.aiGenerator.dto.response;

import lombok.Data;

@Data
public class OperationResultResponse {
    private boolean success;
    private String message;
    private Object data;

    public OperationResultResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public OperationResultResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static OperationResultResponse success(String message) {
        return new OperationResultResponse(true, message);
    }

    public static OperationResultResponse success(String message, Object data) {
        return new OperationResultResponse(true, message, data);
    }

    public static OperationResultResponse error(String message) {
        return new OperationResultResponse(false, message);
    }
}