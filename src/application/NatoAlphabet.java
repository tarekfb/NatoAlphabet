package application;

import java.util.HashMap;
import java.util.Random;

public class NatoAlphabet { //TODO: implement Swedish alternative? https://www.wikiwand.com/sv/Bokstavering
	
	private HashMap<String, String> natoTelephony = new HashMap<String, String>();
		
	private String[] alphabet = new String [] {
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
	};
	private static int progressCount = 0;
	private int totalCounter = 0;
	private static int timeLimit = 0;
	private static int maxQuestions = 0;
	private static int highscore = 0;

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
	public static int getHighscore() {
		return highscore;
	}
	public static void setHighscore(int highscore) {
		NatoAlphabet.highscore = highscore;
	}
	
	public String[] getAlphabet() {
		return alphabet;
	}
	
	public void setAlphabet(String[] alphabet) {
		this.alphabet = alphabet;
	}
	public HashMap<String, String> getNatoTelephony() {
		return natoTelephony;
	}
	public void setNatoTelephony(HashMap<String, String> natoTelephony) {
		this.natoTelephony = natoTelephony;
	}
	public static int levDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }//Levensteihn distance algorithm.
	//I understand the general idea of the algorithm
	//but would prefer to fully understand the implementation details
	
	public int alphabetDistance(char c) {
		String currentLetter = String.valueOf(c);
		
			
	}
	
	public String getRandomTelephony() {
		int rnd = new Random().nextInt(getNatoTelephonyArray().length);
		return getNatoTelephonyArray()[rnd];
	}
	public char getRandomChar() {
		int rnd = new Random().nextInt(getAlphabetArray().length);
		return getAlphabetArray()[rnd];
		//TODO: fix "more random" (spotify shuffle)
	}
	public boolean equalCheck(String userInput, char rndChar) {
		String currentLetter = String.valueOf(rndChar);
		if (levDistance(userInput, natoTelephony.get(currentLetter)) <= 2) return true; //only allow misspellings if 2 error(s)
		return false;
	}
	
	
	/**********************************
	* 
	*below is old data
	*is being used for hashmap entries
	*TODO: remove and fix adding hashmap entries
	***********************************/
	
	private String[] natoTelephonyArray = new String [] {
			"Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", 
			"Hotel", "India", "Juliett", "Kilo", "Lima", "Mike", "November", 
			"Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform",
			"Victor", "Whiskey", "X-ray", "Yankee", "Zulu",
	};
	private char[] alphabetArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	public char[] getAlphabetArray() {
		return alphabetArray;
	}
	public void setAlphabetArray(char[] alphabetArray) {
		this.alphabetArray = alphabetArray;
	}
	public String[] getNatoTelephonyArray() {
		return natoTelephonyArray;
	}
	public void setNatoTelephonyArray(String[] natoTelephonyArray) {
		this.natoTelephonyArray = natoTelephonyArray;
	}


}
