package fit.yujing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fit.yujing.dao.DoorMapper;
import fit.yujing.pojo.Door;
import fit.yujing.pojo.Result;
import fit.yujing.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 23:17
 * @description
 */
@Service
public class DoorServiceImpl implements DoorService {
    @Autowired
    private DoorMapper doorMapper;

    @Override
    public List<Door> listDoors() {
        return doorMapper.selectList(null);
    }

    @Override
    public Door getDoorById(Integer id) {
        return doorMapper.selectById(id);
    }

    @Override
    public Result insertDoor(Door door) {
        return doorMapper.insert(door) == 0 ? Result.fail() : Result.ok();
    }

    @Override
    public Result updateDoor(Door door) {
        return doorMapper.updateById(door) == 0 ? Result.fail() : Result.ok();
    }

    @Override
    public Result deleteDoorById(Integer id) {
        return doorMapper.deleteById(id) == 0 ? Result.fail() : Result.ok();
    }

    @Override
    public Page<Door> pageDoors(Page<Door> page) {
        return doorMapper.selectPage(page, null);
    }
}
