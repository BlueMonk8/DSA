package com.bluemonk.dsa.graphs;

public class TspMain {
	
	public static void main (String[] args) {
		 int [][] dist =  {
		        {0,20,42,25},
		        {20,0,30,34},
		        {42,30,0,10},
		        {25,34,10,0}
		};
		
		 int n = dist.length;
		 
		 new TspNaiveSolution().tspSolver(dist, n);
		 
		 new TspDpSolution().tspSolver(dist, n);
		 
	}
}


class TspNaiveSolution {

	private int ALL_VISITED;
	
	public void tspSolver(int[][] dist, int n) {
		
		ALL_VISITED = (1<<n)-1;
		
		System.out.println("Min Cost : "+tsp(dist, n, 1, 0));
		
	}
	
	public int tsp(int [][] dist, int n, int mask , int pos) {
		
		if (ALL_VISITED == mask) {
			return dist[pos][0];
		}
		
		int ans = Integer.MAX_VALUE;
		// parse each city solution
		for (int city = 0; city<n; city++) {
			
			if ((mask&(1<<city)) == 0) {
				int newAns = dist[pos][city] + tsp (dist, n, (mask | (1<<city)), city);		
				ans = Math.min(newAns, ans);
			}
		}
		return ans;
	}
	
}

class TspDpSolution {
	
	
private int ALL_VISITED;

private int [][] dp;
	
	
	public void tspSolver(int[][] dist, int n) {
		
		ALL_VISITED = (1<<n)-1;
		
		init(n);
		
		System.out.println("Min Cost : "+tsp(dist, n, dp, 1, 0));
		
	}
	
	private void init(int n) {
		 dp = new int [(1<<n)-1][n];
		for (int s=0; s< (1<<n)-1; s++){
			for (int c=0; c<n; c++) {
				dp[s][c] = Integer.MAX_VALUE;
			}
		}		
	}

	public int tsp(int [][] dist, int n, int [][] dp, int mask , int pos) {
		
		if (ALL_VISITED == mask) {
			return dist[pos][0];
		}
		
		// look up 
		
		if (dp[mask][pos]!=Integer.MAX_VALUE) {
			return dp[mask][pos];
		}
		
		int ans = Integer.MAX_VALUE;
		// parse each city solution
		for (int city = 0; city<n; city++) {
			
			if ((mask&(1<<city)) == 0) {
				int newAns = dist[pos][city] + tsp (dist, n, dp, (mask | (1<<city)), city);		
				ans = Math.min(newAns, ans);
			}
		}
		return dp[mask][pos]=ans;
	}
}