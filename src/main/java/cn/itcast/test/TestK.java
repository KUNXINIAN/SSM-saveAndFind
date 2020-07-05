package cn.itcast.test;

import java.util.Arrays;
import java.util.Scanner;

public class TestK {

    private static int[] nums;//排序后
    private static int x;
    private static int res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str1 = sc.nextLine().split(" ");
        int n = Integer.valueOf(str1[0]);
        x = Integer.valueOf(str1[1]);
        nums = new int[n];
        res = 0;

//        System.out.print(nums.toString());

        String[] str2 = sc.nextLine().split(" ");
        for(int i = 0;i<n;i++){
            nums[i] = Integer.valueOf(str2[i]);
        }
        Arrays.sort(nums);
        getNum();
        System.out.print(res+"");


    }

    public static void getNum(){
        int i = 0,j=nums.length-1;
        while(i!=j && (nums[j]-nums[i])>x){

            int disj = nums[j]-nums[j-1];
            int disi = nums[i+1]-nums[i];
            if(disi>disj){
                i++;
                res++;
            }else{
                j--;
                res++;
            }
        }
        if(i == j) res = nums.length-1;
    }
}
