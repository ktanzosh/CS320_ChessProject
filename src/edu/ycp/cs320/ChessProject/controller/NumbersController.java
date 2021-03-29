package edu.ycp.cs320.ChessProject.controller;
import edu.ycp.cs320.ChessProject.model.Numbers;

public class NumbersController {
	
	private Numbers model;
	
	public void setModel(Numbers model) {
		this.model = model;
	}
	
	public Double add(Double first, Double second, Double third) {
		return first + second + third;
	}
	public Double MultiplyNumbersController(Double first, Double second) {
		return first * second;
	}
}
