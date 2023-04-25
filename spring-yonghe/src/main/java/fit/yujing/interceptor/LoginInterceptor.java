package fit.yujing.interceptor;

import fit.yujing.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tiam
 * @date 2023/4/15 22:56
 * @description 登录权限验证
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        log.info("请求的URL:[{}]", url);

        // 查看请求中是否存在token，如果不存在直接跳转到登陆页面
//        String token = tokenUtil.getToken(request);
//        if (StringUtils.isEmpty(token)) {
//            response.sendRedirect("/login");
//            return false;
//        }
        return true;
    }
}
