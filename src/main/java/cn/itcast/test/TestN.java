package cn.itcast.test;

import java.util.Scanner;
public class TestN {
    private static String[] strList;
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        strList = new String[101];
        printAns();
        for(int i =1;i<=100;i++){
            if(strList[i]==null){
                System.out.println(i+"");
                continue;
            }
            System.out.println(strList[i]);
        }

    }

    public static void printAns(){
        for(int i = 1 ;i*15<=100;i++){
            strList[i*15] = "AB";
        }
        for(int i = 1 ;i*5<=100;i++){
            if(strList[i*5]==null) strList[i*5] = "B";
        }
        for(int i = 1 ;i*3<=100;i++){
            if(strList[i*3]==null) strList[i*3] = "A";
        }
    }
}
