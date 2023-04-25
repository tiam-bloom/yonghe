package fit.yujing.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Tiam
 * @date 2023/4/14 22:58
 * @description
 */
@Data
@TableName("tb_door")
@ApiModel("门店信息")
public class Door {
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("唯一标识")
    private Integer id;
    @ApiModelProperty("门店名称")
    private String name;
    @ApiModelProperty("门店电话")
    private String tel;
    @ApiModelProperty("门店地址")
    private String addr;
}

