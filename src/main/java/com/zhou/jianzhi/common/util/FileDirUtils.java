package com.zhou.jianzhi.common.util;

import java.io.File;


public class FileDirUtils {
    //存放签名图片的一级目录
    public static final String UPLOAD_FILE_DIR = SystemUtil.pathToAbsolute("/picOfMZSC");


    /**
     * 上传图片存放的文件夹的创建
     * @param path 经过环境判断的"根目录/picOfMZSC"路径
     * @param secondPath 二级目录,picOfMZSC下的文件夹
     * @return
     */
    public static String createDir(String path,String secondPath,String datePath) {
        //根目录/picOfMZSC
        File fileDir = new File(path);
        //判断"根目录/picOfMZSC"是否存在
        if (!fileDir.exists()) {
            //不存在就创建
            fileDir.mkdirs();
        }
        //二级目录是否存在
        File secondDir = new File(path+"/"+secondPath);
        if (!secondDir.exists()) {
            //不存在就创建
            secondDir.mkdir();
        }
        //创建文件三级目录
        File file = new File(path + "/" + secondPath + "/" + datePath);
        boolean result = file.mkdir();
        return file.getPath();
    }

}
