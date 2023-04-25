package fit.yujing.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fit.yujing.utils.PageUtil;
import fit.yujing.pojo.Order;
import fit.yujing.pojo.Result;
import fit.yujing.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Tiam
 * @date 2023/4/14 23:18
 * @description
 */
@RestController
@RequestMapping("/order")
@Api(value = "OrderController", tags = {"订单管理接口"})
@CrossOrigin
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/listOrders")
    @ApiOperation(value = "查询全部订单", notes = "这是一些备注详细说明")
    public Result<List<Order>> listOrders(){
        return Result.ok(orderService.listOrders());
    }

    @GetMapping("/pageOrders")
    @ApiOperation(value = "分页查询订单")
    public Result<PageUtil<Order>> pageOrders(PageUtil<Order> mypage){
        log.info("分页查询订单:[{}]", mypage);
        Page<Order> page = orderService.pageOrders(PageUtil.of(mypage));
        return Result.ok(PageUtil.of(page));
    }

    @GetMapping("/getOrderById/{id}")
    @ApiOperation(value = "根据id查询订单")
    public Result<Order> getOrderById(@PathVariable("id")Integer id){
        return Result.ok(orderService.getOrderById(id));
    }

    @GetMapping("/listOrdersByDoorId/{doorId}")
    @ApiOperation(value = "根据门店id查询订单")
    public Result<List<Order>> listOrdersByDoorId(@PathVariable("doorId")Integer doorId){
        return Result.ok(orderService.listOrdersByDoorId(doorId));
    }

    @GetMapping("/conditionQuery")
    @ApiOperation("条件查询订单")
    public Result<List<Order>> conditionQuery(@RequestParam Map<String, Object> params){
        log.info("条件查询订单:[{}]", params);
        // 反射校验是否存在键, problem: JavaBean中的属性名和数据库中的字段名不一致
//        for (String key : params.keySet()) {
//            try {
//                Order.class.getDeclaredField(key);
//            } catch (NoSuchFieldException e) {
//                // todo: 日志warning
//                params.remove(key);
//            }
//        }
        return Result.ok(orderService.conditionQuery(params));
    }

    @PostMapping("/insertOrder")
    @ApiOperation("添加订单")
    public Result insertOrder(@RequestBody Order order){
        log.info("添加订单:[{}]", order);
        return orderService.insertOrder(order);
    }

    @PutMapping("/updateOrder")
    @ApiOperation("修改订单")
    public Result updateOrder(@RequestBody Order order){
        log.info("修改订单:[{}]", order);
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/deleteOrderById/{id}")
    @ApiOperation("根据id删除订单")
    public Result deleteOrderById(@PathVariable("id") Integer id){
        return orderService.deleteOrderById(id);
    }

    @DeleteMapping("/deleteOrdersByDoorId/{doorId}")
    @ApiOperation( "根据门店id删除订单")
    public Result deleteOrdersByDoorId(@PathVariable("doorId") Integer doorId){
        return orderService.deleteOrdersByDoorId(doorId);
    }
}
