package springweb.aiGenerator.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    // 私有构造函数
    private ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // ==================== 泛型重构核心 ====================

    /**
     * 创建成功响应（不带数据）
     */
    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    /**
     * 创建成功响应（带数据）
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "操作成功", data);
    }

    /**
     * 创建成功响应（自定义消息和数据）
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    // ==================== 错误响应方法 ====================

    /**
     * 创建错误响应（带消息）
     */
    public static <T> ApiResponse<T> error(String message) {
        return error(message, null);
    }

    /**
     * 创建错误响应（带消息和数据）
     */
    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(false, message, data);
    }

    // ==================== Getters ====================

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    // ==================== Setters (按需) ====================

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}