package models

import groovyx.gaelyk.datastore.*

@Entity(unindexed=false)
class User {
	String email
	String contact
}