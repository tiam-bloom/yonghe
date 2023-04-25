import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import fit.yujing.dao.OrderMapper;
import fit.yujing.pojo.Door;
import fit.yujing.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Tiam
 * @date 2023/4/23 11:30
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
// 告诉junit spring配置文件位置
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:springmvc-config.xml"})
public class MapperTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testSelectOrderById(){
        Order order = orderMapper.selectById(3);
        System.out.println(order);
    }

    @Test
    public void testSelectByPage() {
//        Page<Order> orderPage = orderMapper.selectPage(new Page<>(2,5), null);
        Page<Order> orderPage = orderMapper.selectPage(new Page<>(2,5), null);
        System.out.println(orderPage);
        orderPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void testSelectByPage1() {
//        MyPage<Order> mypage = new MyPage<>(2, 5);
//        Page<Order> page = orderMapper.selectPage(MyPage.of(2,5), null);
//        MyPage<Order> orderMyPage = new MyPage<>(page);
//        System.out.println(orderMyPage);
    }

    @Test
    public void testJoinSelect(){
/*        MPJQueryWrapper<Order> wrapper2 = new MPJQueryWrapper<>();
        Map<String,Object> params = new HashMap<>();
        params.put("order_no","p003");
        wrapper2.selectAll(Order.class).allEq(params).leftJoin("tb_door on door.id = order.door_id");
        List<Order> orders = orderMapper.selectJoinList(Order.class, wrapper2);
        orders.forEach(System.out::println);*/
    }

    @Test
    public void testJoinSelect1(){
        MPJLambdaWrapper<Order> wrapper = new MPJLambdaWrapper<Order>()
                .selectAll(Order.class)//查询user表全部字段
                .select(Door::getName)//查询user_address tel 字段
                .leftJoin(Order.class,Order::getDoorId,Door::getId)
                .eq(Order::getOrderNo,"p003");
//        orderMapper.selectJoinList(Order.class, wrapper).forEach(System.out::println);
    }
}
