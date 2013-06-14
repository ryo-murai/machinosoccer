package models

import groovyx.gaelyk.datastore.*

@Entity(unindexed=false)
class Member {
	@Parent com.google.appengine.api.datastore.Key userKey
	String familyName
	String givenName
	String familyNameKana
	String givenNameKana
	Integer birthFY
	Integer gender
	
	@Ignore Age getAge() {
		Age.fromBirthFY(this.birthFY)
	}
}