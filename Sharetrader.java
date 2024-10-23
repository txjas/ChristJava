import java.util.Scanner;

    public class Sharetrader {
        static int max = 0;
    
        public static void maxProfit(int[] price) {
            if ( price.length < 2) {
                System.out.println("Not enough data to calculate profit.");
                return;
            }
    
            int len =price.length;
            for (int i = 0; i <len; i++) {
                for (int j = i + 1; j <len; j++) {
                   
                    int firstProfit = price[j] - price[i];
                    
                    
                    for (int k = j + 1; k <len; k++) {
                        for (int l = k + 1; l < len; l++) {
                           
                            int secondProfit = price[l] - price[k];
                            
                          
                            int totalP = firstProfit + secondProfit;
                            if (totalP > max) {
                                max = totalP;
                            }
                        }
                    }
                }
            }
            
            System.out.println("Maximum Profit: " + max);
        }
    
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
    
            System.out.print("Enter the number of stock prices: ");
            int n = scanner.nextInt();
            int[] prices = new int[n];
    
            System.out.println("Enter the stock prices:");
            for (int i = 0; i < n; i++) {
                prices[i] = scanner.nextInt();
            }
    
            maxProfit(prices);
            scanner.close();
        }
    }
