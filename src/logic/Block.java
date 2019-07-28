package logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Block {
	private Point bCentre;
	private Point bLowerLeft;
	private Point bLowerRight;
	private Point bUpperLeft;
	private Point bUpperRight;
	
	private Map<AccessPoint, RSS> accessPointsRSS;
	private List<AccessPoint> accessPoints;
	
	
	public Point getbCentre() {
		return bCentre;
	}
	public Map<AccessPoint, RSS> getAccessPointsRSS() {
		return accessPointsRSS;
	}
	public void setAccessPointsRSS(Map<AccessPoint, RSS> accessPointsRSS) {
		this.accessPointsRSS = accessPointsRSS;
	}
	public List<AccessPoint> getAccessPoints() {
		return accessPoints;
	}
	public void setAccessPoints(List<AccessPoint> accessPoints) {
		this.accessPoints = accessPoints;
	}
	public void setbCentre(Point bCentre) {
		this.bCentre = bCentre;
	}
	public Point getbLowerLeft() {
		return bLowerLeft;
	}
	public void setbLowerLeft(Point bLowerLeft) {
		this.bLowerLeft = bLowerLeft;
	}
	public Point getbLowerRight() {
		return bLowerRight;
	}
	public void setbLowerRight(Point bLowerRight) {
		this.bLowerRight = bLowerRight;
	}
	public Point getbUpperLeft() {
		return bUpperLeft;
	}
	public void setbUpperLeft(Point bUpperLeft) {
		this.bUpperLeft = bUpperLeft;
	}
	public Point getbUpperRight() {
		return bUpperRight;
	}
	public void setbUpperRight(Point bUpperRight) {
		this.bUpperRight = bUpperRight;
	}
	
	
	public void generateAccessPointsRSS() {
		
		int upperLeftDist = 0;
		int upperRightDist = 0;
		int lowerLeftDist = 0;
		int lowerRightDist = 0;
		int centreDist = 0;
		int averageDist = 0;
		this.accessPointsRSS = new HashMap<AccessPoint,RSS>();
		for(AccessPoint a : this.accessPoints) {
			RSS rss = new RSS();
			upperLeftDist = Helper.calDistance(a, this.bUpperLeft);
			upperRightDist = Helper.calDistance(a, this.bUpperRight);
			lowerLeftDist = Helper.calDistance(a, this.bLowerLeft);
			lowerRightDist = Helper.calDistance(a, this.bLowerRight);
			centreDist = Helper.calDistance(a, this.bCentre);
			
			averageDist = (upperLeftDist + upperRightDist + lowerLeftDist + lowerRightDist + centreDist)/5;
			
			rss.setAverDist(averageDist);
			rss.setAverValue(a.getRSSForDist(averageDist));
			
			rss.setUpperLeft(upperLeftDist);
			rss.setUpperLeftValue(a.getRSSForDist(upperLeftDist));

			rss.setUpperRight(upperRightDist);
			rss.setUpperRightValue(a.getRSSForDist(upperRightDist));

			rss.setLowerLeft(lowerLeftDist);
			rss.setLowerLeftValue(a.getRSSForDist(lowerLeftDist));
			
			rss.setLowerRight(lowerRightDist);
			rss.setLowerRightValue(a.getRSSForDist(lowerRightDist));

			rss.setCentriodDist(centreDist);
			rss.setCentriodValue(a.getRSSForDist(centreDist));
			
			rss.calMin();
			rss.calMax();
			rss.calStd();
			
			accessPointsRSS.put(a, rss);
			
		}
	}
	
	
}
