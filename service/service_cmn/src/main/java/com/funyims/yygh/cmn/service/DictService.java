package com.funyims.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.funyims.yygh.model.cmn.Dict;
import com.funyims.yygh.model.hosp.HospitalSet;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Fun_yims
 * @date 2022/07/10 15:06
 */
public interface DictService extends IService<Dict> {
//        根据数据ID查询子数据列表
    List<Dict> findChildData(Long id);
    //    导出数据字典接口
    void exportDictData(HttpServletResponse response);
    //    导入数据字典接口
    void importDictData(MultipartFile file);
}
