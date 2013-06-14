<!DOCTYPE html>
<html lang="ja">
<head>
    <title>参加者登録</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<form class="form form-horizontal" action="/admin/event/edit" method="post">
<!-- todo: horizontal form -->
<fieldset>
	<input name="id" type="hidden" value="${request.event.id?:''}" />
	<label for="date">
	開催日
	<input name="year" type="text" class="span1" placeholder="西暦" value="${request.event.year}" />年
	<input name="month" type="text" class="span1" placeholder="月" value="${request.event.month}" />月
	<input name="day" type="text" class="span1" placeholder="日" value="${request.event.day}" />日
	</label>

	<label for="lessonClassId">
	クラス
	<select	class="" name="lessonClassId">
		<% request.lessons.each { l -> %>
		<option value="${l.name()}" ${l.name() == request.event.lessonClassId ? 'selected' : ''}>${l.label}</option>
		<% } %>
	</select>
	</label>

	<label for="limit">
	定員
	<input name="limit" type="text" class="span1" value="${request.event.limit}" />名
	</label>

    <label class="radio inline">
      <input type="radio" name="isActive" id="activeStateY" value="true" ${request.event.isActive? 'checked':''}>募集中
    </label>
    <label class="radio inline">
      <input type="radio" name="isActive" id="activeStateN" value="false" ${request.event.isActive? '':'checked'}>募集期間外
    </label>

	<label for="dueApply">
	締切日時
	<input name="dueYear" type="text" class="span1" placeholder="西暦" value="${request.event.dueYear}" />年
	<input name="dueMonth" type="text" class="span1" placeholder="月" value="${request.event.dueMonth}" />月
	<input name="dueDay" type="text" class="span1" placeholder="日" value="${request.event.dueDay}" />日
	<input name="dueHour" type="text" class="span1" placeholder="時" value="${request.event.dueHour}" />時
	</label>

	<label for="description">
	案内文
	<textarea name="description" rows="3">${request.event.description}</textarea>
	</label>
</fieldset>
 
	<input type="submit" class="btn btn-primary" value="${request.btnLabel}" />
	<a href="/admin/events" class="btn">イベント一覧に戻る</a>
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