import java.util.*;

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

    public static void main(String[] args) {
        leetcode2 m = new leetcode2();

    }


}
