package cl.chileindica.util.compare;

import java.util.List;

class Table{
	
	private String tableName;
	private List<Column> columns;
	private List<Key> keys;
	private Database database;

	public Table(String tableName, Database database){
		this.tableName=tableName;
		this.database=database;
	}
	
	public void addKeys(List<Key>keys){
		this.keys=keys;
		
	}
	
	public void addColumns(List<Column> columns){
		this.columns=columns;
	}

	public String getTableName() {
		return tableName;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public List<Key> getKeys() {
		return keys;
	}

	public Database getDatabase() {
		
		return database;
	}
	
}