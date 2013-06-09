import org.joda.time.DateTime;

enum LessonClass {
	H25C03_05('3～5歳（幼稚園・保育園児）', 3..5, new DateTime(2999, 12, 31, 23, 59)),
	H25C06_07('6～7歳（小学1～2年生）', 6..7, new DateTime(2999, 12, 31, 23, 59))

	final String label
	final DateTime expired
	final Range range
	LessonClass(String label, Range r, DateTime expiried) {
		this.label = label
		this.range = r
		this.expired = expired
	}
	
	static LessonClass findByAge(int age) {
		LessonClass.values().find { l ->
			l.range.contains(age)
		}
	}

	static LessonClass findByBirthFY(int birthFY) {
		findByAge(ageInFY(birthFY))
	}
		
	static int ageInFY(int birthFY) {
		def now = DateTime.now()
		switch(now.monthOfYear) {
			case 1..3:
				return now.year - birthFY - 2 
			default:
				return now.year - birthFY - 1
		}
	}
}
