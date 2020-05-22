import java.util.Scanner;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Programm {
	static boolean parsePoint(String[] command, Log logger) throws Exception {
		if (!command[0].equals("check")) {
			throw new Exception("Unknown command!");
		}

		try {
			double x = Double.parseDouble(command[1]);
			double y = Double.parseDouble(command[2]);
			double r = Double.parseDouble(command[3]);

			Point p = new Point(x, y, r);

			logger.update(p);

			return p.hit;
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Cannot parse numbers");
		}
	}

	public static void main(String[] args) {
		Log logger = new Log();

		// Subscribe mbean
		try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("lab_se_4.beans:type=Info");
            Info infoBean = new Info();
            server.registerMBean(infoBean, name);
            logger.setInfo(infoBean);
        } catch (Exception e) {
            System.exit(1);
        }

		try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("lab_se_4.beans:type=Interval");
            Interval intervalBean = new Interval();
            server.registerMBean(intervalBean, name);
            logger.setInterval(intervalBean);
        } catch (Exception e) {
            System.exit(1);
        }        

		Scanner scanner = new Scanner(System.in);

		while (true) {
			String command = scanner.nextLine();
	    	String[] parsedCommands = command.split(" ");

	    	switch (parsedCommands.length) {
		    	case 1:
		    		switch (parsedCommands[0]) {
		    			case "history":
		    				System.out.println(logger);
		    				break;
		    			case "exit":
		    				return;
		    			default:
		    				System.out.println("Unknown command!");
		    		}

		    		break;
		    	case 4:
		    		try {
		    			if (parsePoint(parsedCommands, logger)) {
		    				System.out.println("Hit");
		    			}
		    			else {
		    				System.out.println("Miss");
		    			}
		    		}
		    		catch (Exception e) {
		    			System.out.println(e.getMessage());
		    		}
		    		break;
		    	default:
		    		System.out.println("Unknown command!");
		    }
		}
	}
}