import java.util.Arrays;
import java.util.Scanner;

class Creditcard {
    static int[] ccNumber; // Declare array

    // Constructor to initialize credit card number
    Creditcard() {
        Scanner scanner = new Scanner(System.in);
        ccNumber = new int[9]; // Set size to 8 for this example
        
        System.out.println("Enter credit card number (8 digits):");
        for (int i = 0; i < ccNumber.length; i++) {
            ccNumber[i] = scanner.nextInt(); // Input each digit
        }
    }

    // Method to check the length of the array and reverse it
    void checkLength() {
        if (ccNumber.length == 8 || ccNumber.length == 9) {
            System.out.println("Credit Card Number valid");
            
            // Remove last element and reverse the remaining array
            int last = ccNumber[ccNumber.length - 1]; // Get the last element
            int[] newArray = Arrays.copyOf(ccNumber, ccNumber.length - 1); // Copy without last element
            reverseArray(newArray); // Reverse the array
            ccNumber = newArray; // Store reversed array back in ccNumber
            
            System.out.println("Reversed array: " + Arrays.toString(ccNumber));
            
            // Call the other functions to continue the process
            otherStuff(last); // Pass 'last' as a parameter for validation
        } else {
            System.out.println("Credit Card number Invalid");
        }
    }

    // Method to reverse the array
    void reverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;
        
        while (start < end) {
            // Swap elements at start and end
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            
            start++;
            end--;
        }
    }

    // Method to process the credit card according to the rules
    void otherStuff(int last) {
        // Double values at odd positions and adjust them if they are greater than 9
        for (int i = 0; i < ccNumber.length; i += 2) { // Odd positions (0, 2, 4, etc.)
            int doubledValue = ccNumber[i] * 2;
            
            // If doubled value is greater than 9, add the digits
            if (doubledValue > 9) {
                doubledValue = (doubledValue / 10) + (doubledValue % 10); // Add digits
            }
            
            ccNumber[i] = doubledValue; // Update array with processed value
        }
        System.out.println("Doubled values: " + Arrays.toString(ccNumber));
        
        // Sum all digits
        int sum = 0;
        for (int i = 0; i < ccNumber.length; i++) {
            sum += ccNumber[i]; // Adding each element to the sum
        }
        System.out.println("Sum of all digits in the array: " + sum);
        
        // Subtract the last digit of the sum from 10 and check if it matches 'last'
        int lastDigit = sum % 10;
        System.out.println("Last digit of the sum: " + lastDigit);
        
        int result = 10 - lastDigit;
        System.out.println("Result after subtracting last digit from 10: " + result);
        
        // Validate card
        if (result == last) {
            System.out.println("Card is valid");
        } else {
            System.out.println("Card is Invalid");
        }
    }
    
    // Main method to create and use the CreditCard object
    public static void main(String[] args) {
        // Create a CreditCard object and invoke methods
        Creditcard card = new Creditcard(); // Constructor will prompt user for input
        card.checkLength(); // Check length and process the card
    }
}

