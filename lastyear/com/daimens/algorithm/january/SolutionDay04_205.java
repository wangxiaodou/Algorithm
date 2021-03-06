package com.daimens.algorithm.january;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Demon Song
 * 205. Isomorphic Strings
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character 
 * while preserving the order of characters. No two characters may map to 
 * the same character but a character may map to itself.
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 *
 */
public class SolutionDay04_205 {
	public boolean isIsomorphic(String s,String t){
		if(s == null || t == null) return false;
		if(s.length() != t.length()) return false;
		
		Map<Character,Character> map = new HashMap<Character,Character>();
		Set<Character> set = new HashSet<Character>();
		
		for(int i = 0; i < s.length(); i++){
			char c1 = s.charAt(i);
			char c2 = t.charAt(i);
			
			if(map.containsKey(c1)){
				if(map.get(c1)!=c2) return false;
			}else{
				if(set.contains(c2)) return false;
				else{
					map.put(c1, c2);
					set.add(c2);
				}
			}
		}
		return true;
	}
}
