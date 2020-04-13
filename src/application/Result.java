package application;

import java.util.ArrayList;

public class Result {
	private String userInput;
	private String solution;
	private double seconds;
	private boolean equalValue;
	
	private static ArrayList<Result> resultList = new ArrayList<Result>();

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public double getSeconds() {
		return seconds;
	}

	public void setSeconds(double seconds) {
		this.seconds = seconds;
	}

	public boolean isEqualValue() {
		return equalValue;
	}

	public void setEqualValue(boolean equalValue) {
		this.equalValue = equalValue;
	}

	public ArrayList<Result> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<Result> resultList) {
		Result.resultList = resultList;
	}
	
	
}
