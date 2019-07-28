package logic;

public class Point {
	private int row;
	private int column;
	
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	

	
	public String toString() {
		return Integer.toString(row) + "-" +  Integer.toString(column);
	}

}
