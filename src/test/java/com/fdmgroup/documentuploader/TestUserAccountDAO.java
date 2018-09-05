package com.fdmgroup.documentuploader;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestUserAccountDAO {

	@Test
	public void test() {
		Map<SecurityQuestion,String> QA = new HashMap<>();
		QA.put(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET, "Bad dog");
		
		UserAccount user = new UserAccount("fredtodd","Todd","Fred"."FredsCool","Fred@gmail.com",)
	}

}
