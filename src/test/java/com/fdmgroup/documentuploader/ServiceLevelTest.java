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
		assertEquals(ServiceLevels.BRONZE, result.getServiceLevel());
		assertEquals(0, result.getMonthlyCost());
		assertEquals(2, result.getDocumentLimit());
		assertEquals(2, result.getMonthlyDocumentUploadLimit());
		assertEquals(1, result.getUserLimit());
		assertEquals(0, result.getAdditionalUserCost());
		assertEquals(result.getHasAdverts(), true);
	}
	
	@Test
	public void test_constructor_createsServiceLevelOfBronze_whenProvidedWithBronzeLevel() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel(ServiceLevels.BRONZE);
		
		// Assert
		assertEquals(ServiceLevels.BRONZE, result.getServiceLevel());
		assertEquals(0, result.getMonthlyCost());
		assertEquals(2, result.getDocumentLimit());
		assertEquals(2, result.getMonthlyDocumentUploadLimit());
		assertEquals(1, result.getUserLimit());
		assertEquals(0, result.getAdditionalUserCost());
		assertEquals(result.getHasAdverts(), true);
	}
	
	@Test
	public void test_constructor_createsServiceLevelOfSilver_whenProvidedWithSilverLevel() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel(ServiceLevels.SILVER);
		
		// Assert
		assertEquals(ServiceLevels.SILVER, result.getServiceLevel());
		assertEquals(1, result.getMonthlyCost());
		assertEquals(5, result.getDocumentLimit());
		assertEquals(10, result.getMonthlyDocumentUploadLimit());
		assertEquals(1, result.getUserLimit());
		assertEquals(0, result.getAdditionalUserCost());
		assertEquals(result.getHasAdverts(), true);
	}
	
	@Test
	public void test_constructor_createsServiceLevelOfGold_whenProvidedWithGoldLevel() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel(ServiceLevels.GOLD);
		
		// Assert
		assertEquals(ServiceLevels.GOLD, result.getServiceLevel());
		assertEquals(2, result.getMonthlyCost());
		assertEquals(20, result.getDocumentLimit());
		assertEquals(50, result.getMonthlyDocumentUploadLimit());
		assertEquals(2, result.getUserLimit());
		assertEquals(0, result.getAdditionalUserCost());
		assertEquals(result.getHasAdverts(), false);
	}
	
	@Test
	public void test_constructor_createsServiceLevelOfUnlimited_whenProvidedWithUnlimitedLevel() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel(ServiceLevels.UNLIMITED);
		
		// Assert
		assertEquals(ServiceLevels.UNLIMITED, result.getServiceLevel());
		assertEquals(5, result.getMonthlyCost());
		assertEquals(-1, result.getDocumentLimit());
		assertEquals(-1, result.getMonthlyDocumentUploadLimit());
		assertEquals(10, result.getUserLimit());
		assertEquals(1, result.getAdditionalUserCost());
		assertEquals(result.getHasAdverts(), false);
	}
	
	@Test
	public void test_constructor_createsServiceLevelOfEnterprise_whenProvidedWithEnterpriseLevel() {
		// Arrange
		
		// Act
		ServiceLevel result = new ServiceLevel(ServiceLevels.ENTERPRISE);
		
		// Assert
		assertEquals(ServiceLevels.ENTERPRISE, result.getServiceLevel());
		assertEquals(15, result.getMonthlyCost());
		assertEquals(-1, result.getDocumentLimit());
		assertEquals(-1, result.getMonthlyDocumentUploadLimit());
		assertEquals(200, result.getUserLimit());
		assertEquals(1, result.getAdditionalUserCost());
		assertEquals(result.getHasAdverts(), false);
	}
	
	@Test
	public void test_changeServiceLevel_changesServiceLevelFromBronzeToGold() {
		// Arrange
		ServiceLevel test = new ServiceLevel(ServiceLevels.BRONZE);
				
		// Act
		test.changeServiceLevel(ServiceLevels.GOLD);
		
		// Assert
		assertEquals(ServiceLevels.GOLD, test.getServiceLevel());
		assertEquals(2, test.getMonthlyCost());
		assertEquals(20, test.getDocumentLimit());
		assertEquals(50, test.getMonthlyDocumentUploadLimit());
		assertEquals(2, test.getUserLimit());
		assertEquals(0, test.getAdditionalUserCost());
		assertEquals(test.getHasAdverts(), false);
	}

}
