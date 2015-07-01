package cl.chileindica.util.compare;

import java.util.*;
import java.util.Map.Entry;

class DBComparator {

	static class Diff<A> {

		private A left;
		private A right;

		public void setLeft(A left) {
			this.left = left;
		}

		public void setRight(A right) {
			this.right = right;
		}

		public A getRight() {
			return right;
		}

		public A getLeft() {
			return left;
		}

		public boolean isOnlyLeft() {
			return right == null;
		}

		public boolean isOnlyRight() {
			return left == null;
		}

		public boolean isBoth() {
			return (right != null && left != null);
		}

	}

	static class DatabaseDiff {

	}

	static class TableDiff extends Diff<Table> {

	}

	static class ColumnDiff extends Diff<Column> {

	}

	public static interface Process<A, B> {

	}

	public static <A, B, C extends Process<A, B>> void compare(A a1, A a2) {

	}

	public static void compareTables(Table t1, Table t2) {

		List<Column> c1 = t1.getColumns();
		List<Column> c2 = t2.getColumns();

		Map<String, ColumnDiff> diffList = new HashMap<String, ColumnDiff>();

		for (Column c : c1) {
			ColumnDiff diff = new ColumnDiff();
			diff.setLeft(c);
			diffList.put(c.getColumnName(), diff);
		}

		for (Column c : c2) {
			ColumnDiff diff = diffList.get(c.getColumnName());
			if (diff == null) {
				diff = new ColumnDiff();
			}
			diff.setRight(c);
			diffList.put(c.getColumnName(), diff);
		}

		for (Entry<String, ColumnDiff> diff : diffList.entrySet()) {
			String columnName = diff.getKey();
			ColumnDiff td = diff.getValue();

			if (td.isOnlyLeft()) {
				System.out.println("ALTER TABLE "+t2.getTableName()+" ADD "+columnName+ " " +td.getLeft().getTypeName() +"("+ td.getLeft().getColumnSize()+") "+ (td.getLeft().getColumnIsNullable().equals("YES")?"":"" ) +" ;");
				
			} else if (td.isOnlyRight()) {
				System.out.println("//ALTER TABLE "+t1.getTableName()+" DROP COLUMN "+columnName+";");
			} else {
				compareColumns(td.getLeft(), td.getRight());
				// System.out.println(columnName
				// +" is  present at both databases");
			}

		}

	}

	private static void compareColumns(Column left, Column right) {
		String columnName = left.getColumnName();
		if (!left.getColumnIsNullable().equals(right.getColumnIsNullable())) {
			
			System.out.println("ALTER TABLE "+ left.getTableName()+ " MODIFY "+columnName+ " " +(right.getColumnIsNullable().equals("YES")?"NOT NULL":"NULL")+";" );
			
			//System.out.println(columnName + " is " +  (left.getColumnIsNullable().equals("YES")?" nullable ":"not nullable") +" at left table " +left.getTableName() +" instead of " + (right.getColumnIsNullable().equals("YES")?" nullable ":"not nullable")  );
		}

		if ( (left.getType() != right.getType() ) || (left.getColumnSize() != right.getColumnSize() )) {
			
			System.out.println("ALTER TABLE "+ left.getTableName()+ " MODIFY "+columnName+ " "+ left.getTypeName() +"(" +left.getColumnSize()+");" );
		}
	}

	public static void compareDatabases(Database db1, Database db2) {

		List<Table> t1 = db1.getTables();
		List<Table> t2 = db2.getTables();

		Map<String, TableDiff> diffList = new HashMap<String, TableDiff>();

		for (Table t : t1) {
			TableDiff diff = new TableDiff();
			diff.setLeft(t);
			diffList.put(t.getTableName(), diff);
		}

		for (Table t : t2) {

			TableDiff diff = diffList.get(t.getTableName());
			if (diff == null) {
				diff = new TableDiff();
			}
			diff.setRight(t);
			diffList.put(t.getTableName(), diff);
		}

		for (Entry<String, TableDiff> diff : diffList.entrySet()) {
			String tableName = diff.getKey();
			TableDiff td = diff.getValue();

			if (td.isOnlyLeft()) {
				System.out.println("CREATE TABLE "+tableName+";");
			} else if (td.isOnlyRight()) {
				System.out.println("//DROP TABLE "+tableName+";");
			} else {
				compareTables(td.getLeft(), td.getRight());
			}

		}

	}

}