package com.prad.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.prad.dto.District;
import com.prad.dto.State;
import com.prad.dto.VaccineCenterDto;
import com.prad.services.TrackerService;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
	public HttpEntity<byte[]> downloadCertificate(Model m, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String token=request.getParameter("token");
		String beneficiaryId=request.getParameter("beneficiaryId");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		headers.set("Authorization", "Bearer "+token);
		headers.set("Content-type", "application/pdf");
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		String FILE_URL = "/Users/prasadtikkas/Desktop/p.pdf";
        
        ResponseEntity<byte[]> response2 = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/registration/certificate/public/download?beneficiary_reference_id="+beneficiaryId+"", HttpMethod.GET, entity, byte[].class);
        Files.write(Paths.get(FILE_URL), response2.getBody());

	    m.addAttribute("token",token);  
		
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_PDF);
	    header.set(HttpHeaders.CONTENT_DISPOSITION,
	                   "attachment; filename="+beneficiaryId+".pdf");
	    header.setContentLength(response2.getBody().length);
	    
	    return new HttpEntity<byte[]>(response2.getBody(), header);
	}
	
	@Scheduled(fixedRate = 1000*30)
	@GetMapping("/slotfinder/find")
	public void slotfinder() {
		try {
			List<VaccineCenterDto> centerListDto = trackerService.searchByDistrict("366");
			
			if(centerListDto.size()!=0) {
				
				for(VaccineCenterDto dto : centerListDto) {
					
					if(dto.getVaccine().equalsIgnoreCase("COVISHIELD") && dto.getMin_age_limit().equals("45")) {
						System.out.println("FOUND!!!");
						final JFXPanel fxPanel = new JFXPanel();		 
						String bip = "alarm.mp3";
						Media hit = new Media(new File(bip).toURI().toString());
						MediaPlayer mediaPlayer = new MediaPlayer(hit);
						mediaPlayer.play();  
					}else {
						System.out.println(dto.getVaccine()+"::"+dto.getMin_age_limit());
					}
				}
			}else {
		        System.out.println("Slot not Found");
			}
			
		} catch (Exception e) {
			System.out.println("Exception in SlotFinder:"+ e);
		}
	}
	
	public static void main(String[] args) throws Exception { 
		
	}
	
}
