package cn.itcast.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.*;

public class TestG {

    public static void main(String[] args) {
        System.out.print(lengthOfLongestSubstring("abcabcabc"));
    }

    @Test
    public static int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0) return 0;
        int res = 1;
        HashMap<Character,Integer> map = new HashMap<>();
        int slow=0,fast = 0;
        while(fast<s.length()){
            if(!map.containsKey(s.charAt(fast))){
                map.put(s.charAt(fast),fast);
                fast++;

            }else{
                res = Math.max(fast-slow,res);
                while(s.charAt(slow)!=s.charAt(fast)){
                    map.remove(s.charAt(slow));
                    slow++;
                }
                map.put(s.charAt(fast),fast);
                fast++;
            }
        }
        return res;

    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        if(nums==null||nums.length==0)    return res;

        for(int k = 0;k<nums.length-2;k++){
            if(nums[k]>0) break;
            if(k>0 && nums[k] == nums[k-1]) continue;
            int i = k+1,j = nums.length - 1;

            while(i<j){

                int sum = nums[k]+nums[i]+nums[j];
                if(sum == 0){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[k]);
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    res.add(temp);
                    while(i<j&&nums[i]==nums[++i]);
                    while(i<j && nums[j]==nums[--j]);
                }else if(sum>0){
                    while(i < j && nums[j] == nums[j-1]) j--;
                }else{
                    while(i < j && nums[i] == nums[i+1]) i++;
                }
            }

        }
        return res;

    }
}
