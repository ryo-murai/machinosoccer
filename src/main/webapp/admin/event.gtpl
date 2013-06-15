<!DOCTYPE html>
<html lang="ja">
<head>
    <title>イベント</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<h2>イベント</h2>
<form class="form form-horizontal" action="/admin/event/edit" method="post">
<!-- todo: horizontal form -->
<fieldset>
	<input name="id" type="hidden" value="${request.event.id?:''}" />
	<div class="control-group">
	<label for="date" class="control-label">開催日</label>
	<div class="controls">
		<input name="year" type="text" class="span1" placeholder="西暦" value="${request.event.year}" />年
		<input name="month" type="text" class="span1" placeholder="月" value="${request.event.month}" />月
		<input name="day" type="text" class="span1" placeholder="日" value="${request.event.day}" />日
	</div>
	</div>

	<div class="control-group">
	<label for="lessonClassId" class="control-label">クラス</label>
	<div class ="controls">
	<select	class="" name="lessonClassId">
		<% request.lessons.each { l -> %>
		<option value="${l.name()}" ${l.name() == request.event.lessonClassId ? 'selected' : ''}>${l.label}</option>
		<% } %>
	</select>
	</div>
	</div>

	<div class="control-group">
	<label class="control-label" for="duration">時間</label>
	<div class ="controls">
	<div class="input-prepend">
		<input name="duration" type="text" id="duration" class="" value="${request.event.duration}" />
		<div class="btn-group">
		<button class="btn dropdown-toggle" data-toggle="dropdown">定型文
		<span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li>10:00-11:00</li>
			<li>11:00-12:30</li>
		</ul>
		<span class="help-inline">未実装ですが、定型文からの入力支援とクラス選択と連動入力</span>
		</div>
  	</div>
	</div>
	</div>

	<div class="control-group">
	<label class="control-label" for="location">場所</label>
	<div class ="controls">
	<div class="input-prepend">
		<input name="location" type="text" class="" value="${request.event.location}" />
		<div class="btn-group">
		<button class="btn dropdown-toggle" data-toggle="dropdown">定型文
		<span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li>柏の葉小学校グラウンド</li>
			<li>柏の葉小学校体育館</li>
		</ul>
		<span class="help-inline">未実装ですが、定型文からの入力支援</span>
		</div>
  	</div>
	</div>
	</div>

	<div class="control-group">
	<label class="control-label" for="limit">定員</label>
	<div class ="controls">
	<input name="limit" type="text" class="span1" value="${request.event.limit}" /><span>名</span>
	</div>
	</div>
    
	<div class="control-group">
	<label class="control-label" for="isActive">募集状況</label>
	<div class ="controls alert">
		<div>募集開始前にイベントを登録したり、締切日時まえに募集を打ち切りたいときは、募集期間外に変更します</div>
	    <label class="radio inline">
	      <input type="radio" name="isActive" id="activeStateY" value="true" ${request.event.isActive? 'checked':''}>募集中
	    </label>
	   
	    <label class="radio inline">
	      <input type="radio" name="isActive" id="activeStateN" value="false" ${request.event.isActive? '':'checked'}>募集期間外
	    </label>
	</div>
	</div>

	<div class="control-group">
	<label for="dueApply" class="control-label">締切日時</label>
	<div class ="controls">
		<input name="dueYear" type="text" class="span1" placeholder="西暦" value="${request.event.dueYear}" /><span>年</span>
		<input name="dueMonth" type="text" class="span1" placeholder="月" value="${request.event.dueMonth}" /><span>月</span>
		<input name="dueDay" type="text" class="span1" placeholder="日" value="${request.event.dueDay}" /><span>日</span>
		<div class="input-prepend">
		<input name="dueHour" type="text" class="span1" placeholder="時" value="${request.event.dueHour}" />
		<span class="add-on">:00</span>
		</div>
	</div>
	</div>


	<div class="control-group">
	<label for="description" class="control-label">備考</label>
	<div class ="controls">
		<textarea name="description" class="span4" rows="3">${request.event.description}</textarea>
	</div>
	</div>
</fieldset>
 
	<div class="control-group">
	<label for="submit" class="control-label"></label>
	<div class ="controls">
		<input type="submit" class="btn btn-primary" value="${request.btnLabel}" />
		<a href="/admin/events" class="btn">イベント一覧に戻る</a>
	</div>
	</div>
</form>
<% if(request.event.id != '') { %>
<div class="alert alert-error">
<strong>危険操作</strong>
<form class="form form-horizontal" 
	action="/admin/event/delete/${request.event.id}" 
	method="post" 
	onsubmit="return confirm('本当によろしいですか？');">
	<input type="submit" class="btn btn-danger" value="削除" />
	<span class="help-block"><small>削除すると、このイベントへの参加申込情報もすべて削除されます</small></span>
</form>
</div>
<% } %>

    <script src="/js/jquery-1.7.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>