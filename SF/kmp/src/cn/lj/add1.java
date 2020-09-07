package cn.lj;

import java.util.HashMap;

public class add1 {
    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int target = 9;
        Solution solution = new add1().new Solution();
    }

//    class Solution{
//        public int[] twoSum(int[] nums, int target){
//            for (int i = 0; i < nums.length; i++) {
//                for (int j = i+1; j < nums.length; j++) {
//                    if (nums[i]+nums[j]==target){
//                        return new int[]{i,j};
//                    }
//                }
//            }
//            return null;
//        }
//    }

    class Solution{
        public int[] twoSum(int[] nums, int target) throws IllegalAccessException {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])){
                    return new int[] {map.get(target-nums[i]),i};
                }
                map.put(nums[i],i);
            }
            throw new IllegalAccessException("");
        }
    }
}
