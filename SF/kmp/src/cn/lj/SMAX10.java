package cn.lj;

public class SMAX10 {

    public static void main(String[] args) {
        Solution solution = new SMAX10().new Solution();
    }

    class Solution{
        public int maxArea(int[] height){
/*            int length = height.length;
            int maxArea = 0;
            for (int i = 0; i < length; i++) {
                for (int j = i+1; j < length; j++) {
                    maxArea=Math.max(maxArea,Math.min(height[i],height[j])*(j-i));
                }
            }
            return maxArea;*/

            int l = 0, r = height.length-1;
            int maxArea = 0;
            while (l < r){
                int area = Math.min(height[l],height[r])*(r-l);

                maxArea = Math.max(maxArea,area);

                if (height[l]<height[r]){
                    ++l;
                }else {
                    --r;
                }
            }
            return maxArea;
        }
    }
}

















