package designs;
	public class Singleton {
	    public static void main(String[] args) {
	        Logger logger1 = Logger.getInstance();
	        Logger logger2 = Logger.getInstance();
	        logger1.logging("First log message.");
	        logger2.logging("Second log message.");
	        if (logger1 == logger2) {
	            System.out.println("Both logger1 and logger2 are the same instance.");
	        } else {
	            System.out.println("logger1 and logger2 are different instances.");
	        }
	    }
	}

class Logger {
   
    private static Logger instance;
    private Logger() {
       
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger(); 
        }
        return instance;
    }

    public void logging(String message) {
        System.out.println("Log: " + message);
    }
}

