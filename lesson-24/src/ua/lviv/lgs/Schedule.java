package ua.lviv.lgs;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {

	Set<Seance> seances = new TreeSet<>((new SeanceComparator()));
	Scanner sc = new Scanner(System.in);

	public Schedule addSeance(Movie movie, Schedule schedule) {
		System.out.println("������ ��� ������� ������(���)");
		int startTimeHour = sc.nextInt();
		System.out.println("������ ��� ������� ������(��)");
		int startTimeMin = sc.nextInt();
		if (startTimeHour > 21 || startTimeHour < 9) {
			System.out.println("�������. ������� ������ 09:00 - 22:00");
			System.exit(0);
		} else if (startTimeMin > 59) {
			System.out.println("�������. ������������ ������ ����.");
			System.exit(0);
		} else {
			seances.add(new Seance(movie, new Time(startTimeHour, startTimeMin)));
			System.out.println("����� ������.");
			return schedule;
		}
		return null;

	}

	public void removeSeance() {
		System.out.println("������ ����� ������ ��� ��������� ������");
		showSeance();
		String movieName = sc.next();
		Optional<Seance> findFirst = seances.stream().filter(s -> s.getMovie().getTitle().equalsIgnoreCase(movieName))
				.findFirst();
		if (findFirst.isPresent()) {
			seances.remove(findFirst.get());
			System.out.println("����� �� ������� " + movieName + " - ��������");
		}

		else {
			System.out.println("���� ������ �� �������� ������ ������");
		}
	}

	public void showSeance() {
		for (Seance seance : seances) {
			System.out.println(seance);
		}
	}

	@Override
	public String toString() {
		return " - " + seances + "";
	}
}

class SeanceComparator implements Comparator<Seance> {
	@Override
	public int compare(Seance o1, Seance o2) {
		return o1.movie.getTitle().compareTo(o2.getMovie().getTitle());
	}
}
