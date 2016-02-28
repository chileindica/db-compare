package cl.chileindica.util.compare;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class Main {
	
	public static void main(String args[]) throws Exception{
		
		
		Properties prop=new Properties();
		String propFileName = "conf/config.properties";
		
		InputStream inputStream = new FileInputStream(propFileName);
		
		prop.load(inputStream);
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String db1=prop.getProperty("db1");
		String db2=prop.getProperty("db2");
		
		String user1=prop.getProperty(db1+".user");
		String user2=prop.getProperty(db2+".user");

		String pwd1=prop.getProperty(db1+".pwd");
		String pwd2=prop.getProperty(db2+".pwd");

		String jdbc1=prop.getProperty(db1+".jdbc");
		String jdbc2=prop.getProperty(db2+".jdbc");
		
		String name1=prop.getProperty(db1+".name");
		String name2=prop.getProperty(db2+".name");


		Connection conn1=DriverManager.getConnection(jdbc1,user1,pwd1);
		Connection conn2=DriverManager.getConnection(jdbc2,user2,pwd2);
		
		Main main=new Main();
		
		Database database1=main.getDatabase(name1,conn1);
		Database database2=main.getDatabase(name2,conn2);
		
		
		//main.displayDatabase(db1);
		//main.displayDatabase(db2);
		
		DBComparator.compareDatabases(database1, database2);
	}
	
	private Database getDatabase(String name,Connection conn) throws Exception{

		DatabaseMetaData m1 = conn.getMetaData();
		List<String> tableNames=getTables(m1);
		
		Database database=new Database(name); 
		
		for(String tableName:tableNames){
			
			Table table=new Table(tableName,database);
			database.addTable(table);
			List<Column> columns = getColumns(m1,tableName);
			table.addColumns(columns);
			
			List<Key> keys = getKeys(m1,tableName);
			table.addKeys(keys);
		}
		return database;
	}

	public void displayDatabase(Database db){
		List<Table> tables=db.getTables();
		for(Table table:tables){
			
			System.out.println("TABLE:"+table.getTableName()+"\n");

			List<Key> keys=table.getKeys();
			System.out.print("KEY:(");
			for(Key key:keys){
				
				System.out.print("\t"+key);
			}
			System.out.println(")");
			
			List<Column> columns=table.getColumns();
			for(Column column:columns){
				System.out.println("\t"+column.getColumnName()+"\t"+column.getTypeName()+"\n");
			}
		}
		
	}
	
	
	public List<String> getTables(DatabaseMetaData m1) throws Exception{
		String   catalog          = null;
		String   schemaPattern    = null;
		String   tableNamePattern = null;
		String[] types            = null;

		List<String> tables=new LinkedList<String>();
		
		ResultSet result = m1.getTables(
		    catalog, schemaPattern, tableNamePattern, types );

		while(result.next()) {
		    String tableName = result.getString(3);
		    tables.add(tableName);
		    
		}
		return tables;
	}
	
	
	
	public List<Column> getColumns(DatabaseMetaData m1,String tableName) throws Exception{
		String   catalog           = null;
		String   schemaPattern     = null;
		String   tableNamePattern  = tableName;
		String   columnNamePattern = null;

		List<Column>columns=new LinkedList<Column>();

		
//		ResultSet indexes = m1.getIndexInfo(catalog, schemaPattern, tableNamePattern, false, false);
		
		ResultSet result = m1.getColumns(
		    catalog, schemaPattern,  tableNamePattern, columnNamePattern);
		
		while(result.next()){
		    String columnName = result.getString(4);
		    int    columnType = result.getInt(5);
		    String columnTypeName=result.getString(6);
		    int    columnSize = result.getInt(7);
		    int    columnDecimalDigits = result.getInt(9);
		    String columnIsNullable = result.getString(18);
		    String columnIsAutoincrement=result.getString(23);
		    
		    Column c=new Column(columnName,columnType, tableName);
		    c.setColumnIsNullable(columnIsNullable);
		    c.setColumnIsAutoincrement(columnIsAutoincrement);
		    c.setColumnDecimalDigits(columnDecimalDigits);
		    c.setColumnSize(columnSize);
		    c.setColumnDecimalDigits(columnDecimalDigits);
		    c.setColumnTypeName(columnTypeName);
		    
		    columns.add(c);
		}
		return columns;
	}
	
	public List<Key> getKeys(DatabaseMetaData m1,String tableName) throws Exception{
		String   catalog   = null;
		String   schema    = null;

		List<Key> keys=new LinkedList<Key>();
		
		ResultSet result = m1.getPrimaryKeys(
		    catalog, schema, tableName);

		while(result.next()){
			Key key=new Key();

		    String columnName = result.getString(4);
		    short columnKetSeq= result.getShort(5);
		    String columnPKName = result.getString(6);
		    
		    key.setColumnName(columnName);
		    key.setColumnKetSeq(columnKetSeq);
		    key.setColumnPKName(columnPKName);
		    
		    keys.add(key);
		}
		return keys;
	}

}
