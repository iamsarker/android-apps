package com.errorpoint.customlistview.demodata;

import java.util.ArrayList;

import com.errorpoint.customlistview.entity.Doctor;

import android.database.Cursor;

public class DemoData {
	public static ArrayList<Doctor> getDoctorSortList() {
		ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
		int i=0;
		
		do {
			Doctor doctor = new Doctor();
			doctor.setCode("D00201"+i);
			doctor.setName("Doctor Name " + i);
			if(i%2==0){
				doctor.setFrequency("A");
			} else if(i%3==0){
				doctor.setFrequency("B");
			} else{
				doctor.setFrequency("C");
			}
			doctorList.add(doctor);
			i++;
		} while (i<=9);
	return doctorList;
	}
}
