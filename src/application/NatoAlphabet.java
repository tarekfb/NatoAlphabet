package application;

import java.util.Random;

public class NatoAlphabet { //implement Swedish alternative? https://www.wikiwand.com/sv/Bokstavering
	private String[] natoTelephony = new String [] {
			"Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", 
			"Hotel", "India", "Juliett", "Kilo", "Lima", "Mike", "November", 
			"Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform",
			"Victor", "Whiskey", "X-ray", "Yankee", "Zulu",
	};
	private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private int progressCount = 0;
	private int totalCounter = 0;
	private static int timeLimit = 3;
	private static int maxQuestions = 5; //change to 0 and do 0-check: if (maxquestions = 0){no time limit}

	public String[] getNatoTelephony() {
		return natoTelephony;
	}
	public void setNatoTelephony(String[] natoTelephony) {
		this.natoTelephony = natoTelephony;
	}
	public char[] getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(char[] alphabet) {
		this.alphabet = alphabet;
	}
	public String getRandomTelephony() {
		int rnd = new Random().nextInt(natoTelephony.length);
		return natoTelephony[rnd];
	}
	public char getRandomChar() {
		int rnd = new Random().nextInt(alphabet.length);
		return alphabet[rnd];
	}
	public String findNatoTelephony(char c) {
		for (int i = 0; i < natoTelephony.length; i++) { // 0 or -1?
			if (c == natoTelephony[i].charAt(0)) {
				return natoTelephony[i];
			}
		}
		return null;
	}

	public boolean equalCheck(String userInput, char rndChar) {
		String listString = findNatoTelephony(rndChar);
		if (listString.equals(userInput)) {
			return true;
		}
		return false;
	}
	public int getProgressCounter() {
		return progressCount;
	}
	public void setProgressCounter(int progressCount) {
		this.progressCount = progressCount;
	}
	public int getTotalCounter() {
		return totalCounter;
	}
	public void setTotalCounter(int totalCount) {
		this.totalCounter = totalCount;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public int getMaxQuestions() {
		return maxQuestions;
	}
	public void setMaxQuestions(int maxQuestions) {
		this.maxQuestions = maxQuestions;
	}

}
