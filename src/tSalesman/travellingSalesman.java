package tSalesman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.*;
import java.math.*;

public class travellingSalesman {
	public static int numCities = 12;
	public static float minDistance = 1000000000;
	
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		//track start time
		long startTime = System.currentTimeMillis();
		
		//define list to hold city information
		List<city> cities = new ArrayList<city>();
		
		Random rand = new Random();
		
		//randomly create n cities
		for (int i = 0; i < numCities; i++) {
			city c = new city();
			c.xCoord = rand.nextInt(100);
			c.yCoord = rand.nextInt(100);
			c.name = "city" + i;
			cities.add(c);
		}
		
		//check cities list
		printToConsole(cities);
		
		printToFile("output.txt", cities);
		
		
		//read from file
		List<distanceData> distances = readDistances("output.txt");
		
		//build file info into something to analyze
		List fileCities = new ArrayList();
		/*
		for (int i = 0; i < fileCities.size(); i++) {
		    if (fileCities.contains(fileCities.get(i).))
			//int element = fileCities.get(i);
		    
		}*/
		
		
		permute(cities,0);
		
		//calculate runtime
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total runtime = " + totalTime +"ms");
		
		String outString = "Number of cities = " + numCities+ "\n";
		outString += "Total runtime = " + totalTime +"ms\n";
		outString += "Solution distance = " + minDistance +"\n";
		printAnalysisToFile("results.txt",outString);
		
	}
	static void permute(java.util.List<city> arr, int k) throws UnsupportedEncodingException, FileNotFoundException, IOException{
    	
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
        	float distance = 0;
        	String output = "";
        	for (int i = 0; i < arr.size(); i++){
        		output += arr.get(i).name + "\t";
        		
        		if (i==(arr.size()-1)){
        			
        			distance += calculateDistance(arr.get(i),arr.get(0)); 
        			output += arr.get(0).name + "\t";
        		}else{
        			
        			distance += calculateDistance(arr.get(i),arr.get(i+1)); 
        		}
        			
        	}
        	
        	if (distance < minDistance) minDistance = distance;
        	
        	output += "\tdistance: " + distance + "\n";
            
            //printAnalysisToFile("results.txt", output);
    		

        }
    }

	//read in the text file to instruction array
	public static List<distanceData>  readDistances(String filename) throws IOException{

	   File file = new File(filename);
 	   List<distanceData> inputs = new ArrayList<distanceData>();
        
       try {
    	   InputStream inputStream = new FileInputStream(file);
    	   Reader      inputStreamReader = new InputStreamReader(inputStream);
    	   BufferedReader br = new BufferedReader(inputStreamReader);
    	   

    	   String strLine;
    	   while ((strLine = br.readLine()) != null) {

    		   String[] parts = strLine.split("\t");
    		   String city1 = parts[0];
    		   String city2 = parts[1];
    		   Float value =  Float.valueOf(parts[2]);	
    		   
    		   distanceData delta = new distanceData();
    		   delta.nameA = city1;
    		   delta.nameB = city2;
    		   delta.distance = value;
    		   inputs.add(delta);
    	    }
    	    inputStream.close();
    	   
	   } catch (FileNotFoundException e1) {
		   e1.printStackTrace();
	   }
       return inputs;
	}
	static float calculateDistance(city a, city b) {
		float d = 0;
		d = (float) Math.sqrt((a.xCoord - b.xCoord)*(a.xCoord - b.xCoord) + (a.yCoord - b.yCoord)*(a.yCoord - b.yCoord));
		return d;
	}
	public static void printToConsole(List<city> cities){
		for (int i = 0; i < cities.size(); i++){
			System.out.print("name: " + cities.get(i).name + " x: " + cities.get(i).xCoord + " y: " + cities.get(i).yCoord + "\n");
		}
	}
	public static void printToFile(String filename, List<city> cities) throws UnsupportedEncodingException, FileNotFoundException, IOException{
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) {
			float d = 0;
			//calculate distances between cities and save to file
			for (int i = 0; i < numCities; i++) {
				for (int j = i+1; j < numCities; j++) {
					d = calculateDistance(cities.get(i),cities.get(j));
					String s = cities.get(i).name + "\t" + cities.get(j).name + "\t" + d + "\n";
					writer.write(s);
				}
			}

		} catch (IOException e) {
			    //exception handling left as an exercise for the reader
		} 
	}
	public static void printAnalysisToFile(String filename, String text) throws UnsupportedEncodingException, FileNotFoundException, IOException{
		
		try (FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
			{
			out.print(text);


			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			} 

	}
}
