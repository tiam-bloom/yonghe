package fit.yujing.pojo;

import lombok.Data;

/**
 * @author Tiam
 * @date 2023/4/24 23:36
 * @description
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
}
