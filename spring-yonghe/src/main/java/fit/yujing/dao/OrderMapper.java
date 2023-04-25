package fit.yujing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import fit.yujing.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 23:10
 * @description
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> listOrders();

    Order getOrderById(Integer id);


}
