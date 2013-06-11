import com.google.appengine.api.datastore.Key;

log.info "Entering controller 'deleteEvent.groovy'"

def key = ['Event', params.id.toLong()] as Key
log.info  "for delete $key"
key.delete()

redirect '/admin/events'