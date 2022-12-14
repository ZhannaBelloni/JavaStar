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
import java.util.ArrayList;
import java.util.regex.Pattern;

import de.hwg_lu.java_star.jdbc.ExcerciseDB;

/**
 * Class to generate request to be send to https://godbolt.org.
 * 
 * <br>
 * It offers different functionality:
 * <ul>
 * <li>compile source code</li>
 * <li>integrate source code into a test class.</li>
 * </ul>
 *
 */
public class Tester {

	private final String TAG = "%%INSERT_CODE_HERE%%";
	
	/**
	 * It prepared the source code to the included into the tester class provided by the JavaStar Application
	 * 
	 * @param sourceCode
	 * @param excerciseNumber
	 * @return Source code to be included into a json document.
	 * @throws IOException
	 * @throws SQLException
	 */
	public String getTextCodeForSource(String sourceCode, int excerciseNumber) throws IOException, SQLException {
		return prepareSourceCode(sourceCode, excerciseNumber);	
	}
	
	
	/**
	 * Send a request to https://godbolt.org to perform also the execution of the program generated by SourceCode
	 * @param sourceCode
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public String testExcerciseSourceCodeWithtesterClass(String sourceCode) throws IOException, SQLException {
		return this.execute(sourceCode, true);				
	}
	
	/**
	 * prepare in source code, integrate into Tester class and send a request to https://godbolt.org 
	 * @param sourceCode
	 * @param excerciseNumber
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public String testExcercise(String sourceCode, int excerciseNumber) throws IOException, SQLException {
		String extendedClass = prepareSourceCode(sourceCode, excerciseNumber);
		return this.execute(extendedClass, true);				
	}
	
	/**
	 * send a request to https://godbolt.org to only compile the source code provided.
	 * @param sourceCode
	 * @param excerciseNumber
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public String compileExcercise(String sourceCode, int excerciseNumber) throws IOException, SQLException {
		return this.execute(sourceCode, false);				
	}
	
	/**
	 * Integrate the source code into the Tester class saved into the database.
	 * @param sourceCode
	 * @param excerciseNumber
	 * @return
	 * @throws SQLException
	 */
	public String prepareSourceCode(String sourceCode, int excerciseNumber) throws SQLException {
		String stringTest = new ExcerciseDB().getExcericeTestSourceCode(excerciseNumber);
		String pattern = "import .*;";
		// search all imports and put at the and of the sourceCode.
		java.util.regex.Matcher m = Pattern.compile(pattern)
			     .matcher(sourceCode);
		String allImports = "";
		while (m.find()) {
			allImports += m.group();
		}
		return allImports += stringTest.replace(this.TAG, sourceCode.replaceAll(pattern, ""));
				
	}
	
	/**
	 * Clean comments, special character to generate a well form json String from sourceCode.
	 * @param sourceCode
	 * @return
	 */
	public String cleanStringForRequest(String sourceCode) {
		return sourceCode.replaceAll("/\\*{1,2}[\\s\\S]*?\\*/", "")
		  .replaceAll("//[\\s\\S]*?\\n", "")
		  .replace("\t", " ")
		  .replace("\n", " ")
		  .replace("\\n", " ")
		  .replace("\r", " ")
		  .replace("\"", "\\\"");
	}
	
	/**
	 * It generate the data (json) to be send to https://godbolt.org
	 * @param sourceCode
	 * @param execute if true use the execute flag in the options of the request.
	 * @return
	 */
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
	
	/**
	 * Send the request for the given sourceCode and the given execute flag.
	 * @param sourceCode
	 * @param execute
	 * @return the complete response from https://godbolt.org
	 * @throws IOException
	 */
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
