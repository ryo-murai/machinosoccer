import com.google.appengine.api.datastore.*
log.info "Entering controller 'updateAttend.groovy'"

def attendMap = params.findAll { p ->
	p.key.toString().startsWith('attendOpt_')
}.collectEntries { k, v ->
	[k.toString().replaceFirst('attendOpt_', '') as Key, v]
}.groupBy { p ->
	p.value
}

log.info "attendMap-- $attendMap"

def currentTime = new Date()

def noAttendedMemberKeys = attendMap.no.collect { k, v -> k }
def toAttendMembers = attendMap.yes.collect { k, v -> 
	def e = new Entity(k) 
	e << [
		attendState: models.ApplyState.Applied.ordinal(),
		appliedTime: currentTime
	]
	e
}

datastore.delete(noAttendedMemberKeys)
datastore.put(toAttendMembers)

// TODO:
redirect 'accept_apply.html'