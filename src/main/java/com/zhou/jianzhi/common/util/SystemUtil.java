package com.zhou.jianzhi.common.util;

import java.io.File;


public class SystemUtil {

	/**
	 * 判断是否是window系统
	 * @return
	 */
	public static boolean isWinSystem() {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().startsWith("win")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是linux系统
	 * @return
	 */
	public static boolean isLinuxSystem() {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().startsWith("linux")) {
			return true;
		}
		return false;
	}

	/**
	 * @param path 文件路径
	 * @return 将文件路径转化为绝对路径
	 */
	public static String pathToAbsolute(String path) {
		String transPath = path;
		if(isWinSystem()) {
			//获取盘符,如C:\
			File[] fdisk = File.listRoots();
			transPath = fdisk[0].getPath() + path;
		}else {
			if(isLinuxSystem()) {
				transPath = path.replace("\\", "/");
			}
		}
		return transPath;
	}

}
