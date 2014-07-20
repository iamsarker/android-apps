package com.errorpoint.customlistview.global;

import java.util.ArrayList;

import com.errorpoint.customlistview.entity.Doctor;
import com.errorpoint.customlistview.entity.DoctorCall;

public  class DoctorVisit {
  
	public static ArrayList<Doctor> DOCTOR_LIST=null;
    public static ArrayList<Doctor> DOCTOR_PLAN_LIST=new ArrayList<Doctor>();
    public static ArrayList<DoctorCall> DOCTOR_CALL_LIST = new ArrayList<DoctorCall>();
    //public static ArrayList<DoctorPlan> DOCTOR_PLAN_STATUS_LIST=new ArrayList<DoctorPlan>();
    
    
   // public static ArrayList<Doctor> DOCTOR_PLAN_LIST_SELECTED=new ArrayList<Doctor>();
    public static String MODE="NEW";
    public static final  String PLAN_CALL="0";
    public static final  String UNPLAN_CALL="1";
    public static final  String PLANNED_CALL_SUCCESSFULLY_SEND="3";
    public static final  String UNPLANNED_CALL_SUCCESSFULLY_SEND="4";
    
    public static long FIRST_DATE;
    public static long LAST_DATE;
    public static long CALL_DAY_LIMIT; // plan day + 2/dynamic value
}
