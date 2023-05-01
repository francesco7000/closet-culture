<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
		table {
			border-collapse: collapse;
			width: 100%;
			max-width: 800px;
			margin: 0 auto;
			text-align: center;
			font-size: 1.2rem;
			line-height: 1.5;
		}
		
		th, td {
			padding: 0.5rem;
			border: 1px solid #ccc;
		}
		
		th {
			background-color: #f2f2f2;
			font-weight: bold;
		}
		
		td p {
			margin: 0;
		}
	</style>
</head>
<body>

	<h1>Carrello</h1>
	<table>
		<thead>
			<tr>
				<th>Articolo</th>
				<th>Quantità</th>
				<th>Varianti</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<h2>Nome articolo</h2>
				</td>
				<td>
					<p>Quantità: 2</p>
				</td>
				<td>
					<p>Colore: rosso, Quantità: 1</p>
					<p>Taglia: L, Quantità: 1</p>
				</td>
			</tr>
			<tr>
				<td>
					<h2>Nome articolo</h2>
				</td>
				<td>
					<p>Quantità: 1</p>
				</td>
				<td>
					<p>Colore: verde, Quantità: 1</p>
					<p>Taglia: M, Quantità: 1</p>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>