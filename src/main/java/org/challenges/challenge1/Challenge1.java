package org.challenges.challenge1;

public class Challenge1 {

	/**
	 * Returns the maximum count of consecutive '0's between '1's in the given <code>binaryNumber</code>. (Trailing
	 * zeros are ignored.)
	 * 
	 * @param binaryNumber
	 *            the binary number as <code>String</code>. It must start with '1'.
	 * @throws IllegalArgumentException
	 *             if the <code>binaryNumber</code> doesn't start with '1' or contains any character other than '0' or
	 *             '1'.
	 */
	public static int findMaxConsecutiveZeros(String binaryNumber) {

	
		char[] ch = binaryNumber.toCharArray();
		if (binaryNumber.length() == 0) {
			throw new IllegalArgumentException("Blank string");
		}
		if (ch[0] != '1') {
			throw new IllegalArgumentException("Input string does not start with 1");
		}
		for (int i = 0; i < ch.length; i++) {
			if ((ch[i] != '1') && (ch[i] != '0')) {
				throw new IllegalArgumentException("Input String contains illeagal digits/characters");
			}
		}

		int count = 0;
		int max = 0;
		for (int j = 0; j < ch.length; j++) {
			if (ch[j] == '0') {
				count++;
			} else {
	
				if (count >= max) {
					max = count;
				}
				count=0;
				
			}
		}
		// what if the string does not end with 1? loop above will not record the last round of 0's to compare
		// if they were more than the max one we have.
		if (count > max) { 
			max = count;
		}
		return max;
	}

}
