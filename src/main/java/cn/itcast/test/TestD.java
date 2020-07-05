package cn.itcast.test;

import java.util.*;

public class TestD {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String s=sc.nextLine();
//        String[] str = s.split(" ");
//        int[] nums = new int[str.length];
//        for(int i =0;i<nums.length;i++){
//            nums[i] = Integer.parseInt(str[i]);
//        }
//        List<Integer> list = getList(nums);
//        for(int i = 0;i<list.size();i++){
//            System.out.print(list.get(i));
//            if(i<list.size()-1) System.out.print(" ");
//        }
        int[] in= new int[]{1,2,3};
        System.out.print(DistanceToHigher(in).toString());

    }

    public static List<Integer> getList(int[] nums){
        List<Integer> list = new LinkedList<>();
        int[] queue = new int[2];
        if(nums.length<2) return list;
        if(nums[0]>nums[1]){
            list.add(1);
            queue[0] = nums[0];
            queue[1] =nums[1];
        }else{
            queue[1] = nums[0];
            queue[0] =nums[1];
        }

        for(int i = 2;i<nums.length;i++){
            int temp = nums[i];
            if(temp>=queue[0]){
                queue[1] = queue[0];
                queue[0] = temp;
            }else if(temp<queue[1]){
                continue;
            }else {
                queue[1] = temp;
                list.add(i);
            }
        }
        return list;

    }

    public static int[] DistanceToHigher (int[] height) {
        if(height == null||height.length==0) return new int[0];
        // write code here
        int[] res = new int[height.length];
        Stack<Integer> stack= new Stack<>();
        for(int i =0 ;i<height.length;i++){
            if(stack.isEmpty()){
                res[i]=0;
                stack.push(i);
                continue;
            }
            while(!stack.isEmpty()&&height[stack.peek()]<height[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                res[i]=0;
                stack.push(i);
            }else{
                res[i]=i-stack.peek();
                stack.push(i);
            }
        }
        return res;
    }
}
