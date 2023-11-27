package vo;

public class Counter {
	private String cntDate;
	private int cntNum;
	
	public String getCntDate() {
		return cntDate;
	}
	public void setCntDate(String cntDate) {
		this.cntDate = cntDate;
	}
	public int getCntNum() {
		return cntNum;
	}
	public void setCntNum(int cntNum) {
		this.cntNum = cntNum;
	}
	
	@Override
	public String toString() {
		return "counter [cntDate=" + cntDate + ", cntNum=" + cntNum + "]";
	}
	
	
}
