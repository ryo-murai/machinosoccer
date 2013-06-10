import org.joda.time.DateTime;

enum Age {
	Three(3, '３歳ー年少'),
	Four(4, '４歳ー年中'),
	Five(5, '５歳ー年長'),
	Six(6, '６歳ー小１'),
	Seve(7, '７歳ー小２')
	
	final int age
	final String label
	Age(int age, String label) {
		this.age = age
		this.label = label
	}

	static Age fromInt(int age) {
		Age.values().find { it.age == age }
	}
	
	static Age fromBirthFY(int birthFY) {
		fromInt(ageInFY(birthFY))
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
