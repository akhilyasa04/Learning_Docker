package com.flmfoods.newcontroller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class NewController {
	
	@GetMapping("/check/{date}")
	public String checkDate(@PathVariable(name="date") String date)
	{
		
		LocalDate input = LocalDate.parse(date);
		
		LocalDate currDate = LocalDate.now();
		
		if(input.isBefore(currDate))
		{
			return "Past";
		}
		
		else if(input.isAfter(currDate))
		{
			return "Future";
		}
		else
		{
			return "Present";
		}
	}

}
