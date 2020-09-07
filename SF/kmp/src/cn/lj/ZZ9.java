package cn.lj;

public class ZZ9 {
    public static void main(String[] args) {
        ZZ9.Solution solution = new ZZ9().new Solution();
    }

    /*class Solution {
        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();

            boolean[][] f = new boolean[m + 1][n + 1];
            f[0][0] = true;
            for (int i = 0; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (p.charAt(j - 1) == '*') {
                        f[i][j] = f[i][j - 2];
                        if (matches(s, p, i, j - 1)) {
                            f[i][j] = f[i][j] || f[i - 1][j];
                        }
                    }
                    else {
                        if (matches(s, p, i, j)) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    }
                }
            }
            return f[m][n];
        }

        public boolean matches(String s, String p, int i, int j) {
            if (i == 0) {
                return false;
            }
            if (p.charAt(j - 1) == '.') {
                return true;
            }
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }*/

    class Solution {
        public boolean isMatch(String s, String p) {
            return check(s.toCharArray(),p.toCharArray(),s.length()-1,p.length()-1);
        }

        boolean check(char[] s,char[] p,int S1,int P1){
            if(P1<0&&S1<0){
                return true;
            }
            if(S1<0){
                if(p[P1]=='*'){
                    return check(s,p,S1,P1-2);
                }else{
                    return false;
                }
            }
            if(P1<0){
                return false;
            }
            if(p[P1]=='.'||p[P1]==s[S1]){
                return check(s,p,S1-1,P1-1);
            }
            if(p[P1]=='*'){
                if(p[P1-1]==s[S1]||p[P1-1]=='.'){
                    return check(s,p,S1-1,P1)||check(s,p,S1,P1-2)||check(s,p,S1-1,P1-2);
                }else{
                    return check(s,p,S1,P1-2);
                }
            }
            return false;
        }
    }

}
