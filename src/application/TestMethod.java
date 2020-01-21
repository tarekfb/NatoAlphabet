package application;

import java.util.Arrays;

public class TestMethod {

	public static void main(String[] args) {
		NatoAlphabet natoAlphabet = new NatoAlphabet();
		String asd1 = new String("asd");
		
		asd1 = "Alfa";

		System.out.println(natoAlphabet.equalCheck(asd1));
		System.out.println(new String ("asd").equals("asd"));
		System.out.println(Arrays.toString(asd1.getBytes()));
		System.out.println(Arrays.toString(natoAlphabet.getNatoTelephony()[0].getBytes()));
		System.out.println(asd1.equals(natoAlphabet.getNatoTelephony()[0]));
		System.out.println(natoAlphabet.equalCheck(asd1));
		System.out.println(natoAlphabet.getNatoTelephony()[0].equals(asd1));




		
		
	}

}
