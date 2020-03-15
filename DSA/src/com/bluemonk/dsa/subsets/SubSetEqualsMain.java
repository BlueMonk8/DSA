package com.bluemonk.dsa.subsets;

public class SubSetEqualsMain {

	public static void main(String[] args) {
	
		int[] arr = {6,8,2,10,3,5};
		int target = 10;
		
		new SubSetEqualsSol().retrieveSubSetsEqualArray(arr, target);
	}
}

class SubSetEqualsSol {
	
	public void retrieveSubSetsEqualArray(int [] arr, int sum) {
		solve(arr, arr.length, sum, "");
	}
	
	
	void solve(int[] arr, int n, int sum, String res) {

		if (sum == 0) {
			System.out.println(res);
			return;
		}

		if (n == 0)
			return;

		solve(arr, n - 1, sum, res);
		solve(arr, n - 1, sum - arr[n - 1], res + " " + arr[n - 1]);
	}
}
