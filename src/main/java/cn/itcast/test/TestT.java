package cn.itcast.test;

import java.util.*;

public class TestT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String splitter = sc.nextLine();
        List<String> container = new ArrayList<>();
        int nums = 1;
        while(sc.hasNextLine()){
            String temp = sc.nextLine();
            if(temp == null||temp.length() == 0) break;
            container.add(temp);
            if(temp.split(",")[1].equals(splitter)){
                nums++;
            }
        }
        int flag =0;
        System.out.println(""+nums);
        for(int i = 0;i<container.size();i++){
            if(container.get(i).split(",")[1].equals(splitter)){
                System.out.println("");
                flag = 0;
            }else{
                if(flag != 0) System.out.print("|");
                System.out.print(container.get(i));
                flag++;
            }
        }
    }
}
