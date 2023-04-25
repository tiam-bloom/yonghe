package fit.yujing.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

/**
 * @author Tiam
 * @date 2023/4/20 18:38
 * @description
 */
public class MPUtil {
    public static <T> QueryWrapper<T> allLike(Map<String, Object> params){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        for (String key : params.keySet()) {
            if (params.get(key) == null || params.get(key).equals("")) continue;
            wrapper.like(key,params.get(key));
        }
        return wrapper;
    }

}
