/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.petdatabse.petdatabase;

/**
 *
 * @author maimounadiallo
 */
import java.util.Scanner;

public class PetDatabase {

	public static Pet[] petList = new Pet[100];//The pet array used to store pet objects max of 100
	public static int petCount = 0;//the number of pet in the array
	public static Scanner s = new Scanner(System.in);//Scanner which reads the input from the user

	public static void main(String[] args) {

		//initializing 100 pet object instances
		for(int i=0;i<petList.length;i++){
			petList[i] = new Pet();
		}

		while (true) {
			int choice = getUserChoice();
                        System.out.println("Your choice: "+choice);
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
					updatePet();
					break;
				}

				case 4: {
					removePets();
					break;
				}
				case 5: {
					searchPetsByName();
					break;
				}
				case 6: {
					searchPetsByAge();
					break;
				}
				case 7: {
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
		System.out.println("3) Update an existing pet");
		System.out.println("4) Remove an existing pet");
		System.out.println("5) Search pets by name");
		System.out.println("6) Search pets by age");
		System.out.println("7) Exit program");
		return s.nextInt();
	}

	/**
	 * This function adds the pets (name, age) based on the user input
	 */
	public static void addPets() {
		int numPets = 0;
		while(true){
			System.out.println("add pet (name, age)\n");
			String name = s.next();
			if (name.equalsIgnoreCase("done")){
				System.out.println("Exiting add pet\n");
				break;
			}
			int age = s.nextInt();
			numPets++;
			petCount++;
			for(int i=0;i<petList.length;i++){
				if (petList[i].getIsRemoved()){
					petList[i].setAge(age);
					petList[i].setName(name);
					petList[i].setIsRemoved(false);
					break;
				}
			}
		}
		System.out.println(Integer.toString(numPets) +" pets addded.\n");
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
		for (int i = 0; i < petList.length; i++) {//to get the table row method
			if (!petList[i].getIsRemoved()) {
				printTableRow(i, petList[i].getName(), petList[i].getAge());//print rows
			}
		}
		printTableFooter(petCount);//call footer
	}

	/**
	 * @param id: pet id input by the user
	 * @return: returns true if the id is valid, else return false
	 */
	private static boolean isValid(int id){
		return id>=0 && id<petList.length && !petList[id].getIsRemoved();
	}

	/**
	 * This function updates the details of the pet based on the pet id input by the user
	 * Details include: (name, age)
	 */
	public static void updatePet() {
		//displays the table first
		showAllPets();
		System.out.println("Enter the pet ID you wish to update: ");
		int petId = s.nextInt();
		if (!isValid(petId)){
			System.out.println("Invalid id. Please try again.\n");
			return;
		}
		String oldPetName = petList[petId].getName();
		int oldPetAge = petList[petId].getAge();
		System.out.println("Enter the new name and new age of the pet: ");
		String newPetName = s.next();
		int newPetAge = s.nextInt();
		petList[petId].setName(newPetName);
		petList[petId].setAge(newPetAge);
		System.out.println(oldPetName+" "+Integer.toString(oldPetAge)+" changed to "+newPetName+" "+Integer.toString(newPetAge));
	}

	/**
	 *
	 * @param petIds: list of pet ids to be displayed in the table
	 * @param rowCount : number of rows in the table
	 */
	private static void printTableById(int[] petIds, int rowCount){
		printTableHeader();
		for(int i=0;i<rowCount;i++){
			int curr_id = petIds[i];
			printTableRow(curr_id, petList[curr_id].getName(), petList[curr_id].getAge());
		}
		printTableFooter(rowCount);
	}

	/**
	 * This function searches the pet by the name as input by the user
	 */
	public static void searchPetsByName() {
		System.out.println("Enter a name to search: ");
		String petName = s.next();
		int[] petIds = new int[100];
		int curr = 0;
		for(int i=0;i<petList.length;i++){
			if (!petList[i].getIsRemoved() && petList[i].getName().equals(petName)){
				petIds[curr++] = i;
			}
		}
		printTableById(petIds, curr);
	}

	/**
	 * This function searches the pet by the name as input by the user
	 */
	public static void searchPetsByAge() {//Show the user the name and display the table by showing all the pet
		System.out.println("Enter age to search: ");
		int petAge = s.nextInt();
		int[] petIds = new int[100];
		int curr = 0;
		for(int i=0;i<petList.length;i++){
			if (!petList[i].getIsRemoved() && petList[i].getAge()==petAge){
				petIds[curr++] = i;
			}
		}
		printTableById(petIds, curr);
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
		String petName = petList[petId].getName();
		int petAge = petList[petId].getAge();
		petList[petId].setIsRemoved(true);
		System.out.println(petName+" "+Integer.toString(petAge)+" is removed.");
	}
}