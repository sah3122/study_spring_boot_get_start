package me.study.spring_boot_get_start.sample;

/**
 * Created by dongchul on 2019-11-20.
 */
public class AppError {
    private String message;
    private String reason;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
