<html>
<head>
    <title>イベント</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
	<table class="table">
		<thead>
			<tr>
				<th>#</th>
				<th>開催日</th>
				<th>クラス</th>
				<th>詳細</th>
				<th>定員</th>
				<th>募集状況</th>
				<th>締切</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<% request.events.eachWithIndex { ev, i -> %>
			<tr>
				<td>${i+1}</td>
				<td>${ev.date}</td>
				<td>${ev.lessonClass}</td>
				<td><pre>${ev.description}</pre></td>
				<td>${ev.limit}</td>
				<td>${ev.activeState}</td>
				<td>${ev.dueApply}</td>
				<td>
					<a href="event/edit/${ev.id}" class="btn">編集</a>
					<a href="event/clone/${ev.id}" class="btn">複製</a>
				</td>
			</tr>
		<% } %>
		</tbody>
	</table>

	<a href="event/new" class="btn">新規イベント</a>
    <script src="/js/jquery-1.7.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>