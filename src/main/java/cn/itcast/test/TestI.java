package cn.itcast.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestI {

    private static int[] arrays;

    public static void main(String[] args) {
//        int[][] dp = new int[][]{{1,2},{2,3}};
        int[] nums = new int[]{1,2,3};
        System.out.print(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        arrays = nums;
        if(nums == null||nums.length==0) return res;
        boolean[] flags = new boolean[arrays.length];
        Arrays.fill(flags,false);
        List<Integer> temp = new ArrayList<>();

        recur(flags,temp,0,res);
        return res;

    }

    public static void recur(boolean[] flags,List<Integer> temp,int times,List<List<Integer>> res){
        if(times == arrays.length){
            System.out.println(temp.toString());
            System.out.println("====");



            List<Integer> temp2 = new ArrayList<>();
            for(int i = 0;i<temp.size();i++){
                temp2.add(temp.get(i));
            }

            res.add(temp2);


            System.out.println(res.toString());
            System.out.println("====");

            return;
        }

        for(int i = 0 ;i<arrays.length;i++){
            if(!flags[i]){
                flags[i] = true;
                temp.add(arrays[i]);
                recur(flags,temp,times+1,res);
                flags[i] = false;

                temp.remove((temp.size()-1));


            }
        }

    }
}

