import com.google.appengine.api.datastore.*
import org.joda.time.*

log.info "Entering controller 'updateEvent.groovy'"

def e = params.id.isEmpty() ? new Entity("Event") : new Entity("Event", params.id.toLong())
e.date = new DateTime(
	params.year.toInteger(), 
	params.month.toInteger(), 
	params.day.toInteger(),
	0,
	0).toDate()
e.limit = params.limit.toInteger()
e.lessonClassId = params.lessonClassId
e.isActive = params.isActive.toBoolean()
e.dueApply = new DateTime(
	params.dueYear.toInteger(),
	params.dueMonth.toInteger(),
	params.dueDay.toInteger(),
	params.dueHour.toInteger(),
	0).toDate()
e.description = params.description

e.save()

redirect '/admin/events'