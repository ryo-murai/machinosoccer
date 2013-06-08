import javax.servlet.http.HttpServletResponse

import org.joda.time.DateTime;

log.info "Entering controller 'event.groovy'"

def event = null
switch(params.operation) {
	case 'edit':
		def id = params.id.toLong()
		def ev = datastore.get('Event', id)
		def date = new DateTime(ev.date.time)
		def dueDate = new DateTime(ev.dueApply.time)
		event = [
			id: id,
			year: date.year,
			month: date.monthOfYear,
			day: date.dayOfMonth,
			limit: ev.limit,
			lessonClassId: ev.lessonClassId,
			isActive: ev.isActive,
			dueYear: dueDate.year,
			dueMonth: dueDate.monthOfYear,
			dueDay: dueDate.dayOfMonth,
			dueHour: dueDate.hourOfDay,
			description: ev.description,
		]
		// DEBUG
		log.info "${event}"
		// fall through
		
	case 'new':
		log.info "1  ${request.event}"
		// create empty event DTO
		request.event = event ?: [
			id: '',
			year: '',
			month: '',
			day: '',
			limit: '',
			lessonClassId: '',
			isActive: '',
			dueYear: '',
			dueMonth: '',
			dueDay: '',
			dueHour: '',
			description: '',
		]
		log.info "2  ${request.event}"
		
		forward 'event.gtpl'
		break;

	default:
		log.info "${params.operation} operation is not support"
		response.sendError HttpServletResponse.SC_NOT_FOUND
		break;
}
