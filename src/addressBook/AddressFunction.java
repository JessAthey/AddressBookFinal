package addressBook;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressFunction {
	public static Scanner myScanner; //makes the scanner visible for the entire class structure

	public static void main(String[] args) {
		myScanner = new Scanner(System.in);
		int userInput = 0; //initializing the variable for input prior to asking for the input

		AddressBook addressBook = new AddressBook();
		while (userInput != 6) {
			printMenu();  //printing main menu
			userInput = getIntInput(); //assigning value from method to initialized variable. This variable is what the switch runs on
			if (userInput < 1 || userInput > 6) {
				System.out.println("Invalid entry, please try again"); 
				//using OR statement to verify the number entered is within the scope of the switch, if it is not it returns this statement
			} else {
				switch (userInput) {
				case 1: //case 1 is for adding an entry to the address book
					addEntry(addressBook);
					break;
				case 2: //case 2 is for removing an entry by email address
					String emailAddress = askForInput("email");
					addressBook.removeEntry(emailAddress);
					break;
				case 3: //case 3 is for searching the address book for a specific entry
					searchForSpecificEntry(addressBook);
					break;
				case 4: //case 4 is for printing the entire address book
					addressBook.printAddressBook();
					break;
				case 5: //case 5 is to clear the address book
					addressBook.deleteAddressBook();
					break;
				default: //using the default switch to terminate the program instead of using the 6th case
					System.out.println("Terminating program");
				}
			}
		}

		myScanner.close(); // Stops the user from entering more input after ending the switch.
	}

	public static int getIntInput() { //what our switch operates on. This input is what the user inputs to make the menus work
		String inputNum = myScanner.nextLine();
		while (inputNum.length() == 0) {
			inputNum = myScanner.nextLine();
		}
		return Integer.parseInt(inputNum); //Parses the input to a number value so the switch can operate
	}

	public static String askForInput(String inputName) { //asks the user for a name entry
		System.out.println("Please enter " + inputName + ": ");
		return myScanner.nextLine(); //returns the answer the user typed in
	}

	public static void addEntry(AddressBook addressBook) { //this is to add an entry to the address book
		System.out.print("Enter first name: "); //asks for input
		String firstName = myScanner.nextLine(); //initializes variable firstName with the value entered by the user

		System.out.print("Enter last name: "); //asks for input
		String lastName = myScanner.nextLine(); //initializes variable lastName with the value entered by the user

		System.out.print("Enter phone number: "); //asks for input
		String phoneNum = myScanner.nextLine(); //initializes variable phoneNum with the value entered by the user

		System.out.print("Enter email: "); //asks for input
		String emailAddress = myScanner.next().strip(); //initializes variable emailAddress with the value entered by the user

		Entry entryObj = new Entry(firstName, lastName, phoneNum, emailAddress); //adds the values to the entryObj Entry object
		addressBook.addEntry(entryObj); //adds the new entryObj to the address book list with the values from previous line
	}
//This is the main menu options. These must be printed after every entry until the program is terminated by entering the number 6. This operates on a switch.
	public static void printMenu() {
		System.out.println("Please select an option:");
		System.out.println("1) Add an entry");
		System.out.println("2) Remove an entry");
		System.out.println("3) Search for an specific entry");
		System.out.println("4) Print the contents of the address book");
		System.out.println("5) Delete the contents of the address book");
		System.out.println("6) Quit the program");
	}
//This is the search menu. The answer to this is returned as userInput. This also operates on a switch.
	public static void searchForSpecificEntry(AddressBook addressBook) {
		System.out.println("Please select an option");
		System.out.println("1) Search By First Name"); //map location 1
		System.out.println("2) Search By Last Name"); //map location 2
		System.out.println("3) Search By Phone"); //map location 3
		System.out.println("4) Search By Email"); //map location 4
//initializing the userInput variable with the method getIntInput
		int userInput = getIntInput();
//Mapping so the program can locate the desired search result. Used mapping because the project required an ArrayList
		Map<Integer, String> searchMapping = Map.of(1, "firstName", 2, "lastName", 3, "phone", 4, "email");
		if (userInput < 0 || userInput > 4) { //verifying user input value
			System.out.println("Invalid input! Try again"); //Tells the user they have entered an incorrect search option
		} else {
			System.out.print("Enter your search query: "); //asking for the searchParam
			String searchParam = myScanner.nextLine();
			List<Entry> entriesList = addressBook.searchForAnEntry(searchMapping.get(userInput), searchParam); //searches the map for the required arguments
			System.out.println("Your search results returned:");
			for (Entry entryObj : entriesList) { 
				System.out.println(entryObj);  //returning result of search
			}
		}
		System.out.println();
	}
}
