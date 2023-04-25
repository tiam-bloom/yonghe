package fit.yujing.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * @author Tiam
 * @date 2023/4/15 12:03
 * @description 统一返回结果层
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Result<T> ok(T data) {
        return genResult(data);
    }

    public static <T> Result<T> ok() {
        return genResult(StateCodeEnum.SUCCESS);
    }

    public static <T> Result<T> ok(String message) {
        return genResult(StateCodeEnum.SUCCESS, message);
    }

    public static <T> Result<T> fail() {
        return genResult(StateCodeEnum.FAILURE);
    }

    public static <T> Result<T> fail(String message) {
        return genResult(StateCodeEnum.FAILURE, message);
    }

    private static <T> Result<T> genResult(StateCodeEnum stateCode) {
        Result<T> res = new Result<>();
        res.setCode(stateCode.getCode());
        res.setMessage(stateCode.getMessage());
        return res;
    }

    // 自定义返回信息
    private static <T> Result<T> genResult(StateCodeEnum stateCode, String message) {
        Result<T> res = new Result<>();
        res.setCode(stateCode.getCode());
        res.setMessage(message);
        return res;
    }

    private static <T> Result<T> genResult(T data) {
        Result<T> res = genResult(StateCodeEnum.SUCCESS);
        res.setData(data);
        return res;
    }
}
