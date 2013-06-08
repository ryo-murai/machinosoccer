import org.joda.time.DateTime;

enum LessonClass {
	H25C03_05('3～5歳（幼稚園・保育園児）', new DateTime(2999, 12, 31, 23, 59)),
	H25C06_07('6～7歳（小学1～2年生）', new DateTime(2999, 12, 31, 23, 59))

	final String label
	final DateTime expired
	LessonClass(String label, DateTime expiried) {
		this.label = label
		this.expired = expired
	}
}
