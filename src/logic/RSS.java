package logic;

public class RSS{
	
	private int averValue;
	private int centriodValue;
	private int minValue;
	private int maxValue;
	
	private int averDist;
	private int centriodDist;
	private int minDist;
	private int maxDist;
	
	private int upperLeft;
	private int upperRight;
	private int lowerLeft;
	private int lowerRight;
	
	private int upperLeftValue;
	private int upperRightValue;
	private int lowerLeftValue;
	private int lowerRightValue;
	
	private double std;
	
	
	public int getUpperLeftValue() {
		return upperLeftValue;
	}
	public void setUpperLeftValue(int upperLeftValue) {
		this.upperLeftValue = upperLeftValue;
	}
	public int getUpperRightValue() {
		return upperRightValue;
	}
	public void setUpperRightValue(int upperRightValue) {
		this.upperRightValue = upperRightValue;
	}
	public int getLowerLeftValue() {
		return lowerLeftValue;
	}
	public void setLowerLeftValue(int lowerLeftValue) {
		this.lowerLeftValue = lowerLeftValue;
	}
	public int getLowerRightValue() {
		return lowerRightValue;
	}
	public void setLowerRightValue(int lowerRightValue) {
		this.lowerRightValue = lowerRightValue;
	}
	public int getUpperLeft() {
		return upperLeft;
	}
	public void setUpperLeft(int upperLeft) {
		this.upperLeft = upperLeft;
	}
	public int getUpperRight() {
		return upperRight;
	}
	public void setUpperRight(int upperRight) {
		this.upperRight = upperRight;
	}
	public int getLowerLeft() {
		return lowerLeft;
	}
	public void setLowerLeft(int lowerLeft) {
		this.lowerLeft = lowerLeft;
	}
	public int getLowerRight() {
		return lowerRight;
	}
	public void setLowerRight(int lowerRight) {
		this.lowerRight = lowerRight;
	}
	public int getAverValue() {
		return averValue;
	}
	public void setAverValue(int averValue) {
		this.averValue = averValue;
	}
	public int getCentriodValue() {
		return centriodValue;
	}
	public void setCentriodValue(int centriodValue) {
		this.centriodValue = centriodValue;
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public int getAverDist() {
		return averDist;
	}
	public void setAverDist(int averDist) {
		this.averDist = averDist;
	}
	public int getCentriodDist() {
		return centriodDist;
	}
	public void setCentriodDist(int centriodDist) {
		this.centriodDist = centriodDist;
	}
	public int getMinDist() {
		return minDist;
	}
	public void setMinDist(int minDist) {
		this.minDist = minDist;
	}
	public int getMaxDist() {
		return maxDist;
	}
	public void setMaxDist(int maxDist) {
		this.maxDist = maxDist;
	}
	public double getStd() {
		return std;
	}
	public void setStd(double std) {
		this.std = std;
	}
	
	public void calMin() {
		int min = upperLeftValue;
		
		if(upperRightValue < min ) {
			min = upperRightValue;
		}
		
		if(lowerRightValue < min) {
			min = lowerRightValue;
		}
		
		if(lowerLeftValue < min) {
			min = lowerLeftValue;
		}
		
		if(centriodValue < min) {
			min = centriodValue;
		}
		this.minValue = min;
	}
	
	public void calMax() {
		int max = upperLeftValue;
		
		if(upperRightValue > max ) {
			max = upperRightValue;
		}
		
		if(lowerRightValue > max) {
			max = lowerRightValue;
		}
		
		if(lowerLeftValue > max) {
			max = lowerLeftValue;
		}
		
		if(centriodValue > max) {
			max = centriodValue;
		}
		this.maxValue = max;
		
	}

	public void calStd() {
		 double stdTmp = Math.pow(this.upperLeftValue- this.averValue, 2);
		 stdTmp += Math.pow(this.upperRightValue- this.averValue, 2);
		 stdTmp += Math.pow(this.lowerRightValue- this.averValue, 2);
		 stdTmp += Math.pow(this.lowerLeftValue- this.averValue, 2);
		 stdTmp += Math.pow(this.centriodValue - this.averValue, 2);
		 
		 this.std = Math.sqrt(stdTmp/5);
		 
		 
	}

}
