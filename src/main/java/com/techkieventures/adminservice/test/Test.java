package com.techkieventures.adminservice.test;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		String s = "teste";

		String v = "aeiou";
		int count = 0;
		int other =0;
		String repeatdword  ="";
		Map<Character, Integer> list=new HashMap<>();
		for (char a : s.toCharArray()) {

			if (v.indexOf(a) != -1) {
				
				list.put(a, count);
				
				if(!list.isEmpty() && list.containsKey(a)) {
//					int k=list.get(a);
//					System.out.println(k);
					
					repeatdword+=a;
					if(list.size()==1) {
						count++;
					}
					
				}
			}else {
				other++;
			}
		}
		System.out.println("total no of vowel char " + count);
		System.out.println("total no of other char " + other);
		System.out.println("repeatdword " + repeatdword);
	}

}
