package cl.chileindica.util.map;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SQLMapper implements Mapper{

	private Connection con;
	

	public SQLMapper() throws Exception {
		Properties prop=new Properties();
		String propFileName = "conf/config.properties";
		
		InputStream inputStream = new FileInputStream(propFileName);
		
		prop.load(inputStream);
		
		
		this.con = DriverManager.getConnection("jdbc:mysql://192.168.3.223/mysql", "root", "camila2012");
	}

	public List<Map<String,String>> process(String sql) throws Exception {

		List<Map<String,String>> table = new LinkedList<Map<String,String>>();

		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();

		int ccount = rst.getMetaData().getColumnCount();
		List<String> columnNames = new LinkedList<String>();

		for (int i = 0; i < ccount; i++) {
			columnNames.add(rst.getMetaData().getColumnName(i + 1));
		}

		while (rst.next()) {
			Map<String,String> row = new HashMap<String,String>();
			Iterator<String> it = columnNames.iterator();
			while (it.hasNext()) {
				String columnName = it.next();
				row.put(columnName, rst.getString(columnName));
			}

			table.add(row);

		}

		return table;
	}

}
