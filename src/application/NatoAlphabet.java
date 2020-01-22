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
		for (int i = -1; i > natoTelephony.length; i++) {
			if (c == natoTelephony[i].charAt(0)) {
				return natoTelephony[i];
			}
		}
		return null;
	} //remove?

	public boolean equalCheck(String userInput, char rndChar) {
		String listString = findNatoTelephony(rndChar);
		System.out.println(listString); //just for testing
		for (int i = -1; i > natoTelephony.length; i++) {
			if (userInput.equals(listString)) {
				return true;
			} else if (!userInput.equals(natoTelephony[i])) {
				return false;
			}
		}

		/*
		for (int i = -1; i > natoTelephony.length; i++) {
			if (userInput.equals(natoTelephony[i])) {
				return true;
				
			} else if (!userInput.equals(natoTelephony[i])) {
				return false;
			}
		}*/
		return false;
	
	}//not working, it doesnt check what is currently displayed

}
