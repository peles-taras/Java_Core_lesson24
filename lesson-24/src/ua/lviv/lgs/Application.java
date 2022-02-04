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
				System.out.println("����� �� �������.");
				System.exit(0);
				break;
			default:
				break;

			}
		}
	}

	public static void menu() {
		System.out.println("1 - �������� ����� � ����������");
		System.out.println("2 - �������� ����������");
		System.out.println("3 - �������� ����� �� ���������� �� ������");
		System.out.println("4 - �������� �����");
		System.out.println("5 - �������� ������");
		System.out.println("6 - �������� �����");
		System.out.println("7 - ����� �� �������");
	}
}
