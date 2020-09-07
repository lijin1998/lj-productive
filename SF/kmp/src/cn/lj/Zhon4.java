package cn.lj;

public class Zhon4 {

    public static void main(String[] args) {
        Solution solution = new Zhon4().new Solution();
    }

/*    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n1 = nums1.length;
            int n2 = nums2.length;
            int s1 = 0,s2=0,s=0,n=0;
            for (int i = 0; i < n1; i++) {
                s1 += nums1[i];
            }
            for (int j = 0; j < n2; j++) {
                s2 += nums2[j];
            }
            s = s1 + s2;
            n = n1 + n2;
            return s/n;
        }
    }*/

    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int[] nums;
            int m = nums1.length;
            int n = nums2.length;
            nums = new int[n + m];
            if (m == 0) {
                if (n % 2 == 0) {
                    return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
                } else {

                    return nums2[n / 2];
                }
            }
            if (n == 0) {
                if (m % 2 == 0) {
                    return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
                } else {
                    return nums1[m / 2];
                }
            }

            int count = 0;
            int i = 0, j = 0;
            while (count != (m + n)) {
                if (i == m) {
                    while (j != n) {
                        nums[count++] = nums2[j++];
                    }
                    break;
                }
                if (j == n) {
                    while (i != m) {
                        nums[count++] = nums1[i++];
                    }
                    break;
                }

                if (nums1[i] < nums2[j]) {
                    nums[count++] = nums1[i++];
                } else {
                    nums[count++] = nums2[j++];
                }
            }

            if (count % 2 == 0) {
                return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
            } else {
                return nums[count / 2];
            }
        }

    }
}
