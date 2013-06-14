package models

import org.joda.time.DateTimeZone;

class Constants {
	static final def MyTimeZone = TimeZone.getTimeZone('Asia/Tokyo')
	static final def MyJodaTimeZone = DateTimeZone.forTimeZone(MyTimeZone)
}

