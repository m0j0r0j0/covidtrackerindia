package com.prad.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.prad.dto.District;
import com.prad.dto.State;
import com.prad.dto.VaccineCenterDto;

@Service
public class TrackerService {

	Format format = NumberFormat.getNumberInstance(new Locale("en", "in"));
	
	@Autowired
	RestTemplate restTemplate;
	
	ObjectMapper mapper = new ObjectMapper();

	public List<Object> getStateWiseData() throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		String input = restTemplate.exchange("https://api.covid19india.org/csv/latest/state_wise.csv", HttpMethod.GET,
				entity, String.class).getBody();

		CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();

		CsvMapper csvMapper = new CsvMapper();

		// Read data from CSV file

		return csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

	}

	public List<District> getDistricWiseData() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		String input = restTemplate.exchange("https://api.covid19india.org/csv/latest/district_wise.csv",
				HttpMethod.GET, entity, String.class).getBody();
		CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
		CsvMapper csvMapper = new CsvMapper();

		List<Object> list = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();
		List<District> dlist = new ArrayList<District>();
		LinkedHashMap<String, String> map = null;
		for (Object l : list) {

			map = (LinkedHashMap<String, String>) l;

			if (!map.get("State_Code").equalsIgnoreCase("MH")) {
				continue;
			}

			if (map.get("District").equalsIgnoreCase("Mumbai Suburban")
					|| map.get("District").equalsIgnoreCase("Other State")
					|| map.get("District").equalsIgnoreCase("Unknown")
					|| map.get("District").equalsIgnoreCase("Hingoli")
					|| map.get("District").equalsIgnoreCase("Sindhudurg")) {
				continue;
			}

			District d = new District();
			d.setActive(Integer.parseInt(map.get("Active")));
			d.setConfirmed(map.get("Confirmed"));
			d.setDeceased(map.get("Deceased"));
			d.setDelta_Active(map.get("Delta_Active"));
			d.setDelta_Confirmed(map.get("Delta_Confirmed"));
			d.setDelta_Deceased(map.get("Delta_Deceased"));
			d.setDelta_Recovered(map.get("Delta_Recovered"));
			d.setDistrict(map.get("District"));
			d.setDistrict_Key(map.get("District_Key"));
			d.setDistrict_Notes(map.get("District_Notes"));
			d.setLast_Updated(map.get("Last_Updated"));
			d.setMigrated_Other(map.get("Migrated_Other"));
			d.setRecovered(map.get("Recovered"));
			d.setSlNo(map.get("SlNo"));
			d.setState(map.get("State"));
			d.setState_Code(map.get("State_Code"));

			dlist.add(d);
		}

		return dlist;

	}

	public State stateMapping(LinkedHashMap<String, String> map) throws ParseException {

		Date date1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(map.get("Last_Updated_Time"));
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, hh:mm a");
		String strDate = formatter.format(date1);

		State s = new State();
		s.setActive(map.get("Active"));
		s.setState_code(map.get("State_code").toLowerCase());
		s.setState(map.get("State"));
		s.setDate(strDate);
		s.setConfirmed(map.get("Confirmed"));
		s.setRecovered(map.get("Recovered"));
		s.setTodaysRecovered(format.format(new BigDecimal(map.get("Delta_Recovered"))));
		s.setTodaysConfirmed(format.format(new BigDecimal(map.get("Delta_Confirmed"))));

		if (s.getState_code().equalsIgnoreCase("MH")) {
			s.setId("021");
		} else if (s.getState_code().equalsIgnoreCase("GJ")) {
			s.setId("012");
		} else if (s.getState_code().equalsIgnoreCase("UP")) {
			s.setId("033");
		} else if (s.getState_code().equalsIgnoreCase("RJ")) {
			s.setId("029");
		} else if (s.getState_code().equalsIgnoreCase("JK")) {
			s.setId("015");
		} else if (s.getState_code().equalsIgnoreCase("AP")) {
			s.setId("002");
		} else if (s.getState_code().equalsIgnoreCase("KL")) {
			s.setId("018");
		} else if (s.getState_code().equalsIgnoreCase("KA")) {
			s.setId("017");
		} else if (s.getState_code().equalsIgnoreCase("TN")) {
			s.setId("031");
		} else if (s.getState_code().equalsIgnoreCase("TG")) {
			s.setId("036");
		} else if (s.getState_code().equalsIgnoreCase("DL")) {
			s.setId("010");
		} else if (s.getState_code().equalsIgnoreCase("WB")) {
			s.setId("035");
		} else if (s.getState_code().equalsIgnoreCase("CT")) {
			s.setId("007");
		} else if (s.getState_code().equalsIgnoreCase("HR")) {
			s.setId("013");
		} else if (s.getState_code().equalsIgnoreCase("BR")) {
			s.setId("005");
		} else if (s.getState_code().equalsIgnoreCase("MP")) {
			s.setId("020");
		} else if (s.getState_code().equalsIgnoreCase("PB")) {
			s.setId("028");
		} else if (s.getState_code().equalsIgnoreCase("JH")) {
			s.setId("016");
		} else if (s.getState_code().equalsIgnoreCase("UT")) {
			s.setId("034");
		} else if (s.getState_code().equalsIgnoreCase("HP")) {
			s.setId("014");
		} else if (s.getState_code().equalsIgnoreCase("GA")) {
			s.setId("011");
		} else if (s.getState_code().equalsIgnoreCase("PY")) {
			s.setId("027");
		} else if (s.getState_code().equalsIgnoreCase("TR")) {
			s.setId("032");
		} else if (s.getState_code().equalsIgnoreCase("MN")) {
			s.setId("022");
		} else if (s.getState_code().equalsIgnoreCase("CH")) {
			s.setId("006");
		} else if (s.getState_code().equalsIgnoreCase("AR")) {
			s.setId("003");
		} else if (s.getState_code().equalsIgnoreCase("ML")) {
			s.setId("023");
		} else if (s.getState_code().equalsIgnoreCase("NL")) {
			s.setId("025");
		} else if (s.getState_code().equalsIgnoreCase("LA")) {
			s.setId("015");
		} else if (s.getState_code().equalsIgnoreCase("SK")) {
			s.setId("030");
		} else if (s.getState_code().equalsIgnoreCase("AN")) {
			s.setId("001");
		} else if (s.getState_code().equalsIgnoreCase("MZ")) {
			s.setId("024");
		} else if (s.getState_code().equalsIgnoreCase("DN")) {
			s.setId("008");
		} else if (s.getState_code().equalsIgnoreCase("LD")) {
			s.setId("019");
		} else if (s.getState_code().equalsIgnoreCase("OR")) {
			s.setId("026");
		} else if (s.getState_code().equalsIgnoreCase("AS")) {
			s.setId("004");
		} else {
			s.setId("0");
		}

		return s;
	}

	public double getVaccineData() throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String input = restTemplate.exchange("https://api.covid19india.org/csv/latest/vaccine_doses_statewise.csv",
				HttpMethod.GET, entity, String.class).getBody();

		CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
		CsvMapper csvMapper = new CsvMapper();
		List<Object> vlist = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

		State vaccineState = new State();
		for (Object l : vlist) {

			LinkedHashMap<String, String> vaccineMap = (LinkedHashMap<String, String>) l;

			if (vaccineMap.get("State").equalsIgnoreCase("Total")) {

				for(Map.Entry<String, String> innerMap : vaccineMap.entrySet()) {
					
					if(innerMap.getValue().equalsIgnoreCase("Total")) {
						continue;
					}
					
					if(StringUtils.isBlank(innerMap.getValue())) {
						continue;
					}
					
					String getValue=StringUtils.isBlank(innerMap.getValue())==true ? "0":innerMap.getValue() ;
					vaccineState.setActive(NumberFormat.getNumberInstance(Locale.UK).format(Integer.parseInt(getValue)));
				}
				break;
			}
		}

		double totalVaccinated = Double.parseDouble(vaccineState.getActive().replace(",", ""));

		return totalVaccinated;

	}
	
	
	public List<District> getDistricWiseDataMap() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		String input = restTemplate.exchange("https://api.covid19india.org/csv/latest/district_wise.csv",
				HttpMethod.GET, entity, String.class).getBody();
		CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
		CsvMapper csvMapper = new CsvMapper();

		List<Object> list = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();
		List<District> dlist = new ArrayList<District>();
		LinkedHashMap<String, String> map = null;
		for (Object l : list) {

			map = (LinkedHashMap<String, String>) l;

			if (!map.get("State_Code").equalsIgnoreCase("MH")) {
				continue;
			}			

			District d = new District();
			d.setActive(Integer.parseInt(map.get("Active")));
			d.setConfirmed(map.get("Confirmed"));
			d.setDeceased(map.get("Deceased"));
			d.setDelta_Active(map.get("Delta_Active"));
			d.setDelta_Confirmed(map.get("Delta_Confirmed"));
			d.setDelta_Deceased(map.get("Delta_Deceased"));
			d.setDelta_Recovered(map.get("Delta_Recovered"));
			d.setDistrict(map.get("District"));
			d.setDistrict_Key(map.get("District_Key"));
			d.setDistrict_Notes(map.get("District_Notes"));
			d.setLast_Updated(map.get("Last_Updated"));
			d.setMigrated_Other(map.get("Migrated_Other"));
			d.setRecovered(map.get("Recovered"));
			d.setSlNo(map.get("SlNo"));
			d.setState(map.get("State"));
			d.setState_Code(map.get("State_Code"));

			if (d.getDistrict().equalsIgnoreCase("Ahmednagar")) {
				d.setId("IN.MH.AH");
			} else if (d.getDistrict().equalsIgnoreCase("Akola")) {
				d.setId("IN.MH.AK");
			} else if (d.getDistrict().equalsIgnoreCase("Amravati")) {
				d.setId("IN.MH.AM");
			} else if (d.getDistrict().equalsIgnoreCase("Aurangabad")) {
				d.setId("IN.MH.AU");
			} else if (d.getDistrict().equalsIgnoreCase("Beed")) {
				d.setId("IN.MH.BI");
			} else if (d.getDistrict().equalsIgnoreCase("Bhandara")) {
				d.setId("IN.MH.BH");
			} else if (d.getDistrict().equalsIgnoreCase("Buldhana")) {
				d.setId("IN.MH.BU");
			} else if (d.getDistrict().equalsIgnoreCase("Chandrapur")) {
				d.setId("IN.MH.CH");
			} else if (d.getDistrict().equalsIgnoreCase("Dhule")) {
				d.setId("IN.MH.DH");
			} else if (d.getDistrict().equalsIgnoreCase("Gadchiroli")) {
				d.setId("IN.MH.GA");
			} else if (d.getDistrict().equalsIgnoreCase("Gondia")) {
				d.setId("IN.MH.GO");
			} else if (d.getDistrict().equalsIgnoreCase("Hingoli")) {
				d.setId("IN.MH.HI");
			} else if (d.getDistrict().equalsIgnoreCase("Jalgaon")) {
				d.setId("IN.MH.JG");
			} else if (d.getDistrict().equalsIgnoreCase("Jalna")) {
				d.setId("IN.MH.JN");
			} else if (d.getDistrict().equalsIgnoreCase("Kolhapur")) {
				d.setId("IN.MH.KO");
			} else if (d.getDistrict().equalsIgnoreCase("Latur")) {
				d.setId("IN.MH.LA");
			} else if (d.getDistrict().equalsIgnoreCase("Mumbai")) {
				d.setId("IN.MH.MC");
			} else if (d.getDistrict().equalsIgnoreCase("Mumbai Suburban")) {
				d.setId("IN.MH.MU");
			} else if (d.getDistrict().equalsIgnoreCase("Nagpur")) {
				d.setId("IN.MH.NG");
			} else if (d.getDistrict().equalsIgnoreCase("Nanded")) {
				d.setId("IN.MH.ND");
			} else if (d.getDistrict().equalsIgnoreCase("Nandurbar")) {
				d.setId("IN.MH.NB");
			} else if (d.getDistrict().equalsIgnoreCase("Nashik")) {
				d.setId("IN.MH.NS");
			} else if (d.getDistrict().equalsIgnoreCase("Osmanabad")) {
				d.setId("IN.MH.OS");
			} else if (d.getDistrict().equalsIgnoreCase("Palghar")) {
				d.setId("IN.MH.PL");
			} else if (d.getDistrict().equalsIgnoreCase("Parbhani")) {
				d.setId("IN.MH.PA");
			} else if (d.getDistrict().equalsIgnoreCase("Pune")) {
				d.setId("IN.MH.PU");
			} else if (d.getDistrict().equalsIgnoreCase("Raigad")) {
				d.setId("IN.MH.RG");
			} else if (d.getDistrict().equalsIgnoreCase("Ratnagiri")) {
				d.setId("IN.MH.RT");
			} else if (d.getDistrict().equalsIgnoreCase("Sangli")) {
				d.setId("IN.MH.SN");
			} else if (d.getDistrict().equalsIgnoreCase("Satara")) {
				d.setId("IN.MH.ST");
			} else if (d.getDistrict().equalsIgnoreCase("Sindhudurg")) {
				d.setId("IN.MH.SI");
			} else if (d.getDistrict().equalsIgnoreCase("Solapur")) {
				d.setId("IN.MH.SO");
			} else if (d.getDistrict().equalsIgnoreCase("Thane")) {
				d.setId("IN.MH.TH");
			} else if (d.getDistrict().equalsIgnoreCase("Wardha")) {
				d.setId("IN.MH.WR");
			} else if (d.getDistrict().equalsIgnoreCase("Washim")) {
				d.setId("IN.MH.WS");
			} else if (d.getDistrict().equalsIgnoreCase("Yavatmal")) {
				d.setId("IN.MH.YA");
			} 
			
			
			
			dlist.add(d);
		}

		return dlist;

	}

	

	public List<State> getLast30DayCases() throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		String input = restTemplate.exchange("https://api.covid19india.org/csv/latest/case_time_series.csv", HttpMethod.GET,
				entity, String.class).getBody();

		CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();

		CsvMapper csvMapper = new CsvMapper();

		List<Object> list = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();
		List<Object> subList = list.subList(list.size()-28, list.size());

		List<State> dlist = new ArrayList<State>();
		
		LinkedHashMap<String, String> map = null;
		for (Object l : subList) {

			map = (LinkedHashMap<String, String>) l;

			int totalConfirmed= Integer.parseInt( StringUtils.isBlank(map.get("Daily Confirmed"))==true  ?  "0":map.get("Daily Confirmed"));
			int totalRecovered= Integer.parseInt( StringUtils.isBlank(map.get("Daily Recovered"))==true  ?  "0":map.get("Daily Recovered"));
			int active= totalConfirmed-totalRecovered;
			State d = new State();
			d.setConfirmed(map.get("Daily Confirmed"));
			d.setActive(active+"");
			d.setRecovered(map.get("Daily Recovered"));
			d.setDate(map.get("Date"));
			dlist.add(d);
		}
		
		return dlist;

	}
	
	
	public List<VaccineCenterDto> searchByPincode(String pincode, String date) throws IOException {

		String response="";
		
		List<VaccineCenterDto> centerList=new LinkedList<VaccineCenterDto>();
		
		
		
		for(int j=0;j<7;j++) {
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, j);
			dt = c.getTime();
			
		
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String vaccineDate= formatter.format(dt);
			
			
			try {

				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
				HttpEntity<String> entity = new HttpEntity<String>(headers);

				response = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pincode+"&date="+vaccineDate+"", HttpMethod.GET,
						entity, String.class).getBody();

			 	//response = restTemplate.getForObject("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pincode+"&date="+vaccineDate+"", String.class);

			 	JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(response);
				JSONArray jarray = (JSONArray) json.get("sessions");
				
				if(jarray.isEmpty()) {
					continue;
				}
				
				for(int i=0;i<jarray.size();i++) {
					JSONObject jobj = (JSONObject) jarray.get(i);
					
					VaccineCenterDto dto=new VaccineCenterDto();
					dto.setAvailable_capacity(jobj.get("available_capacity").toString());
					dto.setFee_type(jobj.get("fee_type").toString());
					dto.setDate(jobj.get("date").toString());
					dto.setVaccine(jobj.get("vaccine").toString());
					dto.setSlots(Arrays.asList(jobj.get("slots").toString()));
					dto.setName(jobj.get("name").toString());
					dto.setMin_age_limit(jobj.get("min_age_limit").toString());
					dto.setPincode(jobj.get("pincode").toString());
					
					centerList.add(dto);
				}
				
			 	
	        } catch (Exception e) {
	            System.out.println("Exception in searchByPincode method:- " + e);
	        }
		}
		
		
		
		return centerList;
	}
	
	public List<State> getStateIdList() throws IOException {

		String response="";
		
		List<State> stateList=new LinkedList<State>();
		
		
		
		for(int j=0;j<7;j++) {
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, j);
			dt = c.getTime();
			
		
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String vaccineDate= formatter.format(dt);
			
			
			try {

			 	response = restTemplate.getForObject("https://cdn-api.co-vin.in/api/v2/admin/location/states", String.class);

			 	//System.out.println("PRASAD:"+response);
			 	JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(response);
				JSONArray jarray = (JSONArray) json.get("states");
				
				if(jarray.isEmpty()) {
					continue;
				}
				
				for(int i=0;i<jarray.size();i++) {
					JSONObject jobj = (JSONObject) jarray.get(i);
					
					State dto=new State();
					dto.setState_code(jobj.get("state_id").toString());
					dto.setState(jobj.get("state_name").toString());
					
					
					stateList.add(dto);
				}
				
			 	
	        } catch (Exception e) {
	            System.out.println("Exception in getStateIdList method:- " + e);
	        }
		}
		
		
		
		return stateList;
	}
	
	
	public List<District> getDictrictByStateId(String id) throws IOException {

		String response="";
		
		List<District> districtList=new LinkedList<District>();
		
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			response = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/admin/location/districts/"+id+"", HttpMethod.GET, entity, String.class).getBody();
			
		 	//response = restTemplate.getForObject("https://cdn-api.co-vin.in/api/v2/admin/location/districts/"+id+"", String.class);

		 	JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(response);
			JSONArray jarray = (JSONArray) json.get("districts");
			
			for(int i=0;i<jarray.size();i++) {
				JSONObject jobj = (JSONObject) jarray.get(i);
				
				District dto=new District();
				dto.setDistrict_Key(jobj.get("district_id").toString());
				dto.setDistrict(jobj.get("district_name").toString());

				districtList.add(dto);
			}
			
		 	
        } catch (Exception e) {
            System.out.println("Exception in getDictrictByStateId method:- " + e);
        }
	
		return districtList;
	}
	
	
	
	public List<VaccineCenterDto> searchByDistrict(String districtId) throws IOException {

		String response="";
		
		List<VaccineCenterDto> centerList=new LinkedList<VaccineCenterDto>();
		
		
		
		for(int j=0;j<7;j++) {
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, j);
			dt = c.getTime();
			
		
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String vaccineDate= formatter.format(dt);
			
			
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
				HttpEntity<String> entity = new HttpEntity<String>(headers);

				response = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id="+districtId+"&date="+vaccineDate+"", HttpMethod.GET,
						entity, String.class).getBody();
			 	//response = restTemplate.getForObject("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id="+districtId+"&date="+vaccineDate+"", String.class);

			 	JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(response);
				JSONArray jarray = (JSONArray) json.get("sessions");
				
				if(jarray.isEmpty()) {
					continue;
				}
				
				for(int i=0;i<jarray.size();i++) {
					JSONObject jobj = (JSONObject) jarray.get(i);
					
					VaccineCenterDto dto=new VaccineCenterDto();
					dto.setAvailable_capacity(jobj.get("available_capacity").toString());
					dto.setFee_type(jobj.get("fee_type").toString());
					dto.setDate(jobj.get("date").toString());
					dto.setVaccine(jobj.get("vaccine").toString());
					dto.setSlots(Arrays.asList(jobj.get("slots").toString()));
					dto.setName(jobj.get("name").toString());
					dto.setMin_age_limit(jobj.get("min_age_limit").toString());
					dto.setPincode(jobj.get("pincode").toString());
					
					centerList.add(dto);
				}
				
			 	
	        } catch (Exception e) {
	            System.out.println("Exception in searchByDistrict method:- " + e);
	        }
		}
		
		
		
		return centerList;
	}
	
	
	@SuppressWarnings("unchecked")
	public String sendOTP(String mobileNo) throws IOException, org.json.simple.parser.ParseException {
		
		JSONObject jsonReq=new JSONObject();
		jsonReq.put("mobile", mobileNo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity(jsonReq, headers);
		
		String response = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/auth/public/generateOTP", HttpMethod.POST,entity, String.class).getBody();
		
		JSONParser parser=new JSONParser();
		JSONObject jsonResp = (JSONObject) parser.parse(response);
		System.out.println("TEST::::"+jsonResp.toJSONString());
		return jsonResp.get("txnId").toString();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public String verifyOTP(String otp, String txnId) throws IOException, org.json.simple.parser.ParseException, NoSuchAlgorithmException {
		
		JSONObject jsonReq=new JSONObject();
		jsonReq.put("otp", encrySHA256(otp));
		jsonReq.put("txnId", txnId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity(jsonReq, headers);
		
		String response = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/auth/public/confirmOTP", HttpMethod.POST,entity, String.class).getBody();
		
		JSONParser parser=new JSONParser();
		JSONObject jsonResp = (JSONObject) parser.parse(response);
		
		return jsonResp.get("token").toString();
	}
	
	
	public String encrySHA256(String input) throws NoSuchAlgorithmException {
		
		return toHexString(getSHA(input));
	}
	
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash); 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
        return hexString.toString(); 
    }
	
}
