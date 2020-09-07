package cn.lj;

import java.util.HashMap;
import java.util.HashSet;

public class ReStr3 {
    public static void main(String[] args) {
        ReStr3.Solution solution = new ReStr3().new Solution();
    }


/*    class Solution {
        public int lengthOfLongestSubstring(String s) {
            HashSet<Character> set = new HashSet<>();
            int n = s.length();

            int rk = -1, strNum = 0;
            for (int i = 0; i < n; i++) {
                if (i!=0){
                    set.remove(s.charAt(i -1));
                }

                while (rk+1<n && !set.contains(s.charAt(rk+1))){
                    set.add(s.charAt(rk+1));
                    ++rk;
                }
                strNum = Math.max(strNum,rk - i + 1);
            }
            return strNum;
        }
    }*/

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            int ans = 0;
            HashMap<Character, Integer> map = new HashMap<>();
            for (int end = 0,start = 0;end<n;end++){
                char c = s.charAt(end);
                if (map.containsKey(c)){
                    start = Math.max(map.get(c),start);
                }
                ans = Math.max(ans,end-start+1);
                map.put(s.charAt(end),end+1);
            }
            return ans;
        }
    }
}
