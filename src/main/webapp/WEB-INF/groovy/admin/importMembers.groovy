import java.io.PrintWriter;

import org.codehaus.groovy.runtime.StackTraceUtils;

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
				def user = new Entity("User")
				user.email = result.email
				user.contact = result.contact
				user.save()
	
				result.members.each { m ->
					def member = new Entity('Member', user.key)
					member << m
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