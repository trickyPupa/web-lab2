package web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.beans.ResultBean;
import web.models.Point;

import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AreaCheckServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double x = Double.parseDouble(request.getParameter("x"));
        double y = Double.parseDouble(request.getParameter("y"));
        double r = Double.parseDouble(request.getParameter("r"));
        double startTime = Double.parseDouble(request.getParameter("startTime"));

        logger.info("Received POST request with parameters: x={}, y={}, r={}", x, y, r);

        boolean result = checkArea(x, y, r);

        logger.info("Area check result: {}", result);

//        request.setAttribute("x", x);
//        request.setAttribute("y", y);
//        request.setAttribute("r", r);
        request.setAttribute("result", result);


        HttpSession session = request.getSession();
        ResultBean resultBean = (ResultBean) session.getAttribute("resultBean");

        if (resultBean == null) {
            logger.info("Creating new ResultBean for session");
            resultBean = new ResultBean();
            session.setAttribute("resultBean", resultBean);
        }

        logger.info("Adding result to ResultBean: x={}, y={}, r={}, result={}", x, y, r, result);
        resultBean.add(new Point(x, y, r, result, System.nanoTime() - startTime));

        logger.info("Forwarding to /result.jsp");
        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private boolean checkArea(double x, double y, double r) {
        boolean rect = x <= 0 && y >= 0 && x >= -r/2 && y <= r;
        boolean circle = x >= 0 && y <= 0 && x*x + y*y <= r*r;
        boolean triangle = x <= 0 && y <= 0 && y > -x - r;

        return rect || circle || triangle;
    }
}
