package logs;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class logs {

	static Logger log = LogManager.getLogger(logs.class);
	
	public static void main(String[] args) {
			
	}
	public static void logs0() {
		log.info("message");
	}
}