package com.example.valid.group;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonForm {

	private String id;

	@NotNull
	@Size(max = 64, message = "{form.name.length}")
	@Pattern(regexp = "\\w{5,20}", message = "{form.name.length}")
	private String name;

	@Min(0)
	private int age;

	@Override
	public String toString() {
		return "PersonForm{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
