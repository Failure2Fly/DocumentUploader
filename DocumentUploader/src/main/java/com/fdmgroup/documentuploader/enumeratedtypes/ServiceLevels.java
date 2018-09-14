package com.fdmgroup.documentuploader.enumeratedtypes;

import java.util.ArrayList;
import java.util.List;

public enum ServiceLevels {

	BRONZE, SILVER, GOLD, UNLIMITED, ENTERPRISE;

	public static List<String> allServiceLevels(){
		List<String> list = new ArrayList<>();
		for(ServiceLevels q: ServiceLevels.values()){
			list.add(q.name().substring(0,1).toUpperCase()+q.name().toLowerCase().substring(1));
		}
		return list;
	}
}
