import com.google.appengine.api.datastore.*
log.info "Entering controller 'login.groovy'"

def user = datastore.execute {
	select single from User as models.User
	where email == params.loginId
}

log.info "user $user"

if(user==null) {
	request.error = 'メールアドレスまたはパスワードが正しくありません'
	request.email = params.loginId
	forward '/login.gtpl'
	return
}

def session = request.getSession(true)
session.setAttribute('user', user as Entity)

redirect '/attend'