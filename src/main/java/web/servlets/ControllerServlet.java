package web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xParam = request.getParameter("x");
        String yParam = request.getParameter("y");
        String rParam = request.getParameter("r");

        request.setAttribute("startTime", System.nanoTime());

        logger.info("Received GET request with parameters: x={}, y={}, r={}", xParam, yParam, rParam);

        if (validate(xParam, yParam, rParam)) {
            logger.info("Forwarding to /check");
            getServletContext().getRequestDispatcher("/check").forward(request, response);
        } else {
            logger.info("Forwarding to /index.jsp");

            request.setAttribute("errorMessage", "некорректные данные");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected boolean validate(String xParam, String yParam, String rParam) {
        try {
            double x = Double.parseDouble(xParam);
            double y = Double.parseDouble(yParam);
            double r = Double.parseDouble(rParam);

            return -1.25 * r <= x && x <= 1.25 * r && -1.25 * r <= y && y <= 1.25 * r && 1 <= r && r <= 5;
        } catch ( NumberFormatException | NullPointerException e ) {
            return false;
        }
    }
}
