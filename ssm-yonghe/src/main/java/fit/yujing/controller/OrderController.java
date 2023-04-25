package fit.yujing.controller;

import fit.yujing.pojo.Door;
import fit.yujing.pojo.Order;
import fit.yujing.service.DoorService;
import fit.yujing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 12:54
 * @description
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderServiceImpl;

    @Autowired
    private DoorService doorServiceImpl;

    @GetMapping("/order_list")
    public String listOrders(Model model) {
        List<Order> orderList = orderServiceImpl.listOrders();
        model.addAttribute("orderList", orderList);

        List<Door> doors = doorServiceImpl.listDoors();
        model.addAttribute("doorList", doors);
        return "order_list";
    }

    @GetMapping("/findAllDoorToOrderAdd")
    public String findAllDoor(Model model) {
        List<Door> doors = doorServiceImpl.listDoors();
        model.addAttribute("doorList", doors);
        return "order_add";
    }

    @PostMapping("/orderAdd")
    public String addOrder(Order order) {
        int i = orderServiceImpl.addOrder(order);
        return "redirect:order_list";
    }

    @GetMapping("/orderInfo")
    public String getOrderById(Integer id,Model model){
        Order order = orderServiceImpl.getOrderById(id);
        model.addAttribute("order",order);
        List<Door> doors = doorServiceImpl.listDoors();
        model.addAttribute("doorList",doors);
        return "order_update";
    }

    @PostMapping("/orderUpdate")
    public String updateOrder(Order order){
        int i = orderServiceImpl.updateOrder(order);
        return "redirect:order_list";
    }

    @RequestMapping("/orderDelete")
    public String deleteOrderById(Integer id){
        int i = orderServiceImpl.deleteOrderById(id);
        return "redirect:order_list";
    }
}
