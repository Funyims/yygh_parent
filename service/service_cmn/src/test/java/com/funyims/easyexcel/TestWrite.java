package com.funyims.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;

/**
 * @author Fun_yims
 * @date 2022/07/12 20:51
 */
public class TestWrite {
    public static void main(String[] args) {
//        构建一个数据list集合
        ArrayList<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData userData = new UserData(i, "lucy" + i);
            list.add(userData);
        }

//      设置Excel 文件路径和文件名称
        String fileName = "/Volumes/BOOTCAMP/Code07/excel//01.xlsx";
//        调用方法实现写操作
        EasyExcel.write(fileName,UserData.class).sheet("用户信息")
                .doWrite(list);
    }

}
