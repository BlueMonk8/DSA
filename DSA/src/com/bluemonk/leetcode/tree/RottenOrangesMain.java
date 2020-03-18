package com.bluemonk.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOrangesMain {

	public static void main(String[] args) {
		
		int [][] arr = { {2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};
		
		System.out.println(new RottenOrangesSol().orangesRotting(arr));

	}
}

class RottenOrangesSol {
	
	 public int orangesRotting(int[][] grid) { 
		 
		 if (grid==null  || grid.length == 0)
			 return 0;
		 
		 int r = grid.length;
	     int c = grid[0].length;
	     
	     Queue<int[]> rottenOranges = new LinkedList<>();
	     int count_fresh = 0;
	     
		/*
		 * Put the position of all rotten oranges in queue and count fresh
		 * oranges count
		 */
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (grid[i][j] == 2) {
					rottenOranges.offer(new int[] { i, j });
				} else if (grid[i][j] == 1) {
					count_fresh++;
				}
			}
		}
	        
		if (count_fresh == 0)
			return 0;
		int count = 0;
		int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	    
		while (!rottenOranges.isEmpty()) {
			++count;
			int size = rottenOranges.size();
			
			for (int i=0; i<size; i++ ) {
				int [] point = rottenOranges.poll();
				
				
				for (int dir[] : dirs) {
					
					int x= point[0] + dir[0];
					int y = point[1] + dir[1];
					
					if(x < 0 || y < 0 || x >= r || y >= c || grid[x][y] == 0 || grid[x][y] == 2) continue;
					
					 grid[x][y] = 2;
	                    //put the new rotten orange at (x , y) in queue
	                    rottenOranges.offer(new int[]{x , y});
	                    //decrease the count of fresh oranges by 1
	                    count_fresh--;
				}
			}
		}
		return count_fresh == 0 ? count-1 : -1;
	 }
}