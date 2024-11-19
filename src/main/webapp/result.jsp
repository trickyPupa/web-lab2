<%@ page import="web.beans.ResultBean" %>
<%@ page import="web.models.Point" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="web.beans.SingletonBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link rel="stylesheet" href="css/styles.css"/>

    <title>я тима</title>
</head>
<body>
    <script src="scripts.js"></script>
    <script src="graph.js"></script>

    <header class="header">
        <div class="fio">
            Лабор Тимофей Владимирович
        </div>
        <div>Результаты</div>
        <div class="placeholder">placeholder</div>
    </header>

    <div class="main">
        <div class="graph">
            <canvas id="canvas2" width="400" height="400"></canvas>
            <script>graphInit();</script>
        </div>

        <div class="records center-wrapper">
            <button class="link-button">
                <a href="index.jsp">На главную</a>
            </button>

            <table class="records-table">
                <thead>
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Статус</th>
                    <th>Время выполнения, мс</th>
                    <th>Дата и время</th>
                </tr>
                </thead>
                <tbody id="records-body">
                <%
//                    ResultBean results = (ResultBean) session.getAttribute("resultBean");
                    SingletonBean results = SingletonBean.getInstance();
                    if (results != null) {
                        ArrayList<Point> list = results.getList();
                        Collections.reverse(list);
                        double currentR = list.get(0).getR();

                        for (Point result : list) {
                %>
                <tr>
                    <td><%= result.getX() %></td>
                    <td><%= result.getY() %></td>
                    <td><%= result.getR() %></td>
                    <td><%= result.getStatus() ? "Попадание" : "Промах" %></td>
                    <td><%= String.format(Locale.ENGLISH, "%.4f", result.getExecutionTime()) %></td>
                    <td><%= result.getFormattedDatetime() %></td>
                </tr>
                <script>drawDot("<%= result.getX() %>", "<%= result.getY() %>", "<%= currentR %>", <%= result.getStatus() %>)</script>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
