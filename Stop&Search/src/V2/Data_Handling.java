package V2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Data_Handling {

	public final String SEP = ",";
	private ArrayList<CrimeStopAndsearch> mergedFiles = new ArrayList<>();
	private TreeMap<String, List<CrimeStopAndsearch>> legislationTree = new TreeMap<>();
	private Map<String, List<CrimeStopAndsearch>> objectOfSearchTree = new HashMap<>();
	

	public void readFile(String filename) throws FileNotFoundException {

		File csvFile = new File(filename);
		String[] parts = filename.split("-");
		Scanner csvScan = new Scanner(csvFile);
		csvScan.nextLine(); // read header
		while (csvScan.hasNextLine()) {
			String line = csvScan.nextLine();
			String[] temp = line.split(SEP, -1);
			temp[3] = parts[3];
			String line2 = String.join(",", temp);
			mergedFiles.add(new CrimeStopAndsearch(line2));
		}
		csvScan.close();
	}
	public void doOtherStuff()
	{	
		for(CrimeStopAndsearch e: mergedFiles) {
			List<CrimeStopAndsearch> legList = legislationTree.get(e.Legislation);
			if (legList == null)
			{
				legList = new ArrayList<>();
				legislationTree.put(e.Legislation, legList);
			}
			legList.add(e);
			
			List<CrimeStopAndsearch> objectSearchList = objectOfSearchTree.get(e.Object_of_search);
			if (objectSearchList == null)
			{
				objectSearchList = new ArrayList<>();
				objectOfSearchTree.put(e.Object_of_search, objectSearchList);
			}
			objectSearchList.add(e);
		}
		
		
		int count = 0;
		long start = System.currentTimeMillis();
		List<CrimeStopAndsearch> matches = objectOfSearchTree.get("Controlled drugs");
		for (CrimeStopAndsearch stop : matches)
			count++;
			//System.out.println(stop);
		long total = System.currentTimeMillis() - start;
		System.out.println("This took "+total+" to count "+count);
		
		long start2 = System.nanoTime();

		int count2 = 0;
		for (CrimeStopAndsearch stop : mergedFiles)
			if (stop.Object_of_search.equals("Controlled drugs"))
			 count2++;
		long total2 = System.currentTimeMillis() - start2;
		System.out.println("This took "+total2+" to count "+count2);
		
		System.out.println("set size "+mergedFiles.size());
		
	}
	public TreeMap<String, List<CrimeStopAndsearch>> getLegislationTree() {
		return legislationTree;
	}
	public Map<String, List<CrimeStopAndsearch>> getObjectOfSearchTree() {
		return objectOfSearchTree;
	}
}
