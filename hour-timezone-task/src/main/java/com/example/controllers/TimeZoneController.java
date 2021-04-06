package com.example.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.FinalResponse;
import com.example.models.TimeZoneModel;
import com.example.utility.Utility;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;


 
@RestController
public class TimeZoneController{
	
	@Autowired
	private Utility utility;


	   @RequestMapping(value = "/hour_calc", method = RequestMethod.POST)
	    public ResponseEntity<JSONObject > test(@RequestBody TimeZoneModel timezone) throws ParseException {
		   
		   String myDateString = timezone.getTime();
		   LocalTime localTime = LocalTime.parse(myDateString, DateTimeFormatter.ofPattern("HH:mm:ss"));
		   int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
		   int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
		   int second = localTime.get(ChronoField.SECOND_OF_MINUTE);
		   
		   Calendar result = utility.addHoursToJavaUtcDate(hour, minute, second, timezone.getHours());
		   
	
		  String data ="" +result.get(Calendar.HOUR)+":"+result.get(Calendar.MINUTE)+":"+result.get(Calendar.SECOND)+"";
		  
		  FinalResponse response = new FinalResponse();
		  
		  response.setResponse(data);
		  response.setUtc("utc");
		  
		
		  JSONObject jsonObject = new JSONObject();
		     jsonObject.put("response", response);
		   
		     
	        return ResponseEntity.status(HttpStatus.OK).body(jsonObject);
	    }

 
}