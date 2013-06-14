import com.google.appengine.api.datastore.*
import static models.Constants.*

log.info "Entering controller 'getattend.groovy'"

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

// temporary implementation
def user = datastore.prepare(new Query('User')).asSingleEntity()
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
log.info "events = ${activeEvents}"
log.info "members = ${members}"
log.info "attendKeys = ${attendKeys}"
// DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG
// DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG


def attends = datastore.get(attendKeys as List<Key>)

request.email = user.email
request.autoApply = true  // TODO: obtain value from datastore
request.description = ''  // TODO: obtain value from datastore

/*
request.description = '''
6月16日（日）　＠A小学校
10:00～11:00　3～5歳（幼稚園・保育園児）　　定員：30名
11:00～12:30　6～7歳（小学1～2年生）　　　　定員：30名
 
6月23日（日）　＠A小学校
10:00～11:00　3～5歳（幼稚園・保育園児）　　定員：30名
11:00～12:30　6～7歳（小学1～2年生）　　　　定員：30名
'''
*/

// TODO: filter by lesson class
request.attendMembers = []
members.each { m ->
	def lessonClass = models.LessonClass.findByBirthFY(m.birthFY.toInteger())
	request.attendMembers.add(
		name: "${m.familyName} ${m.givenName}(${m.familyNameKana} ${m.givenNameKana})",
		printableAge: m.age.label,
		memberKey: m.key as String,
		attendList: activeEvents.findAll { e -> 
			e.lessonClassId == lessonClass.name() 
		}.collect { e ->
			def attendKey = [e.key, 'Attend', m.key.id] as Key
			
			[
				applied: attends.containsKey(attendKey),
				printableDate: e.date.format('MM/dd(E)', MyTimeZone),
				eventKey: e.key as String,
				attendKey: attendKey as String,
			] 
		}
	)
}

forward 'attend.gtpl'
