package cn.itcast.test;

import java.util.*;

/** 笔试第一题
 *  by likunxin
 */

public class TestP {
    private static int[] stanums;
    private static long res; //改成long型，防止溢出
    private static List<Integer> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.valueOf(sc.nextLine());
        String[] str = sc.nextLine().split(" ");
        stanums = new int[N];
        for(int i = 0;i<N;i++){
            stanums[i] = Integer.valueOf(str[i]);
        }
        list = new ArrayList<>();
        getMax();
        System.out.println(res+"");
        for(int i = 0;i<list.size();i++){
            System.out.print(list.get(i));
            if(i!=list.size()-1) System.out.print(" ");
        }

    }

    public static void getMax(){
        Arrays.sort(stanums);
        int mid = stanums.length/2;
        int i = 0,j=mid;
        while(j<=stanums.length-1){
            list.add(stanums[j++]);
            if(i<mid) list.add(stanums[i++]);
        }
        for(int k = 0;k<list.size()-1;k++){
            res +=Math.abs(list.get(k+1)-list.get(k));
        }
        res += Math.abs(list.get(0)-list.get(list.size()-1));
    }
}
