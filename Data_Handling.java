import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Data_Handling {

	private ArrayList<StopAndSearchFiles> fileList = new ArrayList<>();

	public void readFile(String filename) throws FileNotFoundException {
		ArrayList<CrimeStopAndSearch> StopandSearch = new ArrayList<>();

		File csvFile = new File(filename);
		Scanner csvScan = new Scanner(csvFile);
		CrimeStopAndSearch StopAndSearchTemp = null;
		csvScan.nextLine(); // read header
		while (csvScan.hasNextLine()) {
			String line = csvScan.nextLine();
			StopAndSearchTemp = new CrimeStopAndSearch(line);
			StopandSearch.add(StopAndSearchTemp);

		}
		StopAndSearchFiles File = new StopAndSearchFiles(StopandSearch);
		fileList.add(File);

		csvScan.close();
	}

	public ArrayList<StopAndSearchFiles> getFileList() {
		return fileList;
	}

	void outputCrimes() {
		List<String> fileName = FolderReader.getbasicStop_Search();
		int i = 0;
		while (i < fileName.size()) {
			for (StopAndSearchFiles file : fileList) {
				int successful = 0, unsuccessful = 0;
				System.out.println(fileName.get(i));
				i++;
				for (CrimeStopAndSearch currentCrime : file.getStopAndSearchFiles()) {

					
						int[] temp = SuccessfulSearch(currentCrime.outcome_Search);
						successful = successful + temp[0];
						unsuccessful = unsuccessful + temp[1];
						System.out.println(currentCrime.toCSVString());
					
				}
				System.out.println("There are " + file.getListSize() + " recorded crimes; ");
				System.out.println(
						successful + " Successful Searches - " + percent(successful, file.getListSize()));
				System.out.println(unsuccessful + " Unsuccessful Searches -" + percent(unsuccessful, file.getListSize()));
			}
		}
	}

	private static String percent(int num, int div) {
		double perc = ((double) num / div);
		perc *= 100;
		return String.format(" %.1f%%", perc);
	}

	public int[] SuccessfulSearch (Boolean Outcome_LinkedTo_ObjectOf_Search) {
		int successful = 0, unsuccessful = 0;
		
			if (Outcome_LinkedTo_ObjectOf_Search) {
			
				successful++;
			}
			if(!Outcome_LinkedTo_ObjectOf_Search) {
				unsuccessful++;
			}
		
	
		int[] intArray = new int[2];
		intArray[0] = successful;
		intArray[1] = unsuccessful;
		return intArray;

	}
}
