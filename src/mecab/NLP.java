package mecab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class NLP {
	public static void main(String args[]) {
		try {
			String line;
			Scanner scan = new Scanner(System.in);

			ProcessBuilder builder = new ProcessBuilder("/usr/local/bin/mecab");
			builder.redirectErrorStream(true);
			Process process = builder.start();
			
			OutputStream stdin = process.getOutputStream();
			InputStream stderr = process.getErrorStream();
			InputStream stdout = process.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

			while (true) {
				String input = "안녕하세요";							
				if(input.trim().equals("exit")) {
					writer.write("exit\n");
				} else{
					writer.write(input + "\n");
				}
				writer.flush();
				
				line = reader.readLine();
				while(line != null && !line.trim().equals("EOS")) {
					System.out.println("Stdout: " + line);
					line = reader.readLine();
				}
				if(line == null) {
					break;
				}
				
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}
