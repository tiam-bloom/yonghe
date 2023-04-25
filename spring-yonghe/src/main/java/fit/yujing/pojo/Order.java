package fit.yujing.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
//import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Tiam
 * @date 2023/4/14 22:58
 * @description
 */
@Data
@TableName("tb_order")
@ApiModel("订单信息")
public class Order {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("唯一标识")
    private Integer id;
    @ApiModelProperty("门店id")
    private Integer doorId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("订单类型")
    private String orderType;
    @ApiModelProperty("用餐人数")
    private Integer pnum;
    @ApiModelProperty("收银员")
    private String cashier;

    @ApiModelProperty(value="下单时间", example = "2020-01-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh", timezone = "GMT+8")
    private Date orderTime;

    @ApiModelProperty(value = "支付时间", example = "2020-01-01 00:00:00")
    // 后端返回给前端的时间格式
    @JSONField(format="yyyy-MM-dd HH:mm:ss")  // fastjson, 默认也是这个格式
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh", timezone = "GMT+8")  // jackson, 默认是long类型的时间戳
    // 前端传递给后端的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    @ApiModelProperty("支付方式")
    private String payType;
    @ApiModelProperty("支付金额")
    private Double price;
    // 所属门店, 一对一, 非数据库字段
    @TableField(exist = false)
    private Door door;
}
