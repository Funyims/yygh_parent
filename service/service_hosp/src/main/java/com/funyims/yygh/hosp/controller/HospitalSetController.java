package com.funyims.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.funyims.yygh.common.exception.YyghException;
import com.funyims.yygh.common.result.Result;
import com.funyims.yygh.common.utils.MD5;
import com.funyims.yygh.hosp.service.HospitalSetService;
import com.funyims.yygh.model.hosp.HospitalSet;
import com.funyims.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.required;

/**
 * @author Fun_yims
 * @date 2022/07/10 15:11
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    //    注入service
    @Autowired
    private HospitalSetService hospitalSetService;


//    http://localhost:8201/admin/hosp/hospitalSet/findAll


    //    1 、 查询医院的设置表里的所有信息@GetMapping("/findAll")
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        //调用sercice的方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    //    2 、 删除医院信息
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospSet(
            @ApiParam(name = "id", value = "医院ID", required = true)
            @PathVariable("id") Long id) {

        //调用sercice的方法
        boolean flag = hospitalSetService.removeById(id);
        return Result.ok(flag);
    }

//    3、条件查询医院设置信息带分页

    @ApiOperation(value = "条件查询医院设置信息带分页")
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable("current") long current,
                                  @PathVariable("limit") long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
//      创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current, limit);
//       构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospitalSetQueryVo.getHosname();//医院名称
        String hoscode = hospitalSetQueryVo.getHoscode();//医院偏好
//        判断是否为null
        if (StringUtils.isNotEmpty(hosname)) {
            wrapper.like("hosname", hosname);
        }
        if (StringUtils.isNotEmpty(hoscode)) {
            wrapper.eq("hoscode", hoscode);
        }
//        调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);
        return Result.ok(pageHospitalSet);
    }


//     4、添加医院设置

    /*
    {
  "apiUrl": "http://localhost:9999",
  "contactsName": "张三",
  "contactsPhone": "110119111",

  "hoscode": "1000_01",
  "hosname": "北京人民医院",
  "isDeleted": 0
}
     */
    @ApiOperation(value = "添加医院设置")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
//        设置状态1可用 0 不可用
        hospitalSet.setStatus(1);
//        签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
//       调用service
        boolean save = hospitalSetService.save(hospitalSet);
        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //    5、根据id获取医院设置
    @ApiOperation(value = "根据id获取医院设置")
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(
            @ApiParam(name = "id", value = "医院ID", required = true)
            @PathVariable Long id) {
//        try {
//
//            int i=1/0;
//        }catch (Exception e) {
//            throw new YyghException("失败",201);
//        }
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    //    6、修改医院设置
    /*
    {

  "hosname": "和谐12345",
  "id": 1
}
     */
    @ApiOperation(value = "修改医院设置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(
            @ApiParam(name = "hospitalSet", value = "医院实体", required = true)
            @RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //    7、批量删除医院设置
//    [1,2]
    @ApiOperation(value = "批量删除医院设置")
    @DeleteMapping("batchRemoveHospitalSet")
    public Result batchRemoveHospitalSet(
            @ApiParam(name = "idList", value = "批量ids", required = true)
            @RequestBody List<Long> idList) {
        boolean flag = hospitalSetService.removeByIds(idList);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
//    8、医院设置锁定和解锁
    @ApiOperation(value = "医院设置锁定和解锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(
            @ApiParam(name = "id", value = "医院id", required = true)
            @PathVariable Long id,
            @ApiParam(name = "status", value = "锁定状态", required = true)
            @PathVariable Integer status){
//        现根据id查询医院
        HospitalSet hospitalSet = hospitalSetService.getById(id);
//        设置状态
        hospitalSet.setStatus(status);
//        调用方法
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }


//    9、发送签名密钥
    @ApiOperation(value = "发送签名密钥")
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id){
//        现根据id查询医院
        HospitalSet hospitalSet = hospitalSetService.getById(id);
//        获取密钥和名称
        String signKey = hospitalSet.getSignKey();
        String hosname = hospitalSet.getHosname();
//        TODO发送短信
       return Result.ok();
    }


}
