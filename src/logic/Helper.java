package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVWriter;

public class Helper {
	
	public static final int MAX_RSS = 100;
	public static final int THRESOLD_RSS = 80; 
	

	public static final int ref_col = 1158344000;
	public static final int ref_row = 320658000;
	
	public static final int convert_long_lat = 10000000;
	
	public static final int bush_court_rows = 12000;
	public static final int bush_court_cols = 12000;
	
	public static final int[] bin_type = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
	public static final int[] uni_type = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
	public static final int min_bin_type = 50;
	public static final int min_uni_type = 55;
	
	public static final String filePath = "D:\\downloads\\cleaned_data_updated_1.csv";
	public static final String outPut = "D:\\downloads\\out_data_tt";
	

	public static int calDistance(Point p1, Point p2) {
		return (int)Math.sqrt(Math.pow(p1.getRow()-p2.getRow(),2)+ Math.pow(p1.getColumn()-p2.getColumn(),2));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<AccessPoint> accessPoints = readAccessPoints(filePath);
		int blockDim = 1;
		Block[][] blks= createBlocks(blockDim,accessPoints);
		for(int i = 0; i < blks.length; i++) {
			for(int j = 0; j < blks[0].length; j++) {
				blks[i][j].generateAccessPointsRSS();
			}
		}
		writePoints(blks,outPut+"_"+Integer.toString(blockDim)+".csv", accessPoints);
	}
	
	private static void writePoints(Block[][] blks, String outputFile,List<AccessPoint> accessPoints) {
		// first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(outputFile); 
	  
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // create a List which contains String array 
	        List<String[]> data = new ArrayList<String[]>();
	        //creating header
	        ArrayList<String> tmp = new ArrayList<String>();
	        tmp.add("Index");
	        tmp.add("Centre Point");
	        tmp.add("Upper Left");
	        tmp.add("Upper Right");
	        tmp.add("Lower Left");
	        tmp.add("Lower Right");
	        
	                
	        for(int tt = 0; tt < accessPoints.size(); tt++) {
	        	
	        	String x = "bin " + accessPoints.get(tt).getName() + " Average";
	        	tmp.add(x);
	        	x = "bin " + accessPoints.get(tt).getName() + " Center";
	        	tmp.add(x);
	        	x = "bin " + accessPoints.get(tt).getName() + " Minimum";
	        	tmp.add(x);
	        	x = "bin " + accessPoints.get(tt).getName() + " Maximum";
	        	tmp.add(x);
	        }
	        //for(int tt = 1; tt < 10; tt++) {
	        //	String x = "AP " + Integer.toString(tt) + " Average";
	        //	tmp.add(x);
	        //	x = "AP " + Integer.toString(tt) + " Center";
	        //	tmp.add(x);
	        //	x = "AP " + Integer.toString(tt) + " Minimum";
	        //	tmp.add(x);
	        //	x = "AP " + Integer.toString(tt) + "Maximum";
	        //	tmp.add(x);
	        //}
	        
	        data.add(GetStringArray(tmp));
	        //String[] header = {"Index", "Centre Point", "Upper Left", "Upper Right", "Lower Left", "Lower Right"};
	        for(int i = 0; i < blks.length; i++)
	        	for(int j = 0; j < blks[0].length;j++)
	        	{
	        		tmp = new ArrayList<String>();
	        		String name = Integer.toString(i) + "," + Integer.toString(j);
	        		tmp.add(name);
	        		String centreI = Double.toString(-(double)blks[i][j].getbCentre().getRow()/(double)convert_long_lat) + ", " + Double.toString((double)blks[i][j].getbCentre().getColumn()/(double)convert_long_lat);
	        		tmp.add(centreI);
	        		String upperLeft = Double.toString(-(double)blks[i][j].getbUpperLeft().getRow()/(double)convert_long_lat) + ", " + Double.toString((double)blks[i][j].getbUpperLeft().getColumn()/(double)convert_long_lat);
	        		tmp.add(upperLeft);
	        		String upperRight = Double.toString(-(double)blks[i][j].getbUpperRight().getRow()/(double)convert_long_lat) + ", " + Double.toString((double)blks[i][j].getbUpperRight().getColumn()/(double)convert_long_lat);
	        		tmp.add(upperRight);
	        		String lowerLeft = Double.toString(-(double)blks[i][j].getbLowerLeft().getRow()/(double)convert_long_lat) + ", " + Double.toString((double)blks[i][j].getbLowerLeft().getColumn()/(double)convert_long_lat);
	        		tmp.add(lowerLeft);
	        		String lowerRight = Double.toString(-(double)blks[i][j].getbLowerRight().getRow()/(double)convert_long_lat) + ", " + Double.toString((double)blks[i][j].getbLowerRight().getColumn()/(double)convert_long_lat);
	        		tmp.add(lowerRight);
	        		
	        		Map<AccessPoint, RSS> apRss = blks[i][j].getAccessPointsRSS();
	        		List<AccessPoint> aPoints = blks[i][j].getAccessPoints();
	        		
	        		for(int t = 0; t < aPoints.size(); t++) {
	        			RSS rssTmp = apRss.get(aPoints.get(t));
	        			String tmpStr = Integer.toString(rssTmp.getAverValue());
	        			tmp.add(tmpStr);
	        			
	        			//tmpStr = acTmp.getName()+":";
	        			tmpStr = Integer.toString(rssTmp.getCentriodValue());
	        			tmp.add(tmpStr);
	        			
	        			//tmpStr = acTmp.getName()+":";
	        			tmpStr = Integer.toString(rssTmp.getMinValue());
	        			tmp.add(tmpStr);
	 
	        			//tmpStr = acTmp.getName()+":";
	        			tmpStr = Integer.toString(rssTmp.getMaxValue());
	        			tmp.add(tmpStr);
	        		}
	        	                   
	        		//addAccessPoints(blks[i][j].getAccessPointsRSS(),tmp);
	        		data.add(GetStringArray(tmp)); 
	        		        	
	        	}
	        
	        writer.writeAll(data); 
	  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
		
	}
	
	
	public static String[] GetStringArray(ArrayList<String> arr) 
	{ 
	  
	        // declaration and initialise String Array 
	        String str[] = new String[arr.size()]; 
	  
	        // ArrayList to Array Conversion 
	        for (int j = 0; j < arr.size(); j++) { 
	  
	            // Assign each value to String array 
	            str[j] = arr.get(j); 
	        } 
	  
	        return str; 
	    } 
	  
