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
        TreeNode left;
        TreeNode right;

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

    private int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreehelp(root);
        return diameter;
    }

    public int diameterOfBinaryTreehelp(TreeNode node) {
        if (node == null) return 0;
        int left = diameterOfBinaryTreehelp(node.left);
        int right = diameterOfBinaryTreehelp(node.right);
        diameter = Math.max(left+right,diameter);
        return Math.max(left,right)+1;
    }


    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num-1)) == 0 && (num & 0x55555555) == num;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        int len1 = list1.length,len2 = list2.length;
        HashMap<String,Integer> hs1 = new HashMap<>(len1);
        HashMap<String,Integer> hs2 = new HashMap<>(len2);
        HashMap<String,Integer> hs3 = new HashMap<>(Math.min(len1,len2));
        int result = Integer.MAX_VALUE;
        String[] ss = new String[Math.min(len1,len2)];
        int ssindex = 0;
        for (int i = 0;i < len1;i++){
            hs1.put(list1[i],i);
        }
        for (int j = 0;j < len2;j++) {
            hs2.put(list2[j],j);
        }
        if (len1 > len2) {
            for (Map.Entry<String,Integer> en:hs2.entrySet()){
                String s = en.getKey();
                if (hs1.containsKey(s) && hs1.get(s)+en.getValue() <= result){
                    result = hs1.get(s)+en.getValue();
                    hs3.put(s,hs1.get(s)+en.getValue());
                }
            }
        }else {
            for (Map.Entry<String,Integer> en:hs1.entrySet()){
                String s = en.getKey();
                if (hs2.containsKey(s) && hs2.get(s)+en.getValue() <= result){
                    result = hs2.get(s)+en.getValue();
                    hs3.put(s,hs2.get(s)+en.getValue());
                }
            }
        }
        for (Map.Entry<String,Integer> map : hs3.entrySet()){
            if (map.getValue() == result) ss[ssindex++] = map.getKey();
        }
        return Arrays.copyOfRange(ss,0,ssindex);
    }

    public int[] findErrorNums(int[] nums) {
        HashSet<Integer> hm = new HashSet<>();
        int[] res = new int[2];
        int len = nums.length;
        for (int i = 0;i < len;i++) {
            if (hm.contains(nums[i])){
                res[0] = nums[i];
            }else {
                hm.add(nums[i]);
            }
        }
        for (int i = 1; i <= len;i++ )
        {
            if (!hm.contains(i)){
                res[1] = i;
                break;
            }
        }
        return res;
    }

    public List<LinkedList<Integer>> A(int[] arr){
        if (arr.length==1) {
            List<LinkedList<Integer>> res = new ArrayList<>();
            LinkedList<Integer> a = new LinkedList<Integer>(){{add(arr[0]);}};
            res.add(a);
            return res;
        }
        List<LinkedList<Integer>> res = new ArrayList<>();
        for (int i = 0;i < arr.length;i++) {
            swap(i,0,arr);
            List<LinkedList<Integer>> al = A(Arrays.copyOfRange(arr,1,arr.length));
            for (LinkedList<Integer> tmp : al) {
                LinkedList tmp1 = new LinkedList<>(tmp);
                tmp1.addFirst(arr[0]);
                res.add(tmp1);
            }
            swap(i,0,arr);
        }
        return  res;
    }

    public void swap(int index1,int index2,int[] arr){
        if (index1 != index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;

        }
    }

    public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        HashMap<Character,Character> hs = new HashMap<>();
        for (int i = 0;i < len;i++){
            char a = s.charAt(i),b = t.charAt(i);
               if (hs.containsKey(a) && hs.get(a) != b ){
                   return false;
               }
               if (!hs.containsKey(a)){
                   if (hs.containsValue(b)) return false;
                   else hs.put(a,b);
               }

        }
        return true;
    }

    public String addBinary(String a, String b) {
        char[] arr = new char[Math.max(a.length(),b.length())+1];
        int arrindex = arr.length-1,jinwei = 0,he;
        for (int i = a.length()-1,j = b.length()-1;i >= 0 || j >= 0;i--,j--){
            if (i < 0) {
                he = b.charAt(j) - '0' + jinwei;
            }else if (j < 0) {
                he = a.charAt(i) - '0' + jinwei;
            }else {
                he = a.charAt(i) - '0' + b.charAt(j) - '0' + jinwei;
            }

            jinwei = he / 2;
            arr[arrindex--] = (char)((he % 2) + '0');

        }
        if(jinwei==1) arr[0] = '1';
        return jinwei == 1 ?  new String(arr) : new String(Arrays.copyOfRange(arr,1,arr.length));
    }

    public boolean wordPattern(String pattern, String str) {
        HashMap<Character,String> hm = new HashMap<>();
        String[] arr = str.split(" ");
        if (pattern.length()!=arr.length) return false;
        for (int i = 0;i < pattern.length();i++){
            char c = pattern.charAt(i);
            if (!hm.containsKey(c)){
                if (hm.containsValue(arr[i])) return false;
                hm.put(c,arr[i]);
            }else {
                if (!hm.get(c).equals(arr[i])){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        leetcode2 m = new leetcode2();
        System.out.println(m.addBinary("0","0"));
    }


}
