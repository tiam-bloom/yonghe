package fit.yujing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fit.yujing.pojo.Order;
import fit.yujing.pojo.Result;

import java.util.List;
import java.util.Map;

/**
 * @author Tiam
 * @date 2023/4/14 23:16
 * @description
 */
public interface OrderService {
    List<Order> listOrders();

    Order getOrderById(Integer id);

    Result insertOrder(Order order);

    Result updateOrder(Order order);

    Result deleteOrderById(Integer id);

    List<Order> conditionQuery(Map<String, Object> params);

    List<Order> listOrdersByDoorId(Integer doorId);

    Result deleteOrdersByDoorId(Integer doorId);

    Page<Order> pageOrders(Page<Order> page);
}
