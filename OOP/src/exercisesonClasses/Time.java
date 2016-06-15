package exercisesonClasses;

public class Time {
	private int hour;
	private int minute;
	private int second;

	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public void setTime(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	@Override
	public String toString() {
		String s = "";
		if (hour < 10) {
			s += "0" + hour + ":";
		} else {
			s += hour + ":";
		}
		if (minute < 10) {
			s += "0" + minute + ":";
		} else {
			s += minute + ":";
		}
		if (second < 10) {
			s += "0" + second;
		} else {
			s += second;
		}
		return s;
	}

	public Time nextSecond() {
		++second;
		return this;
	}

	public Time previousSecond() {
		--second;
		return this;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

}
