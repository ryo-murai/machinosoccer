<html>
<head>
    <title>サッカークラブメンバー</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
	<table class="table">
		<thead>
			<tr>
				<th>名前</th>
				<th>かな</th>
				<th>クラス</th>
				<th>年齢</th>
				<th>性別</th>
				<!-- 
				<th>連絡先</th>
				<th>e-mail</th>
				 -->
			</tr>
		</thead>
		<tbody>
		<% request.members.each { m -> %>
			<tr>
				<td>${m.name}</td>
				<td>${m.nameKana}</td>
				<td>${m.lessonClass}</td>
				<td>${m.age}</td>
				<td>${m.gender}</td>
				<!-- 
				<td>${m.contact}</td>
				<td>${m.email}</td>
				 -->
			</tr>
		<% } %>
		</tbody>
	</table>

    <script src="/js/jquery-1.7.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>