package com.main.UI;

public class Time {
	int year = 0;
	int month = 0;
	int day = 0;
	int hour = 0;
	int minute = 0;
	double secend = 0;

	public Time() {

	}

	public Time(int year, int month, int day, int hour, int minute, double secend) {
		this.year = year;
		this.month = minute;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.secend = secend;
	}

	public Time(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = minute;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public Time(long l) {
		this.year = (int) (l / 10000000000l);
		if (year <= 0 || year >= 10000)
			throw new java.lang.IllegalArgumentException();
		this.month = (int) (l / 100000000l % 100);
		if (month <= 0 || month > 12)
			throw new java.lang.IllegalArgumentException();
		this.day = (int) (l / 1000000 % 100);
		if (day <= 0 || day > 31)
			throw new java.lang.IllegalArgumentException();
		this.hour = (int) (l / 10000 % 100);
		if (hour < 0 || hour >= 24)
			throw new java.lang.IllegalArgumentException();
		this.minute = (int) (l / 100 % 100);
		if (minute < 0 || minute >= 60)
			throw new java.lang.IllegalArgumentException();
		this.secend = (int) (l % 100);
		if (secend < 0 || secend >= 60)
			throw new java.lang.IllegalArgumentException();

	}

	public long toLong() {
		return (long) (year * 10000000000l + month * 100000000 + day * 1000000 + hour * 10000 + minute * 100 + secend);
	}

	public String toString() {
		return new String(
				year + "." + month + "." + day + "  " + hour + ":" + minute + (secend == 0 ? "" : " " + secend + "s"));
	}

}