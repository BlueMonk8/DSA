package com.bluemonk.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FrogJumpMain {

	public static void main(String[] args) {
		int [] stones = {0,1,3,5,6,8,12,17};
		
		System.out.println(new FrogJumpSol().canCross(stones));
		System.out.println(new FrogJumpSol1().canCross(stones));
		
	}
}


class FrogJumpSol {
	
	/*We are using binary search to get the position of stone and dp to store the results
	 * */
	
	boolean canCross(int[] stones) {
		
		int [][] dp = new int [stones.length][stones.length];
		
		for (int [] r : dp) {
			Arrays.fill(r, -1);
		}
		
		return solve(stones, 0, 0, dp) == 1;
	}

	private int solve(int[] stones, int idx, int jumpSize, int[][] dp) {
		
		if (dp[idx][jumpSize]>=0) {
			return dp[idx][jumpSize];			
		}
		
		// 
		
		int ind1 = Arrays.binarySearch(stones, idx + 1, stones.length, stones[idx] + jumpSize);
		if (ind1 >= 0 && solve(stones, ind1, jumpSize, dp) == 1) {
			dp[idx][jumpSize] = 1;
			return 1;
		}

		int ind2 = Arrays.binarySearch(stones, idx + 1, stones.length, stones[idx] + jumpSize - 1);

		if (ind2 >= 0 && solve(stones, ind2, jumpSize-1, dp) == 1) {
			dp[idx][jumpSize-1] = 1;
			return 1;
		}

		int ind3 = Arrays.binarySearch(stones, idx + 1, stones.length, stones[idx] + jumpSize + 1);
		if (ind3 >= 0 && solve(stones, ind3, jumpSize+1, dp) == 1) {
			dp[idx][jumpSize+1] = 1;
			return 1;
		}
		
		dp[idx][jumpSize] = (idx==stones.length-1) ? 1 :0;
		return dp[idx][jumpSize];
		
	}
}

 class FrogJumpSol1 {
	 
	/*To save the result use the hash map*/ 
	 
	 boolean canCross(int[] stones) { 
		 
		 HashMap<Integer, Set<Integer>> map = new HashMap<>();
		 
		 for (int i=0; i< stones.length; i++) {
			 map.put(stones[i], new HashSet<Integer>());
		 }
		 
		 map.get(0).add(0);
		 
		 for (int s=0; s<stones.length; s++) {
			 for (int k : map.get(stones[s])) {
				 for (int steps = k-1; steps<= k+1;steps++) {
					 if (steps>0 && map.containsKey(stones[s]+steps)) {
						  map.get(stones[s] + steps).add(steps);
					 }
				 }
				 
			 }
			 
		 }
		 return !map.get(stones[stones.length - 1]).isEmpty();
	 }
    
}
 
  