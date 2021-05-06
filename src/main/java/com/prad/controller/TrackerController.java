package com.prad.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.prad.dto.District;
import com.prad.dto.State;
import com.prad.dto.VaccineCenterDto;
import com.prad.services.TrackerService;

@Controller
public class TrackerController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	TrackerService trackerService;

	Format format = NumberFormat.getNumberInstance(new Locale("en", "in"));

	@GetMapping(value = { "/{count}", "/" })
	public String index2(@PathVariable(value = "count", required = false) String isCountEnable, Model m, HttpServletRequest request)
			throws IOException, ParseException {

		Socket socket = new Socket();
		socket.connect(new InetSocketAddress("google.com", 80));
		
		State state = new State();
		List<Object> stateList = trackerService.getStateWiseData();
		for (Object l : stateList) {
			LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) l;
			if (map.get("State").equalsIgnoreCase("Total")) {
				state.setActive(NumberFormat.getNumberInstance(Locale.UK).format(Integer.parseInt(map.get("Active"))));
				state.setConfirmed(
						NumberFormat.getNumberInstance(Locale.UK).format(Integer.parseInt(map.get("Confirmed"))));
				state.setRecovered(
						NumberFormat.getNumberInstance(Locale.UK).format(Integer.parseInt(map.get("Recovered"))));
				state.setDate(map.get("Last_Updated_Time"));
				break;
			}
		}

		List<District> dlist = trackerService.getDistricWiseData();

		// List<Object> slist = trackerService.getStateWiseData();

		List<State> mapStateList = new ArrayList<State>();

		LinkedHashMap<String, String> mapState = null;
		for (Object l : stateList) {
			mapState = (LinkedHashMap) l;
			State s = trackerService.stateMapping(mapState);
			mapStateList.add(s);
		}

		Date date1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(state.getDate());
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, hh:mm a");
		String strDate = formatter.format(date1);
		state.setDate(strDate);

		double totalVaccine = trackerService.getVaccineData();
		double totalVaccinePercentage = (totalVaccine / 1391197718) * 100;

		String totalVaccineString = format.format(new BigDecimal(totalVaccine));

		List<District> districtList = trackerService.getDistricWiseDataMap();

		isCountEnable = StringUtils.isBlank(isCountEnable) == true ? "N" : "Y";

		boolean isLocalUrl=request.getRequestURL().toString().contains("localhost") ? true : false;
		
		List<State> dailyGraphList = trackerService.getLast30DayCases();
		
		m.addAttribute("districWiseDataMap", districtList);
		m.addAttribute("totalVaccine", totalVaccineString);
		m.addAttribute("totalVaccinePercentage", Math.round(totalVaccinePercentage));
		m.addAttribute("mapStateList", mapStateList);
		m.addAttribute("state", state);
		m.addAttribute("districtList", dlist);
		m.addAttribute("isCountEnable", isCountEnable);
		m.addAttribute("isLocalUrl", isLocalUrl);
		m.addAttribute("dailyGraphList", dailyGraphList);
		m.addAttribute("localIpAddress", socket.getLocalAddress());
		
		return "dashboard";
	}

	@GetMapping(value = "/map")
	public String getAllState(Model m) throws IOException, ParseException {

		List<Object> list = trackerService.getStateWiseData();

		List<State> dlist = new ArrayList<State>();

		LinkedHashMap<String, String> map = null;
		for (Object l : list) {

			map = (LinkedHashMap) l;

			State s = trackerService.stateMapping(map);

			dlist.add(s);
		}

		m.addAttribute("mapStateList", dlist);

		return "map";

	}

	@GetMapping(value = "/index")
	public String index(Model m) throws IOException {
		return "test";

	}

	@GetMapping(value = "/vaccineDashboard")
	public String vaccineDashboard(Model m) throws IOException {
		
		List<District> districtDto = trackerService.getDictrictByStateId("21");
		m.addAttribute("districtDto", districtDto);
		return "vaccineDashboard";

	}
	
	@GetMapping(value = "/searchByPincode")
	public String searchByPincode(Model m,HttpServletRequest request) throws Exception {
		
		String pincode=request.getParameter("pincode");
		String date="";//request.getParameter("date");
		
		List<VaccineCenterDto> responseDto = trackerService.searchByPincode(pincode, date);			
		List<District> districtDto = trackerService.getDictrictByStateId("21");
		
		//m.addAttribute("stateList", trackerService.getStateIdList());
		m.addAttribute("pincode", pincode);
		m.addAttribute("responseDto", responseDto);
		m.addAttribute("districtDto", districtDto);
		
		return "vaccineDashboard";

	}
	
	@PostMapping(value = "/getSlotByDistrict")
	public String getDistrictByStateId(Model m, HttpServletRequest request) throws Exception {
		
		String districtId=request.getParameter("districtList");
		System.out.println("PRASAD::"+districtId);
		List<VaccineCenterDto> centerListDto = trackerService.searchByDistrict(districtId);
		
					
		List<District> districtDto = trackerService.getDictrictByStateId("21");

		m.addAttribute("districtDto", districtDto);
		m.addAttribute("responseDto", centerListDto);
		
		
		return "vaccineDashboard";
		

	}
	
	
}
