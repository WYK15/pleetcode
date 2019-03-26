import java.util.Stack;

public class leetcode2 {

    public static class ListNode {
        int val;
        leetcode1.ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class TreeNode {
        int val;
        leetcode1.TreeNode left;
        leetcode1.TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }


    public String convertToBase7(int num) {
        if (num == 0) return "0";
        Stack<Character> s = new Stack<>();
        int num1 = Math.abs(num);
        while (num1!=0) {
            s.push((char)(num1%7+48) );
            num1 /= 7;
        }
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty())
        {
            sb.append(s.pop());
        }
        return  num < 0 ?  "-"+sb.toString() : sb.toString();
    }


    public static void main(String[] args) {
        leetcode2 m = new leetcode2();

    }



}
