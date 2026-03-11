import java.util.*;

public class LoginAnalyzer {

    ArrayList<LoginAttempt> loginHistory = new ArrayList<>();

    Queue<LoginAttempt> recentQueue = new LinkedList<>();
    Stack<LoginAttempt> recentStack = new Stack<>();

    HashMap<String,Integer> ruleCount = new HashMap<>();

    PriorityQueue<LoginAttempt> riskQueue =
            new PriorityQueue<>((a,b)->b.risk-a.risk);

    int failedCount = 0;

    public void analyzeLogin(String user,String pass,String time,int hour){

        boolean success=false;
        int risk=0;

        // Check credentials
        if(user.equals("admin") && pass.equals("1234")){
            success=true;
            failedCount=0;
        }
        else{
            failedCount++;
            risk+=3;
            incrementRule("Failed Login");
        }

        // Check repeated failures
        if(failedCount>=2){
            risk+=2;
            incrementRule("Repeated Failure");
        }

        // Check unusual login time (12 AM – 5 AM)
        if(hour>=0 && hour<=5){
            risk+=2;   // FIXED (was 1 before)
            incrementRule("Unusual Login Time");
        }

        LoginAttempt attempt=new LoginAttempt(time,success,risk);

        // Store login
        loginHistory.add(attempt);

        recentQueue.add(attempt);
        recentStack.push(attempt);
        riskQueue.add(attempt);

        if(recentQueue.size()>5)
            recentQueue.poll();

        System.out.println("Risk Level: "+attempt.getRiskLevel());
    }

    private void incrementRule(String rule){

        ruleCount.put(rule,
                ruleCount.getOrDefault(rule,0)+1);
    }

    public void showRecentLogins(){

        System.out.println("\nRecent Logins (Queue)");

        Algorithms.printHeader();

        for(LoginAttempt a:recentQueue)
            System.out.println(a.toRow());
    }

    public void showMostRecent(){

        System.out.println("\nMost Recent Login (Stack)");

        Algorithms.printHeader();

        if(!recentStack.isEmpty())
            System.out.println(recentStack.peek().toRow());
    }

    public void showRuleFrequency(){

        System.out.println("\nRule Frequency");

        for(String key:ruleCount.keySet())
            System.out.println(key+" : "+ruleCount.get(key));
    }

    public void showHighRiskFirst(){

        System.out.println("\nHigh Risk First");

        Algorithms.printHeader();

        PriorityQueue<LoginAttempt> temp =
                new PriorityQueue<>(riskQueue);

        while(!temp.isEmpty())
            System.out.println(temp.poll().toRow());
    }

    public ArrayList<LoginAttempt> getHistory(){
        return loginHistory;
    }
}