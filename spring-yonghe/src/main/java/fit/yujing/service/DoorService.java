package fit.yujing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fit.yujing.pojo.Door;
import fit.yujing.pojo.Result;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 23:16
 * @description
 */
public interface DoorService {
    List<Door> listDoors();

    Door getDoorById(Integer id);

    Result insertDoor(Door door);

    Result updateDoor(Door door);

    Result deleteDoorById(Integer id);

    Page<Door> pageDoors(Page<Door> page);
}
