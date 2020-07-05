package cn.itcast.test;

import java.util.Scanner;

/** 笔试第二题
 *  by likunxin
 */
public class TestQ {
    private static int[][] staNums;
    private static int N;
    private static double res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.valueOf(sc.nextLine());
        staNums = new int[N][2];

        for(int j = 0;j<N;j++){
            String s = sc.nextLine();
            staNums[j][0] = Integer.valueOf(s.split(" ")[0]);
            staNums[j][1] = Integer.valueOf(s.split(" ")[1]);
        }
        getRes();
        System.out.println(res);
    }


    public static void getRes() {

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                res = Math.max(res, Math.min((double) (staNums[i][0] + staNums[j][0]) / 2, (double) (staNums[i][1] + staNums[j][1]) / 2));
            }
        }
    }
}
