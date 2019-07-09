package com.zxf.massagecenter.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zxf.massagecenter.common.MyResponse;
import com.zxf.massagecenter.modle.po.McUser;
import com.zxf.massagecenter.service.IMcUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxiongfeng
 * @since 2019-07-08
 */
@RestController
@RequestMapping("/mcUser")
@Api(description = "McUserController")
public class McUserController {

    @Autowired
    private IMcUserService iMcUserService;

    @ApiOperation(value = "查询列表",notes = "查询列表")
    @GetMapping("/getList")
    public MyResponse getList(){
       // McUser mcUser = iMcUserService.selectById("1");
        Wrapper<McUser> objectEntityWrapper = new EntityWrapper<>();
        objectEntityWrapper.eq("id","1");
        List<McUser> mcUserList = iMcUserService.selectList(objectEntityWrapper);
        return MyResponse.ok(mcUserList);
    }

}

