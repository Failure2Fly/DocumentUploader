package com.fdmgroup.documentuploader;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fdmgroup.pojo.ServiceLevel;
import com.fdmgroup.pojo.ServiceLevels;

public class ServiceLevelTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_noArgConstructor_createsServiceLevelWithDefaultLevelOfBronze() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel();
		
		// Assert
		assertEquals(result.getServiceLevel(), ServiceLevels.BRONZE);
		assertEquals(result.getDocumentUploadLimit(), 2);
		assertEquals(result.getHasAdverts(), true);
	}
	
	@Test
	public void test_constructor_CreaturesServiceLevelOfBronze_whenProvidedWithBronzeLevel() {
		// Arrange
		
		// Act
		SerivceLevel result = new ServiceLevel(ServiceLevels.BRONZE);
	}

}
