package ua.lviv.lgs;

public class Time {
	private int hour;
	private int min;

	public Time(int hour, int min) {
		if ((hour >= 0 && hour <= 23) && (min >= 0 && min <= 59)) {
			this.hour = hour;
			this.min = min;
		}
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	@Override
	public String toString() {
		return "" + hour + "год:" + min + "хв";
	}

}
