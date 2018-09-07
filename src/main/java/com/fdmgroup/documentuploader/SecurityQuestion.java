package com.fdmgroup.documentuploader;

import java.util.ArrayList;
import java.util.List;

public enum SecurityQuestion {
	WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET, WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME,WHAT_WAS_THE_FIRST_CONCERT_TOU_ATTENDED,WHAT_WAS_THE_MAKE_AND_MODE;
	
	public static List<String> allQuestions(){
		List<String> list = new ArrayList<>();
		for(SecurityQuestion q: SecurityQuestion.values()){
			list.add(q.name().toLowerCase().replace("_", " ")+"?");
		}
		return list;
	}
}
