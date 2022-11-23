package Spring_Boot_Project.Coronavirus.Tracker.Models;

public class LocationStats {
	
	private String State;
	private String Country;
	private int Total_Cases;
	private int PrevdayCases;
	
	/*
	 * Getter and Setters for the given parameters
	 */
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public int getTotal_Cases() {
		return Total_Cases;
	}
	public void setTotal_Cases(int total_Cases) {
		Total_Cases = total_Cases;
	}
	
	 @Override
	    public String toString() {
	        return "LocationStats{" +
	                "state='" + State + '\'' +
	                ", country='" + Country + '\'' +
	                ", latestTotalCases=" + Total_Cases +
	                '}';
	    }
	public int getPrevdayCases() {
		return PrevdayCases;
	}
	public void setPrevdayCases(int prevdayCases) {
		PrevdayCases = prevdayCases;
	}
//	public void setPrevdayCases(int prevdayCases2) {
//		// TODO Auto-generated method stub
//		
//	}

}
