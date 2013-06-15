package models

import groovyx.gaelyk.datastore.*

@Entity(unindexed=false)
class Attend {
	@Parent com.google.appengine.api.datastore.Key eventKey
	Integer attendState
	Date appliedTime
}