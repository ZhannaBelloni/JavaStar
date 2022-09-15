package de.hwg_lu.java_star.excercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/* curl -H "Content-Type: application/json"  -XPOST 'https://godbolt.org/api/compiler/java1800/compile' -d '{"source": "class HelloWorld { public static void main(String[] args) {      System.out.println(\"Hello World\");     }}", "options": {
"compilerOptions": {
    "executorRequest": true
},
"filters": {
    "execute": true
}
}}'

*/
public class EvilExecutor {

	public String prepareRequest(String sourceCode) {
		String jsonInputString = "{";
		jsonInputString += "\"source\": \"" + sourceCode.replaceAll("/\\*{1,2}[\\s\\S]*?\\*/", "").replaceAll("//[\\s\\S]*?\\n", "").replace("\"", "\\\"").replace("\n", " ").replace("\r", "") + "\"";
		jsonInputString += ", \"options\": {" + "\"compilerOptions\": {" + "    \"executorRequest\": true" + "},"
				+ "\"filters\": {" + "    \"execute\": true" + "}";
		jsonInputString += "}}";
		return jsonInputString;
		
		
	}
	
	public String execute(String sourceCode) throws IOException {
		URL url = new URL("https://godbolt.org/api/compiler/java1800/compile");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		// con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);

		String jsonInputString = prepareRequest(sourceCode);

		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		String stdOut = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			stdOut += response.toString();
		}

		return stdOut;
	}

}
