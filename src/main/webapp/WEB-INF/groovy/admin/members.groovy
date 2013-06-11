log.info "Entering controller 'members.groovy'"

def dsMembers = datastore.execute {
	select all from Member
}

request.members = []
dsMembers.each { m ->
	def age = Age.fromBirthFY(m.birthFY.toInteger())
	request.members.add(
		name: "${m.familyName} ${m.givenName}",
		nameKana: "${m.familyNameKana} ${m.givenNameKana}",
		lessonClass: LessonClass.findByAge(age.age).label,
		age: age.label,
		gender: Gender.values()[m.gender.toInteger()].label,
	)
}

forward '/admin/members.gtpl'