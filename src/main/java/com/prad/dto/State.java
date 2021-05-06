package com.prad.dto;

public class State {

	private String id;
	private String State;
	private String State_code;
	private String Active;
	private String Confirmed;
	private String TodaysConfirmed;
	private String Recovered;
	private String TodaysRecovered;
	private String Deaths;
	private String date;
	
		
	
	public String getTodaysConfirmed() {
		return TodaysConfirmed;
	}
	public void setTodaysConfirmed(String todaysConfirmed) {
		TodaysConfirmed = todaysConfirmed;
	}
	public String getTodaysRecovered() {
		return TodaysRecovered;
	}
	public void setTodaysRecovered(String todaysRecovered) {
		TodaysRecovered = todaysRecovered;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getState_code() {
		return State_code;
	}
	public void setState_code(String state_code) {
		State_code = state_code;
	}
	public String getActive() {
		return Active;
	}
	public void setActive(String active) {
		Active = active;
	}
	public String getConfirmed() {
		return Confirmed;
	}
	public void setConfirmed(String confirmed) {
		Confirmed = confirmed;
	}
	public String getRecovered() {
		return Recovered;
	}
	public void setRecovered(String recovered) {
		Recovered = recovered;
	}
	public String getDeaths() {
		return Deaths;
	}
	public void setDeaths(String deaths) {
		Deaths = deaths;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "State [id=" + id + ", State=" + State + ", State_code=" + State_code + ", Active=" + Active
				+ ", Confirmed=" + Confirmed + ", Recovered=" + Recovered + ", Deaths=" + Deaths + ", date=" + date
				+ "]";
	}
	
	
	
	
	
	
}
