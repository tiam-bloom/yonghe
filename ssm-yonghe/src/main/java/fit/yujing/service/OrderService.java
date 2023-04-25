package fit.yujing.service;

import fit.yujing.pojo.Order;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 12:58
 * @description
 */
public interface OrderService {
    List<Order> listOrders();

    int addOrder(Order order);

    Order getOrderById(Integer id);

    int updateOrder(Order order);

    int deleteOrderById(Integer id);
}
