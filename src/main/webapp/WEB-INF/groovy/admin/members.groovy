log.info "Entering controller 'members.groovy'"

def dsMembers = datastore.execute {
	select all from Member
}

request.members = []
dsMembers.each { m ->
	int age = LessonClass.ageInFY(m.birthFY.toInteger())
	request.members.add(
		name: "${m.familyName} ${m.givenName}",
		nameKana: "${m.familyNameKana} ${m.givenNameKana}",
		lessonClass: LessonClass.findByAge(age).label,
		age: age,
		gender: Gender.values()[m.gender.toInteger()].label,
	)
}

forward '/admin/members.gtpl'