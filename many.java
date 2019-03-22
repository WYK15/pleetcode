
import sun.reflect.generics.tree.Tree;

import javax.sound.sampled.Line;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class many {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        boolean hasremain = true;/**/
        ListNode head = new ListNode(0);
        ListNode pre = head;
        int index = 0;
        while (hasremain) {
            hasremain = false;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    hasremain = true;
                    if (lists[i].val < min) {
                        index = i;
                        min = lists[i].val;
                    }
                }
            }
            if (hasremain) {
                ListNode now = new ListNode(min);
                lists[index] = lists[index].next;
                now.next = null;
                pre.next = now;
                pre = now;
            }
        }
        return head.next;
    }


    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode go = head;
        ListNode pre = null;
        ListNode newhead = null;
        while (go != null && go.next != null) {
            if (newhead == null) newhead = go.next;
            ListNode tmp = go.next;
            go.next = tmp.next;
            tmp.next = go;
            if (pre != null) pre.next = tmp;
            pre = go;
            go = go.next;
        }
        return newhead;
    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count++] = nums[i];
            }

        }
        return count;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newhead = new ListNode(0);
        ListNode pre = newhead;
        Stack<ListNode> stack = new Stack<>();
        int count = 0;
        while (head != null) {
            count = 0;
            while (count < k) {
                stack.push(head);
                head = head.next;
                count++;
                if (head == null) break;
            }
            if (count == k) {
                while (!stack.isEmpty()) {
                    ListNode top = stack.pop();
                    ListNode now = new ListNode(top.val);
                    now.next = null;
                    pre.next = now;
                    pre = now;
                }
            } else {
                ListNode ppre = null;
                ListNode top = null;
                while (!stack.isEmpty()) {
                    top = new ListNode(stack.pop().val);
                    top.next = ppre;
                    ppre = top;
                }
                pre.next = ppre;
            }
        }
        return newhead.next;
    }

    public void nextPermutation(int[] nums) {
        int change1 = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                change1 = i - 1;
                break;
            }
        }
        if (change1 == -1) {
            Arrays.sort(nums);
            return;
        }
        int change2 = change1 + 1;
        int min = nums[change1 + 1];
        for (int p = change1 + 1; p < nums.length; p++) {
            if (nums[p] > nums[change1] && nums[p] < min) {
                min = nums[p];
                change2 = p;
            }
        }
        nums[change2] = nums[change1];
        nums[change1] = min;
        Arrays.sort(nums, change1 + 1, nums.length);

    }


   /* public int longestValidParentheses(String s) {
        for (int i = 0;i<s.length();i++){
            if (s.charAt(i)!=')') {s = s.substring(i,s.length());break;}
        }

        int result = 0;
        for (int i = 0;i<s.length();i++)
        {

            if (s.charAt(i)==')' ) continue;
            if ( (s.length()-i)<result ) break;
            StringBuilder sb = new StringBuilder();
            sb.append(s,i,i+result);
            for(int j = i+result;j<s.length();j++)
            {
                if (isValid(sb.toString())){
                    result = sb.length();
                }
            }
        }
        return  result;
    }

    public boolean isValid(String s) {
        if (s.length()%2!=0) return false;
        Stack<Character> stack = new Stack<>();
        Character c ;
        Character top ;
        for (int i = 0;i<s.length();i++){
            c = s.charAt(i);
            if (c=='{'||c=='['||c=='('){
                stack.push(c);
            }else{
                if (stack.isEmpty()) return false;
                top = stack.pop();
                if (  (c==')' && top!='(')|| (c=='}'&&top!='{')||(c==']' && top!='[')  ){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }*/


    public int longestValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else {
                if (!stack.isEmpty()) {
                    if (stack.pop() == '(') {
                        result += 2;
                    } else {
                        break;
                    }
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }
        return result;
    }


   /* public int search(int[] nums, int target) {
        if (nums.length==1 && nums[0]==target) return 0;
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        while (left<right){
            mid = (left+right)/2;
            if (nums[left]==target) return left;
            if (nums[right]==target) return right;
            if (nums[mid]==target) return mid;
            if (target>nums[mid]) left++;
            if (target<nums[mid]) right--;
        }
        return -1;
    }*/


    public int search(int[] nums, int target) {
        if (nums.length == 1 && nums[0] == target) return 0;
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            if (nums[mid] == target) return mid;
            if (nums[mid] > nums[left]) { //第一种情况
                if (target > nums[mid]) {
                    left = mid + 1;
                } else {
                    if (target > nums[left]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            } else { //第二种情况
                if (target > nums[mid]) {
                    if (target < nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    right = mid - 1;
                }
            }

        }
        return -1;
    }



    /*public int[] searchRange(int[] nums, int target) {
        int[] res={-1,-1};
        ArrayList<Integer> result = new ArrayList<>();
        if(nums.length==0) {return res;}
        int head = 0,tail = nums.length-1,mid = 0;
        while (head<=tail){
            mid = (head+tail)/2;
            if (nums[head]==target){
                result.add(head);
            }
            if (nums[tail]==target){
                result.add(tail);
            }

            if (target>nums[mid]){
                head = mid+1;
            }
            else if (target<nums[mid]){
               tail = mid-1;
            }
            else{
                result.add(mid);
                head++;
                tail--;
            }
        }
        if (result.isEmpty()) return res;

        Collections.sort(result);
        res[0] = result.get(0);res[1]=result.get(result.size()-1);
        System.out.println(res[0]+"**"+res[1]);
        return res;
    }
*/

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length == 0) {
            return res;
        }
        int min = nums.length, max = 0;
        boolean istophead = false, istoptail = false;
        int head = 0, tail = nums.length - 1, mid = 0;
        while (head <= tail) {
            mid = (head + tail) / 2;
            istophead = false;
            istoptail = false;
            if (nums[head] == target && head <= min) {
                min = head;
                istophead = true;
            }
            if (nums[tail] == target && tail > max) {
                max = tail;
                istoptail = true;
            }
            if (target > nums[mid]) {
                head = mid + 1;
            } else if (target < nums[mid]) {
                tail = mid - 1;
            } else {
                if (!istophead) head++;
                if (!istoptail) tail--;
            }
        }
        if (max >= min) {
            res[0] = min;
            res[1] = max;
        }
        return res;
    }


    public boolean isValidSudoku(char[][] board) {
        //第一条规则
        HashSet<Character> hs = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            hs.clear();
            for (int j = 0; j < 9; j++) {
                if (Character.isDigit(board[i][j])) {
                    if (!hs.contains(board[i][j])) hs.add(board[i][j]);
                    else return false;
                }
            }
        }

        //second rule
        for (int i = 0; i < 9; i++) {//列
            hs.clear();
            for (int j = 0; j < 9; j++) {//行
                if (Character.isDigit(board[j][i])) {
                    if (!hs.contains(board[j][i])) hs.add(board[j][i]);
                    else return false;
                }
            }
        }

        //重点这里，二维数组分块遍历
        //third rule
        for (int start = 0; start < 9; start += 3) {
            for (int startin = 0; startin < 9; startin += 3) {
                hs.clear();
                for (int hang = start; hang < start + 3; hang++) {
                    for (int lie = startin; lie < startin + 3; lie++) {
                        if (Character.isDigit(board[hang][lie])) {
                            if (!hs.contains(board[hang][lie])) hs.add(board[hang][lie]);
                            else return false;
                        }
                        // System.out.printf(hang+"   "+lie+"   ");
                    }

                }
            }
        }

        return true;
    }

    public String countAndSay(int n) {
        if (n == 1) return "1";
        String s = countAndSay(n - 1);
        StringBuilder res = new StringBuilder();
        int count = 0;
        char last = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == last) {
                count++;
            } else {
                res.append(count + 1);
                res.append(s.charAt(i - 1));
                count = 0;
            }
            last = s.charAt(i);
        }
        return res.toString();
    }



    /*public int firstMissingPositive(int[] nums) {
        if (nums.length==0) return 1;
        int min = Integer.MAX_VALUE;
        int max = nums[0];
        HashSet<Integer> hs = new HashSet<>();
        for(int i = 0;i<nums.length;i++) {
            if (nums[i] > 0) {
                hs.add(nums[i]);
                if (nums[i] < min) {
                    min = nums[i];
                }
                if (nums[i] > max) {
                    max = nums[i];
                }
            }
        }
        if (min>1)  return 1;

        char[] arr = new char[max];
        Arrays.fill(arr,'a');

         Iterator<Integer> iter = hs.iterator();
        while (iter.hasNext()) {
            Integer next =  iter.next();
            arr[next-1] = 'b';
        }

        for (int i = 1; i < arr.length; i++) {
            if(arr[i]=='a'){
                return i+1;
            }
        }

    return max+1;
    }*/

    /*public int firstMissingPositive(int[] nums) {
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (count == nums[i]) {
                count ++;
                i = -1;
            }
        }
        return count;
    }*/

    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;
        int min = Integer.MAX_VALUE;
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                hs.add(nums[i]);
                if (nums[i] < min) {
                    min = nums[i];
                }
            }
        }
        if (min > 1) return 1;

        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == count) {
                count++;
                i = -1;
            }
        }

        return count;
    }

    /*public String multiply(String num1, String num2) {
         String res = "0";
         if (num1.equals("0")||num2.equals("0")) return res;
         int jinwei = 0;
         int reswei = 0;
         StringBuilder count0 = new StringBuilder();
         ArrayList<String> al = new ArrayList<>();
            for(int i = num2.length()-1;i>=0;i--){
                jinwei = 0;
                StringBuilder sb = new StringBuilder();
               for(int j = num1.length()-1;j>=0;j--){
                   reswei = (num2.charAt(i)-'0')*(num1.charAt(j)-'0');
                   reswei+=jinwei;
                   sb.insert(0,reswei%10);
                   jinwei = reswei/10;
               }
                if (jinwei!=0) sb.insert(0,String.valueOf(jinwei));
                al.add(sb.toString()+count0.toString());
                count0.append("0");
            }
        Iterator<String> iter =  al.iterator();
        while (iter.hasNext()){

            String thisone = iter.next();
            System.out.print(res + " +  ");
          res = add(res,thisone);
            System.out.println(thisone  + "= " +res);
        }

        return res;
    }
*/


    public String multiply(String num1, String num2) {
        String res = "0";
        char[] num1arr = num1.toCharArray();
        char[] num2arr = num2.toCharArray();
        if (num1.equals("0") || num2.equals("0")) return res;
        int jinwei = 0;
        int reswei = 0;
        StringBuilder count0 = new StringBuilder();
        for (int i = num2arr.length - 1; i >= 0; i--) {
            jinwei = 0;
            StringBuilder sb = new StringBuilder();
            Stack<Integer> st = new Stack<>();
            for (int j = num1arr.length - 1; j >= 0; j--) {
                reswei = (num2arr[i] - '0') * (num1arr[j] - '0');
                reswei += jinwei;
                st.push(reswei % 10);
                jinwei = reswei / 10;
            }
            while (!st.isEmpty()) {
                sb.append(st.pop());
            }
            if (jinwei != 0) sb.insert(0, String.valueOf(jinwei));
            res = add(res, sb.append(count0.toString()).toString());
            count0.append("0");
        }
        return res;
    }

    public String add(String num1, String num2) {
        Stack<Integer> stack = new Stack<>();
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        int i = arr1.length - 1, j = arr2.length - 1;
        int jinwei = 0;
        int he = 0;
        for (; i >= 0 || j >= 0; i--, j--) {
            if (i < 0) {
                he = arr2[j] - '0' + jinwei;
            } else if (j < 0) {
                he = arr1[i] - '0' + jinwei;
            } else {
                he = arr1[i] - '0' + arr2[j] - '0' + jinwei;
            }
            stack.push(he % 10);
            jinwei = he / 10;
        }
        if (jinwei != 0) stack.push(jinwei);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }


    public int maxSubArray(int[] nums) {
        int max = nums[0], tmp = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (tmp < 0) tmp = 0;
            tmp += nums[i];
            if (tmp >= max) max = tmp;
        }
        return max;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        if (matrix.length == 0) return al;
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (top >= 0 && bottom >= 0 && top <= bottom && left <= right) {
            if (top == bottom) {
                for (int i = left; i <= right; i++) {
                    al.add(matrix[top][i]);
                }
            } else if (left == right) {
                for (int i = top; i <= bottom; i++) {
                    al.add(matrix[i][right]);
                }
            } else {
                for (int i = left; i <= right; i++) {
                    al.add(matrix[top][i]);
                }
                for (int i = top + 1; i <= bottom; i++) {
                    al.add(matrix[i][right]);
                }
                for (int i = right - 1; i >= left; i--) {
                    al.add(matrix[bottom][i]);
                }
                for (int i = bottom - 1; i >= top + 1; i--) {
                    al.add(matrix[i][left]);
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return al;
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);

        List<Integer> path = new ArrayList<Integer>();
        helper(path, 0, target, candidates, result, 0);

        return result;
    }

    public void helper(List<Integer> path, int count, int target, int[] candidates, List<List<Integer>> res, int index) {
        if (count == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (count < target) {
            int tmpcount;
            for (int i = index; i < candidates.length; i++) {
                tmpcount = count + candidates[i];
                if (tmpcount > target) {
                    break;
                }
                if (tmpcount <= target) {
                    path.add(candidates[i]);
                    helper(path, tmpcount, target, candidates, res, i);
                    path.remove(path.size() - 1);
                }
            }
        }
    }


    public List<List<Integer>> combination() {
        List<List<Integer>> result = new ArrayList<>();
        fun2(result);
        return result;
    }

    public void fun2(List<List<Integer>> res) {

        res.add(new ArrayList<Integer>() {{
            add(4);
        }});
    }

    public char findTheDifference(String s, String t) {
        char[] s1arr = s.toCharArray();
        char[] s2arr = t.toCharArray();
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < s1arr.length; i++) {
            sum1 += s1arr[i];
        }
        for (int i = 0; i < s2arr.length; i++) {
            sum2 += s2arr[i];
        }
        return (char) Math.abs(sum2 - sum1);

    }

    public String toGoatLatin(String S) {
        String[] words = S.split(" ");
        StringBuilder sb = new StringBuilder();
        char[] yuanyin = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        Set<Character> set = new HashSet<Character>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
            add('A');
            add('E');
            add('I');
            add('O');
            add('U');
        }};

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (set.contains(word.charAt(0))) {
                sb.append(word + "ma");

            } else {
                sb.append(word.substring(1) + word.charAt(0) + "ma");
            }
            for (int j = 0; j <= i; j++) {
                sb.append('a');
            }
            if (i < words.length - 1) sb.append(' ');
        }
        return sb.toString();
    }


    public double largestTriangleArea(int[][] points) {
        int length = points.length;
        if (length < 3) return 0;
        int index1, index2, index3;
        double maxarea = 0;
        double tmps = 0;
        for (index1 = 0; index1 <= length - 3; index1++) {
            int point11 = points[index1][0];
            int point12 = points[index1][1];
            for (index2 = index1 + 1; index2 <= length - 2; index2++) {
                int point21 = points[index2][0];
                int point22 = points[index2][1];
                for (index3 = index2 + 1; index3 <= length - 1; index3++) {
                    int point31 = points[index3][0];
                    int point32 = points[index3][1];
                    double len1 = callen(point11, point12, point21, point22);
                    double len2 = callen(point11, point12, point31, point32);
                    double len3 = callen(point21, point22, point31, point32);
                    tmps = (len1 + len2 + len3) / 2;
                    double area = Math.sqrt(tmps * (tmps - len1) * (tmps - len2) * (tmps - len3));
                    if (area > maxarea) maxarea = area;
                }
            }
        }
        return maxarea;
    }

    public double callen(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
    }


    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] arr = new int[len];
        arr[0] = nums[0];
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + nums[i];
        }

        for (int start = 0; start < arr.length; start++) {
            for (int index = start + 1; index < arr.length; index++) {
                if (arr[index] - arr[start] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    //33325
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len < 2) return len;
        int jizhi1 = 1, jizhi2 = -1;
        int count1 = 1, count2 = 1;
        int index1 = 0, index2 = 0;
        for (int i = 1; i < len; i++) {
            if ((nums[i] - nums[index2]) * jizhi2 < 0) {
                index2 = i;
                count2++;
                jizhi2 *= (-1);
            }
            if ((nums[i] - nums[index1]) * jizhi1 < 0) {
                index1 = i;
                count1++;
                jizhi1 *= (-1);
            }
        }
        System.out.println(count1 + "*******" + count2);
        return Math.max(count1, count2);
    }


    public int integerBreak(int n) {
        int ji = 1;
        if (n <= 3) return n - 1;
        int max = 0;
        for (int part = 2; part <= n / 2; part++) {
            ji = 0;
            if (n % part == 0) {
                int ave = n / part;
                ji += pow(ave, part);
                if (ji > max) max = ji;
            } else {
                int[] aves = {n / part + 1, n / part};
                for (int nn : aves) {
                    ji = 0;
                    if (((part - 1) * nn) >= n) continue;
                    ji += pow(nn, part - 1);
                    ji *= (n - (part - 1) * nn);
                    if (ji > max) {
                        max = ji;
                        System.out.println(max + "*****" + nn);
                    }
                }
            }
        }
        return max;
    }


    public int pow(int num, int pp) {
        int ji = 1;
        for (int j = 0; j < pp; j++) {
            ji *= num;
        }
        return ji;
    }


    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int alltime = 0;
        int tmptime = 0;
        int len = timeSeries.length;
        for (int i = 0; i < len - 1; i++) {
            tmptime = timeSeries[i] + duration - 1;
            if (tmptime < timeSeries[i + 1]) {
                alltime += duration;
            } else {
                alltime += (timeSeries[i + 1] - timeSeries[i]);
            }


        }
        return alltime + duration;
    }


    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int temperature = temperatures[i];
            while (!stack.empty() && temperature > temperatures[stack.peek()]) {
                int t = stack.pop();
                result[t] = i - t;
            }
            stack.push(i);
        }

        return result;
    }


    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] > 0;
    }


    public List<List<Integer>> combinationSum3(int k, int n) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        int deep = 0;
        int he = 0;
        LinkedList<Integer> tmp = new LinkedList<>();
        help1(deep, k, n, res, tmp, he);
        return res;
    }

    public void help1(int deep, int k, int n, LinkedList<List<Integer>> res, LinkedList<Integer> tmp, int he) {
        if (deep > k) return;
        else if (deep == k) {
            if (he == n) res.add(new LinkedList<>(tmp));
        } else {
            for (int i = tmp.size() == 0 ? 1 : tmp.getLast() + 1; i <= 9; i++) {
                tmp.add(i);
                help1(deep + 1, k, n, res, tmp, he + i);
                tmp.removeLast();
            }
        }
    }


    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (slow == 0 || nums[slow] != nums[fast]) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int finder = 0;

        while (finder == 0 || nums[finder] != nums[fast]) {
            finder = nums[finder];
            fast = nums[fast];
        }


        return nums[finder];
    }

   /* public int combinationSum4(int[] nums, int target) {
        int res = 0,he =0;
        Arrays.sort(nums);
        res += help(0,target,nums);
        return  res;
    }

    public int help(int he,int target,int[] nums){
        int res = 0;
        if (he==target){
            res++;
        }else{
            for (int i = 0;i<nums.length;i++){
                int hetmp = nums[i] + he;
                if (hetmp>target) break;
                res += help(hetmp,target,nums);
            }
        }
        return res;
    }*/

    public int combinationSum4(int[] nums, int target) {


        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i < target + 1; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] = dp[i] + dp[i - num];
                } else {
                    break;
                }
            }
        }

        return dp[target];

    }

    /*public int canCompleteCircuit(int[] gas, int[] cost) {
        int len1 = gas.length;
        int res = -1;
        int minindex = 0;
        LinkedList<Integer> al = new LinkedList<>();
        for (int i = 0;i<len1;i++){
            if (cost[i]<=cost[minindex]){
                minindex = i;
                if (!al.isEmpty() && cost[minindex]!= al.getLast()){
                    al.clear();
                }
                al.push(minindex);
            }
        }

        for (int maxindex:al){
            int oil = 0,count=0;
            while(count<len1){
                oil += gas[maxindex];
                oil -= cost[maxindex];
                if (oil < 0) {break;}
                maxindex++;
                if (maxindex>=len1) maxindex = 0;
                count++;
            }
            if (oil >= 0) return minindex;
        }

        return  res;
    }*/


    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len1 = gas.length;
        int past = 0, startindex = 0;
        int oil = 0;
        for (int i = 0; i < len1; i++) {
            oil += (gas[i] - cost[i]);
            if (oil < 0) {
                past += oil;
                startindex = i + 1;
                oil = 0;
            }
        }
        return past + oil >= 0 ? startindex : -1;
    }

    public int majorityElement(int[] nums) {
        int len2 = nums.length / 2;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int n : nums) {
            if (hm.get(n) == null) {
                hm.put(n, 1);
            } else {
                hm.put(n, hm.get(n) + 1);
            }
            if (hm.get(n) >= len2) return n;
        }

        return nums[0];
    }


    public void moveZeroes(int[] nums) {
        int index1 = 0, index2 = 0;
        int len = nums.length;
        for (index2 = 0; index2 < len; index2++) {
            if (nums[index2] != 0) {
                nums[index1++] = nums[index2];
            }
        }

        for (; index1 < len; index1++) {
            nums[index1] = 0;
        }
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[][] dp = new int[len][len];
        for (int i = 0; i < triangle.get(len - 1).size(); i++) {
            dp[len - 1][i] = triangle.get(len - 1).get(i);
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);

            }
        }

        return dp[0][0];
    }

    public String addBinary(String a, String b) {
        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();
        int i = arr1.length - 1, j = arr2.length - 1;
        char[] res = new char[Math.max(i + 1, j + 1)];
        StringBuilder sb = new StringBuilder();
        int jinwei = 0;
        int tmp = 0;
        while (i >= 0 || j >= 0) {
            if (i < 0) {
                tmp = arr2[j] - '0' + jinwei;
                res[j] = (char) (tmp % 2 + '0');
                jinwei = tmp / 2;
                j--;
            } else if (j < 0) {
                tmp = arr1[i] - '0' + jinwei;
                res[i] = (char) (tmp % 2 + '0');
                jinwei = tmp / 2;
                i--;
            } else {
                tmp = arr1[i] - '0' + arr2[j] - '0' + jinwei;
                res[Math.max(i, j)] = (char) (tmp % 2 + '0');
                jinwei = tmp / 2;
                i--;
                j--;
            }
        }
        if (jinwei > 0) sb.append(1);
        for (char c : res) sb.append(c);
        return sb.toString();
    }

    public static void quanpailie(Object[] arr, int index) {
        if (index == arr.length - 1) {
            for (Object i : arr) System.out.printf("%d ", i);
            System.out.println();
        }
        for (int j = index; j < arr.length; j++) {
            swap(arr, index, j);
            quanpailie(arr, index + 1);
            swap(arr, index, j);
        }
    }

    public static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (slow != null && fast != null) {
            if (fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    dp[i][i] = 0;
                } else if (i == 1 || j == 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }


    public int minPathSum(int[][] grid) {
        int len = grid.length;
        int len1 = grid[0].length;
        int[][] dp = new int[len][len1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len1 - 1; j >= 0; j--) {
                if (i == len - 1 && j == len1 - 1) {
                    dp[i][j] = grid[i][j];
                    continue;
                }
                if (i == len - 1) dp[i][j] = dp[i][j + 1] + grid[i][j];
                else if (j == len1 - 1) dp[i][j] = dp[i + 1][j] + grid[i][j];
                else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }

    public boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (right(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    //79
    public boolean right(char[][] board, String word, int index, int i, int j) {
        if (index == word.length()) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word.charAt(index) != board[i][j]) {
            return false;
        }
        board[i][j] ^= 128;
        boolean res = right(board, word, index + 1, i - 1, j) ||
                right(board, word, index + 1, i + 1, j) ||
                right(board, word, index + 1, i, j + 1) ||
                right(board, word, index + 1, i, j - 1);
        board[i][j] &= 128;
        return res;
    }

    public List<String> restoreIpAddresses(String s) {
        LinkedList<String> ll = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        restartiphelp(s, 0, ll, sb, 1);
        return ll;
    }

    public void restartiphelp(String s, int index, LinkedList<String> res, StringBuilder sb, int part) {
        if (part == 5) {
            if (index == s.length()) {
                res.push(sb.toString().substring(0, sb.length() - 1));
            }
            return;
        }

        for (int i = index + 1; i <= index + 3 && i <= s.length(); i++) {
            String stmp = s.substring(index, i);
            int tmp = Integer.valueOf(stmp);
            if (tmp <= 255 && !(stmp.length() >= 2 && stmp.startsWith("0"))) {
                sb.append(stmp);
                sb.append(".");
                restartiphelp(s, i, res, sb, part + 1);
                sb.delete(sb.length() - stmp.length() - 1, sb.length());
            }
        }
    }

    public int findone(int[] arr) {
        int a = arr[0];
        for (int i = 1; i < arr.length; i++) {
            a ^= arr[i];
        }
        return a;
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        while (n > 1) {
            if (n % 2 != 0) return false;
            n = n >> 1;
        }
        if (n == 0) return false;
        else return true;
    }

    public void sortColors(int[] nums) {
        int right = nums.length - 1;
        if (right == 0) return;
        int left = 0;
        for (int i = 0; i < nums.length && i <= right; i++) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
            }
            if (nums[i] == 2) {
                swap(nums, right, i);
                right--;
                i--;
            }
        }

    }

    public void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public int calcows(int n) {
        ConcurrentHashMap<Integer, Integer> hm = new ConcurrentHashMap<>();
        hm.put(0, 1);
        int count = 1;
        for (int year = 1; year <= n; year++) {
            for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
                int tmpyear = entry.getKey();
                int value = entry.getValue();
                int sub = year - tmpyear;
                if (sub > 0) {
                    if (sub == 20) {
                        count -= hm.get(tmpyear);
                        hm.remove(tmpyear);
                    }
                    if (sub < 15 && sub % 4 == 0) {
                        count += value;
                        if (hm.get(year) == null) hm.put(year, value);
                        else hm.put(year, hm.get(year) + value);
                    }
                }
            }
        }
        return count;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public int maxDepth(TreeNode root) {
        return maxdepthelp(root, 0);
    }

    public int maxdepthelp(TreeNode node, int dep) {
        if (node == null) return dep;
        return Math.max(maxdepthelp(node.left, dep + 1), maxdepthelp(node.right, dep + 1));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> al = new ArrayList<List<Integer>>();
        if (root == null) return al;
        ArrayList<ArrayList<TreeNode>> nodes = new ArrayList<>();
        nodes.add(new ArrayList<TreeNode>() {{
            add(root);
        }});
        for (int i = 0; i < nodes.size(); i++) {
            ArrayList<TreeNode> tmpnode = nodes.get(i);
            ArrayList<Integer> ttmpal = new ArrayList<>();
            ArrayList<TreeNode> tmpal = new ArrayList<>();
            for (TreeNode treeNode : tmpnode) {
                ttmpal.add(treeNode.val);
                if (treeNode.left != null) {
                    tmpal.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    tmpal.add(treeNode.right);
                }
            }
            al.add(ttmpal);
            if (tmpal.size() > 0) nodes.add(tmpal);
        }
        return al;
    }


    public int findBottomLeftValue(TreeNode root) {
        int res = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            res = queue.peek().val;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode tmpnode = queue.poll();
                if (tmpnode.left != null) queue.offer(tmpnode.left);
                if (tmpnode.right != null) queue.offer(tmpnode.right);
            }
        }
        return res;
    }


    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while (stack.size() >= 2) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();
            if (node1 == null && node2 == null) continue;
            if (node1 != null && node2 != null && node1.val == node2.val) {
                stack.push(node1.right);
                stack.push(node2.left);
                stack.push(node1.left);
                stack.push(node2.right);
            } else {
                return false;
            }
        }
        return true;

    }


    public boolean isSymmetrichelp(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 != null && node2 != null && node1.val == node2.val) {
            return isSymmetrichelp(node1.left, node2.right)
                    && isSymmetrichelp(node1.right, node2.left);
        } else return false;
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len];
        dp[0] = wordDict.contains(s.charAt(0));
        for (int i = 0; i < s.length(); i++) {
            int j = 0;
            for (j = 1; j < i; j++) {
                if (wordDict.contains(s.substring(j, i)) && dp[j - 1]) {
                    dp[i] = true;
                    break;
                }
            }
            if (j == i) dp[i] = false;
        }
        return dp[len - 1];
    }


    public void quicksort(int[] arr, int left, int right) {
        if (left > right) return;
        int standard = arr[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && arr[j] >= standard) {
                j--;
            }
            while (i < j && arr[i] <= standard) {
                i++;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, left, i);
        quicksort(arr, left, i - 1);
        quicksort(arr, i + 1, right);
    }


    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        LinkedList<Integer> ll = new LinkedList<>();
        sumNumvershelper(root, ll, 0);
        int res = 0;
        for (int n : ll) {
            res += n;
        }
        return res;
    }

    public void sumNumvershelper(TreeNode node, LinkedList<Integer> ll, int val) {
        if (node != null) {
            val = val * 10 + node.val;
        }
        if (node.left == null && node.right == null) {
            ll.add(val);
            return;
        }
        if (node.left != null) {
            sumNumvershelper(node.left, ll, val);
        }
        if (node.right != null) {
            sumNumvershelper(node.right, ll, val);
        }
    }


    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        /*
        boolean[][] dp = new boolean[len][len];
        int count = 0;
        for (int i = 0;i < len;i++){
            for (int j = i+1;j<len;j++){
                if (j==i+1)  {
                    dp[i][j] =true;
                    continue;
                }
                dp[i][j] = dp[i][j-1] && (A[j]-A[j-1]==A[j-1]-A[j-2]);
                if (dp[i][j]) count++;
            }
        }
        return count;*/
        int res = 0, cur = 1;
        if (A == null || len <= 2) return 0;
        int dif = A[1] - A[0];
        for (int i = 2; i < len; i++) {
            if (A[i] - A[i - 1] == dif) {
                res += cur++;
            } else {
                dif = A[i] - A[i - 1];
                cur = 1;
            }
        }
        return res;
    }


    public static boolean help(int sum, Integer[] al, char last) {
        if (sum < 0) return false;
        if (sum == 0) {
            if (last == 'A') return true;
            else return true;
        }
        int he = 0;
        int len = al.length;
        for (int j = 0; j < len; j++) {
            for (int i = 1; i <= j; i++) {
                al[j] -= i;
                if (last == 'A') {
                    return help(sum - i, al, 'B');
                }
                if (last == 'B') {
                    return help(sum - i, al, 'A');
                }
                al[j] += i;
            }
        }
        return false;
    }

    static class stu implements Cloneable {
        private String name = "name1";
        private int age = 10;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            stu ss = (stu) super.clone();
            ss.setName(new String(name));
            ss.setAge(age);
            return ss;
        }
    }

    public int maxstolemoney(int[] arr) {
        int len = arr.length;
        int val1 = 0, val2 = 0;
        int[][] dp = new int[len][len];
        dp[0][1] = Math.max(arr[0], arr[1]);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    dp[i][j] = arr[j];
                    continue;
                }
                if (j - 2 >= 0) dp[i][j] = Math.max(dp[i][j - 2] + arr[j], dp[i][j - 1]);
                if (dp[i][j] > dp[val1][val2]) {
                    val1 = i;
                    val2 = j;
                }
            }
        }
        return dp[val1][val2];
    }


    public void maxsumlen(int[] arr) {
        int len = arr.length;
        int maxsum = 0;
        int val1 = 0, val2 = 0;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = arr[i];
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j - 1] + arr[j], dp[i + 1][j] + arr[i]);
                if (dp[i][j] > dp[val1][val2]) {
                    val1 = i;
                    val2 = j;
                }
            }
        }
        System.out.println((val1 + 1) + "****" + (val2 + 1));

    }


    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 == 1) return false;
        sum /= 2;
        int[][] dp = new int[len][sum];
        for (int i = nums[0]; i < sum; i++)
            dp[0][i] = nums[0];
        for (int i = 0; i < len; i++) {
            for (int j = nums[i]; j < sum; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
            }
        }
        return dp[len - 1][sum - 1] == sum;
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates.length == 0) return res;
        LinkedList<Integer> tmp = new LinkedList<>();
        Arrays.sort(candidates);
        combinationSum2help(candidates, tmp, res, 0, target, 0);
        return res;
    }

    public void combinationSum2help(int[] arr, LinkedList tmp,
                                    List<List<Integer>> res, int sum, int target, int index) {
        if (sum == target) {
            if (!res.contains(tmp)) res.add(new LinkedList<>(tmp));
            return;
        }
        if (sum < target) {
            for (int i = index; i < arr.length; i++) {
                if (sum + arr[i] > target) break;
                tmp.push(arr[i]);
                combinationSum2help(arr, tmp, res, sum + arr[i], target, i + 1);
                tmp.pop();
            }
        }
    }


    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        //O(n)
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        //O(n)
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hm.entrySet());
        //O(nlg(n))
        Collections.sort(list, (o1, o2) -> {
            return o2.getValue().compareTo(o1.getValue());
        });

        ArrayList<Integer> res = new ArrayList<>();
        //O(k)
        for (int i = 0; i < k; i++) {
            res.add(list.get(i).getKey());
        }
        return res;
    }


    //PriorityQueue最大堆  重写compare方法可以变为最小堆
    // treemap类似PriorityQueue ,两者的区别在于可不可以插入重复的元素,
    // 即 treemap重写hashcode()也能实现PriorityQueue

   /* public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();

        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });

        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++)
            if (map.containsKey(nums[i]))
                map.put(nums[i],map.get(nums[i])+1);
            else
                map.put(nums[i],1);

        Set<Map.Entry<Integer,Integer>> set = map.entrySet();

        for (Map.Entry<Integer,Integer> entry : set)
            pq.add(entry);


        for (int i = 0; i < k; i++)
            list.add(pq.poll().getKey());

        return list;
    }*/


    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            int[] res = {};
            return res;
        }
        ArrayList<Integer> al = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        findfrequentTreeSumhelp(root, hm);
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int tmp = entry.getValue();
            if (al.isEmpty()) al.add(entry.getKey());
            else {
                if (tmp == hm.get(al.get(0))) al.add(entry.getKey());
                if (tmp > hm.get(al.get(0))) {
                    al = new ArrayList<>();
                    al.add(entry.getKey());
                }
            }
        }

        int len = al.size();
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = al.get(i);
        }
        return array;
    }

    public int findfrequentTreeSumhelp(TreeNode root, HashMap<Integer, Integer> hm) {
        int sum = 0;
        if (root.left != null) sum += findfrequentTreeSumhelp(root.left, hm);
        if (root.right != null) sum += findfrequentTreeSumhelp(root.right, hm);
        sum += root.val;
        hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        return sum;
    }

    //328


    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenstart = head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            if (head.next != null) even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenstart;
        return head;
    }

    /*
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] dp = new int[len];
        List<Integer> lastrow = triangle.get(len-1);
        for (int i = 0;i<lastrow.size();i++) dp[i] = lastrow.get(i);
        for (int i = len-2;i>=0;i--){
            List<Integer> lin = triangle.get(i);
            int lenin = lin.size();
            for (int j = 0;j < lenin;j++)
            dp[j] = lin.get(j) + Math.min(dp[j],dp[j+1]);
        }
        return dp[0];

    }
    */

    //236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack1 = findpath(root, p, stack);
        stack.clear();
        Stack<TreeNode> stack2 = findpath(root, q, stack);
        int shortlen = Math.min(stack1.size(), stack2.size());
        TreeNode res = null;
        for (int i = 0; i < shortlen; i++) {
            if (stack1.get(i) == stack2.get(i)) res = stack1.get(i);
        }
        return res;

    }

    public Stack<TreeNode> findpath(TreeNode root, TreeNode target, Stack<TreeNode> stack) {
        if (root == null) return null;
        if (stack.size() == 0) {
            stack.push(root);
        }
        if (root == target) {
            return (Stack<TreeNode>) stack.clone();
        }
        stack.push(root.left);
        Stack<TreeNode> stacktmp1 = findpath(root.left, target, stack);
        stack.pop();


        stack.push(root.right);
        Stack<TreeNode> stacktmp2 = findpath(root.right, target, stack);
        stack.pop();
        if (stacktmp1 != null) return stacktmp1;
        if (stacktmp2 != null) return stacktmp2;
        return null;
    }


    public void dd() {
        TreeNode root = new TreeNode(1);
        TreeNode ll1 = new TreeNode(2);
        TreeNode ll2 = new TreeNode(1);
        root.left = ll1;
        root.right = null;
        ll1.left = null;
        ll1.right = null;
        ll2.left = null;
        ll2.right = null;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> res1 = findpath(root, root, stack);
        stack.empty();
        Stack<TreeNode> res2 = findpath(root, ll1, stack);
        for (TreeNode tr : res1) System.out.println(tr.val + "**");
        for (TreeNode tr : res2) System.out.println(tr.val);
    }


    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hs = new HashSet<>();
        HashSet<Integer> res = new HashSet<>();
        for (int n : nums1) hs.add(n);
        for (int p : nums2) {
            if (hs.contains(p)) res.add(p);
        }
        Integer[] resarr = new Integer[res.size()];
        res.toArray(resarr);
        int[] resarr1 = new int[resarr.length];
        for (int i = 0; i < resarr.length; i++) resarr1[i] = resarr[i];
        return resarr1;
    }

    //232


    //799
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) return list;
        queue.offer(root);
        while (!queue.isEmpty()) {
            list.add(queue.peek().val);
            System.out.println(queue.peek().val);
            int length = queue.size();
            while (length-- > 0) {
                TreeNode tmp = queue.poll();
                if (tmp.right != null)
                    queue.offer(tmp.right);

                if (tmp.left != null)
                    queue.offer(tmp.left);
            }
        }
        return list;
    }


    public String frequencySort(String s) {
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        char[] chararr = s.toCharArray();
        HashMap<Character, Integer> hm = new HashMap<>();
        for (char c : chararr) {
            if (!hm.containsKey(c)) {
                hm.put(c, 1);
            } else {
                hm.put(c, hm.get(c) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            pq.add(entry);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(makestring(pq.poll()));
        }
        return sb.toString();
    }

    public String makestring(Map.Entry<Character, Integer> map) {
        char c = map.getKey();
        int count = map.getValue();
        StringBuilder sb = new StringBuilder();
        while (count-- > 0) {
            sb.append(c);
        }
        return sb.toString();
    }


    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> ts = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            int[] ints = matrix[i];
            for (int j = 0; j < ints.length; j++) {
                int anInt = ints[j];
                ts.add(anInt);
            }
        }
        for (int i = 1; i <= k - 1; i++) {
            ts.poll();
        }
        return ts.poll();
    }

    public void reorderList(ListNode head) {
        ListNode slow = mid(head), headgo = head, tmpList;
        Stack<ListNode> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }

        while (stack.size() > 1 && headgo != stack.peek()) {
            //stack只有一个元素的时候，不用执行了
            tmpList = stack.pop();
            tmpList.next = headgo.next;
            headgo.next = tmpList;
            headgo = tmpList.next;
            stack.peek().next = null;
        }
    }

    public ListNode mid(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public String customSortString(String S, String T) {
        HashMap<Character, Integer> hm = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        char[] sarr = S.toCharArray();
        char[] tarr = T.toCharArray();
        int[] arr = new int[tarr.length];
        Arrays.fill(arr, 0);
        for (int i = 0, len = sarr.length; i < len; i++) {
            hm.put(sarr[i], i);
        }
        for (char c : tarr) {
            if (hm.containsKey(c)) arr[hm.get(c)]++;
            else sb.append(c);
        }
        for (int i = 0, len = arr.length; i < len; i++) {
            StringBuilder tmpsb = new StringBuilder();
            for (int j = 0; j < arr[i]; j++) tmpsb.append(sarr[i]);
            sb.append(tmpsb);
        }
        return sb.toString();
    }


    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int c : nums) pq.offer(c);
        while ((--k) > 0) pq.poll();
        return pq.poll();
    }

    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        HashSet<Integer> hs = new HashSet<>();
        for (int n : nums) {
            if (hs.contains(n)) {
                res[0] = n;
            }
            hs.add(n);
        }
        for (int i = 1, len = nums.length; i <= len; i++) {
            if (!hs.contains(i)) {
                res[1] = i;
                break;
            }
        }
        return res;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[m + n + 1] = nums1[m];
                m--;
            } else {
                nums1[m + n + 1] = nums2[n];
                n--;
            }
        }
        if (m < 0) {
            for (int i = 0; i < n + 1; i++) {
                nums1[i] = nums2[i];
            }
        }

        for (int p : nums1) System.out.println(p);
    }


    public ListNode reverseList(ListNode head) {
        /*
        ListNode pre = null,p = head;
        while(p!=null){
            ListNode tmp = p.next;
            p.next = pre;
            pre = p;
            p = tmp;
        }
        return pre;
        */
        return reverseListhelp(null, head); //递归
    }

    public ListNode reverseListhelp(ListNode pre, ListNode p) {
        if (p == null) {
            return pre;
        }
        ListNode tmp = p.next;
        p.next = pre;
        return reverseListhelp(p, tmp);
    }

    //748
    /*public String shortestCompletingWord(String licensePlate, String[] words) {
        //
        char[] arr1 = licensePlate.toCharArray();
        HashMap<Character,Integer> hm = new HashMap<>();
        String res = null;
        int min = Integer.MAX_VALUE;
        for (char c:arr1){
            if (Character.isAlphabetic(c)) hm.put(Character.toLowerCase(c),hm.getOrDefault(Character.toLowerCase(c),0)+1);
        }
        for (int i = 0,len = words.length;i<len;i++){
            char[] tmp =  words[i].toCharArray();
            int lenin =  tmp.length;
            HashMap<Character,Integer> tmphm  = new HashMap<>();

            for (int j = 0;j<lenin;j++){
                tmphm.put(tmp[j],tmphm.getOrDefault(tmp[j],0)+1);
            }

            boolean falg = true;
            for (Map.Entry<Character,Integer> entry:hm.entrySet()){
                Character c = entry.getKey();
                Integer vv = entry.getValue();
                if (!tmphm.containsKey(c) || tmphm.get(c) < vv ) {
                    falg = false;
                    break;
                }
            }
            System.out.println(hm);
            System.out.println(tmphm);
            if (falg ) {

                if ( lenin < min){
                    res = words[i];
                    min = lenin;
                }
            }
        }
        return  res;
        // 第二种方式  int数组 26   不用hashmap
    }*/


    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] arr = new int[26];
        String res = null;
        int minlen = Integer.MAX_VALUE;
        HashSet<Integer> hs = new HashSet<>();
        char[] chararr = licensePlate.toCharArray();
        for (char c : chararr) {
            if (Character.isAlphabetic(c)) {
                c = Character.toLowerCase(c);
                arr[c - 'a']++;
                hs.add(c - 'a');
            }
        }
        for (String s : words) {
            char[] tmpsarr = s.toCharArray();
            int[] tmpcountarr = new int[26];
            for (char c : tmpsarr) {
                if (Character.isAlphabetic(c)) {
                    c = Character.toLowerCase(c);
                    tmpcountarr[c - 'a']++;
                }
            }
            boolean flag = true;
            for (Integer index : hs) {
                if (tmpcountarr[index] == 0 || tmpcountarr[index] < arr[index]) {
                    flag = false;
                    break;
                }
            }
            if (flag && s.length() < minlen) {
                res = s;
                minlen = s.length();
            }
        }
        return res;
    }

    //73
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        boolean flag = false, flag2 = false;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) flag = true;
        }
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) flag2 = true;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        if (flag) {
            for (int i = 0; i < rows; i++) matrix[i][0] = 0;
        }

        if (flag2) {
            for (int i = 0; i < cols; i++) {
                matrix[0][i] = 0;
            }
        }


    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p = headA;
        ListNode tmp = null;
        while (p.next != null) {
            p = p.next;
        }
        tmp = p;
        p.next = headB;
        ListNode slow = headA, fast = headA;
        do {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                fast = null;
            }
        } while (fast != null && slow != fast);
        if (fast == null) {
            tmp.next = null;
            return null;
        }
        fast = headA;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        tmp.next = null;
        return fast;
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec1[2] < rec2[0] || rec1[0] > rec2[2]) return false;
        if (rec1[3] < rec2[1] || rec1[1] > rec2[3]) return false;
        int xstart, xend, ystart, yend;
        int start1 = rec1[0], end1 = rec1[2], start2 = rec2[0], end2 = rec2[2];
        if (start2 > start1 && end2 > end1) {
            xstart = start2;
            xend = end1;
        } else if (start1 > start2 && end1 > end2) {
            xstart = start1;
            xend = end2;
        } else if (start2 > start1 && end1 > end2) {
            xstart = start2;
            xend = end2;
        } else {
            xstart = start1;
            xend = end1;
        }
        start1 = rec1[1];
        end1 = rec1[3];
        start2 = rec2[1];
        end2 = rec2[3];
        if (start2 > start1 && end2 > end1) {
            ystart = start2;
            yend = end1;
        } else if (end1 > end2 && start1 > start2) {
            ystart = start1;
            yend = end2;
        } else if (end1 > end2 && start2 > start1) {
            ystart = start2;
            yend = end2;
        } else {
            ystart = start1;
            yend = end1;
        }
        for (; xstart <= xend; xstart++) {
            for (int j = ystart; j <= yend; j++) {
                if (existinrec2(xstart, j, rec2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existinrec2(int x, int y, int[] rec2) {
        if (x > rec2[0] && x < rec2[2] && y > rec2[1] && y < rec2[3]) return true;
        return false;
    }

    public int minDepth(TreeNode root) {
        return minDepth(root, 1);
    }

    public int minDepth(TreeNode root, int count) {
        if (root.left == null && root.right == null) return count;
        if (root.left == null) return minDepth(root.right, count + 1);
        if (root.right == null) return minDepth(root.left, count + 1);
        return Math.min(minDepth(root.left, count + 1), minDepth(root.right, count + 1));
    }
/*
    public int trailingZeroes(int n) {
        int count = 0;
        int tmp ;
        int chengji = 1;
        for (int i = 2; i <= n;i+=2){
            if (i%10==0) {20
                count++;
                continue;
            }
            chengji *= i;
            if (chengji > 1000) chengji = chengji >> 1;
        }
        if(n>=5) chengji *= 5;
        while ((tmp =chengji%10)== 0){
            count++;
            chengji /= 10;
        }
        return count;
    }
    */

//278
  /*  public int firstBadVersion(int n) {
        return firstBadVersionhelp(1,n);
    }

    public int firstBadVersionhelp(int left,int right){
        int mid = left ;
        while (left <= right){
            mid = (left/2+right/2);
            if (isBadVersion(mid) && !isBadVersion(mid-1)){
                return mid;
            }else if (isBadVersion(mid) && isBadVersion(mid-1)){
                right = mid-1;
            }else if (!isBadVersion(mid) && isBadVersion(mid+1) ){
                return mid+1;
            }else {

                left = mid + 1;
            }
        }
        return mid;
    }*/


    public int trailingZeroes(int n) {
        int sum = 0;
        while (n > 5) {
            sum += n / 5;
            n /= 5;
        }
        return sum;
    }


    //877
    public boolean stoneGame(int[] piles) {
        return true;
    }

    //289
    public void gameOfLife(int[][] board) {
        int row = board.length, col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //计算board[i][j]周围的活细胞和死细胞的数量
                int live = 0;
                for (int rowin = i - 1; rowin <= i + 1; rowin++) {
                    for (int colin = j - 1; colin <= j + 1; colin++) {
                        if (rowin >= 0 && colin >= 0 && rowin < row && colin < col)
                            if (board[rowin][colin] == 1 || board[rowin][colin] == 3) live++;
                    }
                }
                if (board[i][j] == 1) live--;
                if (board[i][j] == 1) {
                    if (live < 2 || live > 3) board[i][j] = 3;
                } else {
                    if (live == 3) board[i][j] = 2;
                }
                if (i == 0 && j == 1) System.out.println(board[i][j]);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 2) board[i][j] = 1;
                if (board[i][j] == 3) board[i][j] = 0;
            }
        }
    }

    //701
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return root;
        TreeNode pre = root, iter = root;
        while (iter != null) {
            pre = iter;
            if (iter.val < val) {
                iter = iter.right;
            } else {
                iter = iter.left;
            }
        }
        TreeNode insertone = new TreeNode(val);
        if (pre.val < val) {
            pre.right = insertone;
        } else {
            pre.left = insertone;
        }
        return root;
    }

    //553
    public String optimalDivision(int[] nums) {
        int len = nums.length;
        StringBuilder sb = new StringBuilder();
        if (len == 1) {
            return String.valueOf(nums[0]);
        } else if (len == 2) {
            return String.valueOf(nums[0]) + "/" + String.valueOf(nums[1]);
        } else {
            sb.append(nums[0]);
            sb.append("/(");
            for (int i = 1; i < len; i++) {
                sb.append(nums[i]);
                sb.append("/");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        }
        return sb.toString();
    }

    /*二叉树前序 递归实现
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> al = new ArrayList<>();
        preorderTraversalhelp1(root,al);
        return  al;
    }

    public void preorderTraversalhelp1(TreeNode root,ArrayList res){
        if (root!=null){
            res.add(root.val);
            preorderTraversalhelp1(root.left,res);
            preorderTraversalhelp1(root.right,res);
        }
    }
    */

    //二叉树 非递归实现
    public List<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode tmp = stack.pop();
                res.add(tmp.val);
                //打印先左 后右，则先打印的 后推入 ，即先推入 右，后推入左
                if (tmp.right != null) stack.push(tmp.right);
                if (tmp.left != null) stack.push(tmp.left);
            }
        }
        return res;
    }

    //712
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1][len2];
        for (int i = 1; i < len1 - 1; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i);
        }
        for (int j = 1; j < len2 - 1; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j);
        }
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                int tmp = s1.charAt(i) == s2.charAt(j) ? 0 : s1.charAt(i) + s2.charAt(j);
                dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i), Math.min(dp[i][j - 1] + s2.charAt(j), dp[i - 1][j - 1] + tmp));
            }
        }
        return dp[len1 - 1][len2 - 1];
    }

    //442
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0, len = nums.length; i < len; i++) {
            if (nums[Math.abs(nums[i]) - 1] < 0) {
                al.add(Math.abs(nums[i]));
            }
            nums[Math.abs(nums[i]) - 1] *= (-1);
        }
        return al;
    }

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int len = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts) {
            int tmp = Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - ghost[0]);
            if (tmp <= len) return false;
        }
        return true;
    }

    public List<List<String>> partition(String s) {
        ArrayList<List<String>> res = new ArrayList<>();
        LinkedList<String> tmp = new LinkedList<>();
        partitionhelp(res, tmp, s);
        return res;
    }

    public void partitionhelp(ArrayList<List<String>> res, LinkedList<String> tmp, String s) {
        if (s.length() == 0) {
            res.add(new LinkedList<>(tmp));
            return;
        }
        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String tmps = s.substring(0, i);
            if (ishuiwen(tmps)) {
                tmp.add(tmps);
                partitionhelp(res, tmp, s.substring(i));
                tmp.removeLast();
            }
        }
    }

    public boolean ishuiwen(String s) {
        int i = 0, len = s.length() - 1;
        while (len > i) {
            if (s.charAt(len--) != s.charAt(i++)) return false;
        }
        return true;
    }

    //leetcode 894
    public List<TreeNode> allPossibleFBT(int N) {
        ArrayList<TreeNode> res = new ArrayList<>();
        if (N % 2 == 0) return res;
        if (N == 1) {
            TreeNode tn = new TreeNode(0);
            res.add(tn);
            return res;
        }
        N--;
        for (int i = 1; i < N; i += 2) {
            for (TreeNode tn1 : allPossibleFBT(i)) {
                for (TreeNode tn2 : allPossibleFBT(N - i)) {
                    TreeNode root = new TreeNode(0);
                    root.left = tn1;
                    root.right = tn2;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public List<Integer> largestValues(TreeNode root) {
        LinkedList<TreeNode> ll = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        ll.add(root);
        while (!ll.isEmpty()) {
            int tmp = ll.size();
            int max = Integer.MIN_VALUE;
            while (tmp-- > 0) {
                TreeNode now = ll.poll();
                if (now.val > max) max = now.val;
                if (now.left != null) ll.offer(now.left);
                if (now.right != null) ll.offer(now.right);
            }
            res.add(max);
        }
        return res;
    }

    /*public void flatten(TreeNode root) {
        flattenhelp(root);
    }




    public TreeNode flattenhelp(TreeNode root){
        if (root==null) return null;
        TreeNode ll = flattenhelp(root.left);
        TreeNode rr = flattenhelp(root.right);
        root.left = null;
        root.right = ll;
        TreeNode tmp = root;
        while (tmp.right!=null){
            tmp = tmp.right;
        }
        tmp.right = rr;
        return root;
    }*/

    public void flatten(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode pre = root;
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                if (pre != node) {
                    pre.left = null;
                    pre.right = node;
                    pre = node;
                }
            }
        }
    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head.next.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode tmp = sortList(slow.next);
        slow.next = null;
        return merge(sortList(head), tmp);
    }

    public ListNode merge(ListNode node1, ListNode node2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                pre.next = node1;
                node1 = node1.next;
            } else {
                pre.next = node2;
                node2 = node2.next;
            }
            pre = pre.next;
        }
        if (node1 != null) {
            pre.next = node1;
        }
        if (node2 != null) {
            pre.next = node1;
        }
        return head.next;
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        int count0 = 0, count1 = 0;
        for (int i = 1; i <= len; i++) {
            String s = strs[i - 1];
            count0 = 0;
            count1 = 0;
            for (int p = 0, tmplen = s.length(); p < tmplen; p++) {
                if (s.charAt(p) == '0') count0++;
                else count1++;
            }
            for (int tmpm = 0; tmpm <= m; tmpm++) {
                for (int tmpn = 0; tmpn <= n; tmpn++) {
                    if (count0 > tmpm || count1 > tmpn) dp[i][tmpm][tmpn] = dp[i - 1][tmpm][tmpn];
                    else
                        dp[i][tmpm][tmpn] = Math.max(dp[i - 1][tmpm][tmpn], dp[i - 1][tmpm - count0][tmpn - count1] + 1);
                }
            }
        }
        return dp[len][m][n];
    }

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        ArrayList<int[]> res = new ArrayList<>();
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 == 0 || len2 == 0) return res;
        PriorityQueue<int[]> queue = new PriorityQueue<>(len1 * len2, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] + o1[1]) - (o2[0] + o2[1]);
            }
        });

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                queue.add(new int[]{nums1[i], nums2[j]});
            }
        }
        k = k > len1 * len2 ? len1 * len2 : k;
        while (k-- > 0) {
            res.add(queue.poll());
        }
        return res;
    }

    public String predictPartyVictory(String senate) {
        String union1 = "Radiant", union2 = "Dire";
        LinkedList<Integer> rll = new LinkedList<>();
        LinkedList<Integer> dll = new LinkedList<>();
        int len = senate.length();
        char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            arr[i] = senate.charAt(i);
            if (arr[i] == 'R') {
                rll.add(i);
            } else {
                dll.add(i);
            }
        }
        while (!rll.isEmpty() && !dll.isEmpty()) {
            int rindex = rll.pollFirst();
            int dindex = dll.pollFirst();
            if (rindex < dindex) rll.addLast(rindex + len);
            else dll.addLast(dindex + len);
        }
        return rll.isEmpty() ? union2 : union1;
    }

  /*  public int rand10() {
        int a = rand7();
        int b = rand7();
        if (a > 4 && b <4) return rand10();
        else return (a+b)%10 + 1;
    }*/

    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    //477
    /*
    public int totalHammingDistance(int[] nums) {
        int len = nums.length;
        int result = 0;
        HashMap<String,Integer> hm = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i+1;j < len;j++){
                if (nums[i] >= nums[j] ){
                    if( hm.containsKey( String.valueOf(nums[j]).concat(String.valueOf(nums[i])) ) ){
                        result += hm.get( String.valueOf(nums[j]).concat(String.valueOf(nums[i])) );
                    }else {
                        int tmp = HammingDistance(nums[j],nums[i]);
                        hm.put(String.valueOf(nums[j]).concat(String.valueOf(nums[i])),tmp);
                        result += tmp;
                    }
                }else {
                    if (hm.containsKey(String.valueOf(nums[i]).concat(String.valueOf(nums[j])))){
                        result += hm.get(String.valueOf(nums[i]).concat(String.valueOf(nums[j])));
                    }else {
                        int tmp = HammingDistance(nums[i],nums[j]);
                        hm.put(String.valueOf(nums[i]).concat(String.valueOf(nums[j])),tmp);
                        result += tmp;
                    }
                }
            }
        }
        return result;
    }
    */

    public int totalHammingDistance(int[] nums) {
        int result = 0, len = nums.length;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < len; j++) {
                if ((nums[j] >> i & 1) == 1) {
                    count++;
                }
            }
            result += count * (len - count);
        }
        return result;
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[]{};
        int row = matrix.length, col = matrix[0].length;
        int i = 0, j = 0;
        int index = 0;
        int order = 1;//1向上 -1向下
        int[] result = new int[row * col];
        while (i != row && j != col) {
            result[index++] = matrix[i][j];
            if (order == 1) {
                if (i == 0 || j == col - 1) {
                    order *= (-1);
                    //需要改变方向为-1
                    if (j == col - 1) {
                        i++;
                    } else {
                        j++;
                    }
                } else {
                    i--;
                    j++;
                }
            } else {
                if (j == 0 || i == row - 1) {
                    order *= (-1);
                    if (i == row - 1) {
                        j++;
                    } else {
                        i++;
                    }
                } else {
                    i++;
                    j--;
                }
            }
        }
        result[row * col - 1] = matrix[row - 1][col - 1];
        return result;
    }


    public int HammingDistance(int a, int b) {
        int res = a ^ b;
        int count = 0;
        while (res != 0) {
            count++;
            res = res & (res - 1);
        }
        return count;
    }

    public int rangeBitwiseAnd(int m, int n) {
        return n > m ? rangeBitwiseAnd(m >> 1, n >> 1) << 1 : m;
    }

    public int flipgame(int[] fronts, int[] backs) {
        int len = fronts.length;
        HashSet<Integer> hs = new HashSet<>(len);
        for (int i = 0; i < len; i++) {
            if (fronts[i] == backs[i]) {
                hs.add(fronts[i]);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (!hs.contains(fronts[i]) && fronts[i] < min) {
                min = fronts[i];
            }
        }
        for (int i = 0; i < len; i++) {
            if (!hs.contains(backs[i]) && backs[i] < min) {
                min = backs[i];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        if (len == 0) return 0;
        int max = Integer.MIN_VALUE;
        Arrays.fill(dp, 1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Integer.max(dp[j] + 1, dp[i]);
                    if (dp[i] > max) max = dp[i];
                }
            }
        }
        if (max == Integer.MIN_VALUE) max = 1;
        return max;
    }

    public int findLUSlength(String a, String b) {
        if (a.equals(b)) return -1;
        return Math.max(a.length(), b.length());
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix.length==0) return 0;
        int max = Integer.MIN_VALUE;
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0;i < col;i++){
            if (matrix[0][i] == '0') dp[0][i] = 0;
            else dp[0][i] = 1;
            max = Math.max(max,dp[0][i]);
        }
        for (int i = 0;i < row;i++){
            if (matrix[i][0] == '0') dp[i][0] = 0;
            else dp[i][0] = 1;
            max = Math.max(max,dp[i][0]);
        }
        //System.out.println(m);
        for (int i = 1;i < row;i++){
            for (int j = 1;j < col;j++){
               if (matrix[i-1][j-1] == '1') {
                   dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1])) + 1;
                   max = Math.max(dp[i][j],max);
               }
            }

        }
        return max*max;
    }

    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int[] sums = new int[len];
        int index = 0;
        int max = 0;
        sums[0] = nums[0];
        for (int i = 1;i < len;i++){
            sums[i] = sums[i-1] + nums[i];
        }
        for (int i = 0;i < len;i++)
        {
            if(sums[i] >= s) {
                index = i;
                max = i + 1;
                break;
            }
        }
        for (int i = 1; i < len;i++){
            if (index >= len) break;
            for (int j = index;j < len;j++){
                if (sums[j] - sums[i] >= s) {
                    index = j;
                    max = Math.min(j-i,max);
                    break;
                }
            }
        }
        return max;
    }


    public int maxProduct(String[] words) {
        int len = words.length;
        int[] records = new int[len];
        int max = 0;
        for (int i = 0;i < len;i++){
            for (char c : words[i].toCharArray()){
                records[i] |= 1 << (c - 'a');
            }
        }
        for (int i = 0;i < len;i++){
            for (int j = 0;j < len;j++){
                if ( (records[i] & records[j] ) == 0){
                    max = Math.max(words[i].length()*words[j].length(),max);
                }
            }
        }
        return max;
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int[] times = new int[len];
        Arrays.fill(times,0);
        int[] result = new int[n];
        result[0] = 1;
        for (int i = 1;i < n;i++)
        {
            int min = Integer.MAX_VALUE;
            int minindex = 0;
            for (int j = 0;j < len;j++){
                int tmp = primes[j] * result[times[j]];
                if (tmp < min) {
                    min = tmp;
                    minindex = j;
                }
            }
            if (result[i-1] != min) result[i] = min;
            else i--;
            times[minindex]++;
        }
        return result[n-1];
    }

   /* public List<List<Integer>> subsetsWithDup(int[] nums) {
        int len = nums.length;
        HashSet<List<Integer>> lastres = new HashSet<>();
        ArrayList<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        lastres = subsetsWithDuphelp(nums,0,nums.length-1);
        lastres.add(new ArrayList<>(0));
        if (len == 0) return res;
        for (List<Integer> l : lastres)  res.add(l);
        return  res;
    }

    public HashSet<List<Integer>> subsetsWithDuphelp(int[] nums,int i,int j){
        HashSet<List<Integer>> res = new HashSet<>();
        if (i==j){
            res.add(new ArrayList<Integer>(){{add(nums[i]);}});
            return res;
        }
        HashSet<List<Integer>> lastres = subsetsWithDuphelp(nums,i+1,j);
        for (List<Integer> l : lastres){
            res.add(new ArrayList<>(l));
            l.add(nums[i]);
            res.add(new ArrayList<>(l));
            res.add(new ArrayList<Integer>(1){{add(nums[i]);}});
        }
        return res;
    }*/

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int len = nums.length;
        ArrayList<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        Arrays.sort(nums);
        subsetshelp(res,tmp,nums,0);
        return  res;
    }

    public void subsetshelp(List<List<Integer>> res,List<Integer> tmp,int[] nums,int index){
            res.add(new ArrayList<>(tmp));
        for (int i = index;i < nums.length;i++){
            if (i > index && nums[i] == nums[i-1]) continue;
            tmp.add(nums[i]);
            subsetshelp(res,tmp,nums,i+1);
            tmp.remove(tmp.size()-1);
        }
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len1 = pushed.length,len2 = popped.length;
        if (len1!=len2) return false;
        Stack<Integer> stack = new Stack<>();
        int index1 = 0,index2 = 0;
        while (index2<len2){
            if (stack.isEmpty() || stack.peek()!=popped[index2]) {
                if (index1>=len1) break;
                stack.push(pushed[index1++]);
                continue;
            }
            if (stack.size()!=len2-index2) return false;
            if (stack.peek()==popped[index2]){
                stack.pop();
                index2++;
            }
        }
        return stack.isEmpty();
    }


  /*  public List<Integer> lexicalOrder(int n) {
        TreeSet<Integer> ts = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) return 0;
                String s1 = String.valueOf(o1),s2 = String.valueOf(o2);
                int len1 = s1.length(),len2 = s2.length();
                int len = Math.min(len1,len2);
                for (int i = 0;i < len; i++) {
                    if (s1.charAt(i) > s2.charAt(i)) return 1;
                    if (s1.charAt(i) < s2.charAt(i)) return -1;
                }
                return len1 > len2 ? o2 : o1;
            }
        });

        for (int i = 1; i <= n; i++) {
            ts.add(i);
        }

        ArrayList<Integer> al = new ArrayList<>();
        while (!ts.isEmpty()){
            al.add(ts.pollFirst());
        }
        return al;
    }*/

    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> res = new ArrayList<>(n+1);
        for (int i = 1;i <= 9 && i <= n;i++){
            lexicalOrderhelp(res,n,i);
        }
        return res;
    }

    private void lexicalOrderhelp(List<Integer> res,int n,int i){
        if (i <= n) res.add(i);
        for (int j = 0;j <= 9;j++ )
        {
            if (i*10+j <= n) lexicalOrderhelp(res,n,i *10 + j);
            else break;
        }
    }

    class Solution {

        public int[] nums;
        public Solution(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            ArrayList<Integer> al = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (num == target) al.add(i);
            }
            return al.get(new Random().nextInt(al.size()));
        }
    }

    /*public int findCircleNum(int[][] M) {
        int len = M.length;
        int[] position = new int[len];
        Arrays.fill(position,-1);
        ArrayList<HashSet<Integer>> al = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0;j < len;j++){
                if (M[i][j] == 1){
                    if (position[i]!=-1 && position[j] == -1) {
                        al.get(position[i]).add(j);
                        position[j] = position[i];
                    }
                    else if (position[i] == -1 && position[j] != -1) {
                        al.get(position[j]).add(i);
                        position[i] = position[j];
                    }
                    else if (position[i] == -1 && position[j] == -1) {
                        HashSet<Integer> hs = new HashSet<>();
                        hs.add(i); hs.add(j);
                        al.add(hs);
                        position[i] = al.size()-1;
                        position[j] = position[i];
                    } else {
                        if (position[i] != position[j]) {
                            for (Integer n : al.get(position[j])) {
                                al.get(position[i]).add(n);
                                position[n] = position[i];
                            }
                            al.set(position[j],null);
                        }
                    }
                }
            }
        }
    }*/

