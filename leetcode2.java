

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class leetcode2 {

    public static class ListNode {
        int val;
        ListNode next;

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

    /*public boolean repeatedSubstringPattern(String s) {
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
    }*/


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

    public int searchInsert(int[] nums, int target) {
        int left = 0,right = nums.length-1,mid;
        if (right == -1) return 0;
        while (left < right) {
            mid = (left + right) >> 1;
            if (nums[mid] == target)  return mid;
            if (nums[mid] < target) {
                left = mid+1;
            }
            if(nums[mid] > target){
                right = mid-1;
            }
        }
        if (nums[left] == target) return left;
        if (nums[left] > target) return left == 0 ? 0 :left-1;
        else return left+1;

    }

    public int hammingWeight(int n) {
        int result = 0;
        for ( int i = 0; i < 32;i++) {
            if ((n & 0x00000001) == 1) result++;
            n >>= 1;
        }
        return result;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;
        if (target < letters[0] || target > letters[len-1]) {
            return letters[0];
        }
        for (int i = 0; i < len;i++) {
            if (letters[i] > target) return letters[i];
        }
        return letters[0];
    }

    class BSTIterator {

        LinkedList<TreeNode> ll = new LinkedList<TreeNode>();

        public BSTIterator(TreeNode root) {
            while(root!=null) {
                ll.add(root);
                root = root.left;
            }
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode now = ll.pollFirst();
            TreeNode tmp = now.left;
            while (tmp!=null){
                ll.add(tmp);
                tmp = tmp.left;
            }
            return now.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return ll.isEmpty();
        }
    }

    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String,List<String>> hm = new HashMap<>();
        String content = "",filename = "";
        String[] detail,tmp;
        for (String path : paths) {
            detail = path.split(" ");
            for (int i = 1,len = detail.length; i < len;i++)
            {
                tmp = detail[i].split("[\\(||)]");
                filename = tmp[0];
                content = tmp[1];
                if (!hm.containsKey(content)) {
                    List<String> l = new LinkedList<String>();
                    l.add(detail[0]+"/"+filename);
                    hm.put(content,l);
                }else {
                    hm.get(content).add(detail[0]+"/"+filename);
                }
            }
        }
        List<List<String>> res = new LinkedList<>();
        for (List<String> l : hm.values()){
            if (l.size()>1 )res.add(l);
        }
        return res;
    }

        HashMap<Integer,Integer> hm = new HashMap<>();
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[]{};
        findModeh(root);
        int index = 0,max = 0,count = 0;
        for (Integer n : hm.values()){
            if (n > max){
                count = 0;
                max = n;
            }
            if (n == max){
                count++;
            }
        }
        int[] result = new int[count];
        for (Integer n : hm.keySet()){
            if (hm.get(n) == max) result[index++] = n;
        }
        return result;

    }

    public void findModeh(TreeNode root) {
        if (root!=null) {
            hm.put(root.val,hm.getOrDefault(root.val,0)+1);
            findModeh(root.left);
            findModeh(root.right);
        }
    }

    public String solveEquation(String equation) {
        int index = 0,len = equation.length();
        String[] eq = equation.split("=");
        String left = eq[0],right = eq[1];
        int len1 = left.length(),len2 = right.length();
        int xvalue=0,value=0;
        while (index < len1) {
            char c  = left.charAt(index);
            String s = "";
            int ope = index-1;
            if (Character.isDigit(c)){
                while (index < len1 && Character.isDigit(left.charAt(index))){
                    s += left.charAt(index);
                    index++;
                }
                index--;
                if (index+1 >= len1 || left.charAt(index+1)!='x'){
                    //不带x
                    if (ope < 0 || left.charAt(ope) == '+'){
                        value -= Integer.valueOf(s);
                    }else {
                        value += Integer.valueOf(s);
                    }
                    index++;
                }else {
                    //带x
                    if (ope<0 || left.charAt(ope)=='+'){
                        xvalue += Integer.valueOf(s);
                    }else {
                        xvalue -= Integer.valueOf(s);
                    }
                    index += 2;
                }
            }
            if (c == 'x'){
                if (index -1 < 0 || left.charAt(index-1)=='+') xvalue++;
                else xvalue--;
                index++;
            }
            if (c == '+' || c == '-') index++;
        }
        index = 0;
        while (index < len2) {
            char c  = right.charAt(index);
            int ope = index-1;
            String s = "";
            if (Character.isDigit(c)){
                while (index < len2 && Character.isDigit(right.charAt(index))){
                    s += right.charAt(index);
                    index++;
                }
                index--;
                if (index+1 >= len2 || right.charAt(index+1)!='x'){
                    //不带x
                    if (ope < 0 || right.charAt(ope) == '+'){
                        value += Integer.valueOf(s);
                    }else {
                        value -= Integer.valueOf(s);
                    }
                    index++;
                }else {
                    //带x
                    if (ope<0 || right.charAt(ope)=='+'){
                        xvalue -= Integer.valueOf(s);
                    }else {
                        xvalue += Integer.valueOf(s);
                    }
                    index += 2;
                }
            }
            if (c == 'x'){
                if (ope < 0 || right.charAt(ope)=='+') xvalue--;
                else xvalue++;
                index++;
            }
            if (c == '+' || c == '-') index++;
        }
        return xvalue == 0 ? value ==0 ? "Infinite solutions":  "No solution" : "x="+value/xvalue;
    }

    //926
    public int minFlipsMonoIncr(String S) {
        char[] arr = S.toCharArray();
        int one_count =0,one_front = 0,len = arr.length,result ;
        for (int i = 0;i < len;i++){
            if (arr[i] == '1') one_count++;
        }
        result = Math.min(one_count,len-one_count);
        for (int i = 0;i < len;i++){
            if (arr[i] == '1') one_front++;
            result = Math.min(one_front + len - i -1- (one_count-one_front),result);
        }
        return result;
    }

    //贪心  646
  /*  public int findLongestChain(int[][] pairs) {
        int len = pairs.length;
        if (len == 1 ) return 1;
        Arrays.sort(pairs, (a,b)->a[1]-b[1]);
        int start = pairs[0][1],result = 0;
        for (int i = 0;i < len;i++) {
            if (pairs[i][0] > start){
                result++;
                start = pairs[i][1];
            }
        }

        return result;
    }*/

    //动态规划 646
    public int findLongestChain(int[][] pairs) {
        int len = pairs.length;
        if (len == 1 ) return 1;
        Arrays.sort(pairs, (a,b)->a[0]-b[0]);
        int[] dp = new int[len];
        Arrays.fill(dp,1);
        int result = 1;
        for (int i = 0; i < len;i++) {
            for (int j = 0; j < len;j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[j]+1,result);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i : dp) {
            max = Math.max(i,max);
        }
        return max;
    }

    public String replaceWords(List<String> dict, String sentence) {
        StringBuilder sb = new StringBuilder();
        HashSet<String> hs = new HashSet<>();
        boolean t1  = false;
        for (String s : dict) {
            t1 = false;
            for (String t : hs) {
                if (s.startsWith(t)) {
                    t1 = true;
                    break;
                }
            }
            if (!t1) hs.add(s);
        }
        String[] arr = sentence.split(" ");
        boolean issu = false;
        String root = "";
        for (String a : arr){
            //判断是否是继承词
            issu = false;
            for ( String prefix : hs) {
                if (a.startsWith(prefix)) {
                    issu = true;
                    root = prefix;
                    break;
                }
            }
            if (issu) {
                sb.append(root);
            }else {
                sb.append(a);
            }
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return new String(sb);
    }

    //931
    public int minFallingPathSum(int[][] A) {
        int row = A.length;
        if (row == 0) return 0;
        int col = A[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0;i < col;i++) {
            dp[row-1][i] = A[row-1][i];
        }
        for (int r = row-2;r >= 0;r--){
            for (int c = 0; c < col;c++) {
                if (c == 0) dp[r][c] = Math.min(dp[r+1][0],1 < col ? dp[r+1][1] : dp[r+1][0])+A[r][c];
                else if (c == col-1){
                    dp[r][c] = A[r][c] + Math.min(dp[r+1][c-1],dp[r+1][c]);
                }
                else {
                    dp[r][c] = Math.min(Math.min(dp[r+1][c-1],dp[r+1][c]),c < col ? dp[r+1][c+1] : dp[r+1][c])+A[r][c];
                }
            }
        }
        int max = dp[0][0];
        for (int i = 0; i < col;i++) {
            max = Math.min(dp[0][i],max);
        }
        return max;
    }

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        if (len == 0) return new int[]{};
        HashMap<Integer,Integer> hm = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < len << 1 ;i++) {
            while ( !stack.isEmpty() &&  i > stack.peek() && nums[i % len] > nums[stack.peek() % len]) {
                if (!hm.containsKey(stack.peek())) hm.put(stack.pop(),i % len);
            }
            stack.push(i);
        }
        int[] result = new int[len];
        for (int i = 0; i < len;i++) {
            result[i] = hm.containsKey(i) ?  nums[hm.get(i)] : -1;
        }
        return result;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums2.length == 0) return new int[]{};
        HashMap<Integer,Integer> hm = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(nums2[0]);
        for (int i = 1; i < nums2.length;i++){
            while (!stack.isEmpty()&&nums2[i] > stack.peek()){
                hm.put(stack.pop(),nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length;i++){
            result[i] = hm.getOrDefault(nums1[i],-1);
        }
        return result;
    }

    public int[] nextLargerNodes(ListNode head) {
        if (head==null) return new int[]{};
        HashMap<ListNode,Integer> hm = new HashMap<>();
        Stack<ListNode> stack = new Stack<>();
        stack.push(head);
        ListNode tmp = head.next;
        int count = 1;
        while (tmp!=null) {
            while (!stack.isEmpty() && tmp.val > stack.peek().val){
                hm.put(stack.pop(),tmp.val);
            }
            stack.push(tmp);
            tmp = tmp.next;
            count++;
        }

        int[] result = new int[count];
        tmp = head;
        int index = 0;
        while (tmp!=null){
            result[index++] = hm.getOrDefault(tmp,0);
            tmp = tmp.next;
        }
        return result;
    }


    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N+1];
        dist[K] = -1;
        boolean[] flag = new boolean[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        flag[K] = true;
        int count = N;
        for (int i = 0;i < times.length;i++) {
            if (times[i][0] == K ) {
                dist[times[i][1]] = times[i][2];
            }
        }

        while ( count > 0){
            //从no中挑选一个v 距离值最小的 加入 yes中
            int tmpmin = 0;
            for (int i = 1;i < dist.length;i++) {
                if (!flag[i] && dist[i] < dist[tmpmin]) tmpmin = i;
            }
            count--;
            if (dist[tmpmin] == Integer.MAX_VALUE) break;
            flag[tmpmin] = true;

            //修改在no中和v相连的所有节点的权值
            for (int j = 0;j < times.length;j++) {
                if (times[j][0] == tmpmin) {
                    dist[times[j][1]] = Math.min(dist[times[j][1]],dist[tmpmin] + times[j][2]);
                }
            }
        }
        int max = -1;
        for (int i = 1;i < dist.length;i++) {
            max = Math.max(dist[i],max);
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }

    public int findNthDigit(int n) {
        if(n <= 9) return n;
        int nlen = 1;
        long nine = 9;
        while ( ( n - nlen * nine) > 0) {
            n = (int) (n - nlen * nine);
            nlen++;
            nine *= 10;
        }

        int in = n % nlen;
        int bias = n / nlen;
        long num = (long)Math.pow(10,nlen-1) + (in == 0 ? bias-1 : bias);
        if (in == 0) return (int)(num%10);
        for (int i = 0;i < nlen - in;i++) {
            num /= 10;
        }
        num %= 10;
        return (int)num;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
       if (root.left == null && root.right == null) {
           //叶子节点
           if (sum == root.val) return true;
           else return false;
       }else {
           return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);
       }

    }

    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0,len = A.length;
        int[] sums = new int[len];
        sums[0] = A[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i-1] + A[i];
        }
        if (sums[len-1] % 3 != 0) return false;
        int partsum = sums[len-1] / 3;
        int pre = len,back = 0;
        for (int i = 0; i < len;i++) {
            if (sums[i] == partsum) {
                pre = i;
                break;
            }
        }
         for (int j = len-2; j > pre;j--) {
             if (sums[len-1] - sums[j] == partsum) {
                 back =j;
                 break;
             }
         }
        return pre >= back ? false : true;
    }

    public List<Integer> pancakeSort(int[] A) {
        LinkedList<Integer> ll = new LinkedList<>();
        int len = A.length;
        int[] arr = new int[len];
        HashMap<Integer,Integer> hm = new HashMap<>(); // value -> key
        for (int i = 0; i < len;i++) {
            arr[i] = i + 1;
            hm.put(i+1,i);
        }
        int index = len -1 ;
        while (index >= 1) {
            if (arr[index] != A[index]) {
                //找到A[index]在arr中的 对应的 下标
                if (hm.get(A[index])+1 != 1) ll.addFirst(hm.get(A[index])+1);
                if (index+1 != 1) ll.addFirst(index+1);
                reverse(arr,0,hm.get(A[index]),hm);
                reverse(arr,0,index,hm);
            }
            index--;
        }
        return ll;
    }

    private void reverse(int[] arr,int start,int end,HashMap<Integer,Integer> hm){
        if (start == end) return;
        int tmp ;
        while (end > start) {
            hm.put(arr[end],start);
            hm.put(arr[start],end);
            tmp = arr[end];
            arr[end] = arr[start];
            arr[start] = tmp;
            end--;
            start++;
        }
    }

    public boolean checkInclusion(String s1, String s2) {
        // sliding windows
        if(s1.length() > s2.length() )
            return false;
        int[] cnt1 = new int[26], cnt2 = new int[26];
        char[] arr1 = s1.toCharArray(), arr2 = s2.toCharArray();
        for(int i = 0; i<arr1.length; i++)
            cnt1[arr1[i]-'a']++;

        for(int i = 0; i < arr2.length; i++){
            if(i < arr1.length){
                cnt2[arr2[i] - 'a']++;
            } else {
                if(Arrays.equals(cnt1,cnt2))
                    return true;
                cnt2[arr2[i - arr1.length]-'a']--;
                cnt2[arr2[i]-'a']++;
            }
        }
        return Arrays.equals(cnt1,cnt2);
    }

    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) return m * n;
        int mincol = Integer.MAX_VALUE,minrow = Integer.MAX_VALUE;
        for (int[] s : ops) {
            mincol = Math.min(s[0],mincol);
            minrow = Math.min(s[1],minrow);
        }
        return mincol * minrow;
    }

 /*   public  int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        pathSumh(root,sum);
        pathSum(root.left,sum);
        pathSum(root.right,sum);
        return count;
    }

    public void pathSumh(TreeNode root, int sum) {
        if (root != null) {
            if (root.val == sum) count++;
            pathSumh(root.left,sum - root.val);
            pathSumh(root.right,sum - root.val);
        }
    }*/

    /*public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String,Integer> hm = new HashMap<>();
        LinkedList<TreeNode> ll = new LinkedList<>();
        findDuplicateSubtrees(root,hm,ll);
        return ll;
    }*/

   /* public String findDuplicateSubtrees(TreeNode root,Map<String,Integer> map,LinkedList<TreeNode> res){
        if (root == null) return "#";
        String s = root.val + "," +
                findDuplicateSubtrees(root.left,map,res)+ "," +
                findDuplicateSubtrees(root.right,map,res);
        map.put(s,map.getOrDefault(s,0)+1);
        if (map.get(s) == 2) res.add(root);
        return s;
    }*/

    public boolean isValid(String S) {
        if (S.length() == 0) return  false;
        while (S.indexOf("abc") != -1) {
            S = S.replaceAll("(abc)+","");
        }
        return S.length() == 0;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head,even = head.next;
        ListNode feven = even;
        while (odd!=null && even != null) {
            odd.next = even.next;
            if (odd.next!=null) even.next = odd.next.next;
            else {
                break;
            }
            odd = odd.next;
            even = even.next;
        }
        odd.next = feven;
        return head;
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return shoppingOffersdfs(price,special,needs,0);
    }

    public int shoppingOffersdfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs,int value){
        int result = 0;
        for (int i = 0; i < price.size();i++){
            result += price.get(i) * needs.get(i);
        }
        result += value;
        for (int i = 0;i < special.size();i++) {
            List<Integer> l = special.get(i);
            List<Integer> tmp = new LinkedList<>(needs);
            boolean canbuy = true;
            for (int j = 0;j < l.size()-1;j++) {
                if (needs.get(j) >= l.get(j)){
                    needs.set(j,needs.get(j)-l.get(j));
                }else {
                    canbuy = false;
                    break;
                }
            }
            if (!canbuy) {
                needs = tmp;
                continue;
            }else {
                result = Math.min(shoppingOffersdfs(price,special,needs,value+l.get(l.size()-1)),result);
                needs = tmp;
            }
        }
        return result;
    }

    //481
    public int magicalString(int n) {
        if (n == 0 || n == 1) return n;
        if (n <= 3) return 1;
        int[] arr = new int[n+2];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 2;
        int result = 1;
        int index = 4,number;
        int groupindex = 3,num;
        while (index <= n ){
            num = arr[groupindex++];//第groupindex组 有 num个1 / 2；
            number = arr[index-1] == 1 ? 2 : 1;
            if (num == 1) {
                if (number == 1) result++;
                arr[index++] = number;
            }
            else {
                if (number == 1) result++;
                arr[index++] = number;
                if (index <=n && number == 1) result++;
                arr[index++] = number;
            }
        }
        return result;
    }


    //382
    class Solution {

        /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        ArrayList<ListNode> al = new ArrayList<>();
        public Solution(ListNode head) {
            while (head!=null) {
                al.add(head);
                head = head.next;
            }
        }

        /** Returns a random node's value. */
        public int getRandom() {
            return al.get(new Random().nextInt(al.size())).val;
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        HashSet<Integer> ed = new HashSet<>(rooms.size());
        canVisitAllRooms(rooms,ed,0);
        return ed.size() == rooms.size() ? true : false;
    }


    public void canVisitAllRooms(List<List<Integer>> rooms,Set<Integer> visited,int position) {
        if (!visited.contains(position)) {
            visited.add(position);
            for (int i = 0; i < rooms.get(position).size(); i++) {
                canVisitAllRooms(rooms, visited,rooms.get(position).get(i));
            }
        }
    }

    /*public String largestNumber(int[] nums) {
        String[] s = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s,(a,b)->{
            int alen = a.length(),blen = b.length();
            int maxlen = Math.max(alen,blen);
            for (int i = 0; i < maxlen;i++){
                if (i < alen && i < blen) {
                    if (a.charAt(i)!=b.charAt(i)) return b.charAt(i)-a.charAt(i);
                }else if (i < alen && i >= blen) {
                   // return a.charAt(i) < b.charAt(i-1) ? 1 : -1;
                    for (int j = 0;j < blen;j++) {
                        if (a.charAt(i)!=b.charAt(j)) return b.charAt(j)-a.charAt(i);
                    }
                }else {
                   // return b.charAt(i) < a.charAt(i-1) ? -1 : 1;
                    for (int j = 0;j < alen;j++){
                        if (b.charAt(i)!=a.charAt(j)) return b.charAt(i)-a.charAt(j);
                    }
                }
            }
            return 0;
        });
        StringBuilder sb = new StringBuilder();
        for (String n : s) sb.append(n);
        int start0 = 0;
        for (;start0 < sb.length();start0++) {
            if (sb.charAt(start0) != '0') break;
        }
        return start0 == sb.length() ? "0": sb.substring(start0);
    }*/

    public String largestNumber(int[] nums) {
        ArrayList<String> al = new ArrayList<>();
        for (int n:nums) al.add(String.valueOf(n));
        Collections.sort(al,(a,b)->{
            return (a+b).compareTo(b+a);
        });
        String s= new String();
        for (int i = al.size()-1; i>= 0;i--) {
            s += al.get(i);
        }
        int start0 = 0;
        for (;start0 < s.length();start0++) {
            if (s.charAt(start0) != '0') break;
        }
        return start0 == s.length() ? "0": s.substring(start0);

    }

    public List<String> letterCasePermutation(String S) {
        HashSet<String> res = new HashSet<>();
        StringBuilder sb = new StringBuilder(S);
        letterCasePermutation(res,0,sb);
        LinkedList<String> s = new LinkedList<>();
        for (String ss : res) s.add(ss);
        if (res.isEmpty() || S.length() > 0) s.add(S);
        return s;
    }

    public void letterCasePermutation( HashSet<String> res,int index,StringBuilder sb) {
        if (index<sb.length() ) {
            if (Character.isAlphabetic(sb.charAt(index))) {
                sb.setCharAt(index,Character.toUpperCase(sb.charAt(index)));
                res.add(sb.toString());
                letterCasePermutation(res,index+1,sb);
                sb.setCharAt(index,Character.toLowerCase(sb.charAt(index)));
                res.add(sb.toString());
                letterCasePermutation(res,index+1,sb);
            }else {
                letterCasePermutation(res,index+1,sb);
            }
        }
    }

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int maxC = 0;
        for(int i = A.length;i >= 2;i--) {
            if (A[i-1]+A[i-2] > A[i] ) {
                maxC = Math.max(A[i-1]+A[i-2]+A[i],maxC);
            }
        }
        return maxC;
    }

    public int kthSmallest(TreeNode root, int k) {
       LinkedList<Integer> res = kthSmallest(root);
       return res.get(k-1);
    }

    public LinkedList<Integer> kthSmallest(TreeNode root ) {
        LinkedList<Integer> ll = new LinkedList<>();
        if (root.left!=null) ll.addAll(kthSmallest(root.left));
        ll.add(root.val);
        if (root.right!=null) ll.addAll(kthSmallest(root.right));
        return ll;
    }

    public boolean isLongPressedName(String name, String typed) {
        char[] arr1 = name.toCharArray(),arr2 = typed.toCharArray();
        int len1 = arr1.length,len2 = arr2.length;
        int i = 0,j = 0;
        while (i < len1 && j < len2) {
            if (arr1[i] == arr2[j] ){
                i++;
                j++;
            }else {
                if (i-1 >= 0 && arr2[j] == arr1[i-1]) {
                    j++;
                }else {
                    return false;
                }
            }
        }
        if (i >= len1 && j >= len2) return true;
        if (i >= len1 && j < len2) {
            for (int p = j;p < len2;p++) {
                if (arr2[p] != arr1[len1-1]) return false;
            }
        }else {
            return false;
        }
        return true;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] == 1) count++;
            else {
                result = Math.max(result,count);
                count = 0;
            }
        }
        return Math.max(result,count);
    }

    /*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val < q.val) return root;
        if (root.val < p.val && root.val > q.val) return root;
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right,p,q);
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left,p,q);
        return root;
    }*/

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode tmp1 = lowestCommonAncestor(root.left,p,q);
        TreeNode tmp2 = lowestCommonAncestor(root.right,p,q);
        if (tmp1 == null) return tmp2;
        if (tmp2 == null) return tmp1;
        if (tmp1 != null && tmp2!=null) return root;
        return null;
    }

    //409
    public int longestPalindrome(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        for (int i = 0;i < s.length();i++) {
            hm.put(s.charAt(i),hm.getOrDefault(s.charAt(i),0)+1);
        }
        int result = 0;
        boolean hasji = false;
        for (int n : hm.values()){
            if (n % 2 == 0) {
                result += n;
            }else {
                result += (n-1);
                hasji = true;
            }
        }
        return hasji ? result+1 : result;
    }

    int result = 0;
    public int countArrangement(int N) {
        boolean[] used = new boolean[N+1];
        Arrays.fill(used,false);
        countArrangement(N,1,used);
        return result;
    }

    public void countArrangement(int N,int index,boolean[] used) {
        if (index > N) {
            result++;
        }else {
            for (int i = 1;i <= N;i++) {
                if (!used[i]) {
                    if (N % index == 0 || index % N == 0) {
                        used[index] = true;
                        countArrangement(N,index+1,used);
                        used[index] = false;
                    }
                }
            }
        }
    }

    //2013
   /* public List<Boolean> camelMatch(String[] queries, String pattern) {
        StringBuilder sb = new StringBuilder();
        sb.append("[a-z]*");
        for (int i = 0;i < pattern.length();i++) {
            sb.append(pattern.charAt(i));
            sb.append("[a-z]*");
        }
        String pat = sb.toString();
        ArrayList<Boolean> res = new ArrayList<>();
        for (int i = 0;i < queries.length;i++) {
            res.add(i,Pattern.matches(pat,queries[i]));
        }
        return res;

    }*/

    public int brokenCalc(int X, int Y) {
        if (X >= Y) return X - Y;
        if (Y % 2 == 0) {
            return 1+brokenCalc(X,Y / 2);
        }else {
            return 1+brokenCalc(X,(Y + 1) );
        }
    }

    class MagicDictionary {

        HashMap<Integer,HashSet<String>> hm;

        /** Initialize your data structure here. */
        public MagicDictionary() {

        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            hm = new HashMap<>(dict.length);
            for (String s : dict) {
                if (hm.containsKey(s.length())){
                    hm.get(s.length()).add(s);
                }else {
                    HashSet<String> tmp = new HashSet<>();
                    tmp.add(s);
                    hm.put(s.length(),tmp);
                }
            }
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            HashSet<String> set = hm.getOrDefault(word.length(),null);
            if (set == null) return false;
            int diff = 0;
            for (String s : set) {
                diff = 0;
                for (int i = 0;i < s.length();i++) {
                    if (diff >= 2) break;
                    if (s.charAt(i)!=word.charAt(i)) diff++;
                }
                if (diff == 1) return true;
            }
            return false;
        }
    }

   /* public boolean isSubsequence(String s, String t) {
        char[] arr1 = s.toCharArray(),arr2 = t.toCharArray();
        int i = 0,j = 0,len1 = arr1.length,len2 = arr2.length;
        if (len1 == 0) return true;
        if (len2 == 0) return false;
        while (i < len1) {
            if (j >= len2) break;
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
            }else {
                j++;
            }
        }
        return i == len1;
    }*/
    //392
    public boolean isSubsequence(String s, String t) {
        int index=-1;
        for(int i=0;i<s.length();i++) {
            index=t.indexOf(s.charAt(i),index+1);
            if(index==-1) {
                return false;
            }
        }
        return true;
    }

    public boolean hasPath(char[][] matrix, String str) {
        int row = matrix.length;
        if (row == 0) return str.length() == 0;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row;i++) {
            for (int j = 0;j < col;j++) {
                if (dfs(matrix,str,i,j,0,visited)) return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] matrix, String str,int row,int col,int index,boolean[][] visited){
        if (row >=0 && row < matrix.length && col >= 0 && col < matrix[0].length && !visited[row][col]) {
            if (index == str.length()) {
                return true;
            }
            if (str.charAt(index) == matrix[row][col]) {
                visited[row][col] = true;
                boolean flag = dfs(matrix, str, row + 1, col, index + 1, visited) ||
                        dfs(matrix, str, row, col + 1, index + 1, visited) ||
                        dfs(matrix, str, row - 1, col, index + 1, visited) ||
                        dfs(matrix, str, row, col - 1, index + 1, visited);
                if (!flag) {
                    visited[row][col] = false;
                    return false;
                }
                else return flag;
            } else {
                return false;
            }
        }
        return index == str.length();
    }

    private static int C(int a ,int b) {
        if (a < b) return 0;
        if (a == b) return 1;
        int aji = 1,bji = 1;
        while (b > 0) {
            aji *= (a--);
            bji *= (b--);
        }
        return (aji / bji) % 99997867;
    }


    public int[] sortArrayByParity(int[] A) {
        int left = 0,right = A.length;
        if (right == 0 || right == 1) return A;
        int tmp;
        while (left < right) {
            while (left < right && A[left] % 2 != 0) {
                left++;
            }
            while (left < right && A[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;
            }
        }
        return A;
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length,sum = 0;
        for (int i = 0;i < len;i+=2) {
            sum += nums[i];
        }
        return sum;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        for (int i = 0;i < len;i++) {
            if (nums[Math.abs(nums[i])] > 0) nums[nums[i]] *= (-1);
        }
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < len;i++) {
            if (nums[i] > 0) res.add(i);
        }
        return res;
    }


    public void rotate(int[] nums, int k) {
       int len = nums.length;
       if (len == 0) return;
       int mid = len - k;
       reverse(nums,0,mid-1);
       reverse(nums,mid,len-1);
       reverse(nums,0,len-1);
    }

    public void reverse(int[] nums,int left,int right) {
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }


    public int arrayNesting(int[] nums) {
        int result = Integer.MIN_VALUE;
        int len = nums.length;
        boolean[] visited = new boolean[len];
        for (int i = 0;i < len;i++) {
            if (visited[i]) continue;
            visited[i] = true;
            HashSet<Integer> hs = new HashSet<>();
            int index = i;
            while (!hs.contains(nums[index])) {
                visited[index] = true;
                hs.add(nums[index]);
                index = nums[index];
            }
            result = Math.max(hs.size(),result);
        }
        return result;
    }


   /* public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len == 1 ) return 1;
        for (int i = 0;i < len;i++) {
            int index = i+1;
            for (; index < len;index++) {
                if (nums[index] != nums[i]) break;
            }
            if (index - i >= 2) {
                 int gap = index - i -2;
                 for (int p = index; p < len;p++) {
                     nums[p-gap] = nums[p];
                 }
                 len -= gap;
            }
        }
        return len;
    }*/

   public int removeDuplicates(int[] nums) {
        int len = nums.length,pos = 1;
        if (len <= 1) return len;
        for (int i = 0;i < len;i++) {
            if (nums[i] != nums[pos-1]) {
                nums[++pos] = nums[i];
            }
        }
        return pos+1;
   }


    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[right]){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return nums[left];
    }

    public int maxSumAfterPartitioning(int[] A, int K) {
       int len = A.length;
        int[] dp = new int[len];
        Arrays.fill(dp,0);
        for (int i = 0; i < len;i++) {
            int max = A[i];
            for (int j = 1;j < K && (i - j) >= 0;j++) {
                max = Math.max(max,A[i-j]);
                dp[i] = Math.max(dp[i],i-j -1 < 0 ? 0 : dp[i - j -1] + max * (i-j+1));
            }
        }
        return dp[len-1];
    }

    public boolean isHappy(int n) {
        HashSet<Integer> visited = new HashSet<>();
        while (!visited.contains(n)) {
            if ( n == 1) return true;
            visited.add(n);
            int tmp = n;
            int sum = 0;
            while (tmp != 0) {
                sum += (tmp % 10) * (tmp % 10);
                tmp /= 10;
            }
            n = sum;
        }
        return false;
    }

    public int findShortestSubArray(int[] nums) {
       int len = nums.length;
       HashMap<Integer,Integer> hm = new HashMap<>(len);
       for (int n : nums){
           hm.put(n,hm.getOrDefault(n,0)+1);
       }
       int maxtimes = 0;
       for (Integer integer : hm.values()) {
           maxtimes = Math.max(maxtimes,integer);
       }
       if (maxtimes == 1) return maxtimes;
       LinkedList<Integer> ll = new LinkedList<>();
       for (Map.Entry<Integer,Integer> entry : hm.entrySet()) {
           if (entry.getValue() == maxtimes) {
               ll.add(entry.getKey());
               System.out.println(entry.getKey());
           }
       }
       int min = Integer.MAX_VALUE;
       for (int n : nums) {
           int start = 0,end = len-1;
           for (;start < len;start++) {
               if (nums[start] == n) break;
           }
           for (;end > start;end--) {
               if (nums[end] == n ) break;
           }
           min = Math.min(min,end-start+1);
       }
       return min;

    }

    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int len = A.length;
        int lsum = 0,msum = 0;
        int result = 0;
        int[] sums = new int[len];
        for (int i = 0;i < len;i++){
            sums[i] = (i -1 >= 0 ? sums[i-1]:0) + A[i];
        }
        for (int i = 0;i <= len-L;i++) {
            lsum = sums[i+L-1] - sums[i] + A[i];
            msum = 0;
            //i之前的
            for (int j = 0;(j + M) <= i;j++)  {
                msum = Math.max(msum,sums[j+M-1] - sums[j] + A[j]);
            }
            //i + L 之后的
            for (int j = i + L;j <= len-M;j++){
                msum = Math.max(msum,sums[j+M-1] - sums[j] + A[j]);
            }
            result = Math.max(result,msum+lsum);
        }
        return result;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummyHead1 = new ListNode(0);
        ListNode dummyHead2 = new ListNode(0);
        ListNode node1 = dummyHead1;
        ListNode node2 = dummyHead2;
        while (head != null) {
            if (head.val < x) {
                node1.next = head;
                head = head.next;
                node1 = node1.next;
                node1.next = null;
            } else {
                node2.next = head;
                head = head.next;
                node2 = node2.next;
                node2.next = null;
            }
        }
        node1.next = dummyHead2.next;
        return dummyHead1.next;
    }


    public int findMinDifference(List<String> timePoints) {
        int[] times=new int[timePoints.size()];
        int idx=0;
        for (String s:timePoints)
        {
            times[idx++]=getMinute(s);
        }
        Arrays.sort(times);
        int res=times[0]+24*60-times[times.length-1];
        for (int i=1;i<times.length;i++)
        {
            res=Math.min(times[i]-times[i-1],res);
        }
        return res;
    }

    private int getMinute(String s) {
        String[] strs=s.split(":");
        return Integer.parseInt(strs[0])*60+Integer.parseInt(strs[1]);
    }

    public int firstUniqChar(String s) {
        int [] times = new int[26];
        for(int i = 0 ; i < s.length() ; i++){
            times[s.charAt(i) - 'a']++;
        }

        for(int i = 0 ; i < s.length() ;i++){
            if(times[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }


    //384
    /*class Solution {
        int[] numss;
        public Solution(int[] nums) {
            this.numss = nums;
        }

        *//** Resets the array to its original configuration and return it. *//*
        public int[] reset() {
            return numss;
        }

        *//** Returns a random shuffling of the array. *//*
        public int[] shuffle() {
            int[] backup = Arrays.copyOf(numss,numss.length);
            Random rand = new Random();
            for (int i = backup.length-1;i > 0;i--) {
                //从i到len-1挑选一个 与 i 交换
                int l = rand.nextInt(i+1);
                int tmp = backup[i];
                backup[i] = backup[l];
                backup[l] = tmp;
            }
            return backup;
        }
    }*/

    public List<String> summaryRanges(int[] nums) {
        int len = nums.length;
        boolean constant = false;
        StringBuilder sb = new StringBuilder();
        LinkedList<String> ll  = new LinkedList<>();
        if (len == 0) return ll;
        sb.append(String.valueOf(nums[0]));
        for (int i = 1;i <= len;i++) {
            if (i == len) constant = false;
            else constant = nums[i] - nums[i-1] == 1;
            if (!constant){
                if (String.valueOf(nums[i-1]).equals(sb.toString())) ll.add(sb.toString());
                else {
                    sb.append("->");
                    sb.append(String.valueOf(nums[i-1]));
                    ll.add(sb.toString());
                }
                sb = new StringBuilder();
                if (i < len) sb.append(String.valueOf(nums[i]));
            }
        }
        return ll;
    }

    public boolean buddyStrings(String A, String B) {
        int alen = A.length(),blen = B.length();
        if (alen!=blen) return false;
        int[] arr = new int[26];
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < alen;i++) {
            arr[A.charAt(i)-'a']++;
            if (A.charAt(i)!=B.charAt(i)){
                al.add(i);
            }
        }
        if (al.size()==0) {
            for (int i = 0;i < 26;i++) {
                if (arr[i] >= 2) return true;
            }
            return false;
        }
        if (al.size()!=2) return false;
        int tmp1 = al.get(0),tmp2 = al.get(1);
        if (A.charAt(tmp1)==B.charAt(tmp2) && A.charAt(tmp2)==B.charAt(tmp1)) return true;
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] arr = new int[numCourses];
        Arrays.fill(arr,0);
        for (int i = 0;i < prerequisites.length;i++) {
            arr[prerequisites[i][0]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0;i < arr.length;i++) {
            if (arr[i] == 0) queue.offer(i);
        }

        int count = 0;
        while (!queue.isEmpty())
        {
            int front = queue.pop();
            count++;
            for (int i = 0;i < prerequisites.length;i++) {
                if (prerequisites[i][1] == front) {
                    arr[prerequisites[i][0]]--;
                    if (arr[prerequisites[i][0]]==0) {
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }
        return count == numCourses;
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        LinkedList<Integer> tmp = new LinkedList<>();
        Set<List<Integer>> ll = new HashSet<>();
        if (nums.length == 0) return new LinkedList<>();
        findSubsequences(nums,tmp,0,ll);
        return new LinkedList<>(ll);
    }

    public void findSubsequences(int[] nums,LinkedList<Integer> tmp,int index,Set<List<Integer>> res) {

        if (tmp.size() >= 2) {
            res.add(new LinkedList<>(tmp));
        }
        if (index == nums.length){
            return ;
        }
        for (int i = index;i < nums.length;i++) {
            if(index == 0 || tmp.getLast() <= nums[i]) {
                tmp.addLast(nums[i]);
                findSubsequences(nums,tmp,i+1,res);
                tmp.removeLast();
            }else {
                findSubsequences(nums,tmp,i+1,res);
            }
        }
    }


    public static void main(String[] args)
    {

    }

}



