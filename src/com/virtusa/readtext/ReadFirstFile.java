package com.virtusa.readtext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReadFirstFile {

	public static void main(String[] args) throws FileNotFoundException {

		String baseNumber = "103945";
		String filePath = "C:\\eclipseProjects\\ReadTextFiles\\res\\COTSCP02.txt";

		// System.out.println(ReadFirstFile.docCOTSCP02(filePath, baseNumber));

		baseNumber = "ID0100350";
		filePath = "C:\\eclipseProjects\\ReadTextFiles\\res\\IFCBA032.txt";

		System.out.println(ReadFirstFile.docIFCBA032(filePath, baseNumber));

	}

	public static List docIFCBA032(String filePath, String baseNumber) throws FileNotFoundException {

		File file = new File(filePath);
		Scanner sc = new Scanner(file);

		Boolean invalidRecord = false;
		
		String ccy = "";

		// we just need to use \\Z as delimiter
		sc.useDelimiter("\\Z");

		// get text to string

		String text = sc.next();

		// Split the text using ACTIVITIES OF ACCOUNT
		String[] parts = text
				.split("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");

		System.out.println("Length : " + parts.length);

		System.out.println(parts[2]);

		if (!parts[2].equals(null)) {

			// Remove unwanted spaces
			parts[2] = parts[2].trim();

			// Split in to rows
			String[] rows = parts[2].split("\n");

			System.out.println("row Length : " + rows.length);

			String selectedRow = "";
			for (String row : rows) {

				System.out.println("Base number is : " + baseNumber);

				if (row.contains(baseNumber)) {

					selectedRow = row;
					break;
				}

			}
			
			

			if (selectedRow.equals("")) {
				System.out.println("Base Number doesn't contain.");
			} else {

				String getCCY = selectedRow;
				getCCY = getCCY.replaceAll("\\s+", " ");

				System.out.println(getCCY);

				ccy = getCCY.split(" ")[4].split(" ")[0];

				if (ccy.length() != 3) {
					System.out.println("Invalid CCY");
					System.out.println("CCY : " + ccy);
					ccy = "N/A";
					System.out.println("CCY : " + ccy);
				} else {

					System.out.println("CCY : " + ccy);
				}

			}

		} else {
			System.out.println("Content Doesn't Appear.");
		}
		return null;

		// System.out.println(text);

	}

	public static List docCOTSCP02(String filePath, String baseNumber) throws FileNotFoundException {

		File file = new File(filePath);
		Scanner sc = new Scanner(file);

		// we just need to use \\Z as delimiter
		sc.useDelimiter("\\Z");

		// get text to string

		String text = sc.next();

		// System.out.println(text);

		// Split the text using ACTIVITIES OF ACCOUNT
		String[] parts = text.split("ACTIVITIES OF ACCOUNT");

		System.out.println("Length : " + parts.length);

		List<String> reqParts = new ArrayList<String>();

		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

		// int j = 0;

		for (int i = 0; i < parts.length; i++) {

			if (parts[i].contains("/" + baseNumber + "/") && parts[i].contains("CLOSING BALANCE")) {
				reqParts.add(parts[i]);

			} else {

				// System.out.println("No CLOSING BALANCE found.");
			}

		}

		String cb = "";
		String acc = "";
		String ccy = "";

		if (reqParts.size() == 0) {

			cb = "N/A";
			acc = "N/A";
			ccy = "N/A";

			System.out.println("Account Number : " + acc);
			System.out.println("Currency : " + ccy);
			System.out.println("Closing Balance : " + cb + "\n\n ***************");

		} else {

			for (String b : reqParts) {

				Map<String, String> mapings = new HashMap<String, String>();

				String cbString = b;

				String[] cbAll = cbString.split("CLOSING BALANCE");
				String[] cbrest = cbAll[1].split("\n");

				if (cbrest.length == 1 || cbrest[0].trim().equals("")) {
					cb = "N/A";
				} else {
					cb = cbrest[0].trim();
				}

				String[] accAll = cbString.split("A/C : ");
				String[] accrest = accAll[1].split("\\(");

				if (accrest.length == 1 || accrest[0].trim().equals("")) {
					acc = "N/A";

				} else {
					acc = accrest[0].trim();
				}
				String[] ccyArr = accrest[1].split("\\)");

				if (ccyArr.length == 1 || ccyArr[0].trim().equals("")) {
					ccy = "N/A";

				} else {
					ccy = ccyArr[0].trim();

				}
				System.out.println("Contain length : " + reqParts.size());

				System.out.println("Account Number : " + acc);
				System.out.println("Currency : " + ccy);
				System.out.println("Closing Balance : " + cb + "\n\n ***************");

				mapings.put("Account Number", acc);
				mapings.put("Currency", ccy);
				mapings.put("Closing Balance", cb);

				mapList.add(mapings);

			}

		}

		System.out.println(mapList);

		return mapList;

	}

}
