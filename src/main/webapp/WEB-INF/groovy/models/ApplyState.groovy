package models

enum ApplyState {
	NotAttended('不参加'),
	Applied('参加申込'),
	Accepted('参加確定'),
	Denied('参加不可'),
	Canceled('キャンセル'),
	
	final String label
	ApplyState(String label) {
		this.label = label
	}
}
