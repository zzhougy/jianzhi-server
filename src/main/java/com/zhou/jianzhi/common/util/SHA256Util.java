package com.zhou.jianzhi.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * sha-256加密
 */
public class SHA256Util {
    /** 私有构造器 **/
    private SHA256Util(){};
    /** 加密算法 **/
    public final static String HASH_ALGORITHM_NAME="SHA-256";
    /** 循环次数 **/
    public final static int HASH_ITERATIONS=15;
    /** 执行加密 **/
    public static String sha256(String password,String salt){
        return new SimpleHash(HASH_ALGORITHM_NAME,password,salt,HASH_ITERATIONS).toString();
    }
}
