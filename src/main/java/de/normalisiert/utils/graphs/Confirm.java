package de.normalisiert.utils.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*该类用于测试处理结果和标准结果是否相同*/
public class Confirm {
    public static void main(String[] args) throws FileNotFoundException {
        //使用文件存放路径，standard是标准结果，result是自己的结果
        File file_standard = new File("/Users/likunxin/Downloads/huaweiTest/result.txt");
        File file_result = new File("/Users/likunxin/Downloads/huaweiTest/output_data.txt");
        Scanner scanner_st = new Scanner(file_standard);
        Scanner scanner_re = new Scanner(file_result);
        //首先确认总环数是否正确
        int N_st = scanner_st.nextInt();
        int N_re = scanner_re.nextInt();
        if (N_re!=N_st){
            System.out.println("搜索结果环数错误！标准："+N_st+",现有："+N_re);
            return;
        }
        //环数正确的情况下进行比对
        int cnt = 1;
        boolean flag = true;
        while (scanner_st.hasNextLine()){
            String[] vertex_st = scanner_st.nextLine().split(",");
            String[] vertex_re = scanner_re.nextLine().split(",");
            for (int i = 0; i < vertex_st.length; i++) {
                if (!vertex_re[i].equals(vertex_st[i])){
                    System.out.println("第"+cnt+"个环结点不匹配");
                    System.out.println("标准结果："+ Arrays.asList(vertex_st));
                    System.out.println("计算结果："+ Arrays.asList(vertex_re));
                    flag = false;
                    continue;
                }
            }
            cnt++;
        }

        if (flag) System.out.println("完全匹配！");

        scanner_re.close();
        scanner_st.close();


    }
}
