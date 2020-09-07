package cn.lj;

import java.util.Arrays;

public class GTqian {
    public static void main(String[] args) {
        String[] strs = {"keep","kmp","ks"};
        System.out.println(replaceSpace(strs));
    }

    private static String replaceSpace(String[] strs) {
        if (!checkStrs(strs)){
            return "";
        }
        int len = strs.length;
        StringBuffer res = new StringBuffer();
        Arrays.sort(strs);
        int m = strs[0].length();
        int n = strs[len - 1].length();
        int number = Math.min(n, m);
        for (int i = 0; i < number; i++) {
            if (strs[0].charAt(i) == strs[len - 1].charAt(i)){
                res.append(strs[0].charAt(i));
            }else  break;
        }
        return res.toString();
    }

    private static boolean checkStrs(String[] strs) {
        boolean flag = false;
        if (strs!=null){
            for (int i = 0; i < strs.length; i++) {
                if (strs[i]!=null&&strs[i].length()!=0){
                    flag=true;
                }else {
                    flag=false;
                    break;
                }
            }
        }
        return flag;
    }
}
