package tSalesman;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.math.*;

public class travellingSalesman {
	public static int numCities = 3;
	
	public static void main(String[] args) {
		//track start time
		long startTime = System.currentTimeMillis();
		
		//define list to hold city information
		List cities = new ArrayList();
		
		Random rand = new Random();
		
		//randomly create n cities
		for (int i = 0; i < numCities; i++) {
			city c = new city();
			c.xCoord = rand.nextInt(100);
			c.yCoord = rand.nextInt(100);
			cities.add(c);
		}
		//calculate distances between cities and save to file
		for (int i = 0; i < numCities; i++) {
			for (int j = i; j < numCities; j++) {
				
			}
		}
		
		
		//calculate runtime
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total runtime = " + totalTime +"ms");
		
	}
	float calculateDistance(city a, city b) {
		float d = 0;
		d = (float) Math.sqrt((a.xCoord - b.xCoord) + (a.yCoord - b.yCoord));
		return d;
	}
	public static void printToFile(String filename, city a, city b,float dist, long time) throws UnsupportedEncodingException, FileNotFoundException, IOException{
		
		try (FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
			{
			if (time == 0) {
				out.println("Total runtime = " + time +"ms");
			} else {
				
			}
			out.println("");

			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			} 

	}
}
