package de.hwg_lu.java_star.excercises;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.ExcerciseDB;

public class Tester {

	private final String TAG = "%%INSERT_CODE_HERE%%";
	
	public String getTextCodeForSource(String sourceCode, int excerciseNumber) throws IOException, SQLException {
		return prepareSourceCode(sourceCode, excerciseNumber);	
	}
	
	public String testExcerciseSourceCodeWithtesterClass(String sourceCode) throws IOException, SQLException {
		return this.execute(sourceCode, true);				
	}
	
	public String testExcercise(String sourceCode, int excerciseNumber) throws IOException, SQLException {
		String extendedClass = prepareSourceCode(sourceCode, excerciseNumber);
		return this.execute(extendedClass, true);				
	}
	
	public String compileExcercise(String sourceCode, int excerciseNumber) throws IOException, SQLException {
		return this.execute(sourceCode, false);				
	}
	
	public String prepareSourceCode(String sourceCode, int excerciseNumber) throws SQLException {
		String stringTest = new ExcerciseDB().getExcericeTestSourceCode(excerciseNumber);
		String pattern = "import .*;";
		return stringTest.replace(this.TAG, sourceCode.replaceAll(pattern, ""));
				
	}
	
	public String cleanStringForRequest(String sourceCode) {
		return sourceCode.replaceAll("/\\*{1,2}[\\s\\S]*?\\*/", "")
		  .replaceAll("//[\\s\\S]*?\\n", "")
		  .replace("\t", " ")
		  .replace("\n", " ")
		  .replace("\\n", " ")
		  .replace("\r", " ")
		  .replace("\"", "\\\"");
	}
	
	public String prepareRequest(String sourceCode, boolean execute) {
		String jsonInputString = "{";
		jsonInputString += "\"source\": \"" + cleanStringForRequest(sourceCode) + "\"";
		jsonInputString += ", \"options\": {" + "\"compilerOptions\": {" + "    \"executorRequest\": "; 
		jsonInputString += execute ? "true" : "false";
		jsonInputString += "},"
				+ "\"filters\": {" + "    \"execute\": ";
		jsonInputString += execute ? "true" : "false";
		jsonInputString += " }";
		jsonInputString += "}}";
		return jsonInputString.trim().replaceAll(" +", " ");

	}
	
	public String execute(String sourceCode, boolean execute) throws IOException {
		
		System.out.println("[DEBUG] Running execute with");
		
		URL url = new URL("https://godbolt.org/api/compiler/java1800/compile");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		// con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setDoOutput(true);

		String jsonInputString = prepareRequest(sourceCode, execute);
		System.out.println("executing request with data:");
		System.out.println(jsonInputString);

		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
		
		/*
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		out.write(jsonInputString);
		out.close();
		*/

		String stdOut = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			stdOut += response.toString();
		} catch (IOException e ) {
			return e.toString() + " for data: " + jsonInputString;
		}
		System.out.println("[DEBUG] Response: "+ stdOut);
		return stdOut;
	}
	
}
