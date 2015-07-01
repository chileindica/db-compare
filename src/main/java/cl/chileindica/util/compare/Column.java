package cl.chileindica.util.compare;

import java.sql.Types;

class Column{
	private String columnName;
	private int type;
	private String tableName;
	
	private String columnTypeName;
	
    private int    columnSize;
    private int    columnDecimalDigits;
    private String columnIsNullable;
    private String columnIsAutoincrement;
	
	
	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public int getColumnDecimalDigits() {
		return columnDecimalDigits;
	}

	public void setColumnDecimalDigits(int columnDecimalDigits) {
		this.columnDecimalDigits = columnDecimalDigits;
	}

	public String getColumnIsNullable() {
		return columnIsNullable;
	}

	public void setColumnIsNullable(String columnIsNullable) {
		this.columnIsNullable = columnIsNullable;
	}

	public String getColumnIsAutoincrement() {
		return columnIsAutoincrement;
	}

	public void setColumnIsAutoincrement(String columnIsAutoincrement) {
		this.columnIsAutoincrement = columnIsAutoincrement;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Column(String columnName, int type, String tableName){
		this.columnName=columnName;
		this.type=type;
		this.tableName=tableName;
	}

	public String getColumnName() {
		return columnName;
	}
	
	public String getTableName() {
		return tableName;
	}

	public int getType() {
		return type;
	}

	public String getTypeName() {
		return getSqlTypeName(type);
		
	}
	
	public static String getSqlTypeName(int type) {
	    switch (type) {
	    case Types.BIT:
	        return "BIT";
	    case Types.TINYINT:
	        return "TINYINT";
	    case Types.SMALLINT:
	        return "SMALLINT";
	    case Types.INTEGER:
	        return "INTEGER";
	    case Types.BIGINT:
	        return "BIGINT";
	    case Types.FLOAT:
	        return "FLOAT";
	    case Types.REAL:
	        return "REAL";
	    case Types.DOUBLE:
	        return "DOUBLE";
	    case Types.NUMERIC:
	        return "NUMERIC";
	    case Types.DECIMAL:
	        return "DECIMAL";
	    case Types.CHAR:
	        return "CHAR";
	    case Types.VARCHAR:
	        return "VARCHAR";
	    case Types.LONGVARCHAR:
	        return "LONGVARCHAR";
	    case Types.DATE:
	        return "DATE";
	    case Types.TIME:
	        return "TIME";
	    case Types.TIMESTAMP:
	        return "TIMESTAMP";
	    case Types.BINARY:
	        return "BINARY";
	    case Types.VARBINARY:
	        return "VARBINARY";
	    case Types.LONGVARBINARY:
	        return "LONGVARBINARY";
	    case Types.NULL:
	        return "NULL";
	    case Types.OTHER:
	        return "OTHER";
	    case Types.JAVA_OBJECT:
	        return "JAVA_OBJECT";
	    case Types.DISTINCT:
	        return "DISTINCT";
	    case Types.STRUCT:
	        return "STRUCT";
	    case Types.ARRAY:
	        return "ARRAY";
	    case Types.BLOB:
	        return "BLOB";
	    case Types.CLOB:
	        return "CLOB";
	    case Types.REF:
	        return "REF";
	    case Types.DATALINK:
	        return "DATALINK";
	    case Types.BOOLEAN:
	        return "BOOLEAN";
	    case Types.ROWID:
	        return "ROWID";
	    case Types.NCHAR:
	        return "NCHAR";
	    case Types.NVARCHAR:
	        return "NVARCHAR";
	    case Types.LONGNVARCHAR:
	        return "LONGNVARCHAR";
	    case Types.NCLOB:
	        return "NCLOB";
	    case Types.SQLXML:
	        return "SQLXML";
	    }

	    return "?";
	}

	public String getColumnTypeName() {
		return columnTypeName;
	}

	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
	
}