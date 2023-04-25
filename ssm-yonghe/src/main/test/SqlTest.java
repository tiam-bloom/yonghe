import fit.yujing.dao.DoorMapper;
import fit.yujing.pojo.Door;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 14:40
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件位置
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:springmvc-config.xml"})
public class SqlTest {
    @Autowired
    private DoorMapper doorMapper;

    @Test
    public void testUpdateDoor() {
        Door door = new Door();
        door.setId(6);
        door.setName("雷军");
        door.setTel("666");
        door.setAddr("成都");
        int i = doorMapper.updateDoor(door);
        System.out.println(i);
    }

    @Test
    public void testSelectAllDoors() {
        List<Door> doors = doorMapper.listDoors();
        doors.forEach(System.out::println);
    }

    @Test
    public void testInsertDoor() {
        Door door = new Door();
        door.setName("彭志辉");
        door.setTel("123");
        door.setAddr("杭州");
        int i = doorMapper.addDoor(door);
        System.out.println(i);
    }
}
