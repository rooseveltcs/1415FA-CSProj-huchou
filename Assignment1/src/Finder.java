import java.io.*;
import java.util.*;

class Factor {
	int input;
	List<Integer> factors;
	
	Factor (int n) {
		input = n;
		factors = new ArrayList<Integer>();
		// Calculates the factors and adds them to the list
		for(int i = 1; i <= input / 2; i++) {
			if(input % i == 0) {
				factors.add(i);
			}
		}
		factors.add(input);
	}
	
	public List<Integer> getFactors () {
		return factors;
	}
	
	public int getInput () {
		return input;
	}
	
	public String toString () {
		String result = "The factor(s) are " + factors.get(0);
		for(int i = 1; i < factors.size(); i++) {
			result += (", " + factors.get(i));
		}
		return result + "\n";
	}
}

public class Finder {
	
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean run = true;
		
		do {
			System.out.print("Enter an integer or two between the numbers 1 and 100: ");

			String userInput = reader.readLine();
			System.out.println();
			StringTokenizer tokenizer = new StringTokenizer(userInput);
			String firstToken = "";
			
			// Deals with empty inputs
			if (tokenizer.hasMoreTokens()) {
				firstToken = tokenizer.nextToken();
			}
			
			if (checkValidInput(firstToken)) {
				Factor firstInteger = new Factor(Integer.parseInt(firstToken));
				
				// If there is a second token check if it's another valid integer
				if (tokenizer.hasMoreTokens()) {
					String secondToken = tokenizer.nextToken();
					
					// If both integers are valid then calculate the greatest common denominator
					if (checkValidInput(secondToken)) {
						Factor secondInteger = new Factor(Integer.parseInt(secondToken));
						int greatestCD = findGCD(firstInteger.getFactors(), secondInteger.getFactors());
						System.out.println("The greatest common denominator is " + greatestCD);
						System.out.println();
					}
				} else {
					System.out.println(firstInteger);
				}
			}
			
			if (firstToken.equalsIgnoreCase("quit")) {
				run = false;
				System.out.println("Quitting...");
			}
		} while(run);
		
	}
	
	public static boolean checkValidInput (String s) {
		try {
			int n = Integer.parseInt(s);
			if (n >= 1 && n <= 100) {
				return true;
			} else {
				System.out.println("The integer is not between 1 and 100.");
				System.out.println();
			}
		} catch (Exception e) {
			if(!s.equalsIgnoreCase("quit")) {
				System.out.println("You entered an invalid input.");
				System.out.println();
			} 
		}
		return false;
	}
	
	public static int findGCD (List<Integer> factors1, List<Integer> factors2) {
		// Goes through the first list and checks each integer in the second list to find GCD
		int greatestCD = 1;
		for(int n : factors1) {
			for(int m: factors2) {
				if(n == m && n > greatestCD) {
					greatestCD = n;
				}
			}
		}
		return greatestCD;
	}
	
}
