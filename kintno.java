public import java.util.Scanner;

public class kintno {

    static int arr[];

    static void kfrequency(int k) {
        int max = maxno();
        int freq[] = new int[max + 1];
        count(freq);
        result(k, freq);
    }

    static int maxno() {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    static void count(int[] frequency) {
        for (int i = 0; i < arr.length; i++) {
            frequency[arr[i]]++;
        }
    }

    static void result(int K, int[] frequency) {
        int[] topK = new int[K];  
        for (int i = 0; i < K; i++) {
            int maxNum = maxfn(frequency);
            topK[i] = maxNum;  
            frequency[maxNum] = -1;  
        }

        
        for (int i = K - 1; i >= 0; i--) {
            System.out.print(topK[i] + " ");
        }
    }

    static int maxfn(int[] frequency) {
        int maxFreq = -1;
        int maxNum = -1;
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > maxFreq) {
                maxFreq = frequency[i];
                maxNum = i;
            }
        }
        return maxNum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in an array:");
        int N = sc.nextInt();
        arr = new int[N];
        System.out.println("Enter the numbers in array:");
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Enter the value of K:");
        int k = sc.nextInt();
        kfrequency(k);
    }
}
