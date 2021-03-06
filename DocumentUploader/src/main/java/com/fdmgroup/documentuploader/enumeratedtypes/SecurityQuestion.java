package com.fdmgroup.documentuploader.enumeratedtypes;

import java.util.ArrayList;
import java.util.List;

public enum SecurityQuestion {
WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET, 
WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME, 
WHAT_WAS_THE_FIRST_CONCERT_YOU_ATTENDED, 
WHAT_WAS_THE_MAKE_AND_MODEL_OF_YOUR_FIRST_CAR,
WHO_IS_YOUR_FAVORITE_ACTOR;
	
	public static List<String> allQuestions(){
		List<String> list = new ArrayList<>();
		for(SecurityQuestion q: SecurityQuestion.values()){
			list.add(q.name().substring(0,1).toUpperCase()+q.name().toLowerCase().substring(1).replace("_", " ")+"?");
		}
		return list;
	}
}
