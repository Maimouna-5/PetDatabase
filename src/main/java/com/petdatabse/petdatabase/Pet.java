package com.petdatabse.petdatabase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

class Pet {
	private String name;
	private int age;
	private boolean isRemoved;

	//Constructor
	public Pet(String name, int age) {
		this.name = name;
		this.age = age;
		isRemoved = false;
	}

	public Pet(){
		isRemoved = true;
	}

	//Setters
	void setName(String name){
		this.name = name;
	}

	void setAge(int age){
		this.age = age;
	}

	void setIsRemoved(boolean isRemoved){
		this.isRemoved = isRemoved;
	}

	//Getters
	String getName(){
		return name;
	}

	int getAge(){
		return age;
	}

	boolean getIsRemoved(){
		return isRemoved;
	}
        
        @Override public String toString() {
		return name + " " + age;
	}
}