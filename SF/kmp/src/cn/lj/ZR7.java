package cn.lj;

public class ZR7 {

    public static void main(String[] args) {
        Solution solution = new ZR7().new Solution();
    }

    class Solution {
        public int reverse(int x) {

            int res = 0;
            while (x!=0){
                int i = x%10;
                int newRes = res*10+i;
                if ((newRes-i)/10!=res) return 0;
                res = newRes;
                x = x/10;
            }
            return res;
        }
    }
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return (int) res == res ? (int) res : 0;
    }

}
