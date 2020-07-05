package cn.itcast.test;

import java.util.*;

public class TestH {

    private static int salary = 0;
    private static int times = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        int len = Integer.valueOf(s1);
        int[] nums = new int[len];
        String s2 = sc.nextLine();
        String[] str2= s2.split(" ");
        for(int i = 0 ;i<str2.length;i++){
            nums[i] = Integer.valueOf(str2[i]);
        }
        getS(nums,0,0);
        int res  = (int)(salary/times);
        System.out.print(res+"");

    }

    public static void getS(int[] nums,int index,int preDays){
        StringBuilder sb = new StringBuilder();
        List<Integer> temp = new ArrayList<>();




        if(index == nums.length){
            return;
        }
        if(nums[index] == 0){
            getS(nums,index+1,0);
        }else if(nums[index]==1){
            salary += preDays+1;
            getS(nums,index+1,preDays+1);
        }else{
            salary = salary*2+preDays+1;
            times*=2;
            getS(nums,index+1,0);


            getS(nums,index+1,preDays+1);
        }
    }
}
