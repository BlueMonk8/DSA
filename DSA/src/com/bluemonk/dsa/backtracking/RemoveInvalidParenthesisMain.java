package com.bluemonk.dsa.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParenthesisMain {

	public static void main(String[] args) {

		String exp = "(a)())()))";
		
		new RemoveInvalidParenthesisSol().removeInvalidParenthesis(exp);
	}
}


class RemoveInvalidParenthesisSol {
	
	
	public void removeInvalidParenthesis (String exp) {
		List<String> validExp = new ArrayList<>();
        Set<String> visited = new HashSet<>();
		solve (exp, numOfInvalidParenthesis(exp), visited, validExp);
		
		validExp.forEach(System.out::println);
			
		
	}

	private void solve(String exp, int invalidParen, Set<String> visited, List<String> validExp) {
		
		if (invalidParen==0) {
			validExp.add(exp);
			return;
		}
		
		
		for (int k=0; k< exp.length(); k++) {
			
			if (exp.charAt(k) != '(' && exp.charAt(k) != ')') {
                continue;
            }
			
			String childExp = exp.substring(0, k) + exp.substring(k+1, exp.length());

			if (!visited.contains(childExp)) {
				visited.add(childExp);
				
				int removeParen = numOfInvalidParenthesis(childExp);
				
				if (removeParen < invalidParen) {
					solve(childExp, removeParen, visited, validExp);
				}
			}
		}
		
	}

	private int numOfInvalidParenthesis(String exp) {
		int countL=0;
		int countR=0;
		
		char open = '(';
		char close = ')';
		
		for (char c: exp.toCharArray()) {
			
			if (open == c) {
				countL++;
			} else if (close == c) {
				if (countL==0) {
					countR++;
				} else {
					countL--;
				}
			}	
		}
		return countL+countR;
	}
}