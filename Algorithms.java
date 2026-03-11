import java.util.*;

public class Algorithms {

    public static void printHeader() {

        System.out.printf("%-10s %-10s %-10s %-10s\n",
                "Time", "Success", "Risk", "Level");

        System.out.println("------------------------------------------");
    }

    // Linear Search
    public static void linearSearch(ArrayList<LoginAttempt> history) {

        System.out.println("\nFailed Login Attempts");

        printHeader();

        for (LoginAttempt a : history) {

            if (!a.success)
                System.out.println(a.toRow());
        }
    }

    // Binary Search
    public static LoginAttempt binarySearch(LoginAttempt[] arr, int risk) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (arr[mid].risk == risk)
                return arr[mid];

            if (arr[mid].risk < risk)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return null;
    }

    // Bubble Sort
    public static void bubbleSort(LoginAttempt[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (arr[j].risk > arr[j + 1].risk) {

                    LoginAttempt temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Merge Sort
    public static void mergeSort(LoginAttempt[] arr, int left, int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(LoginAttempt[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        LoginAttempt[] L = new LoginAttempt[n1];
        LoginAttempt[] R = new LoginAttempt[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {

            if (L[i].risk <= R[j].risk)
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }
}