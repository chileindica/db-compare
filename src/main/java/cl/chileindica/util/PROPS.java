package cl.chileindica.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PROPS {

	public static void read(String db1) {

		try {
			Properties prop = new Properties();
			String propFileName = "conf/config.properties";

			InputStream inputStream = new FileInputStream(propFileName);

			prop.load(inputStream);
			
			String user1=prop.getProperty(db1+".user");
			String pwd1=prop.getProperty(db1+".pwd");
			String jdbc1=prop.getProperty(db1+".jdbc");
			String name1=prop.getProperty(db1+".name");
			
		} catch (Exception e) {

		}

	}

}
