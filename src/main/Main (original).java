package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.String;
import java.util.Random;

//data path: "/Users/Nam/Documents/Eclipse Documents/Learning English/src/main/Dictionary"

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		ReadTextFile file = new ReadTextFile();
		Mode mode = new Mode();
		CheckAnswer answer = new CheckAnswer();
		mode.selectMode();
		mode.selectTime();
		switch (mode.mode) {
		case 1:
			file.openFile();
			try {
				for (int i = 0; i < mode.time; i++) {
					file.getRandomLine();
					System.out.println(file.word[file.n] + ":");
					do {
						answer.check(file.mean[file.n]);
					} while (answer.flag == false);
				}
			} catch (NoSuchElementException ex) {
				System.out.println("Tu dien luyen tap khong co du so tu!!!");
			}
			System.out.println("Ket thuc luyen tap!");
			break;
		case 2:
			file.openFile();
			try {
				for (int i = 0; i < mode.time; i++) {
					file.getRandomLine();
					System.out.println(file.mean[file.n] + ":");
					do {
						answer.check(file.word[file.n]);
					} while (answer.flag == false);
				}
			} catch (NoSuchElementException ex) {
				System.out.println("Tu dien luyen tap khong co du so tu!!!");
			}
			System.out.println("Ket thuc luyen tap!");
			break;
		}
	}
}

class Mode {
	int mode = 0;
	int time = 0;
	Scanner sc;

	public void selectMode() {
		sc = new Scanner(System.in);
		do {
			System.out.println("Chon che do");
			System.out.println("1. Cho tu, kiem tra nghia");
			System.out.println("2. Cho nghia, kiem tra tu");
			mode = sc.nextInt();
		} while ((mode <= 0) || (mode >= 3));
	}

	public void selectTime() {
		sc = new Scanner(System.in);
		do {
			System.out.println("Nhap so tu can luyen");
			time = sc.nextInt();
		} while (time <= 0);
	}
}

class ReadTextFile {
	String[] inputFile;
	String[] word, mean;
	Scanner sc, sc2;
	int i = 0, j = 0, n = 0;

	public void openFile() throws FileNotFoundException {
		try {
			FileInputStream path = new FileInputStream(
					"/Users/Nam/Documents/Eclipse Documents/Learning English/src/main/Dictionary");
			sc = new Scanner(path);
			sc2 = new Scanner(path);
		} catch (FileNotFoundException ex) {
			System.out.println("Khong co tu dien/ Sai duong dan!!!");
			ex.printStackTrace();
		}
		while (sc2.hasNextLine()) {
			sc2.nextLine();
			j++;
		}
		inputFile = new String[j];
		word = new String[j];
		mean = new String[j];
		while (sc.hasNextLine()) {
			inputFile[i] = sc.nextLine();
			System.out.println(inputFile[i]);
			getWord(inputFile[i]);
			getMeaning(inputFile[i]);
			i++;
		}
	}

	public void getRandomLine() {
		Random rand = new Random();
		n = rand.nextInt(j - 1);
	}

	private void getWord(String inputLine) {
		String[] parts = inputLine.split(":");

		word[i] = parts[0];
		word[i] = word[i].trim();
	}

	private void getMeaning(String inputLine) {
		String[] parts = inputLine.split(":");

		mean[i] = parts[1];
		mean[i] = mean[i].trim();
	}
}

class CheckAnswer {
	String answer;
	boolean flag;
	Scanner sc;

	private void input() {
		System.out.println("Nhap dap an:");
		sc = new Scanner(System.in);
		answer = sc.nextLine();
	}

	public void check(String text) {
		input();
		if (text.equals(answer))
			flag = true;
		else
			flag = false;
	}
}