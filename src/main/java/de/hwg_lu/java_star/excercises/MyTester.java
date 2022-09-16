package de.hwg_lu.java_star.excercises;

class MyTester {
	public class Person {
		String name;
		int age;
		String address;

		Person() {
			this.name = "";
			this.age = -1;
			this.address = "";
		}

		Person(String name, int age, String address) {
			this.name = name;
			this.age = age;
			this.address = address;
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	};

	public static void main(String[] args) {
		String output = "OK";
		Person hw = new MyTester().new Person();
		hw.setAge(18);
		hw.setName("Mark");
		hw.setAddress("Berlin");
		if (hw.getAddress() != "Berlin") {
			output += "error setting 'address' attribute";
		}
		if (hw.getName() != "Mark") {
			output += "error setting 'name' attribute";
		}
		if (hw.getAge() != 18) {
			output += "error setting 'age' attribute";
		}
		System.out.println(output);
	}
}