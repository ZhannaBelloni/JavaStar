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
	
	public String testExcercise(String sourceCode, int excerciseNumber) throws IOException, SQLException {
		String extendedClass = prepareSourceCode(sourceCode, excerciseNumber);
		return this.execute(extendedClass);				
	}
	
	public String prepareSourceCode(String sourceCode, int excerciseNumber) throws SQLException {
		String stringTest = new ExcerciseDB().getExcericeTestSourceCode(excerciseNumber);
		
		return stringTest.replace(this.TAG, sourceCode);
				
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
	
	public String prepareRequest(String sourceCode) {
		String jsonInputString = "{";
		jsonInputString += "\"source\": \"" + cleanStringForRequest(sourceCode) + "\"";
		jsonInputString += ", \"options\": {" + "\"compilerOptions\": {" + "    \"executorRequest\": true" + "},"
				+ "\"filters\": {" + "    \"execute\": true" + "}";
		jsonInputString += "}}";
		return jsonInputString.trim().replaceAll(" +", " ");

	}
	
	public String execute(String sourceCode) throws IOException {
		URL url = new URL("https://godbolt.org/api/compiler/java1800/compile");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		// con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setDoOutput(true);

		String jsonInputString = prepareRequest(sourceCode);

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

		return stdOut;
	}
	
}
