package de.normalisiert.utils.graphs;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


/**
 * Testfile for elementary cycle search.
 *
 * @author Frank Meyer
 *
 */
public class TestCycles {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String fileName0 ="/Users/likunxin/Downloads/huaweiTest/test_data.txt";
		String fileName = "/Users/likunxin/Downloads/huawei-preliminary-data-master/test_data.txt";

		File file = new File(fileName0);
		Scanner sc = new Scanner(file);

		long time = System.currentTimeMillis();

		int nodeNum = 25700;
		String nodes[] = new String[nodeNum];
		boolean adjMatrix[][] = new boolean[nodeNum][nodeNum];

		for (int i = 0; i < nodeNum; i++) {
			nodes[i] = "Node " + i;
		}

		while(sc.hasNextLine()){
			String[] str = sc.nextLine().split(",");
			adjMatrix[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = true;
		}

//		String nodes[] = new String[10];
//		boolean adjMatrix[][] = new boolean[10][10];
//
//		for (int i = 0; i < 10; i++) {
//			nodes[i] = "Node " + i;
//		}
//
//        adjMatrix[0][1] = true;
//        adjMatrix[1][2] = true;
//        adjMatrix[2][0] = true; adjMatrix[2][6] = true;
//        adjMatrix[3][4] = true;
//        adjMatrix[4][5] = true; adjMatrix[4][6] = true;
//        adjMatrix[5][3] = true;
//        adjMatrix[6][7] = true;
//        adjMatrix[7][8] = true;
//        adjMatrix[8][6] = true;
//
//        adjMatrix[6][1] = true;

		ElementaryCyclesSearch ecs = new ElementaryCyclesSearch(adjMatrix, nodes);
		List cycles = ecs.getElementaryCycles();
		System.out.println(cycles.size()+"");

		int time1 =0 ;
		for (int i = 0; i < cycles.size(); i++) {
			List cycle = (List) cycles.get(i);
			if(cycle.size()>=3&&cycle.size()<=7){
				for (int j = 0;j < cycle.size(); j++) {
					String node = (String) cycle.get(j);
					if (j < cycle.size() - 1) {
						System.out.print(node + " -> ");
					} else {
						System.out.print(node);
					}
				}
				System.out.print("\n");
				System.out.println(++time1+"");
			}

		}
		System.out.println("程序运行时间 ：");
		System.out.print(System.currentTimeMillis()-time+"毫秒");
	}

}
