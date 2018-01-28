package org.processmining.plugins.helloworld;

public class Person {
	private Name name;
	private int age;

	public Person() {
		age = 0;
	}

	public Name getName() {
		return name;
	}

	void setName(Name name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	void setAge(int age) {
		this.age = age;
	}
}

class Name {
	private String first, last;

	public Name(String first, String last) {
		this.first = first;
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}
}