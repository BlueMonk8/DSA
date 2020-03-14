package com.bluemonk.dsa.backtracking;

import java.util.LinkedList;
import java.util.List;

public class PhoneCombinationMain {

	public static void main(String[] args) {
		SolutionPhoneCombo sol = new SolutionPhoneCombo();
		sol.letterCombinationsWithIteration("234").forEach(System.out::println);

	}

}

class SolutionPhoneCombo {

	public List<String> letterCombinationsWithIteration(String digits) {
		LinkedList<String> ans = new LinkedList<>();
		if (digits.isEmpty())
			return ans;
		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		ans.add("");
		for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			while (ans.peek().length() == i) {
				String t = ans.remove();
				for (char s : mapping[x].toCharArray())
					ans.add(t + s);
			}
		}
		return ans;
	}
}
