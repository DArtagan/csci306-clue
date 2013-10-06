package clue;

import java.io.FileWriter;
import java.io.IOException;

public class BadConfigFormatException extends Exception {
	private String logfile = "error.log";

	public BadConfigFormatException(String message) {
		super(message);

		try {
			FileWriter log = new FileWriter(logfile, true);
			log.write(this.toString());
			log.write("\r\n");
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
