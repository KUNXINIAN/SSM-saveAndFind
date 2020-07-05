package cn.itcast.test;
import java.util.*;

public class TestE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s=sc.nextLine();
        String[] str = s.split(",");
        int[] values=new int[str.length];
        for(int i=0;i<values.length;i++){
            values[i] = Math.max(isBao(str[i]),isShun(str[i]));
        }
        StringBuilder ans = new StringBuilder();
        for(int i =12;i>=1;i--){
            for(int j =0;j<str.length;j++){
                if(i==values[j]){
                    ans.append(str[j]).append(",");
                }
            }
        }
        if(ans!=null&&ans.length()>0)  System.out.print(ans.substring(0,ans.length()-1));


    }

    public static int isBao(String s){
        //返回价值，否则为0
        int res=0;
        int max = 0;
        char last = s.charAt(3);
        for(int i=4;i<s.length();i++){
            if(s.charAt(i)==last){
                res++;
            }else{
                max=Math.max(res,max);
                res=1;
                last = s.charAt(i);
            }
        }
        if(Math.max(res,max)<3) return 0;
        return (Math.max(res,max)-2)*2;

    }

    public static  int isShun(String s){
        int res=0;
        int max = 0;
        char last = s.charAt(3);
        for(int i=4;i<s.length();i++){
            if((s.charAt(i)-last)==1){
                res++;
                last = s.charAt(i);
            }else{
                max=Math.max(res,max);
                res=1;
                last = s.charAt(i);
            }
        }
        if(Math.max(res,max)<3) return 0;
        return (Math.max(res,max)-2)*2-1;

    }
}
