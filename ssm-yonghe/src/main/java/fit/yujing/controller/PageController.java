package fit.yujing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Tiam
 * @date 2023/4/14 10:45
 * @description
 */
@Controller
public class PageController {

    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page) {
        return page;
    }
}
