package web.utils;

import jakarta.servlet.http.HttpServletRequest;

public class PointCheckRequest {
    private final double x;
    private final double y;
    private final double r;

    public static PointCheckRequest of(HttpServletRequest request)
            throws NullPointerException, IllegalArgumentException {
        double x = Double.parseDouble(request.getParameter("x"));
        double y = Double.parseDouble(request.getParameter("y"));
        double r = Double.parseDouble(request.getParameter("r"));

        if (!validate(x, y, r)) {
            throw new IllegalArgumentException("некорректные данные");
        }

        return new PointCheckRequest(x, y, r);
    }

    private PointCheckRequest(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
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

    private static boolean validate(double x, double y, double r) {
        return -1.25 * r <= x && x <= 1.25 * r && -1.25 * r <= y && y <= 1.25 * r && 1 <= r && r <= 5;
    }
}
