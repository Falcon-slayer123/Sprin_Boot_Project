package Spring_Boot_Project.Coronavirus.Tracker.Services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import Spring_Boot_Project.Coronavirus.Tracker.Models.LocationStats;

@Service
public class TrackerServices {
	
	/*
	 * Services Class for the Request and Response of the Data
	 */
	
	public static String VIRUS_DATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationStats> locstats = new ArrayList<>();
	
	public List<LocationStats> getLocStats() {
		return locstats;
		
	}
	
	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchData() throws IOException, InterruptedException {
		List<LocationStats> newstats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpresponse = client.send(request,HttpResponse.BodyHandlers.ofString());
		StringReader csvread = new StringReader(httpresponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvread);
		for (CSVRecord record : records) {
			LocationStats loc_stats = new LocationStats();
			loc_stats.setState(record.get("Province/State"));
			loc_stats.setCountry(record.get("Country/Region"));
			int latest_cases = (Integer.parseInt(record.get(record.size()-1)));
			int previous_cases = (Integer.parseInt(record.get(record.size()-2)));
			loc_stats.setTotal_Cases(latest_cases);
			loc_stats.setPrevdayCases(latest_cases - previous_cases);
			newstats.add(loc_stats);
			
		}
		this.locstats=newstats;
		
	}

}

