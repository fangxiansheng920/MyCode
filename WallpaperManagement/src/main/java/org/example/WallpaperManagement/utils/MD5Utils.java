package org.example.WallpaperManagement.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
 
     /**
      * 获取MD5加密,加密之后不可逆
      * 
      * @param pwd
      *            需要加密的字符串
      * @return String字符串 加密后的字符串
      */
     public static String getPwd(String pwd) {
         try {
             // 创建加密对象
             MessageDigest digest = MessageDigest.getInstance("md5");
             byte[] bs = digest.digest(pwd.getBytes());
             String hexString = "";
             for (byte b : bs) {
                 int temp = b & 255;
                 if (temp < 16 && temp >= 0) {
                     hexString = hexString + "0" + Integer.toHexString(temp);
                 } else {
                     hexString = hexString + Integer.toHexString(temp);
                 }
             }
             return hexString;
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
         return "";
     }
 
     /**
      * @param args
      */
     public static void main(String[] args) {
         String pwd = MD5Utils.getPwd("adminHighcom");
         System.out.println(pwd);
     }
 
 }
