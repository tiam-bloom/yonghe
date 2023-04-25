package fit.yujing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fit.yujing.dao.DoorMapper;
import fit.yujing.dao.OrderMapper;
import fit.yujing.pojo.Order;
import fit.yujing.pojo.Result;
import fit.yujing.service.OrderService;
import fit.yujing.utils.MPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Tiam
 * @date 2023/4/14 23:16
 * @description
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DoorMapper doorMapper;

    @Override
    public List<Order> listOrders() {
        return orderMapper.listOrders();
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public Result insertOrder(Order order) {
        return orderMapper.insert(order) == 0 ? Result.fail("添加失败") : Result.ok("添加成功");
    }

    @Override
    public Result updateOrder(Order order) {
        return orderMapper.updateById(order) == 0 ? Result.fail("修改失败") : Result.ok("修改成功");
    }

    @Override
    public Result deleteOrderById(Integer id) {
        return orderMapper.deleteById(id) == 0 ? Result.fail("删除失败") : Result.ok("删除成功");
    }

    @Override
    public List<Order> conditionQuery(Map<String, Object> params) {
        QueryWrapper<Order> wrapper = new QueryWrapper<Order>().allEq(params);
        QueryWrapper<Order> wrapper1 = MPUtil.allLike(params);
        List<Order> orders = orderMapper.selectList(wrapper);
        // todo: 优化
        for (Order order : orders) {
            order.setDoor(doorMapper.selectById(order.getDoorId()));
        }
        return orders;
    }

    @Override
    public List<Order> listOrdersByDoorId(Integer doorId) {
        return orderMapper.selectList(new QueryWrapper<Order>().eq("door_id", doorId));
    }

    @Override
    public Result deleteOrdersByDoorId(Integer doorId) {
        return orderMapper.delete(new QueryWrapper<Order>().eq("door_id",doorId)) == 0 ? Result.fail("删除失败") : Result.ok("删除成功");
    }

    @Override
    public Page<Order> pageOrders(Page<Order> page) {
        Page<Order> orderPage = orderMapper.selectPage(page, null);
        List<Order> orders = orderPage.getRecords();
        for (Order order : orders) {
            order.setDoor(doorMapper.selectById(order.getDoorId()));
        }
        return orderPage;
    }
}
