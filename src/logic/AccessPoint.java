package logic;

public class AccessPoint extends Point{
	private int minRSSValue;
	private int[] decayRSSFor30m;
	private int typeOfAP;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTypeOfAP() {
		return typeOfAP;
	}
	public void setTypeOfAP(int typeOfAP) {
		this.typeOfAP = typeOfAP;
	}
	public AccessPoint() {
		decayRSSFor30m = new int[30];
	}
	public int getMinRSSValue() {
		return minRSSValue;
	}
	public void setMinRSSValue(int minRSSValue) {
		this.minRSSValue = minRSSValue;
	}
	public int[] getDecayRSSFor30m() {
		return decayRSSFor30m;
	}
	public void setDecayRSSFor30m(int[] decayRSSFor30m) {
		for(int i = 0; i < 30; i++) {
			this.decayRSSFor30m[i] = decayRSSFor30m[i];
		}
		//this.decayRSSFor30m = decayRSSFor30m;
	}
	
	public int getRSSForDist(int dist) {
		int index = dist/100;
		int remind = dist % 100;
		int rssValue = 0;
		if(index > 30)
			rssValue = Helper.MAX_RSS;
		else if(index == 30)
			rssValue = decayRSSFor30m[index -1] + this.minRSSValue;
		else if(index == 0)
			rssValue = decayRSSFor30m[0] + this.minRSSValue;
		else if(remind != 0 ) {
			float upperValue = decayRSSFor30m[index ];
			float lowerValue = decayRSSFor30m[index - 1];
			int addedValue = (int) Math.round(((float) remind/100.0)*(upperValue - lowerValue));
			int finalValue = decayRSSFor30m[index-1] + addedValue + this.minRSSValue;
			rssValue = finalValue; 
		}else {
			rssValue =  decayRSSFor30m[index-1] + this.minRSSValue;
		}
		if(rssValue > Helper.THRESOLD_RSS )
			rssValue =  Helper.MAX_RSS;
		return rssValue;
	}
	
	

}
