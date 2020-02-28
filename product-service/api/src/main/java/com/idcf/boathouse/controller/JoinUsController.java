package com.idcf.boathouse.controller;

import com.idcf.boathouse.mapper.JoinUsMapper;
import com.idcf.boathouse.models.JoinUs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jaylee
 */
@RestController
@Api(tags = "加盟接口")
@RequestMapping("/join")
public class JoinUsController {

    @Resource
    private JoinUsMapper joinUsMapper;

    @RequestMapping(value = "save", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("提交加盟信息")
    public int save(@RequestBody JoinUs joinUs) {
        return joinUsMapper.insert(joinUs);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("获取已申请加盟列表")
    public Object list() {
        return joinUsMapper.selectList(null);
    }
}
