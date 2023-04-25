package fit.yujing.pojo;

/**
 * @author Tiam
 * @date 2023/4/15 12:21
 * @description
 */
public enum StateCodeEnum {
    SUCCESS(200, "成功"),
    FAILURE(400, "失败");

    private int code;
    private String message;

    StateCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
