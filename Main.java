import java.util.*;

public class Main {

    public static void main(String[] args){

        LoginAnalyzer analyzer = new LoginAnalyzer();
        Scanner sc = new Scanner(System.in);

        System.out.println("Login Behavior Analysis System");

        while(true){

            System.out.print("\nEnter Username (or exit): ");
            String user = sc.nextLine();

            if(user.equalsIgnoreCase("exit"))
                break;

            System.out.print("Enter Password: ");
            String pass = sc.nextLine();

            System.out.print("Enter Time (HH:MM): ");
            String time = sc.nextLine();

            if(!time.matches("\\d{2}:\\d{2}")){
                System.out.println("Invalid time format. Use HH:MM.");
                continue;
            }

            String[] parts = time.split(":");
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            if(hour < 0 || hour > 23 || minute < 0 || minute > 59){
                System.out.println("Invalid time values.");
                continue;
            }

            analyzer.analyzeLogin(user,pass,time,hour);
        }

        analyzer.showRecentLogins();
        analyzer.showMostRecent();
        analyzer.showRuleFrequency();
        analyzer.showHighRiskFirst();

        ArrayList<LoginAttempt> history = analyzer.getHistory();

        Algorithms.linearSearch(history);

        LoginAttempt[] arr = history.toArray(new LoginAttempt[0]);

        Algorithms.mergeSort(arr,0,arr.length-1);

        System.out.println("\nSorted Logins");

        Algorithms.printHeader();

        for(LoginAttempt a:arr)
            System.out.println(a.toRow());

        LoginAttempt result = Algorithms.binarySearch(arr,3);

        System.out.println("\nBinary Search Result");

        if(result!=null)
            System.out.println(result.toRow());
        else
            System.out.println("No login found");

        sc.close();
    }
}