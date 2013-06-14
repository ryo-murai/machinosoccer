import java.io.PrintWriter

import com.google.appengine.api.datastore.*

import groovy.json.JsonSlurper

log.info "Entering controller 'importMember.groovy'"

switch(request.method) {
	case 'POST':
		def slurper = new JsonSlurper()
		try {
			def result = slurper.parseText(params.json)
//			def users = datastore.execute {
//				select all from User
//				where email == result.email
//				limit 1
//			}
			datastore.withTransaction {
				// todo: support update request
//				def user = users.size > 0 ? users.first : new Entity("User")
				def user = new models.User(
					email: result.email, 
					contact: result.contact
				)
				user.save()
	
				result.members.each { m ->
					def member = new models.Member(m.plus('userKey': (user as Entity).key))
					member.save()
				}
			}

			request.message = 'succeeded'
			request.status = 'alert-success'
		} catch (Exception e) {
			def str = new StringWriter()
			def writer = new PrintWriter(str)
			e.printStackTrace(writer)
			request.message = str.toString()
			request.status = 'alert-error'
		}
	
	default:
		forward '/admin/importMembers.gtpl'
		break;
}