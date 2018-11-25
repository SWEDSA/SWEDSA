package V2;
/**
 * This will be the main class that we will use to output and manipulate data
 */
public class CrimeStopAndsearch {

	public String CrimeType;
	public int Successful;
	public int partially_successful;
	public int unsuccessful;
	public String ethnic;
	public final String SEP = ",";
	public String type;
	public String Date;
	public String Part_of_a_policing_operation;
	public String Policing_operation;
	public Float latitude;
	public Float longitude;
	public String Gender;
	public String Age_range;
	public String Self_defined_ethnicity;
	public String Officer_defined_ethnicity;
	public String Legislation;
	public String Object_of_search;
	public Boolean Outcome;
	public String Outcome_linked_to_object_of_search;
	public Boolean Removal_of_more_than_just_outer_clothing;
	public int total;
	
	public String toCSVString() {
		
		
		String unfilteredCSV = type + ", " + Date + ", " + Gender + ", " + Part_of_a_policing_operation + ", " + Policing_operation + ", " + latitude
				+ " " + longitude + " " + Age_range + " " + Self_defined_ethnicity + " " + Officer_defined_ethnicity
				+ ", " + Legislation + ", " + Object_of_search + ", " + Outcome + ", "
				+ Outcome_linked_to_object_of_search + ", " + Removal_of_more_than_just_outer_clothing;
		 String filteredCSV = unfilteredCSV.replace("null", "Unspecified").replace(", ,", ", Unspecified,");
		
		
		return filteredCSV;

	}

	/**
	 * Constructor to hold each unique column from csv Doc into it's unique variable
	 * so that we can refer back to output or manipulate data
	 * 
	 * @param csv This will be a string from readnext line that splits by
	 */

	public CrimeStopAndsearch(String csv) {
		String[] csvParts = csv.split(SEP, -1);
		int i = 0;

		type = csvParts[i++];
		Date = csvParts[i++];
		Part_of_a_policing_operation = csvParts[i++];
		Policing_operation = csvParts[i++];
		if(csvParts[i] !=null && csvParts[i].length()>0) {
		latitude = Float.parseFloat(csvParts[i++]);}
		else {latitude = null; i++;}
		if(csvParts[i] !=null && csvParts[i].length()>0) {
			longitude = Float.parseFloat(csvParts[i++]);}
		else {longitude = null; i++;}
		Gender = csvParts[i++];
		Age_range = csvParts[i++];
		Self_defined_ethnicity = csvParts[i++];
		Officer_defined_ethnicity = csvParts[i++];
		Legislation = csvParts[i++];
		Object_of_search = csvParts[i++];
		if(csvParts[i] !=null && csvParts[i].length()>0) {
		Outcome = Boolean.parseBoolean(csvParts[i++]);
		}else {Outcome = null; i++;}
		
		Outcome_linked_to_object_of_search = csvParts[i++];
		if(csvParts[i] !=null && csvParts[i].length()>0) {
		Removal_of_more_than_just_outer_clothing = Boolean.parseBoolean(csvParts[i++]);
		}else {Removal_of_more_than_just_outer_clothing =null; i++;}
	}

	/**
	 * This constructor is used to create an object for the highest legislation.
	 * @param crime
	 * @param Counter
	 */
	public CrimeStopAndsearch(String Crime, int Counter) {
		CrimeType = Crime;
		total = Counter;
	}
	
	/**
	 * This constructor is used to construct an object for the highest successful legislation.
	 * @param legislationtemp
	 * @param successfultemp
	 * @param unsuccessfultemp
	 * @param partialtemp
	 */
	public CrimeStopAndsearch(String legislationtemp, int successfultemp, int unsuccessfultemp, int partialtemp) {
		Legislation = legislationtemp;
		Successful = successfultemp;
		partially_successful = partialtemp;
		unsuccessful = unsuccessfultemp;
	}


	public int getTotal() {
		return total;
	}
	public String highestTotalLeg() {
		return CrimeType + " : " + " Total: " + total;	
	}
	public String highestTotalethnic() {
		return CrimeType + " : " + " Total: " + total;	
	}
	
	public String highestSucessLeg() {
		return Legislation + " : " + " Successful: " + Successful + " partially successful: " + partially_successful
				+ " unsuccessful: " + unsuccessful;	
	}
	
	
	public String getDate() {
		return Date;
	}
	public int getSuccessful() {
		return Successful;
	}
	public String getLegislation() {
		return Legislation;
	}

}