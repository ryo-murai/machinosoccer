import java.text.SimpleDateFormat;

import com.google.appengine.api.datastore.*

log.info "Entering controller 'events.groovy'"

def f1 = new SimpleDateFormat("yyyy'年'MM'月'dd'日'")
def f2 = new SimpleDateFormat("yyyy'年'MM'月'dd'日　'hh':'mm")

request.events = []

def dsEvents = datastore.execute {
	select all from Event
}

dsEvents.each { ev ->
	request.events.add(
		id: ev.key.id,
		description: ev.description,
		date: f1.format(ev.date),
		limit: ev.limit,
		lessonClass: LessonClass.valueOf(ev.lessonClassId).label,
		activeState: ev.isActive?"募集中":"募集期間外",
		dueApply: f2.format(ev.dueApply),
	)
}

forward 'events.gtpl'