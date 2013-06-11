<!DOCTYPE html>
<html lang="ja">
<head>
    <title>参加者インポート</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<form class="form" action="" method="post">
<div>JSON形式で申込者連絡先と、メンバー情報を入れます。
<div><strong>例：</strong></div>
<pre>
{
  "email": "example@example.com",
  "contact": "090-1234-5678",
  "members": [
    {
      "familyName": "山田",
      "givenName": "花子",
      "familyNameKana": "やまだ",
      "givenNameKana": "はなこ",
      "birthFY": 2005,
      "gender": 1
    },
    {
      "familyName": "山田",
      "givenName": "太郎",
      "familyNameKana": "やまだ",
      "givenNameKana": "たろう",
      "birthFY": 2008,
      "gender": 0
    }
  ]
}
</pre>
<div>
<ul>
<li><span><code>birthFY</code>：生まれた年の年度</span></li>
<li><span><code>gender</code>： 0=男、1=女</span></li>
<li><div>詳しくは<a href="https://github.com/ryo-murai/machinosoccer/wiki/Data-Model">データモデル</a>参照</div></li>
</ul>
</div>

</div>
    <textarea name="json" rows="20" class="span8" >${request.json?:''}</textarea>
    <div><input type="submit" class="btn btn-primary" value="インポート" /></div>
</form>

<pre id="result" class="alert ${request.status?:'info'}">${request.message?:''}</pre>
    <script src="/js/jquery-1.7.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>