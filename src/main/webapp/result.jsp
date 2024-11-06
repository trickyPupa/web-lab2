<%@ page import="web.beans.ResultBean" %>
<%@ page import="web.models.Point" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    <header class="header">Результаты</header>

    <div class="main">
        <div class="graph">
            <canvas id="canvas2" width="400" height="400"></canvas>
            <script>graphInit();</script>
        </div>

        <div class="records center-wrapper">
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
                    ResultBean results = (ResultBean) session.getAttribute("resultBean");
                    if (results != null) {
                        for (Point result : results.getList()) {
                %>
                <tr>
                    <td><%= result.getX() %></td>
                    <td><%= result.getY() %></td>
                    <td><%= result.getR() %></td>
                    <td><%= result.getStatus() ? "Попадание" : "Промах" %></td>
                    <td><%= String.format(Locale.ENGLISH, "%.4f", result.getExecutionTime()) %></td>
                    <td><%= result.getDatetime() %></td>
                </tr>
                <script>drawDot("<%= result.getX() %>", "<%= result.getY() %>", "<%= result.getR() %>", <%= result.getStatus() %>)</script>
                <%
                        }
                    }
                %>
                </tbody>
            </table>

            <div>
                <button class="link-button">
                    <a href="index.jsp">На главную</a>
                </button>
            </div>
        </div>
    </div>
</body>
</html>
