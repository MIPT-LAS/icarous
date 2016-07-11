/**
 * ICAROUS Interface
 * Contact: Swee Balachandran (swee.balachandran@nianet.org)
 * 
 * 
 * Copyright (c) 2011-2016 United States Government as represented by
 * the National Aeronautics and Space Administration.  No copyright
 * is claimed in the United States under Title 17, U.S.Code. All Other
 * Rights Reserved.
 */
package gov.nasa.larcfm.ICAROUS;

import com.MAVLink.icarous.*;


public class COM_Thread extends Aircraft implements Runnable{

    public Thread t;
    public String threadName;
        
    public COM_Thread(String name,AircraftData Input, ICAROUS_Interface comInterface){
	threadName       = name;
	SharedData       = Input;
	Intf             = comInterface;
    }

    public void run(){

	while(true){
	
	    Intf.Read();

	    // Handle new flight plan inputs
	    if(SharedData.RcvdMessages.RcvdFlightPlanUpdate == 1){
		    SharedData.RcvdMessages.RcvdFlightPlanUpdate = 0;
		    SharedData.NewFlightPlan = new FlightPlan();
		    SharedData.NewFlightPlan.UpdateFlightPlan(Intf);
	    }

	    // Handle geo fence messages
	    if(SharedData.RcvdMessages.RcvdGeoFenceUpdate== 1){
		SharedData.RcvdMessages.RcvdGeoFenceUpdate = 0;
		SharedData.listOfFences.GetNewGeoFence(Intf);
	    }

	    // Handle traffic information
	    if(SharedData.RcvdMessages.RcvdTrafficUpdate == 1){

	    }

	    // Handle obstacle information
	    if(SharedData.RcvdMessages.RcvdObstacleUpdate == 1){


	    }

	    // Handling mission commands (start/stop)
	    if(SharedData.RcvdMessages.RcvdMissionStart == 1){

		synchronized(SharedData){
		    SharedData.startMission = SharedData.RcvdMessages.msgMissionStartStop.missionStart;
		    SharedData.RcvdMessages.RcvdMissionStart = -1;
		}
		System.out.println("Received mission start");
	     		
	    }

	  
	}
    }

    
    public void start(){
	System.out.println("Starting "+threadName);
	t = new Thread(this,threadName);
	t.start();
    }

	
    
    
    
}
