import java.sql.Date;

import com.google.appengine.api.datastore.*
import org.joda.time.*
import static models.Constants.*

log.info "Entering controller 'updateEvent.groovy'"

def p = [
	date: Date.parse('yyyy-MM-dd', params.date),
	limit: params.limit.toInteger(),
	lessonClassId: params.lessonClassId,
	isActive: params.isActive.toBoolean(),
	dueApply: Html5InputDateTimeFormatter.parseDateTime(params.dueApply).toDate()
]

p << params.subMap('duration', 'location', 'description')

if(!params.id.isEmpty()) p['id'] = params.id.toLong()

new models.Event(p).save()

redirect '/admin/events'