package cn.itcast.test;

import org.junit.jupiter.api.Test;

import java.util.*;

public class TestB {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.valueOf(sc.nextLine()),index = 0;
        HashMap<String,Integer> map = new HashMap<>();
        List<List<String>> userList = new ArrayList<>();


        for(int i = 0; i < N; i++){

            String str = sc.nextLine();
            String[] strList = str.split(" ");
            if(!map.containsKey(strList[1])){
                map.put(strList[1],index);
                List<String> temp = new ArrayList<>();
                temp.add(strList[0]);
                userList.add(temp);
                index++;
            }else{
                int j = map.get(strList[1]);
                userList.get(j).add(strList[0]);
            }
        }


        int t =0 ;
        for(String s:map.keySet()){
            Collections.sort(userList.get(t));
            System.out.print(s+" ");
            for(int i =0;i<userList.get(t).size();i++){
                System.out.print(userList.get(t).get(i));
                if(i!=userList.get(t).size()-1){
                    System.out.print(" ");
                }
            }
            System.out.println("");
            t++;

        }
    }


//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        String[] in1 = sc.nextLine().split(" ");
//        int[] in11=new int[2];
//        int k =0;
//        for(String s:in1){
//            in11[k] = Integer.valueOf(s);
//            k++;
//        }
//        int D = in11[0];
//        int W = in11[1];
//        System.out.print(D+" "+W);
//        List<List<Integer>> sta = new ArrayList<>();
//        for(int i =0;i<2;i++){
//            sta.add(new ArrayList<Integer>());
//            String[] in = sc.nextLine().split(" ");
//            for(String s:in){
//                sta.get(i).add(Integer.valueOf(s));
//            }
//
//        }
//        System.out.print(sta);
//        int col = sta.get(0).size();
//        int[][] stations = new int[2][col];
//        for(int i =0;i<2;i++){
//            for(int j =0;j<col;j++){
//                stations[i][j] = sta.get(i).get(j);
//            }
//        }
////        int[][] stations = sta.toArray(new int[2][col]);
//        System.out.print(minStops(D,W,stations));
//
//    }

    public static int minStops(int target, int start, int[][] stations){
        if(stations.length==0){
            return start>=target?0:-1;}
            PriorityQueue<Integer> queue = new PriorityQueue<Integer>((o1,o2)->{
                return o2-o1;
            });
            int sum = start;
            int ans = 0;
            for(int i = 0;i<stations.length;i++){
                while(sum<stations[i][0]){
                    Integer ii = queue.poll();
                    if(ii == null) return -1;
                    sum +=ii;
                    ans++;
                }
                queue.offer(stations[i][1]);
            }
            while(sum<target){
                Integer ii = queue.poll();
                if(ii == null) return -1;
                sum += ii;
                ans++;
            }
            return ans;
    }




}

