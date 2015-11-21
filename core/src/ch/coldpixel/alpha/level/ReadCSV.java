package ch.coldpixel.alpha.level;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {
    private ArrayList<String> csvArray = new ArrayList<String>();
    
    public ReadCSV() {

    }
    
    public ArrayList<String> read(String path){
        String csvFile = path;
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                csvArray.add(line);
            }   
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        
        return csvArray;
    }
}