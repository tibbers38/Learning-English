package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.String;

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
				for(int i=0; i<mode.time; i++) {
					file.readLine(file.sc);
					System.out.println(file.word+":");
					do {
						answer.check(file.meaning);
					} while (answer.flag == false);
				}
			}
			catch (NoSuchElementException ex) {
				System.out.println("Tu dien luyen tap khong co du so tu!!!");
			}
			finally {
				close(file, mode, answer);
			}
			System.out.println("Ket thuc luyen tap!");
			break;
		case 2:
			file.openFile();
			try {
				for(int i=0; i<mode.time; i++) {
					file.readLine(file.sc);
					System.out.println(file.meaning+":");
					do {
						answer.check(file.word);
					} while (answer.flag == false);
				}
			}
			catch (NoSuchElementException ex) {
				System.out.println("Tu dien luyen tap khong co du so tu!!!");
			}
			finally {
				close(file, mode, answer);
			}
			System.out.println("Ket thuc luyen tap!");
			break;
		}
	}

	private static void close(ReadTextFile file, Mode mode, CheckAnswer answer) {
		file.sc.close();
		mode.sc.close();
		answer.sc.close();
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
	String inputFile;
	String word, meaning;
	Scanner sc;

	public void openFile() throws FileNotFoundException {
		FileInputStream file = new FileInputStream("/Users/Nam/Documents/Eclipse Documents/Learning English/src/main/Dictionary");
        sc = new Scanner(file);
	}
	
	public void readLine (Scanner scanner) {
		inputFile = scanner.nextLine();
		getWord(inputFile);
		getMeaning(inputFile);
	}
	
	private void getWord(String inputFile) {
		String[] parts = inputFile.split(":");
		word = parts[0];
		word = word.trim();
	}
	
	private void getMeaning(String inputFile) {
		String[] parts = inputFile.split(":");
		meaning = parts[1];
		meaning = meaning.trim();
	}
}

class CheckAnswer {
	String answer;
	Scanner sc;
	boolean flag;
	
	public void check(String text) {
		inputAnswer();
		if (text.equals(answer)) flag = true;
		else flag = false;
	}
	
	private void inputAnswer() {
		System.out.println("Nhap dap an:");
		sc = new Scanner(System.in);
		answer = sc.nextLine();
	}
}