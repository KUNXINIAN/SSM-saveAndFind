package de.normalisiert.utils.graphs;

import java.io.*;
import java.util.*;

public class testFinished1{



     public static void DFS(HashMap<Integer, ArrayList<Integer>> gra, ArrayList<ArrayList<Integer>> res,
                            boolean[] visited, ArrayList<Integer> path, int node, int start,
                            HashMap<Integer, HashMap> lut){
         if(node < start || path.size() > 7) return; //起始点要最小
         if(!gra.containsKey(node)){
             return;
         }
         if (visited[node] ){
             if(node == start){
                 res.add(new ArrayList<>(path));
             }
             return;
         }
         visited[node] = true;
         path.add(node);

         //三层查找表
         if(path.size() == 6){
             HashMap map = lut.get(node);
             if(map.containsKey(start)){
                 HashSet<Integer> set = (HashSet) map.get(start);
                 for(Integer integer : set){
                     if(integer.equals(-1)) { //六环情况
                         res.add(new ArrayList<>(path));
                         continue;
                     }
                     if(visited[integer]) continue; //前六环中已经出现
                     ArrayList<Integer> arrTemp = new ArrayList<>(path);
                     arrTemp.add(integer);
                     res.add(new ArrayList<>(arrTemp));
                 }
             }
             path.remove(path.size() - 1);
             visited[node] = false;
             return;
         }

         ArrayList<Integer> tmp = gra.get(node);
         Iterator it = tmp.iterator();
         while(it.hasNext()){
             int temp = (int)it.next();
//             if(node == temp) continue;
             DFS(gra, res, visited, path, temp, start, lut);
         }
         path.remove(path.size() - 1);
         visited[node] = false;
     }

     public void startID(ArrayList arr){
         int min = (int)arr.get(0);
         int minIndex = 0;
         for(int i = 0; i < arr.size(); i++){
             int cur = (int)arr.get(i);
             if(min > cur){
                 min = cur;
                 minIndex = i;
             }
         }
//         ArrayList<Integer> tmp = new ArrayList<>(arr);
//         for(int i = 0; i < tmp.size(); i++){
//             arr.set(i, tmp.get((i + minIndex)%arr.size()));
//         }

         this.reverse(arr,0,minIndex-1);
         this.reverse(arr,minIndex,arr.size()-1);
         this.reverse(arr,0,arr.size()-1);

     }

     public void reverse(ArrayList<Integer> arr, int start, int end) {
        while (start < end) {
            Integer temp = arr.get(start);

            arr.set(start,arr.get(end));
            arr.set(end,temp);
            start++;
            end--;
        }
     }

     public static class ResComparator implements Comparator<ArrayList>{

