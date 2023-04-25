package fit.yujing.service.impl;

import fit.yujing.dao.OrderMapper;
import fit.yujing.pojo.Order;
import fit.yujing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 12:59
 * @description
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderMapper orderMapper;
    public List<Order> listOrders() {
        return orderMapper.listOrders();
    }

    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public int deleteOrderById(Integer id) {
        return orderMapper.deleteOrderById(id);
    }
}
