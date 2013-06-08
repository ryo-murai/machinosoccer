<!DOCTYPE html>
<html lang="ja">
<head>
    <title>参加者インポート</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<form class="form" action="" method="post">
	<textarea name="json" cols="120" rows="20" >${request.json?:''}</textarea>
	<input type="submit" class="btn btn-primary" value="インポート" />
</form>
<div class="alert ${request.state?:'info'}">
<pre>${request.message?:''}</pre>
</div>
    <script src="js/jquery-1.7.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>