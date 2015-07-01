package cl.chileindica.util.compare;

public class Key {
    private String columnName;
    private short columnKetSeq;
    private String columnPKName;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public short getColumnKetSeq() {
		return columnKetSeq;
	}
	public void setColumnKetSeq(short columnKetSeq) {
		this.columnKetSeq = columnKetSeq;
	}
	public String getColumnPKName() {
		return columnPKName;
	}
	public void setColumnPKName(String columnPKName) {
		this.columnPKName = columnPKName;
	}
}
