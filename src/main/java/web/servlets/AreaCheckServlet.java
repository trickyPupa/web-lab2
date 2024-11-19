package web.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.beans.ResultBean;
import web.models.Point;
import web.utils.PointCheckRequestDTO;

import java.io.IOException;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AreaCheckServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PointCheckRequestDTO data = (PointCheckRequestDTO) request.getAttribute("data");
        logger.info("Received GET request with parameters: x={}, y={}, r={}", data.getX(), data.getY(), data.getR());

        boolean result = checkArea(data.getX(), data.getY(), data.getR());
        logger.info("Area check result: {}", result);

//        HttpSession session = request.getSession();
//        ResultBean resultBean = (ResultBean) session.getAttribute("resultBean");
//        if (resultBean == null) {
//            logger.info("Creating new ResultBean for session");
//            resultBean = new ResultBean();
//            session.setAttribute("resultBean", resultBean);
//        }

        HttpSession session = request.getSession();
        ResultBean resultBean = (ResultBean) session.getAttribute("resultBean");

        if (resultBean == null) {
            logger.info("Creating new ResultBean for session");
            resultBean = new ResultBean();
            session.setAttribute("resultBean", resultBean);
        }
        double x = data.getX(), y = data.getY(), r = data.getR();
        logger.info("Adding result to ResultBean: x={}, y={}, r={}, result={}", x, y, r, result);
        resultBean.add(new Point(x, y, r, result,
                (double)(System.nanoTime() - data.getStartTime()) / 1000000));

        logger.info("Forwarding to /result.jsp");
        response.sendRedirect("/result.jsp");
    }

    private boolean checkArea(double x, double y, double r) {
        boolean rect = x <= 0 && y >= 0 && x >= -r/2 && y <= r;
        boolean circle = x >= 0 && y <= 0 && x*x + y*y <= r*r;
        boolean triangle = x <= 0 && y <= 0 && y >= -x - r;

        return rect || circle || triangle;
    }
}