/*    public int findCircleNum(int[][] M) {
        int len = M.length;
        LinkedList<HashSet<Integer>> ll = new LinkedList<>();
        HashMap<Integer,HashSet<Integer>> hm = new HashMap<>(len+1);
        for (int i = 0; i < len; i++) {
            for (int j = 0;j < len;j++){
                if (M[i][j] == 1){
                    if (!hm.containsKey(i) && hm.containsKey(j)) {
                        hm.get(j).add(i);
                        hm.put(i,hm.get(j));
                    }else  if (hm.containsKey(i) && !hm.containsKey(j)) {
                        hm.get(i).add(j);
                        hm.put(j,hm.get(i));
                    }else if (!hm.containsKey(i) && !hm.containsKey(j)) {
                        HashSet<Integer> hs = new HashSet<>();
                        hs.add(i);hs.add(j);
                        ll.add(hs);
                        hm.put(i,hs);
                        hm.put(j,hs);
                    }else {
                        if (hm.get(i) != hm.get(j)) {
                            HashSet<Integer> tmp = hm.get(j);
                            ll.remove(hm.get(j));
                            for (int n : tmp){
                                hm.get(i).add(n);
                                hm.put(n,hm.get(i));
                            }
                        }
                    }
                }
            }
        }
        return ll.size();
    }*/

    public int findCircleNum(int[][] M) {
        int len = M.length;
        int[] visit = new int[len];
        int count = 0;
        for (int i = 0;i < len;i++){
            if (visit[i] == 0) {
                count++;
                findCircleNumhelp(i,len,M,visit);
            }
        }
        return count;
    }

    private void findCircleNumhelp(int n,int len,int[][] M,int[] visit){
        visit[n] = 1;
        for (int i = 0;i < len;i++) {
            if (M[n][i] == 1 && visit[i]==0){
                findCircleNumhelp(i,len,M,visit);
            }
        }
    }


    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode tmp = root,pre;
        ListNode[] result = new ListNode[k];
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        if (k >= len) {
            tmp = root;
            pre = root;
            for (int i = 0;i < k;i++) {
                result[i] = tmp;
                if (tmp != null) {
                    pre = tmp;
                    tmp = tmp.next;
                    pre.next = null;
                }
            }
            return result;
        }
        int group1 = len / k,group2 = group1+1;
        int m = 0;
        while (true) {
            if (m * group2 + (k-m) * group1 == len) break;
            m++;
        }
        tmp = root;pre = root;
        for (int i = 0;i < m;i++) {
            result[i] = tmp;
            for (int j = 0;j < group2;j++) {
                pre = tmp;
                tmp = tmp.next;
            }
            pre.next = null;
        }
        for (int i = m;i < k;i++) {
            result[i] = tmp;
            for (int j = 0;j < group1;j++) {
                pre = tmp;
                tmp = tmp.next;
            }
            pre.next = null;
        }
        return result;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int n : A)
            for (int m : B)
                hm.put(m+n,hm.getOrDefault(m+n,0)+1);
        for (int m : C)
            for (int n : D)
                if (hm.containsKey(0-m-n)) count += hm.get(0-m-n);
        return count;
    }


    public int deleteAndEarn(int[] nums) {
        int len = nums.length;
        int max = 0;
        for (int n : nums){
            if (n > max) max = n;
        }
        int[] arr = new int[max+1];
        for (int n : nums) {
            arr[n] += n;
        }
        int[] dp = new int[max+1];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0],arr[1]);
        for (int i = 2; i <= max;i++){
            dp[i] = Math.max(dp[i-2]+arr[i],dp[i-1]);
        }
        return dp[max];
    }


    public int scoreOfParentheses(String S) {
        char[] arr = S.toCharArray();
        char pre = '1';
        Stack<Character> ope = new Stack<>();
        Stack<Integer> value = new Stack<>();
        for (int i = 0,len = arr.length; i < len; i++) {
            char c = arr[i];
            if (pre == ')' && c=='(' ) ope.push('+');
            else if (pre == ')' && c == ')')
                while (!ope.isEmpty()) {
                    char p = ope.pop();
                    if (p == '+') {
                        value.push(value.pop() + value.pop());
                    }else {
                        value.push(value.pop() * 2);
                        break;
                    }
                }
            else if (pre == '(' && c == '(') ope.push('*');
            else  value.push(1);
            pre = c;
        }
        while (!ope.isEmpty()) {
            char c = ope.pop();
            if (c == '+') value.push(value.pop() + value.pop());
            else value.push(value.pop() * 2);

        }
        return value.pop();
    }

    public int numSquares(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
       int[] dp = new int[n+1];
       dp[1] = 1;
       dp[2] = 2;
       for (int i =  3; i <= n;i++) {
           int min = Integer.MAX_VALUE;
           for (int j = 1; j * j<= i;j++) {
               if ( j *j == i) {min  = 1;break;}
               min = Math.min(dp[i - j*j]+dp[j*j],min);
           }
           dp[i] = min;
       }
       return dp[n];
    }


    public int numRabbits(int[] answers) {
      int result = 0;
      HashMap<Integer,Integer> hm = new HashMap<>();
      for (int n : answers) {
          if ( n == 0) {
              result++ ;
              continue;
          }
          hm.put(n,hm.getOrDefault(n,0)+1);
          if (hm.get(n) % (n+1) == 1) {
              result += (n+1);
          }
      }
        return result;
    }

    public int numComponents(ListNode head, int[] G) {
        HashSet<Integer> hs = new HashSet<>();
        for (int n : G) hs.add(n);
        ListNode p = head;
        Boolean isbroken = true;
        int result  = 0;
        while (p != null) {
            if (hs.contains(p.val) && isbroken) {
                result++;
                isbroken = false;
            }
            if (!hs.contains(p.val)) isbroken = true;
            p = p.next;
        }
        return result;
    }

    public int openLock(String[] deadends, String target) {
        int deadlen = deadends.length;
        HashSet<String> deadset = new HashSet<>(deadlen);
        HashSet<String> visited = new HashSet<>();
        LinkedList<Map.Entry<Integer,String>> ll = new LinkedList<>();
        int result = Integer.MAX_VALUE;
        ll.add(new java.util.AbstractMap.SimpleEntry<Integer, String>(0,"0000"));
        for (int i = 0; i < deadlen; i++) {
            deadset.add(deadends[i]);
        }
        if (deadset.contains("0000")) return -1;
        while (!ll.isEmpty()) {
            Map.Entry<Integer,String> tmp = ll.pollFirst();
            if (tmp.getValue().equals(target) && tmp.getKey() < result) result  = tmp.getKey();
            if (tmp.getKey() > result) continue;
            StringBuilder sb = new StringBuilder(tmp.getValue());
            for (int i = 0;i < 4;i++) {
                sb.setCharAt(i,openLockhelp(sb.charAt(i),'+'));
                String s1 = sb.toString();
                if (!deadset.contains(s1) && !visited.contains(s1))
                {
                    ll.add(new java.util.AbstractMap.SimpleEntry<Integer, String>(tmp.getKey()+1,s1));
                    visited.add(s1);
                }
                sb.setCharAt(i,openLockhelp(sb.charAt(i),'-'));
                sb.setCharAt(i,openLockhelp(sb.charAt(i),'-'));
                s1 = sb.toString();
                if (!deadset.contains(s1) && !visited.contains(s1))
                {
                    ll.add(new java.util.AbstractMap.SimpleEntry<Integer, String>(tmp.getKey()+1,s1));
                    visited.add(s1);
                }
                sb.setCharAt(i,openLockhelp(sb.charAt(i),'+'));
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private char openLockhelp(char  c,char ope) {
        Character res = c;
        if (ope == '+') {
            return c == '9' ? '0' : (char)(c+1);
        }else{
            return c == '0' ? '9' : (char)(c-1);
        }
    }

    public int dd(int[] arr){
        int result = 0;
        for (int i = 0; i < 32;i++) {
            int count = 0,tmp = 1 <<  i;
            for (int n : arr)
                if ((tmp & n) != 0) count++;
            if (count % 3 !=0) result |= tmp;
        }
        return result;
    }

    //653question
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        HashSet<Integer> hs = new HashSet<>();
        findTarget(root,hs);
        for (int n : hs) {
            if (k-n != n && hs.contains(k-n)) return true;
        }
        return false;
    }

    public void findTarget(TreeNode t,HashSet<Integer> hs) {
        if (t != null) {
            hs.add(t.val);
            findTarget(t.left, hs);
            findTarget(t.right, hs);
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int result = 0;
        return sumOfLeftLeaves(root,result,false);
    }

    private int sumOfLeftLeaves(TreeNode node,int result,boolean flag){
        if (flag && node.left == null && node.right == null) result += node.val;
        result += ( (node.left != null ? sumOfLeftLeaves(node.left,result,true) : 0)
                + (node.right != null ? sumOfLeftLeaves(node.right,result,false) : 0));
        return result;
    }

    public int[] twoSum(int[] numbers, int target) {
        int left = 0,right = numbers.length-1;
        while (left <= right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[]{left+1,right+1};
            }else if (numbers[left] + numbers[right] < target) left++;
            else right--;
        }
        return new int[]{};
    }


    //在有序数组arr中，二分查找，返回找到的下表，找不到返回-1
    public int dichotomyfind(int[] arr,int left,int right,int target){
        if (target < arr[left] || target > arr[right]) return -1;
        while (left <= right){
            if (target == arr[left]) return left;
            if (target == arr[right]) return right;
            int mid = (left+right)>>1;
            if (arr[mid] == target) return mid;
             if (target > arr[mid])  left = mid+1;
             if (target < arr[mid])  right = mid-1;
        }
        return -1;
    }

    //896
    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if (len == 1 || len == 2)  return true;
        int di = 0,ns = 2;
        for (int i = 1;i < len;i++) {
            if (A[i] != A[0]) {
                di = A[i]-A[0];
                ns = i;
                break;
            }
        }
        if (di == 0) return true;
        for (int i = ns+1; i < len; i++) {
            if ( ((A[i] - A[i-1]) * di) < 0) {
                return false;
            }
        }
        return true;
    }


    public boolean rotateString(String A, String B) {
        if (A.equals(B)) return true;
        int len1 = A.length(),len2 = B.length();
        if (len1 != len2)  return  false;
        A = A + A;
        return A.contains(B);
    }

    public int numPairsDivisibleBy60(int[] time) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        ArrayList<Integer> al = new ArrayList<>();
        int result = 0;
        for (int n : time) {
            if (!hm.containsKey(n)) al.add(n);
            hm.put(n,hm.getOrDefault(n,0)+1);
        }
        for (Map.Entry<Integer,Integer> entry : hm.entrySet()){
            if ((entry.getKey()*2) % 60 == 0 && entry.getValue() >= 2) result += C(entry.getValue(),2);
        }
        for (int i = 0;i < al.size();i++) {
            for (int j = i+1;j < al.size();j++) {
                if ( (al.get(i)+ al.get(j) ) % 60 == 0 ) {
                    result += hm.get(al.get(i)) * hm.get(al.get(j));
                }
            }
        }
        return result;
    }

    public int C(int A,int B) {
        if (A < B) return 1;
        int zi = 1,mu = 1;
        for (int i = 1;i <= B;i++)
        {
            zi *= A--;
            mu *= i;
        }
        return zi/mu;
    }

    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        int row = grid.length;
        if (row == 0) return 0;
        int col = grid[0].length;
        for (int i = 0;i < row;i++) {
            for (int j = 0;j < col;j++) {
                if (grid[i][j] == 1) fresh++;
            }
        }
        int minutes = 0,times = 2;
        while (fresh > 0) {
            int tmp = fresh;
            for (int i = 0;i < row;i++) {
                for (int j = 0;j < col;j++) {
                    if (grid[i][j] == times) {
                        //up
                        if (i-1 >= 0 && grid[i-1][j] == 1) {
                            grid[i-1][j] = times+1;
                            fresh--;
                        }
                        //down
                        if (i+1 < row && grid[i+1][j] == 1) {
                            grid[i+1][j] = times+1;
                            fresh--;
                        }
                        //left
                        if (j-1 >=0 && grid[i][j-1] == 1) {
                            grid[i][j-1] = times+1;
                            fresh--;
                        }
                        //right
                        if (j+1 < col && grid[i][j+1] == 1){
                            grid[i][j+1] = times+1;
                            fresh--;
                        }
                    }
                }
            }
            times++;
            if (fresh == tmp) return -1;
            minutes++;
        }
        return minutes;
    }


    public static void main(String[] args) {
        many m = new many();
        System.out.println(m.numPairsDivisibleBy60(new int[]{60,120,120,180,70,70}));
    }

}
