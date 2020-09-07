package cn.lj;

import org.junit.Test;

public class SoLution {

    public static void main(String[] args) {
        String st = "I Love Java";
        String s = replaceSpace2(st);
        System.out.println(s);
    }

    public static String replaceSpace(String str) {

        int length = str.length();

// System.out.println("length=" + length);

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < length; i++) {

            char b = str.charAt(i);

            if (String.valueOf(b).equals(" ")) {

                result.append("%20");
            } else {

                result.append(b);
            }
        }

        return result.toString();
    }

    public static String replaceSpace2(String str){
        return str.toString().replaceAll("\\s","%20");
    }
}
