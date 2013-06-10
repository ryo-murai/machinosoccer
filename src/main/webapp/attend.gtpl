<!DOCTYPE html>
<html lang="ja">
<head>
    <title>参加申込</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="well well-small">
    <strong>登録メールアドレス</strong>
    <span class="label label-success">${request.email}</span>
    <div>
<pre>${request.description}</pre>
</div>
</div>

<form class="form" action="accept_apply.html" method="post">

<% request.attendMembers.each { member -> %>

<fieldset>
    <strong>${member.name}</strong>
    <span class="label label-info">${member.printableAge}</span>
    <ul class="unstyled">

    <% member.attendList.each { attend -> %>

    <li>${attend.printableDate}
        <label class="radio inline">
          <input type="radio" name="attendOpt_${attend.attendKey}" id="attendOpt_${attend.attendKey}_y" value="yes" ${attend.applied?"checked":""}>参加
        </label>
        <label class="radio inline">
          <input type="radio" name="attendOpt_${attend.attendKey}" id="attendOpt_${attend.attendKey}_n" value="no" ${attend.applied?"":"checked"}>不参加
        </label>
    </li>

    <% } %>

    </ul>
</fieldset>

<% } %>

    <label class="checkbox">
      <input type="checkbox" name="autoApply" ${request.autoApply?"checked":""} >今後の開催は自動的に「参加」を申し込む
      <span class="help-block"><small>次回からは申込の手間がかからなくなります</small></span>
    </label>

  <div class="alert">
    <input type="submit" class="btn btn-primary" value="申込" />
           定員に達した場合は参加をお断りすることがあります
</div>
</form>
    <script src="js/jquery-1.7.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>