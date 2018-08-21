package com.virtusa.getinput;

import java.util.Scanner;

public class ReadInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print("Enter your username: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("Your username is " + username);
		
		System.out.print("Enter Number one : ");
		String num1 = scanner.nextLine();
		System.out.println();

		System.out.print("Enter Number two : ");
		String num2 = scanner.nextLine();
	
		int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
		System.out.println("Sum is > "+sum);
		
	}

}
