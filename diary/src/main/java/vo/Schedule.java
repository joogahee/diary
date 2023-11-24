package vo;

public class Schedule {
	private int scheduleNo;
	private String memeberId;
	private String scheduleDate;
	private String scheduleMemo;
	private String createdate;
	private String scheduleEmoji;
	
	@Override
	public String toString() {
		return "Schedule [scheduleNo=" + scheduleNo + ", memeberId=" + memeberId + ", scheduleDate=" + scheduleDate
				+ ", scheduleMemo=" + scheduleMemo + ", createdate=" + createdate + ", scheduleEmoji=" + scheduleEmoji
				+ "]";
	}
	
	public int getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	public String getMemeberId() {
		return memeberId;
	}
	public void setMemeberId(String memeberId) {
		this.memeberId = memeberId;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getScheduleMemo() {
		return scheduleMemo;
	}
	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getScheduleEmoji() {
		return scheduleEmoji;
	}
	public void setScheduleEmoji(String scheduleEmoji) {
		this.scheduleEmoji = scheduleEmoji;
	}
	
	
	
	
	
}
