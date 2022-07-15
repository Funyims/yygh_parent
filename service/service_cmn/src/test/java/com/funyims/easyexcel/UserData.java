package com.funyims.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fun_yims
 * @date 2022/07/12 20:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    @ExcelProperty(value = "用户编号",index = 0)
    private int uid;

    @ExcelProperty(value ="用户名称",index = 1)
    private String username;

}
