package StringMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length()>s2.length()) {
			return s1;
		}else {
			return s2;
		}
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		
		if(s.contains("underscores")==true) {
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i)==' ') {
					String t1=s.substring(0, i);
					String t2=s.substring(i+1, s.length());
			s=t1+"_"+t2;
				
				}
			}
		}
	return s;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		int divider = 0;
		String name1;
		String name2;
		String name3;
		String leader = null;
	for (int i = 1; i < s1.length(); i++) {
		if(i!= s1.length()-1) {
		if(s1.charAt(i)==' ' && s1.charAt(i-1) != ' ' && s1.charAt(i+1) != ' ') {
			divider=i;	
			}
		}
	}	
	name1=s1.substring(divider);
	
	for (int i = 1; i < s2.length(); i++) {
		if(i != 0 && i!= s2.length()-1) {
		if(s2.charAt(i)==' ' && s2.charAt(i-1) != ' ' && s2.charAt(i+1) != ' ') {
			
			divider=i;	
			}
		}
	}	
	name2=s2.substring(divider);
	
	for (int i = 1; i < s3.length(); i++) {
		if(i != 0 && i!= s3.length()-1) {
		if(s3.charAt(i)==' ' && s3.charAt(i-1) != ' ' && s3.charAt(i+1) != ' ') {
			
			divider=i;	
			}
		}
	}	
	name3=s3.substring(divider);
		if(name1.compareTo(name2)<0 && name1.compareTo(name3)<0) {
			leader= s1;
		} else if(name2.compareTo(name3)<0 && name2.compareTo(name1)<0) {
			leader= s2;
		} else if(name3.compareTo(name1)<0 && name3.compareTo(name2)<0) {
			leader= s3;
		} 
    leader=leader.trim();
		return leader;
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int yeehaw=0;
		for (int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))==true) {
			yeehaw+=Character.getNumericValue(s.charAt(i));
			}
			}
		return yeehaw;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		ArrayList <Integer> occurences=new ArrayList <Integer>();
		for (int i = 0; i < s.length(); i++) {
			if(i+substring.length()-1<s.length() && s.substring(i, i+substring.length()).equals(substring)) {
				occurences.add(i);
			}
		}
		return occurences.size();
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		byte[] b=s.getBytes();
		
		return Utilities.encrypt(b, (byte) key);
		
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int yeehaw=0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)==' ' && i-substring.length()>-1) {
			   if(s.substring(i-substring.length(), i).equals(substring)) {
				   yeehaw++;
			   }
			}
		}
		return yeehaw;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		ArrayList <Integer> occurences=new ArrayList <Integer>();
		for (int i = 0; i < s.length(); i++) {
			if(i+substring.length()-1<s.length() && s.substring(i, i+substring.length()).equals(substring)) {
				occurences.add(i);
			}
		}
		return occurences.get(occurences.size()-1)-(occurences.get(0)+substring.length());
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String uncluttered=new String();
		for (int i = 0; i < s.length(); i++) {
		if(Character.isLetter(s.charAt(i))==true) {
			uncluttered=uncluttered+s.charAt(i);
		}
		uncluttered=uncluttered.toLowerCase();
		}
		for(int i=0; i<uncluttered.length()/2; i++) {
			if(uncluttered.charAt(i)==uncluttered.charAt(uncluttered.length()-(i+1))) {
				
			}else {
				return false;
			}
		}
		return true;
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
