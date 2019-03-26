import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        if (len==0) return false;
        if (len == 1) return bits[0] == 0 ? true : false;
        int i = 0;
        while (i < len){
            if (bits[i] == 1) {
                if (i+2 >= len) return false;
                i += 2;
            }else i++;
        }
        return true;
    }

    public String addStrings(String num1, String num2) {
        int len1 =  num1.length(),len2 = num2.length();
        char[] arr = new char[Math.max(len1,len2)];
        int index = Math.max(len1,len2) - 1;
        int jinwei = 0,tmp;
        for (int i = len1-1,j = len2-1;i >=0 || j >=0;j--,i--)
        {
            if (i < 0){
                tmp = (num2.charAt(j) - '0' + jinwei);
            }else if (j < 0){
                tmp = num1.charAt(i) - '0' + jinwei;
            }else {
                tmp = num1.charAt(i) + num2.charAt(j) -'0'-'0'+jinwei;
            }
            jinwei = tmp/10;
            arr[index--] = (char)( (tmp % 10) + '0');
        }
        return jinwei == 1 ? "1"+new String(arr) : new String(arr);
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        String num2 = String.valueOf(K);
        int len1 = A.length,len2 = num2.length();
        LinkedList<Integer> res = new LinkedList<Integer>();
        int index = Math.max(len1,len2) - 1;
        int jinwei = 0,tmp;
        for (int i = len1-1,j = len2-1;i >=0 || j >=0;j--,i--)
        {
            if (i < 0){
                tmp = (num2.charAt(j) - '0' + jinwei);
            }else if (j < 0){
                tmp = A[i] + jinwei;
            }else {
                tmp = A[i] + num2.charAt(j) -'0'+jinwei;
            }
            jinwei = tmp/10;
            res.addFirst(tmp % 10);
            index--;
        }
        if (jinwei==1) res.addFirst(1);
        return res;
    }


    public static void main(String[] args) {
        leetcode2 m = new leetcode2();
    }



}
