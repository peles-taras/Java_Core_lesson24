package ua.lviv.lgs;

import java.text.ParseException;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) throws ParseException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Cinema cinema = new Cinema();

		while (true) {
			menu();
			switch (sc.next()) {
			case "1":
				cinema.addMovie();
				break;
			case "2":
				cinema.showMovies();
				break;
			case "3":
				cinema.removeMovie();
				break;
			case "4":
				cinema.addSeance();
				break;
			case "5":
				cinema.showSeances();
				break;
			case "6":
				cinema.removeSeance();
				break;
			case "7":
				System.out.println("Вихід із системи.");
				System.exit(0);
				break;
			default:
				break;

			}
		}
	}

	public static void menu() {
		System.out.println("1 - добавити фільм в фільмотеку");
		System.out.println("2 - показати фільмотеку");
		System.out.println("3 - видалити фільм із фільмотеки та сеансів");
		System.out.println("4 - добавити сеанс");
		System.out.println("5 - показати сеанси");
		System.out.println("6 - видалити сеанс");
		System.out.println("7 - вийти із системи");
	}
}
