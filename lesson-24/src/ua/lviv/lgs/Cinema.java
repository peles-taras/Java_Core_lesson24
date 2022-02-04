package ua.lviv.lgs;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;

public class Cinema {

	TreeMap<Days, Schedule> schedules = new TreeMap<>();
	ArrayList<Movie> moviesLibrary = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	Schedule s = new Schedule();

	public void addMovie() {
		System.out.println("Введіть назву фільму для добавлення в фільмотеку");
		String movieName = sc.next();
		System.out.println("Введіть тривалість фільму(год)");
		int movieDurationHour = sc.nextInt();
		System.out.println("Введіть тривалість фільму(хв)");
		int movieDurationMin = sc.nextInt();
		if (movieDurationMin > 59) {
			System.out.println("Помилка. Неправильний формат часу.");

		} else if (movieDurationHour > 23) {
			System.out.println("Помилка. Фільм занадто довгий");
		} else {
			moviesLibrary.add(new Movie(movieName, new Time(movieDurationHour, movieDurationMin)));
			System.out.println("Фільм " + movieName + " - додано");
		}
	}

	public void showMovies() {
		int count = 1;
		if (moviesLibrary.size() == 0) {
			System.out.println("фільмотека пуста");
		} else {
			for (Movie movie : moviesLibrary) {
				System.out.println("фільм №" + count++ + ": " + movie);
			}
		}
	}

	public void removeMovie() {
		System.out.println("Введіть назву фільму для видалення");
		showMovies();
		String movieName = sc.next();
		Optional<Movie> findFirst = moviesLibrary.stream().filter(m -> m.getTitle().equalsIgnoreCase(movieName))
				.findFirst();
		if (findFirst.isPresent()) {
			moviesLibrary.remove(findFirst.get());
			schedules.values()
					.forEach(s -> s.seances.removeIf(seance -> seance.movie.getTitle().equalsIgnoreCase(movieName)));
			System.out.println("Фільм " + movieName + " - видалено");
		} else {
			System.out.println("Помилка. Немає фільму із такою назвою");
		}
	}

	public void addSeance() {
		System.out.println("Нагадуємо, кінотеатр працює з 9:00 до 22:00");
		System.out.println("Введіть день тижня (Monday, Tuesday, etc.)");
		String dayOFW = sc.next();
		if (Days.valueOf(dayOFW.toUpperCase()) != null) {
			Days valueOf = Days.valueOf(dayOFW.toUpperCase());
			System.out.println("Введіть назву фільма із кінотеки. Доступні фільми:");
			showMovies();
			String movieName = sc.next();
			Optional<Movie> findFirst = moviesLibrary.stream().filter(m -> m.getTitle().equalsIgnoreCase(movieName))
					.findFirst();
			if (findFirst.isPresent()) {
				int indexOf = moviesLibrary.indexOf(findFirst.get());
				schedules.put(valueOf, s.addSeance(moviesLibrary.get(indexOf), s));
			}
		} else {
			System.out.println("Помилка");
		}
	}

	public void showSeances() {
		int count = 1;
		if (schedules.size() < 1) {
			System.out.println("Немає сеансів");
			System.exit(0);
		} else {
			for (Entry<Days, Schedule> entry : schedules.entrySet()) {
				Days key = entry.getKey();
				Schedule val = entry.getValue();
				if (val != null) {
					System.out.println(count + ". " + key + val + "");
					count++;
				}
			}
		}
	}

	public void removeSeance() {
		showSeances();
		System.out.println("Введіть годину початку сеансу");
		int time = sc.nextInt();
		System.out.println("Введіть хвилину початку сеансу");
		int min = sc.nextInt();

		schedules.values().forEach(
				v -> v.seances.removeIf(t -> t.getStartTime().getHour() == time && t.getStartTime().getMin() == min));
		System.out.println("Сеанс назначений на " + time + ":" + min + " - видалено");
	}

	@Override
	public String toString() {
		return "Cinema schedules=" + schedules + ", moviesLibrary=" + moviesLibrary + "";
	}

}
