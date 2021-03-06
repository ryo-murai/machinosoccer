
get "/", forward: "/login.gtpl"
post "/login", forward: "/login.groovy"

get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"

get "/attend", forward: "/getattend.groovy"
post "/attend", forward: "/updateAttend.groovy"

get "/admin/events", forward: "/admin/events.groovy"

get "/admin/event/@operation?/@id?", forward: "/admin/event.groovy"

post "/admin/event/edit", forward: "/admin/updateEvent.groovy"
post "/admin/event/delete/@id?", forward: "/admin/deleteEvent.groovy"

get  "/admin/importMembers", forward: "/admin/importMembers.gtpl"
post "/admin/importMembers", forward: "/admin/importMembers.groovy"

get "/admin/members", forward: "/admin/members.groovy"