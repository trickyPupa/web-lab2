package web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.utils.PointCheckRequestDTO;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PointCheckRequestDTO transfer = PointCheckRequestDTO.of(request);
            request.setAttribute("data", transfer);

            logger.info("Forwarding to /check");
            getServletContext().getRequestDispatcher("/check").forward(request, response);
        }
        catch ( NullPointerException | IllegalArgumentException e ) {
            logger.error(e);
            logger.info("Forwarding to /index.jsp");

            request.setAttribute("errorMessage", "некорректные данные");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