	private static List<AccessPoint> readAccessPoints(String fileName){
		List<AccessPoint> aps = new ArrayList<AccessPoint>();
        Path pathToFile = Paths.get(fileName);
        int apType = 0;
        int i = 0;
		try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {
			
            // read the first line from the text file
            String line = br.readLine();
            line = br.readLine();
            // loop until all lines are read
          
            while (line != null) {
            	
                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");
                
                apType = Integer.parseInt(attributes[6]);
                if(apType > 0) {
                	i++;
                	AccessPoint accPoint = new AccessPoint();
                	accPoint.setTypeOfAP(apType);
                	accPoint.setColumn(Integer.parseInt(attributes[7]));
                	accPoint.setRow(Integer.parseInt(attributes[8]));
                	accPoint.setTypeOfAP(apType);
                	accPoint.setName(attributes[2]);
                	if(apType == 2) {
                		accPoint.setMinRSSValue(min_uni_type);
                		accPoint.setDecayRSSFor30m(uni_type);
                	}else {
                		accPoint.setMinRSSValue(min_bin_type);
                		accPoint.setDecayRSSFor30m(bin_type);
                	}
                	aps.add(accPoint);
                }
                line = br.readLine();
            }
            //return aps;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return aps;
	}
	
	private static Block[][] createBlocks(int blockDim,List<AccessPoint> accessPoints) {
		// TODO Auto-generated method stub
		int rows = bush_court_rows/(blockDim*100);
		int cols = bush_court_cols/(blockDim*100);
		Block[][] b = new Block[rows][cols];
		for(int i = 0; i <rows; i++) {
			for(int j=0; j < cols; j++) {
				b[i][j] = new Block();
				b[i][j].setAccessPoints(accessPoints);
				int baseCol = j * blockDim *100 + ref_col;
				int baseRow = i * blockDim * 100 + ref_row;
				Point bCentre = new Point();
				int column;
				column = baseCol + (blockDim*100/2);
				bCentre.setColumn(column);
				int row;
				row = baseRow + (blockDim*100/2);
				bCentre.setRow(row);
				b[i][j].setbCentre(bCentre);
				Point bLowerLeft = new Point();
				column = baseCol;
				bLowerLeft.setColumn(column);
				row = baseRow;
				bLowerLeft.setRow(row);
				b[i][j].setbLowerLeft(bLowerLeft);
				
				
				Point bLowerRight = new Point();
				column = baseCol + blockDim*100;
				bLowerRight.setColumn(column);
				row = baseRow ;
				bLowerRight.setRow(row);
				b[i][j].setbLowerRight(bLowerRight);
				
				Point bUpperLeft = new Point();
				column = baseCol;
				bUpperLeft.setColumn(column);
				row = baseRow + blockDim*100;
				bUpperLeft.setRow(row);
				b[i][j].setbUpperLeft(bUpperLeft);
				
				Point bUpperRight = new Point();
				column = baseCol + blockDim*100;
				bUpperRight.setColumn(column);
				row = baseRow + blockDim*100;
				bUpperRight.setRow(row);
				b[i][j].setbUpperRight(bUpperRight);
			}
		}
		return b;
	}
}
