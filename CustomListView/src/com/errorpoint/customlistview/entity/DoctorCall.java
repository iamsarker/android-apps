package com.errorpoint.customlistview.entity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

public class DoctorCall {
	private long id;
	private String doctorCode;
	private String doctorName;
    private String visitTime;
    private String companyCallId;
    private String sendData;
	private String shift;
	private long date;
	private String location;
	private String remark;
	private String callType;
	private String callStates;
	private String frequency;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		if(shift.equals("Morning"))
		{
			this.shift = "0";
		}
		else if(shift.equals("Evening"))
		{
			this.shift = "1";
		}
		else{
			this.shift = shift;
		}
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
    public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
    public String getFrequency() {
		return frequency;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		remark = remark.replace(",", "^");
		remark = remark.replace(";", "~");
		remark = remark.replace("’", "'");
		remark = remark.replace("|", "*");
		this.remark = remark;
	}
	
	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getCallStates() {
		return callStates;
	}

	public void setCallStates(String callStates) {
		this.callStates = callStates;
	}
	
   
	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getCompanyCallId() {
		return companyCallId;
	}

	public void setCompanyCallId(String companyCallId) {
		this.companyCallId = companyCallId;
	}

	public String getSendData() {
		return sendData;
	}

	public void setSendData(String sendData) {
		this.sendData = sendData;
	}

	//102 1399680000000,1,D4784,0,1212,1,utu, 0,0|34,80,4,59,7,19,3;33,73,2,58,9,,;
	//102 1399680000000,1,D4784,0,1212,1,utu, 1,0|53,123,3,,,,;
	//102 1399680000000,1,D4784,0,1212,1,utu, 2,1,dfgdfg,dgdfgdgd,tyutyu,werwer,yuiyui|34,79,4,59,5,,;
	@Override
	public String toString() {
		String docCall="";
		docCall="102"+" "+date+","+callStates+","+doctorCode+","+shift+","+visitTime+","+location+","+remark+","+callType+"|";
		return docCall;
	}
	

}
