package cn.itcast.test;

import java.util.*;

public class TestR {


        public static boolean getBoolean (List<List<Integer>> box) {
            HashMap<Integer,Integer> map = new HashMap<>();
            for(List<Integer> list:box){
                map.put(list.get(0),map.getOrDefault(list.get(0),0)+1);
                map.put(list.get(1),map.getOrDefault(list.get(1),0)+1);
            }
            for(Integer i :map.keySet()){
                if(map.get(i)!=4){
                    return false;
                }
            }

            return true;

        }
        /******************************结束写代码******************************/


        public static void main(String[] args){



            Scanner in = new Scanner(System.in);
            int N = Integer.valueOf(in.nextLine());

            boolean[] res = new boolean[N];
            List<List<List<Integer>>> boxAll = new ArrayList<>();
            int tem = N;
            while(tem-- >0){
                boxAll.add(new ArrayList<>());
                boxAll.get(tem).add(new ArrayList<>());
                boxAll.get(tem).add(new ArrayList<>());
                boxAll.get(tem).add(new ArrayList<>());
                boxAll.get(tem).add(new ArrayList<>());
                boxAll.get(tem).add(new ArrayList<>());
                boxAll.get(tem).add(new ArrayList<>());
            }
            int index =0 ,i=0;

            while(in.hasNextLine()){
                String[] str = in.nextLine().split(" ");

                boxAll.get(index/6).get(i).add(Integer.valueOf( str[0]));
                boxAll.get(index/6).get(i++).add(Integer.valueOf( str[1]));
                index++;
                if(i>=5) i =0;


            }
            for(int j = 0;j<N;i++){
                res[j] = getBoolean(boxAll.get(j));
                System.out.println(res[j]?"POSSIBLE":"IMPOSSIBLE");
            }

        }
}


