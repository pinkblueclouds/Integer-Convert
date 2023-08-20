
// TODO: Auto-generated Javadoc
/**
 * The Class IntegerConverter. This class provides static methods for converting
 * integer, binary and hex strings to specified numeric primitives (int, byte)
 */
public class IntegerConvert {

	/**
	 * Instantiates a new integer converter.
	 */
	public IntegerConvert() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Convert string of decimal numbers to an int.
	 *
	 * @param in the String to convert
	 * @return the converted value of the string represented as an int
	 * @throws NumberFormatException the number format exception
	 */
	public static int parseInt(String in) throws NumberFormatException {
		int number = 0;
	    int ascii = (int)'0';
		int negative = checkNeg(in);
		int index = checkNeg(in);
		if (in.charAt(index) != '+' && in.charAt(index) != '_') {
			number = (int)in.charAt(index) - ascii;
		}
		index++;
		while(index < in.length()) {
			if ((int)in.charAt(index) != (int)'_') {
				number = number*10 + ((int)in.charAt(index) - ascii);
			}
			index++;
		}
		if (negative == 1) 
			return number*(-1);
		return number;
	}
	
	/**
	 * Checks if the first character is a negative sign
	 * 
	 * @param String input
	 * @return 1 if there is a negative, 0 if not a negative
	 */
	private static int checkNeg(String input) {
		if (input.charAt(0) == 45) return 1;
		return 0;
	}

	/**
	 * Converts string of decimal numbers to a byte.
	 *
	 * @param in the String to convert
	 * @return the byte
	 * @throws NumberFormatException the number format exception
	 */
	public static byte parseByte(String in) throws NumberFormatException {
		int number = parseInt(in);
		return (byte)number;
	}
	
	/**
	 * Parses an unsigned binary string and returns the equivalent integer value (signed)
	 *
	 * @param in   the input binary string. Should have a leading "0b"
	 * @return  the integer value of the converted string
	 * @throws NumberFormatException the number format exception
	 */
	public static int parseBinStrToInt(String in) throws NumberFormatException {
		int number = 0;
		int ascii = (int)'0';
		for(int index = 2; index < in.length(); index++) {
			if (in.charAt(index) != '_') {
				number = number*2 + ((int)in.charAt(index)-ascii);
			}
		}
		return parseInt(String.valueOf(number));
	}
	
	/**
	 * Parses an unsigned binary string and returns the equivalent byte value (signed) 
	 *
	 * @param in  the input binary string. Should have a leading "0b"
	 * @return the byte value of the converted string
	 * @throws NumberFormatException the number format exception
	 */
	public static byte parseBinStrToByte(String in) throws NumberFormatException {
		int number = 0;
		for(int index = 2; index < in.length(); index++) {
			if (in.charAt(index) != '_') {
				number = number*2 + ((int)in.charAt(index)-(int)'0');
			}
		}
		return parseByte(String.valueOf(number));
	}
	
	/**
	 * Parses an unsigned hex string and returns the equivalent integer value (signed).
	 *
	 * @param in  the input hex string. Should have a leading "0x"
	 * @return the integer value of the converted string
	 * @throws NumberFormatException the number format exception
	 */
	public static int parseHexStrToInt(String in) throws NumberFormatException {
		return 0;
	}
	
	/**
	 * Parses an unsigned hex string and returns the equivalent byte value (signed).
	 *
	 * @param in  the input hex string. Should have a leading "0x"
	 * @return the byte value of the converted string
	 * @throws NumberFormatException the number format exception
	 */
	public static byte parseHexStrToByte(String in) throws NumberFormatException {
		return 0;
	}

	/**
	 * Returns the equivalent unsigned binary string (32 bits)
	 *
	 * @param in the integer to convert
	 * @return the equivalent binary string representation (32 bits)
	 */
	public static String intToBinaryString(int in) {
		return("");
	}
	
	/**
	 * Returns the equivalent unsigned binary string (8 bits)
	 *
	 * @param in the byte to convert
	 * @return the equivalent binary string representation (8 bits)
	 */
	public static String byteToBinaryString(byte in) {
		return("");
	}
	
	/**
	 * Returns the equivalent unsigned hex string (8 hex chars)
	 *
	 * @param in the integer to convert
	 * @return the equivalent hex string representation (8 hex chars)
	 */
	public static String intToHexString(int in) {
		return("");
	}
	
	/**
	 * Returns the equivalent unsigned hex string (2 hex chars)
	 *
	 * @param in the integer to convert
	 * @return the equivalent hex string representation (2 hex chars)
	 */
	public static String byteToHexString(byte in) {
		return("");	
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// You should write basic testing of each of your methods here.
		// I will provide a more comprehensive testing using JUnit.

	}

}