         @Override
         public int compare(ArrayList o1, ArrayList o2) {
             if(o1.size() == o2.size()){
                 for (int i = 0; i < o1.size(); i++){
                     if(o1.get(i).equals(o2.get(i))){
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

    public static ArrayList removeDuplicate(ArrayList list){
        for(int i = 0; i < list.size() - 1; i++){
            while((i+1) < list.size() && list.get(i+1).equals(list.get(i))){
                list.remove(i+1);
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        testFinished1 obj1 = new testFinished1();

        long time = System.currentTimeMillis();
        long timeTemp = System.currentTimeMillis();

        //数据读入
        String fileName0 = "/Users/likunxin/Downloads/huaweiTest/test_data.txt";
        String fileName1 = "/Users/likunxin/Downloads/huawei-preliminary-data-master/test_data.txt";
//        String fileName = "E:\\Java\\Competition\\HuaWeiCodeCraft\\preliminary\\2020HuaweiCodecraft-TestData-master\\1004812\\test_data.txt";

        File file = new File(fileName1);

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

        System.out.print("数据读入耗时:");
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

        HashMap<Integer, ArrayList<Integer>> gra = new HashMap<>();
        boolean[] visited = new boolean[maxID];
        for (int i = 0; i < firstIDs.size(); i++) {
            visited[firstIDs.get(i)] = false;
            visited[secondIDs.get(i)] = false;
        }
        for(int i = 0; i < firstIDs.size(); i++){
            if(!gra.containsKey(firstIDs.get(i))) {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(secondIDs.get(i));
                gra.put(firstIDs.get(i), tmp);
            }else{
                gra.get(firstIDs.get(i)).add(secondIDs.get(i));
            }
        }

        System.out.print("创建有向图耗时:");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");
        timeTemp = System.currentTimeMillis();

        //建立三层查找表（用于代替环中6->7->1，从而节省时间）
        HashMap<Integer, HashMap> lut = new HashMap<>();
        for (Integer key : gra.keySet()) {
            ArrayList<Integer> arr3F = gra.get(key);
            for (Integer obj : arr3F) {
                ArrayList<Integer> arr2F = gra.get(obj);
                if(arr2F != null) {
                    for (Integer keyfor2F : arr2F) {
                        if(!lut.containsKey(key)) { //lut无key
                            HashMap<Integer, HashSet> lutSecondFloor = new HashMap<>();
                            if(!lutSecondFloor.containsKey(keyfor2F)){ //lutSecondFloor无key
                                HashSet<Integer> lutThirdFloor = new HashSet<>();
                                lutThirdFloor.add(obj);
                                lutSecondFloor.put(keyfor2F, lutThirdFloor);
                            }
                            if(!lutSecondFloor.containsKey(obj)){ //代替环中6->1，只有6层无7层时
                                HashSet<Integer> nullThirdFloor = new HashSet<>();
                                nullThirdFloor.add(-1); //第7层写入-1，之后判别
                                lutSecondFloor.put(obj, nullThirdFloor);
                            }
                            if(lutSecondFloor.containsKey(obj) && !lutSecondFloor.get(obj).contains(-1)){
                                HashSet lutThirdFloor = lutSecondFloor.get(obj);
                                lutThirdFloor.add(-1);
                            }
                            lut.put(key, lutSecondFloor);
                        }else { //lut有key
                            HashMap<Integer, HashSet> lutSecondFloor = lut.get(key);
                            if (!lutSecondFloor.containsKey(keyfor2F)) {
                                HashSet<Integer> lutThirdFloor = new HashSet<>();
                                lutThirdFloor.add(obj);
                                lutSecondFloor.put(keyfor2F, lutThirdFloor);
                            }
                            if(!lutSecondFloor.containsKey(obj)){ //代替环中6->1，无7层时
                                HashSet<Integer> nullThirdFloor = new HashSet<>();
                                nullThirdFloor.add(-1);
                                lutSecondFloor.put(obj, nullThirdFloor);
                            }
                            if(lutSecondFloor.containsKey(obj) && !lutSecondFloor.get(obj).contains(-1)){
                                HashSet lutThirdFloor = lutSecondFloor.get(obj);
                                lutThirdFloor.add(-1);
                            }
                            if(lutSecondFloor.containsKey(keyfor2F) && lutSecondFloor.containsKey(obj)) {
                                HashSet lutThirdFloor = lutSecondFloor.get(keyfor2F);
                                lutThirdFloor.add(obj);
                            }
                        }
                    }
                }else {
                    if(!lut.containsKey(key)) {
                        HashMap<Integer, HashSet> lutSecondFloor = new HashMap<>();
                        if (!lutSecondFloor.containsKey(obj)) { //代替环中6->1，无7层时
                            HashSet<Integer> nullThirdFloor = new HashSet<>();
                            nullThirdFloor.add(-1);
                            lutSecondFloor.put(obj, nullThirdFloor);
                        }
                        if (lutSecondFloor.containsKey(obj) && !lutSecondFloor.get(obj).contains(-1)) {
                            HashSet lutThirdFloor = lutSecondFloor.get(obj);
                            lutThirdFloor.add(-1);
                        }
                        lut.put(key, lutSecondFloor);
                    }else{
                        HashMap<Integer, HashSet> lutSecondFloor = lut.get(key);
                        if (!lutSecondFloor.containsKey(obj)) { //代替环中6->1，无7层时
                            HashSet<Integer> nullThirdFloor = new HashSet<>();
                            nullThirdFloor.add(-1);
                            lutSecondFloor.put(obj, nullThirdFloor);
                        }
                        if (lutSecondFloor.containsKey(obj) && !lutSecondFloor.get(obj).contains(-1)) {
                            HashSet lutThirdFloor = lutSecondFloor.get(obj);
                            lutThirdFloor.add(-1);
                        }
                    }
                }
            }
        }

        System.out.print("建立三层查找表耗时:");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");
        timeTemp = System.currentTimeMillis();

        //DFS找环
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        Iterator<Map.Entry<Integer, ArrayList<Integer>>> entries = gra.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, ArrayList<Integer>> entry = entries.next();
            DFS(gra, res, visited, path, entry.getKey(), entry.getKey(), lut);
        }

        System.out.print("DFS找环耗时:");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");
        timeTemp = System.currentTimeMillis();

        //限制长度
        for(int i = res.size() - 1; i >= 0; i--){
            if(res.get(i).size()<3 || res.get(i).size()>7)
                res.remove(i);
            else
                obj1.startID(res.get(i)); //ID最小在前
        }

        //排序
         Collections.sort(res, new ResComparator());

        //去重
        removeDuplicate(res);

        System.out.print("限长、排序、去重共耗时:");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");
        timeTemp = System.currentTimeMillis();

        //输出
        String outputFileName = "/Users/likunxin/Downloads/huaweiTest/output_data3.txt";
        BufferedWriter out=new BufferedWriter(new FileWriter(outputFileName));
        out.write(res.size() + "\n");
        out.flush();
        for (int i = 0; i < res.size(); i++){
            ArrayList cycle = res.get(i);
            for (int j = 0; j < cycle.size(); j++) {
                if (j < cycle.size() - 1) {
                    out.write(cycle.get(j) + ",");
                } else
                    out.write(cycle.get(j) + "");
            }
            out.newLine();
            out.flush();
        }
        out.close();

        //控制台输出
//        for (int i = 0; i < res.size(); i++) {
//            ArrayList cycle = res.get(i);
//            if(cycle.size()>=3&&cycle.size()<=7){
//                for (int j = 0;j < cycle.size(); j++) {
//                    Object node = cycle.get(j);
//                    if (j < cycle.size() - 1) {
//                        System.out.print(node + ",");
//                    } else {
//                        System.out.print(node);
//                    }
//                }
//                System.out.print("\n");
//            }
//        }

        System.out.print("数据输出耗时:");
        System.out.println(System.currentTimeMillis()-timeTemp+"(ms)");

        System.out.print("总运行时间:");
        System.out.println(System.currentTimeMillis()-time+"(ms)");
//        System.out.println(res.size() + "");
    }
}
