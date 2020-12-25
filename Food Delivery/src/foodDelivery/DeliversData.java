package foodDelivery;

import java.io.FileWriter;
import java.io.IOException;

public class DeliversData {
	public static void add(String newValues) {
		try {
		      FileWriter myWriter = new FileWriter("DeliversData.txt",true);
		      myWriter.write(newValues+"\n");//add other data in this line like coordinates and stuff
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  }
	
	/*public static void main(String[] args) {
	    
	  }*/
}
