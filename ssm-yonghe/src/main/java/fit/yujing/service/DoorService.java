package fit.yujing.service;

import fit.yujing.pojo.Door;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 11:15
 * @description
 */
public interface DoorService {
    List<Door> listDoors();

    int addDoor(Door door);

    int removeDoor(Integer id);

    Door getDoorById(Integer id);

    int updateDoor(Door door);
}
