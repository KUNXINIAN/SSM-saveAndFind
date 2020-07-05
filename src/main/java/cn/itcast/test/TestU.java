package cn.itcast.test;

import java.util.*;

public class TestU {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String[] input = sc.nextLine().split(";");
        int[][] grid = new int[10][10];
        for(int i =0 ; i<input.length ; i++){
            int ii = input[i].split("->")[0].charAt(0)-'A';
            int jj = input[i].split("->")[1].charAt(0)-'A';
            grid[ii][jj] = 1;
        }
        List<List<Integer>> res = new ArrayList<>();
        int[] flags = new int[10];
        for(int i =0 ;i<10;i++){
            dfs(grid,i,res,new ArrayList<Integer>(),flags);
        }
        for(int i =0;i<res.get(0).size();i++){
            char t = (char)('A'+res.get(0).get(i));
            System.out.print(""+t);
        }
    }

    public static void dfs(int[][] grid,int temp,List<List<Integer>> res,List<Integer> tempList,int[] flags){
        if(res.size()!=0) return;
        if(flags[temp] == 1) res.add(new ArrayList<>(tempList.subList(tempList.indexOf(temp),tempList.size())));
        for(int i = 0;i<10;i++){
            if(grid[temp][i] == 0) continue;
            flags[temp] = 1;
            tempList.add(temp);
            dfs(grid,i,res,tempList,flags);
            flags[temp] = 0;
        }
    }
}
