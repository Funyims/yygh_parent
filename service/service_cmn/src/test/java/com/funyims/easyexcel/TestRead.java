package com.funyims.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * @author Fun_yims
 * @date 2022/07/12 21:06
 */
public class TestRead {
    public static void main(String[] args) {
//        读取文件路径名称
        String fileName = "/Volumes/BOOTCAMP/Code07/excel//01.xlsx";
//        调用方法读取操作
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
