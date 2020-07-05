package cn.itcast.test;

import java.util.Scanner;

public class TestM {

    private static String str;
    private static int k;
    private static int res=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        k = Integer.valueOf(sc.nextLine());

        getNum();
        System.out.print(res+"");


    }

    public static void getNum(){
        int len = str.length();
        for(int i =0;i<len;i++){
            for(int j = i+k;j<len;j++){
//                if(len-1-j<j-i) break;
                if(str.substring(j+1,len).contains(str.substring(i,j))){

                    res++;
                }
            }
        }
    }
}
