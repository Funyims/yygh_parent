package com.funyims.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.funyims.yygh.cmn.mapper.DictMapper;
import com.funyims.yygh.model.cmn.Dict;
import com.funyims.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

/**
 * @author Fun_yims
 * @date 2022/07/12 22:14
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {
//    一行一行读取
    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext context) {
//        调用方法添加到数据库
//        vo-->dict
        Dict dict =new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
