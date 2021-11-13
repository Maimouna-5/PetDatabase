package com.petdatabse.petdatabase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetDatabase {

	public static List<Pet> dbItems = new ArrayList<>();
	public static Scanner s = new Scanner(System.in);//Scanner which reads the input from the user

	public static void main(String[] args) {
		List<Pet> fetchedItems = fetchFromDataBase();
		if(fetchedItems != null) {
			dbItems = fetchedItems;
		}
		while (true) {
			int choice = getUserChoice();
			switch (choice) {
				case 1: {
					showAllPets();
					break;
				}
				case 2: {
					addPets();
					break;
				}
				case 3: {
					removePets();
					break;
				}
				case 4: {
					saveToDataBase(dbItems);
					System.out.println("Goodbye!");
					return;
				}
				default: {
					System.out.println("Invalid\n");
				}
			}
		}
	}

	/**
	 * This function displays the menu and inputs the user choice
	 * @return choice input by the user
	 */
	public static int getUserChoice() {
		System.out.println("Pet Database Program.");
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1) View all pets");
		System.out.println("2) Add more pets");
		System.out.println("3) Remove a pet");
		System.out.println("4) Exit program");
		System.out.print("Your choice: ");
		return new Scanner(System.in).nextInt();
	}

	/**
	 * This function adds the pets (name, age) based on the user input
	 */
	public static void addPets() {
		int count = 0;
		while(true){
			System.out.println("add pet (name, age)\n");
			if(dbItems.size() == 5) {
				System.out.println("Error: Database is full.");
				break;
			}
			String input = s.nextLine();
			String[] items = input.split(" ");
			String name = items[0];
			if (name.equalsIgnoreCase("done")){
				break;
			}
			if(items.length != 2) {
				System.out.println(input +" is not a valid input.");
				continue;
			}
			int age = Integer.parseInt(items[1]);
			if(age > 20){
				System.out.println("Error: "+ age +" is not a valid age.");
				continue;
			}
			dbItems.add(new Pet(name, age));
			count ++;
		}
		System.out.println(count +" pets addded\n");
	}

	/**
	 * This function prints the table header
	 */
	private static void printTableHeader() {
		System.out.println("+----------------------------+");
		String[] currentRow = new String[] { "|", "ID", "|","        NAME", "|", "AGE", "|" };
		System.out.format("%-1s%-3s%-1s%-20s%-1s%-3s%-1s\n",currentRow);
		System.out.println("+----------------------------+");
	}

	// This function prints each table row: (id, name, age)

	/**
	 * This function prints the table row, each row represents a pet (id, name, age)
	 * @param id: id of the pet
	 * @param name: name of the pet
	 * @param age: age of the pet
	 */
	private static void printTableRow(int id, String name, int age) {
		String[] currentRow = new String[] { "|", Integer.toString(id), "|",name, "|", Integer.toString(age), "|" };
		System.out.format("%-1s%-3s%-1s%-20s%-1s%-3s%-1s\n",currentRow);
	}

	/**
	 * This function prints the table footer, displays the count of rows in the table
	 * @param rowCount: Number of rows in the table
	 */
	private static void printTableFooter(int rowCount) {//print the footer
		System.out.println("+----------------------------+");
		System.out.println(rowCount + " rows in set. ");
	}

	/**
	 * This function displays all pets details
	 */
	public static void showAllPets() {
		printTableHeader();//call the header
		for (int i = 0; i < dbItems.size(); i++) {//to get the table row method
			if (dbItems.get(i).getIsRemoved()) {
				continue;
			}
			printTableRow(i, dbItems.get(i).getName(), dbItems.get(i).getAge());//print rows
		}
		printTableFooter(dbItems.size());//call footer
	}

	/**
	 * @param id: pet id input by the user
	 * @return: returns true if the id is valid, else return false
	 */
	private static boolean isValid(int id){
		return id>=0 && id<dbItems.size() && !dbItems.get(id).getIsRemoved();
	}

	public static List<Pet> fetchFromDataBase() {
		try {
			List<Pet> petList = new ArrayList<>();
			//Read from file
			File inputFile = new File(getFileName());
			InputStream inputStream = new FileInputStream(inputFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			//Start reading file line by line
			while ((line = reader.readLine()) != null) {
				String[] items = line.split(" ");
				petList.add(new Pet(items[0], Integer.parseInt(items[1])));
			}
			//Close any streams
			reader.close();
			return petList;
		} catch (Exception exception) {
			return null;
		}
	}

	private static String getFileName() {
            return new File("pet_database.txt").getAbsolutePath();
	}

	/**
	 * This function stored the list to database
	 */
	private static void saveToDataBase(List<Pet> petList) {
		try {
			clearFileContents(getFileName());
			//Write to file
			BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), true));
			for(Pet pet: petList) {
				writer.write(pet.toString());
				writer.newLine();
			}
			//Close any streams
			writer.close();
		} catch (IOException exception) {
			System.out.println("Error saving to Database: "+exception.getLocalizedMessage());
		}
	}

	/**
	 * This function clears the file
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	private static void clearFileContents(String fileName) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		writer.print("");
		writer.close();
	}

	/**
	 * This function removes pets based on the pet id input by the user name as input by the user
	 */
	public static void removePets() {
		showAllPets();
		System.out.println("Enter the pet ID to remove: ");
		int petId = s.nextInt();
		if (!isValid(petId)){
			System.out.println("Invalid id. Please try again.\n");
			return;
		}
		String petName = dbItems.get(petId).getName();
		int petAge =  dbItems.get(petId).getAge();
		dbItems.get(petId).setIsRemoved(true);
		System.out.println(petName+" "+ petAge +" is removed.");
	}


}