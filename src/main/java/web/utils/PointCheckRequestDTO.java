package web.utils;

import jakarta.servlet.http.HttpServletRequest;

public class PointCheckRequestDTO {
    private final double x;
    private final double y;
    private final double r;
    private final long startTime;

    public static PointCheckRequestDTO of(HttpServletRequest request)
            throws NullPointerException, IllegalArgumentException {
        double x = Double.parseDouble(request.getParameter("x"));
        double y = Double.parseDouble(request.getParameter("y"));
        double r = Double.parseDouble(request.getParameter("r"));

        long startTime = System.nanoTime();

        if (!validate(x, y, r)) {
            throw new IllegalArgumentException("некорректные данные");
        }

        return new PointCheckRequestDTO(x, y, r, startTime);
    }

    private PointCheckRequestDTO(double x, double y, double r, long startTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.startTime = startTime;
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

    public long getStartTime() {
        return startTime;
    }

    private static boolean validate(double x, double y, double r) {
        return -1.25 * r <= x && x <= 1.25 * r && -1.25 * r <= y && y <= 1.25 * r && 1 <= r && r <= 5;
    }
}
