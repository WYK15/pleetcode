import java.util.*;

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

    /*public List<Integer> addToArrayForm(int[] A, int K) {
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
    }*/

    public int[] addToArrayForm(int[] A, int K) {
        String num2 = String.valueOf(K);
        int len1 = A.length,len2 = num2.length(),maxlen = Math.max(len1,len2);
        int[] res = new int[maxlen+1];
        int index = maxlen;
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
            res[index--] = tmp%10;
        }
        if (jinwei==1) res[0] = 1;
        else res = Arrays.copyOfRange(res,1,maxlen+1);
        return res;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int p = 0; p < numRows;p++){
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i = 0;i <= numRows;i++) {
                tmp.add(i == 0 || i == numRows ? 1 : res.get(numRows-1).get(i-1)+res.get(numRows-1).get(i-2));
            }
            res.add(tmp);
        }
        return res;
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        while (n != 1) {
            n >>= 2;
            if (n % 2 != 0) return false;
        }
        return true;
    }

    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        if (len == 0 || len == 1) return false;
        boolean flag = true;
        String regex;
        for (int i = 1; i < len;i++) {
            if ( (len - i) % i == 0) {
                regex = s.substring(0,i);
                flag = true;
                for (int j = i;j < len;j+=i) {
                    if (!s.substring(j,j+i).equals(regex)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return true;
            }
        }
        return false;
    }


    public List<List<Integer>> largeGroupPositions(String S) {
        int start = 0,end = 0,len = S.length();
        List<List<Integer>> res = new ArrayList<>();
        if (len < 3)  return  res;
        char[] arr = S.toCharArray();
        for (int i = 1 ; i < len;i++) {
            if (arr[i]!= arr[end]){
                if (end - start >= 2){
                    ArrayList<Integer> al = new ArrayList<Integer>();
                    al.add(start);
                    al.add(end);
                    res.add(al);
                }
                start = i;
                end = i;
            }else {
                end++;
            }
        }
        if (end == len-1 && end - start >= 2){
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(start);
            al.add(end);
            res.add(al);
        }
        return res;
    }


    public static void main(String[] args) {
        leetcode2 m = new leetcode2();

    }


}
