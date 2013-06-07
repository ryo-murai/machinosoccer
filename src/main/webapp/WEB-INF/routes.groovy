
get "/", forward: "/WEB-INF/pages/index.gtpl"
get "/datetime", forward: "/datetime.groovy"

get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"

get "/attend", forward: "/getattend.groovy"
//post "/attend", forward: "/postattend.groovy"
post "/attend", forward: "/getattend.groovy"
