package de.hwg_lu.java_star.excercises;

import java.util.*;
import java.io.IOException;

class MyTester {
	class LexicalCompare {
		private String lhs = "";

		LexicalCompare(String lhs) {
			this.lhs = lhs;
		}

		int compare(String rhs) {
			if (lhs == null) {
				if (rhs == null) {
					return 0;
				} else {
					return 1;
				}
			}
			return lhs.compareTo(rhs);
		}
	}

	public static void main(String[] args) {
		String output = "OK";
		String str1 = "This is Exercise 1";
		String str2 = "This is Exercise 2";
		LexicalCompare cmp1 = new MyTester().new LexicalCompare(str1);
		int result = cmp1.compare(str2);
		if (result >= 0) {
			output += "error " + str2 + " is bigger then " + str1;
		}
		LexicalCompare cmp2 = new MyTester().new LexicalCompare(null);
		int result2 = cmp2.compare(null);
		if (result2 != 0) {
			output += "error null string should be equal to null string";
		}
		System.out.println(output);
	}
}