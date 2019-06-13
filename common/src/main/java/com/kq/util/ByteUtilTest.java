package com.kq.util;

/**
 * ByteUtilTest
 *
 * @author kq
 * @date 2019-06-13
 */
public class ByteUtilTest {

    public static void main(String[] args) {
        // 11011010(da)     10111011(bb)
        byte[] bs = ByteUtil.short2bytes((short)0xdabb);

        for(byte b : bs){
            System.out.println("b="+b);
        }
        System.out.println(ByteUtil.bytesToHexString(bs));


        short magic = -9541;

        byte[] bs1 = ByteUtil.short2bytes(magic);
        for(byte b : bs1){
            System.out.println("b1="+b);
        }

        System.out.println("无符号-9541="+Short.toUnsignedInt((short)-9541));

        System.out.println(ByteUtil.bytesToHexString(bs1));

        int magicNum = Integer.parseInt("dabb",16);
        System.out.println("magicNum="+magicNum);
        short magicShortNum = Short.parseShort("dabb",16);
        System.out.println("magicShortNum="+magicShortNum);



    }

}
