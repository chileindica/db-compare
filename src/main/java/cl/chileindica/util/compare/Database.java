package cl.chileindica.util.compare;

import java.util.LinkedList;
import java.util.List;

class Database{
	
	private String name;
	private List<Table> tables=new LinkedList<Table>();
	
	public Database(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addTable(Table table){
		tables.add(table);
	}
	
	

	public List<Table> getTables() {
		// TODO Auto-generated method stub
		return tables;
	}
}