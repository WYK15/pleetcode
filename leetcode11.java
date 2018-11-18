import java.util.*;
import java.util.regex.Pattern;

public class leetcode11 {
    public int maxArea(int[] height) {
        int max = 0;
        int tmp = 0;
        int start = 0,end = height.length-1;
        while ( end > start){
           tmp = Math.min(height[start],height[end]) * (end-start);
           if (tmp>max) {
               max = tmp;
           }

            if (height[start]>height[end]) end--;
            else start++;
        }
        return max;
    }



    public int romanToInt(String s) {

        int res = 0;
        HashMap<Character,Integer> hm = new HashMap<Character,Integer>(){{put('I',1);put('V',5);put('X',10);put('L',50);put('C',100);put('D',500);put('M',1000);}};
        for (int i = 0;i<s.length();i++ ){
            if (i<=s.length()-2){
                if (hm.get(s.charAt(i))<hm.get(s.charAt(i+1))) res+=(-1)*hm.get(s.charAt(i));
                else res += (hm.get(s.charAt(i)));
            }else {
                res += hm.get(s.charAt(i));
            }
        }
        return  res;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
     }





    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root==null) return res;
        LinkedList<Integer> tmp = new LinkedList<>();
        helper(res,tmp,0,root,sum);
        return res;
    }

    //113
    public void helper(ArrayList<List<Integer>> res,LinkedList<Integer> tmp,int sum,TreeNode node,int target){
        tmp.add(node.val);
        sum+=node.val;
        if(sum==target && node.left==null && node.right==null){
            res.add(new LinkedList<>(tmp));
        } else {
           if (node.left!=null) helper(res, tmp, sum, node.left, target);
           if (node.right!=null) helper(res, tmp, sum, node.right, target);
        }
        tmp.removeLast();

    }

    public int minMoves2(int[] nums) {
        int len = nums.length;
        int res = 0;
        Arrays.sort(nums);
        int ave = 0;
        if (len%2==0){
            ave = (nums[len/2-1] + nums[len/2])/2;
        }else {
            ave = nums[len/2];
        }
        for (int n : nums){
            res+=Math.abs(n-ave);
        }
        return res;

    }


    public static void main(String[] args) {
        leetcode11 ll = new leetcode11();
        System.out.println(ll.romanToInt("MCMXCVI"));

    }
}
