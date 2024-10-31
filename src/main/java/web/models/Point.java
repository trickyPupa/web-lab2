package web.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Point {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private final double x;
    private final double y;
    private final double r;
    private final double executionTime;
    private final String datetime;

    private final boolean status;

    public Point(double x, double y, double r, boolean status, double executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.status = status;
        this.executionTime = executionTime;
        datetime = LocalDateTime.now().format(formatter);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean getStatus() {
        return status;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public String getDatetime() {
        return datetime;
    }

    public String toString() {
        return "Point{" +
                "x = " + x +
                ", y = " + y +
                ", r = " + r +
                ", is in area = " + status +
                ", execution time = " + executionTime +
                "}";
    }
}
