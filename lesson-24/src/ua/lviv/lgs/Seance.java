package ua.lviv.lgs;

public class Seance {
	Movie movie;
	Time startTime;
	Time endTime;
	private int hourTemp = 0;

	public Seance(Movie movie, Time startTime) {
		this.movie = movie;
		this.startTime = startTime;
		endTime = new Time(startTime.getHour() + movie.Duration.getHour(),
				startTime.getMin() + movie.Duration.getMin());
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public int getEndTimeMin() {
		int temp = startTime.getMin() + movie.Duration.getMin();
		if (temp >= 60) {
			hourTemp++;
			temp -= 60;
			return temp;
		} else {
			return startTime.getMin() + movie.Duration.getMin();
		}
	}

	public int getEndTimeHour() {
		int innerTemp = hourTemp;
		if (startTime.getHour() + movie.Duration.getHour() >= 24) {
			int temp = 24 - startTime.getHour();
			int excessive = movie.Duration.getHour() - temp;
			setHourTemp(0);
			return 1 + excessive + innerTemp;
		} else {
			if (hourTemp == 1)
				setHourTemp(0);
			return startTime.getHour() + movie.Duration.getHour() + 1;
		}
	}

	public int getHourTemp() {
		return hourTemp;
	}

	public void setHourTemp(int hourTemp) {
		this.hourTemp = hourTemp;
	}

	@Override
	public String toString() {
		return "Фільм-" + movie + ", Початок-" + startTime + ", Кінець-" + getEndTimeHour() + "год:" + getEndTimeMin()
				+ "хв" + "";
	}
}
