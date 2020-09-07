package cn.lj;

public class HW8 {
    public static void main(String[] args) {
        Solution solution = new HW8().new Solution();
    }

    class Solution {
/*        public boolean isPalindrome(int x) {
            if (x<0){
                return false;
            }
            int i = 1;
            while (x/i>=10) i *= 10;
            while (x>0){
                int left = x/i;
                int right = x%10;
                if (left!=right) return false;
                x = (x%i) / 10;
                i /= 100;
            }
            return true;
        }*/

        public boolean isPalindrome(int x){

            // 特殊情况：
            // 如上所述，当 x < 0 时，x 不是回文数。
            // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
            // 则其第一位数字也应该是 0
            // 只有 0 满足这一属性
            if (x<0||(x%10==0&&x!=0)){
                return false;
            }
            int i = 0;
            while (x>i){
                i = i*10+x%10;
                x /= 10;
            }

            // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
            // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
            // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
            return x==i||x==i/10;
        }

    }
}
