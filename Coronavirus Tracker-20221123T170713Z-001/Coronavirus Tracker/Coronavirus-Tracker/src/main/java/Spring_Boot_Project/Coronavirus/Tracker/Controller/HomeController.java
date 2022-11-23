package Spring_Boot_Project.Coronavirus.Tracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Spring_Boot_Project.Coronavirus.Tracker.Models.LocationStats;
import Spring_Boot_Project.Coronavirus.Tracker.Services.TrackerServices;

@Controller
public class HomeController {
	
	@Autowired
	TrackerServices trackservice;
	@GetMapping("/")
	public String controller(Model model)
	{
		  List<LocationStats> allStats = trackservice.getLocStats();
	        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotal_Cases()).sum();
	        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getPrevdayCases()).sum();
	        model.addAttribute("locationStats", allStats);
	        model.addAttribute("totalReportedCases", totalReportedCases);
	        model.addAttribute("totalNewCases", totalNewCases);
			return "tracker";
	}
	

}
