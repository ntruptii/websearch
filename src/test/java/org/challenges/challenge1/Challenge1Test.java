package org.challenges.challenge1;
import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.security.SecureRandom;
import java.math.BigInteger;


import org.junit.Test;

public class Challenge1Test {
	
	private String stringize(int length, char ch) {
		char[] bytes = new char[length];
		Arrays.fill(bytes, ch);
		String str = new String(bytes);
		return str;
	}
	/* Taken from http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
	 * to generate 130 bit random string   
	 */
	private String Randomize() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	@Test
	public void test(){
		// make sure exception is thrown for illegal arguments
		/* Fault Injection */
		assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros("2")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros("01000")).isInstanceOf(IllegalArgumentException.class);
		
		assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros("10 0000101")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros("Trupti")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros("")).isInstanceOf(IllegalArgumentException.class);
		
		/* Backslashes is always fun in strings */
		assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros("\\\\\\")).isInstanceOf(IllegalArgumentException.class);

		assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros("\n")).isInstanceOf(IllegalArgumentException.class);
		
		/* Lets try some non-utf8 characters */
		assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros("तृप्ती")).isInstanceOf(IllegalArgumentException.class);
		
		/* 100 rounds of passing random 130 bit string data to function
		 */
		for ( int k = 0; k < 100; k++) {
			String str = Randomize();
			//System.out.println("random str: "+str);
			assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros(str)).isInstanceOf(IllegalArgumentException.class);
		}
		System.out.println("Passed 100 rounds of 130 bit randomg strings: ");
		
		assertThatThrownBy(() -> Challenge1.findMaxConsecutiveZeros("\\\\\\")).isInstanceOf(IllegalArgumentException.class);
		

		/* Standard tests */
		assertThat(Challenge1.findMaxConsecutiveZeros("101")).isEqualTo(1);
		assertThat(Challenge1.findMaxConsecutiveZeros("10010001")).isEqualTo(3);
		assertThat(Challenge1.findMaxConsecutiveZeros("10001001")).isEqualTo(3);
		assertThat(Challenge1.findMaxConsecutiveZeros("100000")).isEqualTo(5);
		assertThat(Challenge1.findMaxConsecutiveZeros("100000101")).isEqualTo(5);
		assertThat(Challenge1.findMaxConsecutiveZeros("10000001")).isEqualTo(6);
		assertThat(Challenge1.findMaxConsecutiveZeros("1111111111")).isEqualTo(0);
		
		/* Tweak the random stress test below with following variables
		 */
		int NoOfStrings = 10;
		int MaxLenOfStrings = 100;
		int MaxNofOfConsecutive0sOr1s = 100;
		
		Random rand1, rand2;
		StringBuilder stringBuilder = new StringBuilder();
		int max = 0;
		int n = 0;
		int str_len = 0;
		rand1 = new Random();
		rand2 = new Random();

		
		for (int i = 0; i < NoOfStrings; i++) {
			// str_len is not really a length but how many random 0s and 1s we will end up appending 
			str_len = rand1.nextInt(MaxLenOfStrings);
			
			
			for ( int j = 0; j < str_len; j++) {
				/* first create a string starting with 1 */
				stringBuilder.append("1");
				
				/* Then append random ( random between 0-MaxNofOfConsecutive0sOr1s) no of 0s*/
				n = rand2.nextInt(MaxNofOfConsecutive0sOr1s);
				if ( n > max) {
						// Keep track of how many zeroes we putting each time and keep getting the higher number
						max = n;
				}
				stringBuilder.append(stringize(n, '0'));
				/* Then append random ( random between 0-MaxNofOfConsecutive0sOr1s) no of 1s */
				n = rand2.nextInt(MaxNofOfConsecutive0sOr1s); 
				stringBuilder.append(stringize(n, '1'));
			}
			
			assertThat(Challenge1.findMaxConsecutiveZeros(stringBuilder.toString())).isEqualTo(max);
			System.out.println("Passed Binary string: "+stringBuilder.toString() + " had max "+max+" no of consecutive 0s");
			max = 0; // reset the max for next string
			stringBuilder.setLength(0); // blank out the string storage for next string
		}

	}

}
