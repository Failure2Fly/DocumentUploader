
package com.fdmgroup.documentuploader;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fdmgroup.documentuploader.pojo.ServiceLevel;
import com.fdmgroup.documentuploader.enumeratedtypes.ServiceLevels;

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
		ServiceLevel result = new ServiceLevel(ServiceLevels.BRONZE);
		
		// Assert
		assertEquals(result.getServiceLevel(), ServiceLevels.BRONZE);
		assertEquals(result.getDocumentUploadLimit(), 2);
		assertEquals(result.getHasAdverts(), true);
	}
	
	@Test
	public void test_constructor_CreaturesServiceLevelOfSilver_whenProvidedWithSilverLevel() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel(ServiceLevels.SILVER);
		
		// Assert
		assertEquals(result.getServiceLevel(), ServiceLevels.SILVER);
		assertEquals(result.getDocumentUploadLimit(), 5);
		assertEquals(result.getHasAdverts(), true);
	}
	
	@Test
	public void test_constructor_CreaturesServiceLevelOfGold_whenProvidedWithGoldLevel() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel(ServiceLevels.GOLD);
		
		// Assert
		assertEquals(result.getServiceLevel(), ServiceLevels.GOLD);
		assertEquals(result.getDocumentUploadLimit(), 20);
		assertEquals(result.getHasAdverts(), false);
	}
	
	@Test
	public void test_constructor_CreaturesServiceLevelOfUnlimited_whenProvidedWithUnlimitedLevel() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel(ServiceLevels.UNLIMITED);
		
		// Assert
		assertEquals(result.getServiceLevel(), ServiceLevels.UNLIMITED);
		assertEquals(result.getDocumentUploadLimit(), -1);
		assertEquals(result.getHasAdverts(), false);
	}
	
	@Test
	public void test_constructor_CreaturesServiceLevelOfEnterprise_whenProvidedWithEnterpriseLevel() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel(ServiceLevels.ENTERPRISE);
		
		// Assert
		assertEquals(result.getServiceLevel(), ServiceLevels.ENTERPRISE);
		assertEquals(result.getDocumentUploadLimit(), -1);
		assertEquals(result.getHasAdverts(), false);
	}

}

