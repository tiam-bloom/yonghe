package fit.yujing.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fit.yujing.pojo.Door;
import fit.yujing.utils.PageUtil;
import fit.yujing.pojo.Result;
import fit.yujing.service.DoorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/14 23:18
 * @description
 */
@RestController
@RequestMapping("/door")
@Api(value = "DoorController", tags = {"门店管理接口"})
@CrossOrigin(origins="*",maxAge=3600)
@Slf4j
public class DoorController {
    @Autowired
    private DoorService doorService;

    @GetMapping("/listDoors")
    @ApiOperation("查询全部门店")
    public Result<List<Door>> listDoors(HttpServletRequest request) {
        return Result.ok(doorService.listDoors());
    }

    @GetMapping("/pageDoors")
    @ApiOperation("分页查询门店")
    public Result<PageUtil<Door>> pageDoors(PageUtil<Door> mypage) {
        log.info("分页查询门店:[{}]", mypage);
        Page<Door> page = doorService.pageDoors(PageUtil.of(mypage));
        return Result.ok(PageUtil.of(page));
    }

    @GetMapping("/getDoorById/{id}")
    @ApiOperation("根据ID查询门店信息")
    public Result<Door> getDoorById(@PathVariable("id") Integer id) {
        return Result.ok(doorService.getDoorById(id));
    }

    @PostMapping("/insertDoor")
    @ApiOperation("添加门店")
    @ApiResponses({@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "失败")})
    public Result insertDoor(@RequestBody Door door) {
        log.info("添加门店:[{}]", door);
        return doorService.insertDoor(door);
    }

    @PutMapping("/updateDoor")
    @ApiOperation("修改门店")
    public Result updateDoor(@RequestBody Door door) {
        log.info("修改门店:[{}]", door);
        return doorService.updateDoor(door);
    }

    @DeleteMapping("/deleteDoorById/{id}")
    @ApiOperation("根据ID删除门店")
    public Result deleteDoorById(@PathVariable("id") Integer id) {
        return doorService.deleteDoorById(id);
    }
}
