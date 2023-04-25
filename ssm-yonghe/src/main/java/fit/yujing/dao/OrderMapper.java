package fit.yujing.dao;

import fit.yujing.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 13:00
 * @description
 */
@Mapper
public interface OrderMapper {

    List<Order> listOrders();

    int addOrder(Order order);

    Order getOrderById(Integer id);

    int updateOrder(Order order);

    int deleteOrderById(Integer id);
}
