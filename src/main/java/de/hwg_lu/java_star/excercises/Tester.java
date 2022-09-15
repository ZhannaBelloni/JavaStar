package de.hwg_lu.java_star.excercises;

import java.io.IOException;

import de.hwg_lu.java_star.excercises.MyTester.HelloWorld;

public class Tester {

	public String testExcercise(String sourceCode) throws IOException {
		
		String extendedClass = prepareRequest(sourceCode);
				
		EvilExecutor ee = new EvilExecutor();
		return ee.execute(extendedClass);				
	}
	
	public String prepareRequest(String sourceCode) {
		return ""
				+ "class Tester { "
				+ "public " + sourceCode + ";\n"
                + "public static void main(String[] args) {      "
				+ "HelloWorld hw = new Tester(). new HelloWorld();"
				+ "System.out.println(hw.toString());"
				+ "System.out.println(\"Hello World\");     }" 
				+ "}";
				
	}
	
}
