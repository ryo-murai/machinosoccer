import com.google.appengine.api.datastore.*
import org.joda.time.*
import static models.Constants.*

log.info "Entering controller 'updateEvent.groovy'"

def p = [
	date: new DateTime(
		params.year.toInteger(),
		params.month.toInteger(),
		params.day.toInteger(),
		0,
		0, MyJodaTimeZone).toDate(),
	limit: params.limit.toInteger(),
	lessonClassId: params.lessonClassId,
	isActive: params.isActive.toBoolean(),
	dueApply: new DateTime(
		params.dueYear.toInteger(),
		params.dueMonth.toInteger(),
		params.dueDay.toInteger(),
		params.dueHour.toInteger(),
		0, MyJodaTimeZone).toDate(),
]

p << params.subMap('duration', 'location', 'description')

if(!params.id.isEmpty()) p['id'] = params.id.toLong()

new models.Event(p).save()

redirect '/admin/events'