package fit.yujing.controller;

import fit.yujing.pojo.Door;
import fit.yujing.service.DoorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 11:16
 * @description
 */
@Controller
public class DoorController {

    @Resource
    private DoorService doorServiceImpl;

    @GetMapping("/door_list")
    public String listDoors(Model model) {
        List<Door> doors =  doorServiceImpl.listDoors();
        model.addAttribute("list",doors);
        return "door_list";
    }

    @PostMapping("/doorAdd")
    public String addDoor(Door door) {
        int i = doorServiceImpl.addDoor(door);
        return "redirect:door_list";
    }

    @RequestMapping("/doorDelete")
    public String removeDoor(Integer id) {
        int i = doorServiceImpl.removeDoor(id);
        return "redirect:door_list";
    }

    @GetMapping("/doorInfo")
    public String getDoorById(Integer id,Model model) {
        Door door = doorServiceImpl.getDoorById(id);
        model.addAttribute("door", door);
        return "door_update";
    }

    @PostMapping("/doorUpdate")
    public String updateDoor(Door door) {
        int i = doorServiceImpl.updateDoor(door);
        return "redirect:door_list";
    }

}
