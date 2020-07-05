package cn.itcast.test;

import java.util.Arrays;
import java.util.Scanner;

public class TestL {

    private static int[] nums;
    private static int res;
    private static int fa;
    private static int total;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str1 = sc.nextLine().split(" ");
        int n = Integer.valueOf(str1[0]);
        fa = Integer.valueOf(str1[1]);

        nums = new int[n];
        res = 0;


        String[] str2 = sc.nextLine().split(" ");
        for(int i = 0;i<n;i++){
            nums[i] = Integer.valueOf(str2[i]);
            total +=nums[i];
        }

        getNum();
        System.out.print(res+"");


    }

    public static void getNum(){
        int i =0;
        int len = nums.length;
        while(fa>=total){
            fa -= total;
            res += len;
        }
        while(i<len){
            if(fa>=nums[i]){
                fa -=nums[i];
                res++;
            }

            i++;

        }


    }

}
