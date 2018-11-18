/**
 * @(#)pol.java
 *
 *
 * @author
 * @version 1.00 2018/4/12
 */
import java.io.*;
import java.util.*;

public class pol {

    /**
     * Creates a new instance of <code>pol</code>.
     */
    public pol() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        int count = 0;
        BufferedReader buf = new BufferedReader(new FileReader("D:\\wenfa.txt"));
        String s = "";
        char startchar = 'S';
        HashSet<Character> endchar = new HashSet<Character>();
        HashSet<Character> notendchar = new HashSet<Character>();
        HashMap<String,HashSet> hm = new HashMap<String,HashSet>();
        int status = 3;
        ArrayList<Integer> al = new ArrayList();
        while( (s=buf.readLine())!=null){
            if(count==0){
                startchar = s.charAt(0);
            }
            for(int i = 0;i<s.length();i++){
                if(Character.isAlphabetic(s.charAt(i))){
                    if(Character.isLowerCase(s.charAt(i)) ){
                        endchar.add(s.charAt(i));
                    }
                    else{
                        notendchar.add(s.charAt(i));
                    }
                }
                String[] arr = s.split(":");
                //hashmap
                if(!hm.containsKey(arr[0])){
                    hm.put(arr[0],new HashSet(){{add(arr[1]);}});
                }else{
                    HashSet<String> tmp = hm.get(arr[0]);
                    tmp.add(arr[1]);
                    hm.put(arr[0],tmp);
                }

                if(arr[1].length()<arr[0].length()){
                    status = 0;
                }
                for(int j = 0;j<arr[0].length();j++){
                    if(Character.isLowerCase(arr[0].charAt(j))){
                        if (status>1) status = 1;
                    }
                }

                for(int j = 0;j<arr[1].length();j++){
                    if(!Character.isLowerCase(arr[1].charAt(0))){
                       if (status>2) status = 2;
                    }
                }


            }
            count++;
        }

        System.out.println("属于"+status+"型文法");
        System.out.println("起始符为 "+startchar);
        System.out.println(endchar);
        System.out.println(notendchar);
        System.out.println("生成式为:");


        for (Map.Entry<String,HashSet> entry:hm.entrySet()){
            String tmp = "";
            System.out.print(entry.getKey()+"->");
            Iterator<String> iter = entry.getValue().iterator();
            while (iter.hasNext()){
                tmp+=iter.next()+"|";
            }
            System.out.println(tmp.substring(0,tmp.length()-1));
        }
    }
}
