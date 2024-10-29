import java.util.Arrays;
import java.util.Scanner;

class Creditcard {
    static int[] ccNumber; // Declare array

    // Constructor to initialize credit card number
    Creditcard() {
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
    }

    

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        ccNumber = new int[9]; 
        
        System.out.println("Enter credit card number (8 digits):");
        for (int i = 0; i < ccNumber.length; i++) {
            ccNumber[i] = scanner.nextInt(); // Input each digit
        }
        Creditcard ob = new Creditcard();
        ob.checkLength();
        ob.reverseArray(ccNumber);
       

    }

    
}
