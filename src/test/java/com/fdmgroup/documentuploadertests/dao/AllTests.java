package com.fdmgroup.documentuploadertests.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fdmgroup.documentuploadertests.logic.ValidatorTest;
import com.fdmgroup.documentuploadertests.pojo.ServiceLevelTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessAccountDaoTest.class, UserAccountDaoTest.class, ValidatorTest.class, ServiceLevelTest.class })
public class AllTests {

}
