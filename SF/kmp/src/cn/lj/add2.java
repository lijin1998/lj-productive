package cn.lj;

import java.util.List;

public class add2 {
    public static void main(String[] args) {
        Solution solution = new add2().new Solution();
    }

    //     * Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

    }

    /*class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode res = new ListNode(1);
            ListNode resTemp = res;
            int nextSum = 0;
            int flag = 0;
            while (l1!=null&&l2!=null){
                int k;
                if (flag==0){
                    k = l1.val + l2.val;
                    res.val=k%10;
                    nextSum=k/10;
                    flag++;
                }else {
                    k = l1.val+ l2.val+nextSum;
                    resTemp.next = new ListNode(k%10);
                    resTemp = resTemp.next;
                    nextSum = k/10;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            while(l1 != null){
                int p = l1.val + nextSum;
                resTemp.next = new ListNode(p % 10);
                resTemp = resTemp.next;
                nextSum = p / 10;
                l1 = l1.next;
            }
            while(l2 != null){
                int p = (l2.val + nextSum);
                resTemp.next = new ListNode(p % 10);
                resTemp = resTemp.next;
                nextSum = p / 10;
                l2 = l2.next;
            }

            if (nextSum!=0)
                resTemp.next = new ListNode(nextSum);
                return res;
        }
    }*/


    class Solution{
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode res = new ListNode(0);
            ListNode p = l1, q = l2,curr = res;
            int carry = 0;
            while (p!=null||q!=null){
                int x=(p!=null)?p.val:0;
                int y=(q!=null)?q.val:0;
                int sum = carry + x + y;
                carry=sum/10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                if (p!=null) p=p.next;
                if (q!=null) q=q.next;
            }
            if (carry>0){
                curr.next = new ListNode(carry);
            }
            return res.next;
        }
    }
}

