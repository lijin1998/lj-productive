package cn.lj;

public class ReStr5 {

    public static void main(String[] args) {
        Solution solution = new ReStr5().new Solution();
    }

    /*class Solution {
        public String longestPalindrome(String s) {
            int len = s.length();
            if (len<2){
                return s;
            }
            int maxlen = 1;
            int begin = 0;
            char[] charArray = s.toCharArray();

            for (int i = 0; i < len - 1; i++) {
                for (int j = i+1; j < len; j++) {
                    if (j-i+1>maxlen&&validPalindromic(charArray,i,j)){
                        maxlen=j-i+1;
                        begin=i;
                    }
                }
            }
            return s.substring(begin,begin+maxlen);
        }

        private boolean validPalindromic(char[] charArray,int left,int right){
            while (left<right){
                if (charArray[left] != charArray[right]){
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }*/

    class Solution {

        public String longestPalindrome(String s) {
            // 特判
            int len = s.length();
            if (len < 2) {
                return s;
            }

            int maxLen = 1;
            int begin = 0;

            // dp[i][j] 表示 s[i, j] 是否是回文串
            boolean[][] dp = new boolean[len][len];
            char[] charArray = s.toCharArray();

            for (int i = 0; i < len; i++) {
                dp[i][i] = true;
            }
            for (int j = 1; j < len; j++) {
                for (int i = 0; i < j; i++) {
                    if (charArray[i] != charArray[j]) {
                        dp[i][j] = false;
                    } else {
                        if (j - i < 3) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }

                    // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
            return s.substring(begin, begin + maxLen);
        }
    }

}
