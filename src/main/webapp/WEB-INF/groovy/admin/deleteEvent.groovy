import com.google.appengine.api.datastore.Key;

log.info "Entering controller 'deleteEvent.groovy'"

def key = ['Event', params.id] as Key
log.info  "for delete $key"
datastore.delete(key)

redirect '/admin/events'