package edu.sjsu.cab.algorithm;
import java.util.Random;


public class Vrp
{

	public static void main(String[] args) {
		
		//initialize variables 
		Distance dist = new Distance();
		int[][] vhpMapping;
		int m;
		
		//randomly create dimensions		
		m = getDimensions();
		
		vhpMapping = dist.matrixGenerator(m);
		System.out.println("Dimensions: " + m +"x" + m);
		//dist.printMatrix(vhpMapping);
		dist.clarkeAndWright(vhpMapping, m);
		//vhpMapping = dist.clarkeAndWright(vhpMapping, m);
		//dist.printMatrix(vhpMapping);
		
	}
	
	public static int getDimensions(){

		Random rand = new Random();		
		return(rand.nextInt(2)+4);
		
	}
	
}