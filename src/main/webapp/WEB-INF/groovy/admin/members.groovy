log.info "Entering controller 'members.groovy'"

def dsMembers = datastore.execute {
	select all from Member
}

request.members = []
dsMembers.each { m ->
	request.members.add(
		name: "${m.familyName} ${m.givenName}",
		nameKana: "${m.familyNameKana} ${m.givenNameKana}",
		lessonClass: LessonClass.findByAge(m.age.intValue).label,
		age: m.age.label,
		gender: Gender.values()[m.gender.toInteger()].label,
	)
}

forward '/admin/members.gtpl'