package models

import groovyx.gaelyk.datastore.*

@Entity(unindexed=false)
class Attend {
	@Parent Key eventKey
	Integer attendState
	Date appliedTime
}