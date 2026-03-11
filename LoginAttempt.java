public class LoginAttempt {

    String time;
    boolean success;
    int risk;

    LoginAttempt(String time, boolean success, int risk) {
        this.time = time;
        this.success = success;
        this.risk = risk;
    }

    public String getRiskLevel() {

        if (risk == 0)
            return "LOW";
        else if (risk <= 3)
            return "MEDIUM";
        else
            return "HIGH";
    }

    public String toRow() {

        return String.format("%-10s %-10s %-10d %-10s",
                time,
                success,
                risk,
                getRiskLevel());
    }
}