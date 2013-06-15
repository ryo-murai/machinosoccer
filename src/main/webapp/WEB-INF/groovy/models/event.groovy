package models

import groovyx.gaelyk.datastore.*

@Entity
class Event {
	@Indexed Date date
	Integer limit
	String lessonClassId
	String duration
	String location
	@Indexed Boolean isActive
	@Indexed Date dueApply
	String description

	@Ignore
	LessonClass getLessonClass() {
		LessonClass.valueOf(this.lessonClassId)
	}
}