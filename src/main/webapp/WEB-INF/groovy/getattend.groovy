import com.google.appengine.api.datastore.*
import static models.Constants.*

log.info "Entering controller 'getattend.groovy'"

def user = request.getSession(false)?.getAttribute('user')
if(!user) {
	log.info 'no session object stored'
	redirect '/'
	return
}

def now = new Date()
def activeEvents = datastore.execute {
	select all from Event as models.Event
	where isActive == true 
	and   date > now
	sort asc by date
}

if(activeEvents.isEmpty()) {
	forward 'noActiveEvents.gtpl'
	return
}

// TODO: handle cancel for future event but overdue


def members = datastore.execute {
	select all from Member as models.Member
	ancestor user.key
}

def attendKeys = members.collectMany { m ->
	def lessonClass = models.LessonClass.findByAge(m.age.intValue)
	activeEvents.findAll { e -> 
		e.lessonClass == lessonClass 
	}.collect { e ->
		def evKey = ['Event', e.id] as Key
		[evKey, 'Attend', m.id] as Key
	}
}

// DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG
// DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG
//log.info "events = ${activeEvents}"
//log.info "members = ${members}"
//log.info "attendKeys = ${attendKeys}"
// DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG
// DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG


def attends = datastore.get(attendKeys as List<Key>)

request.email = user.email
request.autoApply = true  // TODO: obtain value from datastore
request.description = activeEvents.inject('') { s, e -> s +
	e.date.format('MM/dd(E)', MyTimeZone) + e.duration + " " + e.lessonClass.label + '\n' +
	" @" + e.location + " 定員:" + e.limit + "名\n"
}

request.attendMembers = []
members.each { m ->
	def lessonClass = models.LessonClass.findByBirthFY(m.birthFY.toInteger())
	request.attendMembers.add(
		name: "${m.familyName} ${m.givenName}(${m.familyNameKana} ${m.givenNameKana})",
		printableAge: m.age.label,
		memberId: m.id,
		attendList: activeEvents.findAll { e -> 
			e.lessonClass == lessonClass
		}.collect { e ->
			def evKey = ['Event', e.id] as Key
			def attendKey = [evKey, 'Attend', m.id] as Key
			
			[
				applied: attends.containsKey(attendKey),
				printableDate: e.date.format('MM/dd(E)', MyTimeZone),
				eventKey: evKey as String,
				attendKey: attendKey as String,
			] 
		}
	)
}

forward 'attend.gtpl'
