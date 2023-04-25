package fit.yujing.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Tiam
 * @date 2023/4/14 10:32
 * @description
 */
@Data
public class Order {
    private Integer id;
    private Integer doorId;
    // 订单编号
    private String orderNo;
    // 订单类型
    private String orderType;
    // 用餐人数
    private Integer pnum;
    // 收银员
    private String cashier;
    // 下单时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    // 结账时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    private String payType;
    private Double price;
}
