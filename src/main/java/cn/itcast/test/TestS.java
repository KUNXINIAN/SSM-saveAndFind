package cn.itcast.test;

import java.util.*;

public class TestS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        int M = Integer.valueOf(s1.split(" ")[0]);
        int N = Integer.valueOf(s1.split(" ")[1]);
        int[][] grid = new int[N][2];
        int[] server = new int[M];
        String s2 = sc.nextLine();
        String[] s2sp = s2.split(" ");
        for(int i =0;i<M;i++){
            server[i] = Integer.valueOf(s2sp[i]);
        }
        for(int i =0;i<N;i++){
            String temp = sc.nextLine();
            grid[i][0] = Integer.valueOf(temp.split(" ")[0]);
            grid[i][1] = Integer.valueOf(temp.split(" ")[1]);
        }

        System.out.print(""+getRes(grid,server));

    }

    public static int getRes(int[][] grid,int[] server){
        int res = 0;
        Arrays.sort(server);
        Arrays.sort(grid,((o1, o2) -> o1[1]==o2[1]?o1[0]-o2[0]:o2[1]-o1[1]));
        int serverMax = server[server.length-1];

        for(int i = 0;i<grid.length;i++){
            if(grid[i][0]>serverMax) continue;
            for(int j = 0;j<server.length;j++){
                if(server[j] ==-1) continue;
                if(server[j] >=grid[i][0]){
                    res += grid[i][1];
                    server[j] = -1;
                }
            }
        }
        return res;
    }

}
