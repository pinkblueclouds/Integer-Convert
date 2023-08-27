
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
	
	//range for number values
	private static int Max_Byte = 127;
	private static int Min_Byte = -128;
	private static long Max_Int = 2147483647;
	private static long Min_Int = -2147483648;
	
	/**
	 * Convert string of decimal numbers to an int.
	 *
	 * @param in the String to convert
	 * @return the converted value of the string represented as an int
	 * @throws NumberFormatException if string is empty or value is + or -
	 */
	public static int parseInt(String in) throws NumberFormatException {
		if ("".equals(in))
			throw new NumberFormatException("Inputted an empty String");
		if (checkLetters(in)  || (in.length() == 1 && (in.charAt(0)=='-' || in.charAt(0)=='+')))
			throw new NumberFormatException("Non compliant value in String");
		if (countLengthWithoutUnderscore(in) == 0 || countLengthWithoutUnderscore(in.substring(1,in.length()))==0)
			throw new NumberFormatException("Non compliant value in String");
		long number = 0;
		int negative = checkNeg(in);
		int index = checkNeg(in);
		if (in.charAt(index) == '+' || in.charAt(index) == '_') {
			index++;
		}
		while(index < in.length()) {
			if ((int)in.charAt(index) != (int)'_') {
				number = number*10 + ((int)in.charAt(index) - (int)'0');
			}
			index++;
		}
		if (negative == 1 && number > Max_Int+1)
			throw new NumberFormatException("Integer Value is out of domain");
		else if (negative == 1) 
			return (int)number*(-1);
		if (number < Min_Int || number > Max_Int)
			throw new NumberFormatException("Integer Value is out of domain");
		return (int)number;
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
	 * Checks if the first character is +, - or a number 0 to 9
	 * @param input String, index of character it's checking
	 * @return true if value complies, false if it's not compliant
	 */
	private static boolean checkChar(String input, int index) {
		if (index != 0) {
			if (input.charAt(index)=='_' || ((int)input.charAt(index) >= (int)'0'&&(int)input.charAt(index) <= (int)'9'))
				return true;
		}
		else if (input.charAt(index) == '+' || input.charAt(index)=='-' ||((int)input.charAt(index) >= (int)'0'&&(int)input.charAt(index) <= (int)'9'))
			return true;
		return false;
	}
	
	/**
	 * Checks if there are any unwanted characters in the whole string
	 * 
	 * @param input String
	 * @return true if there are characters that don't comply, false if everything complies
	 */
	private static boolean checkLetters(String input) {
		for (int l = 0; l < input.length(); l++) {
			if (checkChar(input,l) == false)
				return true;
		}
		return false;
	}

	/**
	 * Converts string of decimal numbers to a byte.
	 *
	 * @param in the String to convert
	 * @return the byte
	 * @throws NumberFormatException if value to convert is too big
	 */
	public static byte parseByte(String in) throws NumberFormatException {
		int number = parseInt(in);
		if (number < Min_Byte || number > Max_Byte)
			throw new NumberFormatException();
		return (byte)number;
	}
	
	/**
	 * Parses an unsigned binary string and returns the equivalent integer value (signed)
	 *
	 * @param in is the input binary string. Should have a leading "0b"
	 * @return  the integer value of the converted string
	 * @throws NumberFormatException if length is too short or doesn't start with "0b"
	 */
	public static int parseBinStrToInt(String in) throws NumberFormatException {
		if (in.length() <= 2 || countLengthWithoutUnderscore(in) > 34)
			throw new NumberFormatException("Invalid String Length");
		if (!checkBinStart(in))
			throw new NumberFormatException("Binary not indicated");
		if (!checkBinChar(in))
			throw new NumberFormatException("Invalid characters");
		int number = turnBintoInt(in, (int)Max_Int);
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
		if (in.length() <= 2 || countLengthWithoutUnderscore(in) > 10)
			throw new NumberFormatException("Invalid String Length");
		if (!checkBinStart(in))
			throw new NumberFormatException("Binary not indicated");
		if (!checkBinChar(in))
			throw new NumberFormatException("Invalid characters");
		int number = turnBintoInt(in,Max_Byte);
		return parseByte(String.valueOf(number));
	}
	
	/**
	 * Checks that the string starts with "0b"
	 * 
	 * @param the String of numbers given
	 * @return true if it contains "0b"
	 */
	public static boolean checkBinStart(String binary) {
		return (binary.charAt(0)=='0' && binary.charAt(1)=='b');
	}
	
	/**
	 * Turns the binary into a int, disregarding the first two characters
	 * 
	 * @param binary string
	 * @return converted integer
	 */
	public static int turnBintoInt(String binary, int max) {
		int number = 0;
		int start = 2;
		while (binary.charAt(start) == '_')
			start++;
		int largeLen = 8;
		if (max == Max_Int) { largeLen = 32;}
		if (binary.charAt(start)=='0' || (countLengthWithoutUnderscore(binary)-2)<largeLen) {
			for(int index = start; index < binary.length(); index++) {
				if (binary.charAt(index) != '_') {
					number = number*2 + ((int)binary.charAt(index)-(int)'0');
				}
			}
		}
		else {
			int temp = 0;
			for (int index = start+1; index < binary.length(); index++) {
				if (binary.charAt(index) != '_') {
					temp = temp*2 + ((int)binary.charAt(index)-(int)'0');
				}
			}
			number = ((max+1) - temp)*-1;
		}
		return number;
	}
	
	/**
	 * Counts the length of the string excluding the underscores
	 * @param in
	 * @return
	 */
	private static int countLengthWithoutUnderscore(String in) {
		int count = 0;
		for (int i = 0; i < in.length(); i++) {
			if (in.charAt(i)!='_')
				count++;
		}
		return count;
	}
	
	/**
	 * Check if the characters in a binary string are complacent
	 * @param input string
	 * @return false if non-compliant
	 */
	private static boolean checkBinChar(String in) {
		for (int i = 2; i < in.length(); i++) {
			if (!((int)in.charAt(i)==(int)'0' || (int)in.charAt(i)==(int)'1' || in.charAt(i)=='_'))
				return false;
		}
		return true;
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
		String binString = "";
		int input = in;
		for (int i = 0; i < 32; i++) {
			binString = (input%2 == 0) ? "0"+binString:"1"+binString;
			input = input/2;
		}
		if (in < 0) {
			binString = flipBits(binString);
			binString = addOne(binString);
		}
		return(binString);
	}
	
	/**
	 * Flipping bits for negative numbers
	 * @param input String
	 * @return flipped String
	 */
	private static String flipBits(String input) {
		String str = "";
		for (int i = 0; i < input.length(); i++) {
			str += (input.charAt(i)=='0') ? "1": "0";
		}
		return str;
	}
	
	/**
	 * Adding 1 to the negative number
	 * @param input
	 * @return
	 */
	private static String addOne(String input) {
		String newString = "";
		int index = input.length()-1;
		while (true) {
			if ((int)input.charAt(index)==(int)'1') {
				newString = '0' + newString;
				index--;
			}
			else {
				newString = '1' + newString;
				index--;
				break;
			}
		}
		for (int i = index; i >=0; i--) {
			newString = input.charAt(i) + newString;
		}
		return newString;
	}
	
	/**
	 * Returns the equivalent unsigned binary string (8 bits)
	 *
	 * @param in the byte to convert
	 * @return the equivalent binary string representation (8 bits)
	 */
	public static String byteToBinaryString(byte in) {
		String binString = "";
		int input = in;
		for (int i = 0; i < 8; i++) {
			binString = (input%2 == 0) ? "0"+binString:"1"+binString;
			input = input/2;
		}
		if (in < 0) {
			binString = flipBits(binString);
			binString = addOne(binString);
		}
		return(binString);
	}
	
	/**
	 * Returns the equivalent unsigned hex string (8 hex chars)
	 *
	 * @param in the integer to convert
	 * @return the equivalent hex string representation (8 hex chars)
	 */
	public static String intToHexString(int in) {
		String hexString = "";
		if (in < 0) {
			long temp = -(long)in;
			hexString = unsignedIntToHex((temp^4294967295L) + 1);
		}
		else
			hexString = unsignedIntToHex(in);
		return(hexString);
	}
	
	/**
	 * Helper method, turns unsigned number into Hex
	 * @param in
	 * @return
	 */
	private static String unsignedIntToHex(long in) {
		String hex = "";
		long remainder;
		for (int i = 0; i < 8; i++) {
			remainder = in%16;
			in = in/16;
			hex = turnIntToChar((int)remainder) + hex;
		}
		return hex;
	}

	/**
	 * Turn an integer 0-15 into a hex digit 0-9 and a-f
	 * @param input integer
	 * @return the corresponding character
	 */
	private static char turnIntToChar(int in) {
		if (in >= 0 && in <= 9) {
			return (char)('0'+in);
		}
		else
			return (char)('a'+(in-10));
	}
	
	/**
	 * Returns the equivalent unsigned hex string (2 hex chars)
	 *
	 * @param in the integer to convert
	 * @return the equivalent hex string representation (2 hex chars)
	 */
	public static String byteToHexString(byte in) {
		String hexString = "";
		if (in < 0) {
			int temp = -in;
			hexString = unsignedByteToHex((temp^255)+1);
		}
		else
			hexString = unsignedByteToHex(in);
		
		return(hexString);	
	}
	
	private static String unsignedByteToHex(int in) {
		String hex = "";
		int remainder;
		for (int i = 0; i < 2; i++) {
			remainder = in%16;
			in = in/16;
			hex = turnIntToChar(remainder) + hex;
		}
		return hex;
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
		/*System.out.println(parseInt("12345"));
	    System.out.println(parseInt("-543"));
	    System.out.println(parseInt("0123"));
	    System.out.println(parseInt("+0123"));
	    System.out.println(parseByte("12"));
	    */
	    
	    //Testing cases that should error
	    //System.out.println(parseInt("-2147483649"));
	    
	    System.out.println(intToBinaryString(-123));
	    System.out.println(intToBinaryString((int)Min_Int));
	    System.out.println(byteToBinaryString((byte)-123));
	    
		
		System.out.println(intToHexString(-1));
		System.out.println(intToHexString(-2147483648));
		System.out.println(byteToHexString((byte)-128));
		System.out.println("done");
	}

}
