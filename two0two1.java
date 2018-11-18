import java.util.HashMap;
import java.util.Scanner;

public class two0two1 {
    public static void main(String[] args) {
        HashMap<Integer,String> hs = new HashMap<>();
        hs.put(0,"不符合规则,不能识别");
        hs.put(3,"不符合规则,不能识别");
        hs.put(5,"不符合规则,不能识别");
        hs.put(6,"不符合规则,不能识别");
        hs.put(1,"整数");
        hs.put(2,"浮点数");
        hs.put(4,"浮点数");
        Scanner sc = new Scanner(System.in);
        int[][] arr = {{1,6,3},{1,5,2},{2,5,6},{2,6,6},{4,6,6},{4,6,6}};
        while (true){
            String s = sc.next();
            int status = 0;
            int tmp = 0;
            for (int i = 0;i<s.length();i++){
                if (s.charAt(i)=='e') tmp = 1;
                else if (s.charAt(i)=='.') tmp = 2;
                else tmp = 0;
                status = arr[status][tmp];
                if (status==6) break;
            }
            System.out.println(s+ hs.get(status));
        }
    }
}
