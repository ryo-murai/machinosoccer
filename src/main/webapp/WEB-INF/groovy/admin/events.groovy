import java.text.SimpleDateFormat

import com.google.appengine.api.datastore.*

import static models.Constants.*

log.info "Entering controller 'events.groovy'"

request.events = []

def dsEvents = datastore.execute {
	select all from Event as models.Event
	sort asc by date
}

dsEvents.each { ev ->
	request.events.add(
		id: ev.id,
		description: ev.description,
		date: ev.date.format("yyyy'年'MM'月'dd'日('E')'", MyTimeZone),
		limit: ev.limit,
		lessonClass: ev.lessonClass.label,
		duration: ev.duration,
		location: ev.location,
		activeState: ev.isActive?"募集中":"募集期間外",
		dueApply: ev.dueApply.format("yyyy'年'MM'月'dd'日('E')　'HH':'mm", MyTimeZone),
	)
}

forward 'events.gtpl'