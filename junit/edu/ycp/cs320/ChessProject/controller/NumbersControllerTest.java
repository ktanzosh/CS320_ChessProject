package edu.ycp.cs320.ChessProject.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.ChessProject.controller.GuessingGameController;
import edu.ycp.cs320.ChessProject.controller.NumbersController;
import edu.ycp.cs320.ChessProject.model.GuessingGame;
import edu.ycp.cs320.ChessProject.model.Numbers;

public class NumbersControllerTest {

	private Numbers model;
	private NumbersController controller;
	private static final double DELTA = 1e-15;
	
	@Before
	public void setUp() {
		model = new Numbers();
		controller = new NumbersController();
		
		model.setFirst(5);
		model.setSecond(7);
		model.setThird(3);
		
		controller.setModel(model);
	}
	
	@Test
	public void testAdd() {
		double sum = controller.add(model.getFirst(), model.getSecond(), model.getThird());
		assertEquals(15.0, sum, DELTA);
	}
	
	@Test
	public void testMultiply() {
		double product = controller.MultiplyNumbersController(model.getFirst(), model.getSecond());
		assertEquals(35.0, product, DELTA);
		
	}
}
