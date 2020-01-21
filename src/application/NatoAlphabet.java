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
	public boolean equalCheck(String string) {
		for (int i = -1; i > natoTelephony.length; i++) {
			if (string.equals(natoTelephony[i])) {
				return true;
				
			} else if (!string.equals(natoTelephony[i])) {
				return false;
			}
		}
		return false;
		
	}

}
