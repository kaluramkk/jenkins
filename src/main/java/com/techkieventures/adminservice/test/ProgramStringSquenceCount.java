package com.techkieventures.adminservice.test;

import java.io.Serializable;
import java.util.Comparator;

public class ProgramStringSquenceCount {
	public static void main(String[] args) {

		
		String s = "aaaaaaaaaaaaaaaaabbbcccdd";
		int count = 1;
		StringBuilder final_val = new StringBuilder();

		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				count++;
			} else {
				final_val.append(s.charAt(i - 1)).append(count);
				count = 1;
			}
		}
		System.out.println(final_val);
		String reverse_result = getReverseResult(final_val.toString());
		System.out.println(reverse_result);
	}

	private static String getReverseResult(String input) {
		StringBuilder final_val = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isAlphabetic(c)) {
				StringBuilder numbuild = new StringBuilder();
				int j=i+1;
				while(j<input.length() && Character.isDigit(input.charAt(j))) {
					numbuild.append(input.charAt(j));
					j++;
				}
				
				int count =Integer.parseInt(numbuild.toString());
				final_val.append(String.valueOf(c).repeat(count));
				count = 0;
			}

		}
		return final_val.toString();
	}

}
