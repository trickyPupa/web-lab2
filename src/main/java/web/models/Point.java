package web.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Point implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(x, point.x) == 0 && Double.compare(y, point.y) == 0 && Double.compare(r, point.r) == 0 && Double.compare(executionTime, point.executionTime) == 0 && status == point.status && Objects.equals(datetime, point.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, executionTime, datetime, status);
    }
}
