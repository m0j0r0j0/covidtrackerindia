package com.prad.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.DecimalFormat;
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
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.prad.dto.District;
import com.prad.dto.State;
import com.prad.dto.VaccineCenterDto;
import com.prad.services.TrackerService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Controller
@EnableScheduling
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
				state.setActive(format(Double.parseDouble(map.get("Active"))));
				state.setConfirmed(format(Double.parseDouble(map.get("Confirmed"))));
				state.setRecovered(format(Double.parseDouble(map.get("Recovered"))));
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

		String totalVaccineString = format(totalVaccine);//format.format(new BigDecimal(totalVaccine));

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
	
	
	@GetMapping(value = "/certificate")
	public String certificateDashboard(Model m, HttpServletRequest request) throws Exception {
		
		return "downloadCertificateDashboard";

	}
	
	@GetMapping(value = "/sendOTP")
	@ResponseBody
	public String sendOTP(Model m, HttpServletRequest request) throws Exception {

		String mobileNo=request.getParameter("mobileNo");
		
		return trackerService.sendOTP(mobileNo);
	}
	
	@GetMapping(value = "/verifyOTP")
	public String verifyOTP(Model m, HttpServletRequest request) throws Exception {

		String otp=request.getParameter("otp");
		String txnId=request.getParameter("txnId");
		
		if(StringUtils.isBlank(otp) || StringUtils.isBlank(txnId)) {
			System.out.println("OTP:"+otp);
			System.out.println("TXNID:"+txnId);
			return "downloadCertificateDashboard";
		}
		
		String token = trackerService.verifyOTP(otp, txnId);
		 
		m.addAttribute("token",token);
		 
		return "beneficaryId";
	}
	
	
	@GetMapping(value = "/downloadCertificate")
	@Produces("application/pdf")	
	public HttpEntity<byte[]> downloadCertificate(Model m, HttpServletRequest request,HttpServletResponse response)  {
		
		String token=request.getParameter("token");
		String beneficiaryId=request.getParameter("beneficiaryId");
		
		ResponseEntity<byte[]> response2 = trackerService.downloadCertificate(token, beneficiaryId);
        
        HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_PDF);
	    header.set(HttpHeaders.CONTENT_DISPOSITION,
	                   "attachment; filename="+beneficiaryId+".pdf");
	    header.setContentLength(response2.getBody().length);
	    
	    m.addAttribute("token",token);
	    
	    return new HttpEntity<byte[]>(response2.getBody(), header);
	}
	
	@Scheduled(fixedRate = 1000*30)
	@GetMapping("/slotfinder/find")
	public void slotfinder() {
		try {
			
			Date date=new Date();
			
			List<VaccineCenterDto> centerListDto = trackerService.searchByDistrict("366");
			
			if(centerListDto.size()!=0) {
				
				for(VaccineCenterDto dto : centerListDto) {
					
					if(dto.getVaccine().equalsIgnoreCase("COVISHIELD") && dto.getMin_age_limit().equals("45")) {
						System.out.println("FOUND: "+dto.getVaccine()+"- Age: "+dto.getMin_age_limit());
						
						final String ACCOUNT_SID = "AC2c957483acd21df57391e2df69f1e252";
						final String AUTH_TOKEN = "38a74ca2123594afdf1c85b1d3f104df";
						Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
						
						String msg=dto.getName()+", "+dto.getPincode()+",\n"+dto.getDate()+",\n"+dto.getAvailable_capacity();
				        Message message = Message
				                .creator(new PhoneNumber("+917977738033"), // to
				                        new PhoneNumber("+15055025341"), // from
				                        msg)
				                .create();
				        System.out.println(""+message.getSid());
						
					}else {
						System.out.println(dto.getVaccine()+"::"+dto.getMin_age_limit());
					}
				}
			}else {
		        System.out.println("NOT : "+date);
			}
			
		} catch (Exception e) {
			System.out.println("Exception in SlotFinder:"+ e);
		}
	}
	
	public static String format(double value) {
	    if(value < 1000) {
	        return format("###", value);
	    } else {
	        double hundreds = value % 1000;
	        int other = (int) (value / 1000);
	        return format(",##", other) + ',' + format("000", hundreds);
	    }
	}
	
	private static String format(String pattern, Object value) {
	    return new DecimalFormat(pattern).format(value);
	}
	
	@PostMapping("/testswagger")
	@ResponseBody
	public State getSwagger(@RequestBody District s2) {
		State s = new State();
		s.setId(s2.getId());
		s.setState("Test");
		
		return s;
	}
	
}
