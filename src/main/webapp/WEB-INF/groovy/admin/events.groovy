import java.text.SimpleDateFormat;

import com.google.appengine.api.datastore.*

log.info "Entering controller 'events.groovy'"

request.events = []

def dsEvents = datastore.execute {
	select all from Event
	sort asc by date
}

dsEvents.each { ev ->
	request.events.add(
		id: ev.key.id,
		description: ev.description,
		date: ev.date.format("yyyy'年'MM'月'dd'日'"),
		limit: ev.limit,
		lessonClass: LessonClass.valueOf(ev.lessonClassId).label,
		activeState: ev.isActive?"募集中":"募集期間外",
		dueApply: ev.dueApply.format("yyyy'年'MM'月'dd'日　'HH':'mm"),
	)
}

forward 'events.gtpl'