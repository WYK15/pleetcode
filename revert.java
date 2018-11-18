import java.lang.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


public class revert {

    public String intToRoman(int num) {

        String tmp = String.valueOf(num);
        String res = "";
        for (int i = 0; i < tmp.length(); i++) {
            res += cal((tmp.charAt(i) - '0'), tmp.length() - i - 1);
        }
        return res;
    }

    public String cal(int comein, int i) {
        String res = "";
        HashMap<Integer, String> hm = new HashMap<Integer, String>() {{
            put(1, "I");
            put(5, "V");
            put(10, "X");
            put(50, "L");
            put(100, "C");
            put(500, "D");
            put(1000, "M");
        }};
        if (hm.containsKey(comein * (int) (Math.pow(10, i))))
            return hm.get(comein * (int) (Math.pow(10, i))); //1 5 10 10 50 100...
        switch (comein) {
            case 4:
            case 9:
                res += hm.get((int) (Math.pow(10, i)));
                res += hm.get((comein > 5 ? 10 : 5) * (int) (Math.pow(10, i)));
                break;
            case 2:
            case 3:
            case 6:
            case 7:
            case 8:
                if (comein > 5) {
                    res += hm.get(5 * (int) (Math.pow(10, i)));
                    comein -= 5;
                }
                for (int j = 0; j < comein; j++) res += hm.get((int) (Math.pow(10, i)));
                break;
        }
        return res;
    }


    public int pro1() throws IOException {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        HashSet<ArrayList<Integer>> hs = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int j = 0; j < n; j++) {
            arr.add(sc.nextInt());
        }

        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (Math.abs(arr.get(i) - arr.get(j)) == k) {
                    tmp.clear();
                    tmp.add(Math.max(arr.get(i), arr.get(j)));
                    tmp.add(Math.min(arr.get(i), arr.get(j)));
                    hs.add(tmp);
                }
            }
        }

        return hs.size();
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String fstr = strs[0];
        String res = "";
        boolean isa = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fstr.length(); i++) {
            sb.append(fstr.charAt(i));
            isa = true;
            for (int j = 1; j < strs.length; j++) {
                if (sb.length() > strs[j].length()) return sb.substring(0, sb.length() - 1).toString();
                for (int p = 0, q = 0; p < strs[j].length() && q < sb.length(); p++, q++) {
                    if (strs[j].charAt(p) != sb.charAt(q)) {
                        isa = false;
                        break;
                    }
                }
            }
            if (isa) res = sb.toString();
            else return sb.substring(0, sb.length() - 1).toString();
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {

        List al = new ArrayList<ArrayList<Integer>>();
        int head,tail,tmpsum;
        HashSet<Integer> hava = new HashSet<Integer>();
        if(nums.length<3) return al;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            if (nums[i] > 0) break;
            if (hava.contains(nums[i]))      continue;
            hava.add(nums[i]);
            head = i+1;
            tail = nums.length-1;
            while (head<tail){
                tmpsum = nums[head]+nums[tail]+nums[i];
                if (tmpsum==0){
                    ArrayList<Integer> tmparr = new ArrayList();
                    tmparr.add(nums[head]);
                    tmparr.add(nums[tail]);
                    tmparr.add(nums[i]);
                    al.add(tmparr);
                    //去重
                    while (head<tail&&nums[head]==nums[head+1])
                        head++;
                    while (head<tail&&nums[tail]==nums[tail-1])
                        tail--;
                    head++;
                    tail--;
                }else if (tmpsum>0){
                    tail--;
                }else {
                    head++;
                }
            }
        }
        return al;
    }



    public int threeSumClosest(int[] nums, int target) {
        int min = nums[0]+nums[1]+nums[2];
        int tmphe = 0;
        Arrays.sort(nums);
         for (int i = 0;i<nums.length-2;i++){
             if (i>0&&nums[i]==nums[i-1]) continue;
             int head = i+1,end = nums.length-1;
             while (end>head){
                 tmphe = nums[i]+nums[end]+nums[head];
                 if (Math.abs(min-target)>Math.abs(tmphe-target))  min = tmphe;
                 if (tmphe==target) return target;
                 else if (tmphe>target){
                     while (end>head&&nums[end]==nums[end-1])
                        end--;
                     end--;
                 }else{
                     while (end>head&&nums[head]==nums[head+1])
                        head++;
                        head++;
                 }
             }
         }
         return min;
    }

    public static void main(String[] args) {
        int[] arr = {0,0,0};
        System.out.println(new revert().threeSumClosest(arr,1));

    }

    public List<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<>();
        HashMap<Integer,String> hs = new HashMap<>();
        hs.put(2,"abc");hs.put(3,"def");hs.put(4,"ghi");hs.put(5,"jkl");
        hs.put(6,"mno");hs.put(7,"pqrs");hs.put(8,"tuv");hs.put(9,"wxyz");
        if (digits.length()==0) return result;
        letterCombinations(result,hs,"",0,digits);
        return result;
    }

    public void letterCombinations(ArrayList<String> result,HashMap<Integer,String> hs
    ,String nowstring,int index,String digits){
        if (nowstring.length()==digits.length()) {
            result.add(nowstring);
            return;
        }

        String tmp = hs.get(digits.charAt(index)-'0');
        for (int i = 0;i< tmp.length();i++){
            nowstring+=tmp.charAt(i);
            letterCombinations(result,hs,nowstring,index+1,digits);
            nowstring=nowstring.substring(0,nowstring.length()-1);
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i=0;i<nums.length-3;i++){
            for (int j = i+1;j<nums.length-2;j++){
                int tmphe = nums[i]+nums[j];
                int head = j+1;
                int end = nums.length-1;
                while (end>head){
                    if (tmphe+nums[head]+nums[end]==target){
                        ArrayList<Integer> al = new ArrayList<>();
                        al.add(nums[i]);al.add(nums[j]);
                        al.add(nums[head]);al.add(nums[end]);
                        res.add(al);
                        while (end>head&&nums[end]==nums[end-1]) end--;
                        end--;
                        while (end>head&&nums[head]==nums[head+1]) head++;
                        head++;
                    }else if (tmphe+nums[head]+nums[end]>target){
                        end--;
                    }else{
                        head++;
                    }
                }
                while (j<nums.length-2&&nums[j]==nums[j+1]) j++;
            }
            while (i<nums.length-3&&nums[i]==nums[i+1]) i++;
        }
        return  res;
    }



}

