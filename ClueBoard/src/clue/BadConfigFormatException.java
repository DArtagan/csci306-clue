package clue;

import java.io.FileWriter;
import java.io.IOException;

public class BadConfigFormatException extends Exception {
	private String logfile;

	public BadConfigFormatException(String message) {
		super(message);

		logfile = "error.log";
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
