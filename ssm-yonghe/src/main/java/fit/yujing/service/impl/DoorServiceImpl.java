package fit.yujing.service.impl;

import fit.yujing.dao.DoorMapper;
import fit.yujing.pojo.Door;
import fit.yujing.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 11:16
 * @description
 */
@Service
public class DoorServiceImpl implements DoorService {

    @Autowired
    private DoorMapper doorMapper;

    public List<Door> listDoors() {
        return doorMapper.listDoors();
    }

    public int addDoor(Door door) {
        return doorMapper.addDoor(door);
    }

    @Override
    public int removeDoor(Integer id) {
        return doorMapper.removeDoor(id);
    }

    @Override
    public Door getDoorById(Integer id) {
        return doorMapper.getDoorById(id);
    }

    @Override
    public int updateDoor(Door door) {
        return doorMapper.updateDoor(door);
    }
}
