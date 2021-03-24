package edu.ycp.cs320.ChessProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.ChessProject.controller.NumbersController;
import edu.ycp.cs320.ChessProject.model.Numbers;

public class NumbersTest {
	private Numbers model;
	private static final double DELTA = 1e-15;
	
	@Before
	public void setUp() {
		model = new Numbers();
		
		
	}
	
	@Test
	public void testSetFirst() {
		model.setFirst(5);;
		assertEquals(5, model.getFirst(), DELTA);
	}
	
	@Test
	public void testSetSecond() {
		model.setSecond(6);
		assertEquals(6, model.getSecond(), DELTA);
	}
	
	@Test
	public void testSetThird() {
		model.setThird(10);
		assertEquals(10, model.getThird(), DELTA);
	}
	
	public void testSetResult() {
		model.setResult(25);
		assertEquals(25, model.getResult(), DELTA);
	}
	
	 
}
