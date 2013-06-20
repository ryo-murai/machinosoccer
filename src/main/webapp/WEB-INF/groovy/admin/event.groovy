import javax.servlet.http.HttpServletResponse

import org.joda.time.DateTime
import static models.Constants.*

log.info "Entering controller 'event.groovy'"

def event = null
switch(params.operation) {
	case 'clone':
	case 'edit':
		def id = params.id.toLong()
		def ev = datastore.get('Event', id)
		def dueDate = new DateTime(ev.dueApply.time, MyJodaTimeZone)
		event = [
			id: params.operation == 'clone' ? '' : id,
			dateString: ev.date.format('yyyy-MM-dd', MyTimeZone),
			limit: ev.limit,
			lessonClassId: ev.lessonClassId,
			duration: ev.duration,
			location: ev.location,
			isActive: ev.isActive,
			dueApplyString: Html5InputDateTimeFormatter.print(dueDate),
			description: ev.description,
		]
		// fall through
		
	case 'new':
		// set empty if new operation
		request.event = event ?: [
			id: '',
			year: '',
			month: '',
			day: '',
			limit: '',
			lessonClassId: '',
			duration: '',
			location: '',
			isActive: '',
			dueYear: '',
			dueMonth: '',
			dueDay: '',
			dueHour: '',
			description: '',
		]
		
		request.lessons = models.LessonClass.values()
		request.btnLabel = (params.operation == 'edit') ? '更新' : '作成'
		forward 'event.gtpl'
		break;

	default:
		log.info "${params.operation} operation is not support"
		response.sendError HttpServletResponse.SC_NOT_FOUND
		break;
}
