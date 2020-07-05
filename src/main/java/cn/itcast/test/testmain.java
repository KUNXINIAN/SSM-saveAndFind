package cn.itcast.test;

import org.junit.jupiter.api.Test;

import java.util.*;

public class testmain {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        StringBuilder sb = new StringBuilder(sc.nextLine());
//        String sb2=sb.substring(1,sb.length());
//        String[] mysplit=sb2.split("] ");
//        String s = mysplit[1];
//        String[] wordsplit = mysplit[0].split(" ");
//
//        List<String> dict = new ArrayList<String>();
//        for(String word:wordsplit){
//            dict.add(word);
//        }
//        System.out.println(s);
//
//        System.out.println(ifBreak(s,dict));
//
//
//    }

    public static boolean ifBreak(String s,List<String> dict){

        HashSet<String> myDict = new HashSet(dict);
        boolean[] dp = new boolean[s.length()+1];

        dp[0] = true;
        for(int i = 1;i<=s.length();i++){
            for(int j = 0;j<i ; j++){
                if(dp[j] && myDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    @Test
    public  void hello(){
//        Arrays.sort()
        Thread t = new Thread(){

            public void run(){
                pong();
            }
        };
        t.start();
        System.out.println("ping");

    }

    static void pong(){
        System.out.println("pong");
    }

    @Test
    public void hashtest(){
        Map<Short, String> map = new HashMap<>();
        for(short i = 0; i <100; i++)
        {
            map.put(i, String.valueOf(i));
            map.remove(i-1);
        }
        System.out.println(map.size());

    }












    //sc用法：
//    Scanner sc = new Scanner(System.in);  //创建对象
//        while(sc.hasNext())   //当还有数据时
//                Integer.parseInt(sc.nextLine());  //读出数据转为int型
//    StringBuilder sb = new StringBuilder(sc.nextLine());    //读出数据转为字符串（nextLine返回String）
//
//    int N = scanner.nextInt();
//    int[] people = new int[N];
//		for (int i = 0; i < N; ++i)
//    people[i] = scanner.nextInt();


















}
