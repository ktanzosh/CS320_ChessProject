package edu.ycp.cs320.ChessProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.ChessProject.model.GuessingGame;

public class GuessingGameTest {
	private GuessingGame model;
	
	@Before
	public void setUp() {
		model = new GuessingGame();
	}
	
	@Test
	public void testSetMin() {
		model.setMin(1);
		assertEquals(1, model.getMin());
	}
	@Test
	public void testSetMax() {
		model.setMax(100);
		assertEquals(100, model.getMax());
	}
	@Test
	public void isDone() {
		model.setMin(12);
		model.setMax(12);
		assertEquals(12, model.getMax());
		assertEquals(12, model.getMin());
	}
	
	 
}
