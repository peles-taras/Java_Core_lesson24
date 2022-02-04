package ua.lviv.lgs;

public class Movie {
	private String title;
	Time Duration;

	public Movie(String title, Time duration) {
		super();
		this.title = title;
		Duration = duration;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "" + title + ", Тривалість-" + Duration + "";
	}

}
