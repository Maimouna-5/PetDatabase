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
        System.out.println("3) Exit program");
        System.out.print("Your choice: ");
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
}