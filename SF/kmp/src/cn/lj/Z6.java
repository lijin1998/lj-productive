package cn.lj;

import java.util.ArrayList;

public class Z6 {

    public static void main(String[] args) {
        Solution solution = new Z6().new Solution();
    }

//    class Solution {
//        public String convert(String s, int numRows) {
//            if(numRows == 2) return s;
//            List<StringBuilder> rows = new ArrayList<StringBuilder>();
//            for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
//            int i = 0, flag = -1;
//            for(char c : s.toCharArray()) {
//                rows.get(i).append(c);
//                if(i == 0 || i == numRows -1) flag = - flag;
//                i += flag;
//            }
//            StringBuilder res = new StringBuilder();
//            for(StringBuilder row : rows) res.append(row);
//            return res.toString();
//        }
//    }

    class Solution{
        public String convert(String s, int numRows){
            if (numRows==2) return s;
            ArrayList<StringBuilder> rows = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                rows.add(new StringBuilder());
            }
            int i =0,flag = -1;
            for (char c : s.toCharArray()) {
                rows.get(i).append(c);
                if (i==0||i==numRows-1) flag=-flag;
                i += flag;
            }
            StringBuilder res = new StringBuilder();
            for (StringBuilder row : rows) {
                res.append(row);
            }
            return res.toString();
        }
    }
}
