package de.normalisiert.utils.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class test2 {

//    private static int outCycle = 0;

     public static void DFS(HashMap<Integer, ArrayList<Integer>> gra, LinkedList res, int[] visited, ArrayList path, int node, HashSet<Integer> set){
//         System.out.println("现在起点是"+start);

         if(!gra.containsKey(node)){
             return;
         }
         //其他轮开启的已经遍历过node，跳过该点
//         if(visited[node] == -1){
//             return;
//         }

         if (visited[node]==1){
             if(set.contains(node)){
//                截取path中上一次node点位置到最后，得到新的环加入res
                 res.add(new ArrayList<>(path.subList(path.indexOf(node),path.size())));
//                 System.out.println("现在环数 ：" + outCycle++);
         }
             return;
         }

         //本轮开启遍历过设置为1
         visited[node] = 1;
         set.add(node);

         path.add(node);

         //it是node的邻点开头
         ArrayList<Integer> temp = gra.get(node);

         for(int i =0 ;i<temp.size();i++){

             DFS(gra, res, visited, path, (int)temp.get(i), set);

             //删掉已遍历过的有向边
             gra.get(node).remove((int)i);
         }
         path.remove(path.size() - 1);
         set.remove(node);
         //其他轮开启的已经遍历过设置为-1
         visited[node] = 0;

     }

     public static void startID(ArrayList arr){
         int min = (int)arr.get(0);
         int minIndex = 0;
         for(int i = 0; i < arr.size(); i++){
             int cur = (int)arr.get(i);
             if(min > cur){
                 min = cur;
                 minIndex = i;
             }
         }
         ArrayList<Integer> tmp = new ArrayList<>(arr);
         for(int i = 0; i < tmp.size(); i++){
             arr.set(i, tmp.get((i + minIndex)%arr.size()));
         }
     }

     public static class ResComparator implements Comparator<ArrayList>{

         @Override
         public int compare(ArrayList o1, ArrayList o2) {
             if(o1.size() == o2.size()){
                 for (int i = 0; i < o1.size(); i++){
                     if(o1.get(i) == o2.get(i)){
                         continue;
                     }
                     if((int)o1.get(i) < (int)o2.get(i)){
                         return -1;
                     }else return 1;
                 }
             }else if(o1.size() < o2.size()){
                  return -1;
             }else {
                 return 1;
             }
             return 0;
         }
     }

    public static LinkedList removeDuplicate(LinkedList list){
        for(int i = 0; i < list.size() - 1; i++){
            while((i+1) < list.size() && list.get(i+1).equals(list.get(i))){
                list.remove(i+1);
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {

        long time = System.currentTimeMillis();
        long timeTemp = System.currentTimeMillis();

        //数据读入
        String fileName0 = "/Users/likunxin/Downloads/huaweiTest/test_data.txt";
        String fileName1 = "/Users/likunxin/Downloads/huawei-preliminary-data-master/test_data.txt";

        File file = new File(fileName0);

        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);

        ArrayList<Integer> firstIDs = new ArrayList<>();
        ArrayList<Integer> secondIDs = new ArrayList<>();
        String s = "";
        while((s =bReader.readLine()) != null){
            String[] str = s.split(",");
            firstIDs.add(Integer.parseInt(str[0]));
            secondIDs.add(Integer.parseInt(str[1]));
        }

        System.out.print("数据读入耗时 :");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");
        timeTemp = System.currentTimeMillis();

        //创建有向图
        int maxID = 0;
        for (int i = 0; i < firstIDs.size(); i++) {
            if (i == 0 || maxID < firstIDs.get(i))
            {
                maxID = firstIDs.get(i);
            }
            if (maxID < secondIDs.get(i))
            {
                maxID = secondIDs.get(i);
            }
        }
        maxID += 1;

        //int[][] gra = new int[maxID][maxID];
        HashMap<Integer, ArrayList<Integer>> gra = new HashMap<>();
        int[] visited = new int[maxID];
        for (int i = 0; i < firstIDs.size(); i++) {
            visited[firstIDs.get(i)] = 0;
            visited[secondIDs.get(i)] = 0;
        }
        for(int i = 0; i < firstIDs.size(); i++){
//            gra[firstIDs.get(i)][secondIDs.get(i)] = 1;
            if(!gra.containsKey(firstIDs.get(i))) {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(secondIDs.get(i));
                gra.put(firstIDs.get(i), tmp);
            }else{
                gra.get(firstIDs.get(i)).add(secondIDs.get(i));
            }
        }

        System.out.print("创建有向图耗时 :");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");
        timeTemp = System.currentTimeMillis();

        //DFS找环
//        int times = 0;
        LinkedList<ArrayList<Integer>> res = new LinkedList<>();
        ArrayList<Integer> path = new ArrayList<>();
        Iterator<Map.Entry<Integer, ArrayList<Integer>>> entries = gra.entrySet().iterator();
        HashSet<Integer> set = new HashSet<>();
        while (entries.hasNext()) {
//            times++;
//            System.out.println("DFS轮次在 ："+times+"");
            Map.Entry<Integer, ArrayList<Integer>> entry = entries.next();
            DFS(gra, res, visited, path, entry.getKey(), set);
        }

        System.out.print("DFS找环耗时 :");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");
        timeTemp = System.currentTimeMillis();

//        限制长度
        for(int i = res.size() - 1; i >= 0; i--){
            if(res.get(i).size()<3 || res.get(i).size()>7)
                res.remove(i);
            else
                startID((ArrayList) res.get(i)); //ID最小在前
        }

        //排序
         Collections.sort(res, new ResComparator());

        //去重
        removeDuplicate(res);

        System.out.print("限长、排序、去重共耗时 :");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");
        timeTemp = System.currentTimeMillis();

        //输出
//        String outputFileName = "src/com/huawei/projects/student/result.txt";
//        BufferedWriter out=new BufferedWriter(new FileWriter(outputFileName));
//        out.write(res.size() + "\n");
//        out.flush();
//        for (int i = 0; i < res.size(); i++){
//            ArrayList cycle = res.get(i);
//            for (int j = 0; j < cycle.size(); j++) {
//                if (j < cycle.size() - 1) {
//                    out.write(cycle.get(j) + ",");
//                } else
//                    out.write(cycle.get(j) + "");
//            }
//            out.newLine();
//            out.flush();
//        }
//        out.close();

        int size = 0;
        //控制台输出
        for (int i = 0; i < res.size(); i++) {
            ArrayList cycle = res.get(i);
            if(cycle.size()>=3&&cycle.size()<=7){
                size++;
                for (int j = 0;j < cycle.size(); j++) {
                    Object node = cycle.get(j);
                    if (j < cycle.size() - 1) {
                        System.out.print(node + ",");
                    } else {
                        System.out.print(node);
                    }
                }
                System.out.print("\n");
            }
        }

        System.out.println("输出路径数量 : "+size);

        System.out.print("数据输出耗时 :");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");

        System.out.print("总运行时间:");
        System.out.println(System.currentTimeMillis()-time+"(ms)");
//        System.out.println(res.size() + "");
    }
}
