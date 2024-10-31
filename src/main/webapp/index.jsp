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
	<header class="header">
		<div class="fio">
			Лабор Тимофей Владимирович
		</div>
		<div class="group">
			P3225
		</div>
		<div class="variant">
			Вариант 408966
		</div>
	</header>

	<table class="main">
		<thead>
		</thead>

		<tbody>
		<tr>
			<td>
				<div class="graph">
					<canvas id="canvas" width="400" height="400"></canvas>
				</div>
			</td>
			<td>
				<form onsubmit="submitForm()" autocomplete="off" class="inputs-td">
					<div class="inputs">
						<div class="x-container">
							<label class="text" for="x">
								Введите X (-3; 5):
							</label>
							<input type="text" name="x" id="x" autocomplete="off" required/>
						</div>
						<div class="y-container">
							<label class="text" id="y-label" for="y-buttons-group">Выберите Y:</label>
							<div id="y-buttons-group">
								<input class="btn" onclick="toggleActive(this, '.btn')" type="button" value="-3">
								<input class="btn" onclick="toggleActive(this, '.btn')" type="button" value="-2">
								<input class="btn" onclick="toggleActive(this, '.btn')" type="button" value="-1">
								<input class="btn" onclick="toggleActive(this, '.btn')" type="button" value="0">
								<input class="btn" onclick="toggleActive(this, '.btn')" type="button" value="1">
								<input class="btn" onclick="toggleActive(this, '.btn')" type="button" value="2">
								<input class="btn" onclick="toggleActive(this, '.btn')" type="button" value="3">
								<input class="btn" onclick="toggleActive(this, '.btn')" type="button" value="4">
								<input class="btn" onclick="toggleActive(this, '.btn')" type="button" value="5">
							</div>
						</div>
						<div class="r-container">
							<label class="text" for="r-checkboxes-group">Выберите R:</label>
							<div class="r-checkboxes-group" id="r-checkboxes-group">
								<label>
									<input type="checkbox" class="r-checkbox" onclick="checkOnly(this, '.r-checkbox')" value="1"/>
									1
								</label>
								<label>
									<input type="checkbox" class="r-checkbox" onclick="checkOnly(this, '.r-checkbox')" value="2"/>
									2
								</label>
								<label>
									<input type="checkbox" class="r-checkbox" onclick="checkOnly(this, '.r-checkbox')" value="3"/>
									3
								</label>
								<label>
									<input type="checkbox" class="r-checkbox" onclick="checkOnly(this, '.r-checkbox')" value="4"/>
									4
								</label>
								<label>
									<input type="checkbox" class="r-checkbox" onclick="checkOnly(this, '.r-checkbox')" value="5"/>
									5
								</label>
							</div>
						</div>
					</div>
					<p id="text-error">
						<c:if test="${not empty requestScope.errorMessage}">
							${requestScope.errorMessage}
						</c:if>
					</p>
					<button type="submit" class="send-data-button">Отправить</button>
				</form>
			</td>
		</tr>
		</tbody>
	</table>
	<script src="graph.js"></script>
	<script src="scripts.js"></script>
</body>
</html>
