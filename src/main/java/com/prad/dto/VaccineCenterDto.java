package com.prad.dto;

import java.util.*;

public class VaccineCenterDto {

	private String center_id;
	private String name;
	private String address;
	private String state_name;
	private String district_name;
	private String block_name;
	private String pincode;
	private String from;
	private String to;
	private String lat;
	private String longg;
	private String fee_type;
	private String session_id;
	private String date;
	private String available_capacity;
	private String fee;
	private String min_age_limit;
	private String vaccine;
	private List<String>  slots;
	
	
	
	public String getCenter_id() {
		return center_id;
	}
	public void setCenter_id(String center_id) {
		this.center_id = center_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getBlock_name() {
		return block_name;
	}
	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLongg() {
		return longg;
	}
	public void setLongg(String longg) {
		this.longg = longg;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAvailable_capacity() {
		return available_capacity;
	}
	public void setAvailable_capacity(String available_capacity) {
		this.available_capacity = available_capacity;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getMin_age_limit() {
		return min_age_limit;
	}
	public void setMin_age_limit(String min_age_limit) {
		this.min_age_limit = min_age_limit;
	}
	public String getVaccine() {
		return vaccine;
	}
	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}
	public List<String> getSlots() {
		return slots;
	}
	public void setSlots(List<String> slots) {
		this.slots = slots;
	}
	
	
	@Override
	public String toString() {
		return "VaccineCenterDto [center_id=" + center_id + ", name=" + name + ", address=" + address + ", state_name="
				+ state_name + ", district_name=" + district_name + ", block_name=" + block_name + ", pincode="
				+ pincode + ", from=" + from + ", to=" + to + ", lat=" + lat + ", longg=" + longg + ", fee_type="
				+ fee_type + ", session_id=" + session_id + ", date=" + date + ", available_capacity="
				+ available_capacity + ", fee=" + fee + ", min_age_limit=" + min_age_limit + ", vaccine=" + vaccine
				+ ", slots=" + slots + ", getCenter_id()=" + getCenter_id() + ", getName()=" + getName()
				+ ", getAddress()=" + getAddress() + ", getState_name()=" + getState_name() + ", getDistrict_name()="
				+ getDistrict_name() + ", getBlock_name()=" + getBlock_name() + ", getPincode()=" + getPincode()
				+ ", getFrom()=" + getFrom() + ", getTo()=" + getTo() + ", getLat()=" + getLat() + ", getLongg()="
				+ getLongg() + ", getFee_type()=" + getFee_type() + ", getSession_id()=" + getSession_id()
				+ ", getDate()=" + getDate() + ", getAvailable_capacity()=" + getAvailable_capacity() + ", getFee()="
				+ getFee() + ", getMin_age_limit()=" + getMin_age_limit() + ", getVaccine()=" + getVaccine()
				+ ", getSlots()=" + getSlots() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
}
