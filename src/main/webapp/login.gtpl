<!DOCTYPE html>
<html lang="ja">
<head>
    <title>ログイン</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="alert alert-info">
	<h2>まちのサッカークラブ</h2>
	<div>ログインしてください</div>
</div>

<form class="form form-horizontal" action="login" method="post">
<fieldset>
	<div class="control-group">
	<label class="control-label" for="loginId">メールアドレスまたはID</label>
	<div class ="controls">
		<input name="loginId" type="email" class="span4" required value="${request.email?:''}" />
	</div>
	</div>
	<div class="control-group">
	<label class="control-label" for="password">パスワード</label>
	<div class ="controls">
		<input name="password" type="password" class="span4" required />
	 	<div class="${request.error!=null?'alert alert-error':''}">${request.error?:''}</div>
	</div>
	</div>
</fieldset>
 	<div class="control-group">
	<label for="submit" class="control-label"></label>
	<div class ="controls">
		<input type="submit" class="btn btn-primary" value="ログイン" />
	</div>
	</div>
</form>
    <script src="/js/jquery-1.7.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>