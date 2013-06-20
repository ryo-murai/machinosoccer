package models

import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat

class Constants {
	static final def MyTimeZone = TimeZone.getTimeZone('Asia/Tokyo')
	static final def MyJodaTimeZone = DateTimeZone.forTimeZone(MyTimeZone)
	
	static final def Html5InputDateTimeFormatter = 
			DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm")
			.withZone(MyJodaTimeZone)
}

