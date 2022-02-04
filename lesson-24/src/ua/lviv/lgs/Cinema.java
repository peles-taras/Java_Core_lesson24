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
		System.out.println("������ ����� ������ ��� ���������� � ����������");
		String movieName = sc.next();
		System.out.println("������ ��������� ������(���)");
		int movieDurationHour = sc.nextInt();
		System.out.println("������ ��������� ������(��)");
		int movieDurationMin = sc.nextInt();
		if (movieDurationMin > 59) {
			System.out.println("�������. ������������ ������ ����.");

		} else if (movieDurationHour > 23) {
			System.out.println("�������. Գ��� ������� ������");
		} else {
			moviesLibrary.add(new Movie(movieName, new Time(movieDurationHour, movieDurationMin)));
			System.out.println("Գ��� " + movieName + " - ������");
		}
	}

	public void showMovies() {
		int count = 1;
		if (moviesLibrary.size() == 0) {
			System.out.println("���������� �����");
		} else {
			for (Movie movie : moviesLibrary) {
				System.out.println("����� �" + count++ + ": " + movie);
			}
		}
	}

	public void removeMovie() {
		System.out.println("������ ����� ������ ��� ���������");
		showMovies();
		String movieName = sc.next();
		Optional<Movie> findFirst = moviesLibrary.stream().filter(m -> m.getTitle().equalsIgnoreCase(movieName))
				.findFirst();
		if (findFirst.isPresent()) {
			moviesLibrary.remove(findFirst.get());
			schedules.values()
					.forEach(s -> s.seances.removeIf(seance -> seance.movie.getTitle().equalsIgnoreCase(movieName)));
			System.out.println("Գ��� " + movieName + " - ��������");
		} else {
			System.out.println("�������. ���� ������ �� ����� ������");
		}
	}

	public void addSeance() {
		System.out.println("��������, �������� ������ � 9:00 �� 22:00");
		System.out.println("������ ���� ����� (Monday, Tuesday, etc.)");
		String dayOFW = sc.next();
		if (Days.valueOf(dayOFW.toUpperCase()) != null) {
			Days valueOf = Days.valueOf(dayOFW.toUpperCase());
			System.out.println("������ ����� ������ �� �������. ������� ������:");
			showMovies();
			String movieName = sc.next();
			Optional<Movie> findFirst = moviesLibrary.stream().filter(m -> m.getTitle().equalsIgnoreCase(movieName))
					.findFirst();
			if (findFirst.isPresent()) {
				int indexOf = moviesLibrary.indexOf(findFirst.get());
				schedules.put(valueOf, s.addSeance(moviesLibrary.get(indexOf), s));
			}
		} else {
			System.out.println("�������");
		}
	}

	public void showSeances() {
		int count = 1;
		if (schedules.size() < 1) {
			System.out.println("���� ������");
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
		System.out.println("������ ������ ������� ������");
		int time = sc.nextInt();
		System.out.println("������ ������� ������� ������");
		int min = sc.nextInt();

		schedules.values().forEach(
				v -> v.seances.removeIf(t -> t.getStartTime().getHour() == time && t.getStartTime().getMin() == min));
		System.out.println("����� ���������� �� " + time + ":" + min + " - ��������");
	}

	@Override
	public String toString() {
		return "Cinema schedules=" + schedules + ", moviesLibrary=" + moviesLibrary + "";
	}

}
