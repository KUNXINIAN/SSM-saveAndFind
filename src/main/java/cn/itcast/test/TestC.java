package cn.itcast.test;

import java.util.Scanner;
import java.util.*;
public class TestC {
    private static int[] buildings;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int N = Integer.parseInt(s1);
        String[] nums = s2.split(" ");
        buildings = new int[N];
        for(int i =0 ;i<N;i++){
            buildings[i] = Integer.parseInt(nums[i]);
        }
//        System.out.print(buildings.toString()+"//");
        for(int i = 0 ;i<N;i++){
            int temp = getNum(i);
//            System.out.print(i+"//");
            System.out.print(temp+"");
            if(i<N-1) System.out.print(" ");
        }

    }

    public static int getNum(int num){
        Stack<Integer> stack = new Stack<>();
        int size =1;
        for(int i = 0;i<num;i++){
            if(stack.isEmpty()){
                stack.push(buildings[i]);
                size++;
            }
            else if(stack.peek()>buildings[i]){
                stack.push(buildings[i]);
                size++;
            }else{
                while(!stack.isEmpty()&&stack.peek()<=buildings[i]){
                    stack.pop();
                    size--;
                }
                stack.push(buildings[i]);
                size++;
            }
        }

        stack = new Stack<>();

        for(int i = num+1;i<buildings.length;i++){
            if(stack.isEmpty()){
                stack.push(buildings[i]);
                size++;
            }
            else if(stack.peek()<buildings[i]){
                stack.push(buildings[i]);
                size++;
            }
        }
        return size;
    }
}