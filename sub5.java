import java.util.*;

public class sub5 {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        LinkedList<Character> zhan = new LinkedList<>();

    }


    public List<String> generateParenthesis(int n) {

        ArrayList<String> res = new ArrayList<String>();
        if (n==0) return res;
        int left = n;
        int right = n;
        String tmp = "";
        cal(res,"",left,right);
        return  res;
    }

    public  void cal(List<String> res,String tmp,int left,int right){
        if (left==0&&right==0){
            res.add(tmp);
            return;
        }
        else{
            if (left>0&&left<=right){
                cal(res,tmp+"(",left-1,right);
            }
            if (right>0&&left<right){
                cal(res,tmp+")",left,right-1);
            }
        }
    }
}
