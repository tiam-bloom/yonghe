package fit.yujing.dao;

import fit.yujing.pojo.Door;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 11:16
 * @description
 */
@Mapper
public interface DoorMapper {

    List<Door> listDoors();

    int addDoor(Door door);

    int removeDoor(Integer id);

    Door getDoorById(Integer id);

    int updateDoor(Door door);
}
