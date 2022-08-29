package addressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class AddressBook {
	private List<Entry> entriesList; //Makes the entriesList available to the entire class

	public AddressBook() {
		this.entriesList = new ArrayList<>(); //initializing the array list with our entries for the address book
	}

	public boolean addEntry(Entry entry) { //passes in the Entry entry object
		if (this.entriesList.contains(entry)) { //checks to see if the entry already exists
			System.out.println("That entry is already in the address book, please try again\n"); //if the entry already exists print this error message 
			return false; //returns false because the entry already existed in our book - we don't want multiples of the same person!
		}
		this.entriesList.add(entry); //adds the valid entry
		System.out.println("Entry added successfully\n"); //tells the user they were successful
		return true; //returns true if the entry is added successfully 
	}

	public boolean removeEntry(String emailAddress) { //this method is to remove an existing entry
		int indexNum = getEntryIndexByEmail(emailAddress); //initializes the indexNum with the getEntry-Index-ByEmail method while passing in the emailAddress variable.

		if (indexNum == -1) { //verifies if the input is valid or not (This can be found in the getEntryIndexByEmail method)
			System.out.println("Could not locate entry with that email address\n"); //if the entry does not exist print this statement
			return false; //returns false if not found
		}

		this.entriesList.remove(indexNum); //removes the entry based on the valid indexNum
		System.out.println("Entry deleted successfully\n"); //tells the user they deleted the entry succesfully
		return true; //returns true if successful 
	}

	public int getEntryIndexByEmail(String email) { //this is the method to get the index of entries by email
		for (int i = 0; i < this.entriesList.size(); i++) { //for loop to go through the ArrayList based on size while waiting on a valid entry to match
			if (this.entriesList.get(i).getEmail().equals(email)) { //verifies the entry matches. Stops the loop when it matches 
				return i; //returns the matching entry 
			}
		}
		return -1; //returns -1 if the entry is not found (referenced in the removeEntry method)
	}

	public List<Entry> searchForAnEntry(String searchType, String searchQuery) {
		searchQuery = searchQuery.strip(); //strips the searchQuery down to remove whitespace and make it easier to search 
		if (searchType.equals("firstName")) { //if statement checking whether they want to search for firstName
			return searchByFirstName(searchQuery); //If true run the searchByFirstName method with the searchQuery passed through as an argument
		} else if (searchType.equals("lastName")) { //Checks to see if they want to search by lastName
			return searchByLastName(searchQuery); //if true run the searchByLastName method with the searchQuery passed through as an argument 
		} else if (searchType.equals("phone")) { //Checks to see if they want to search my phone number
			return searchByPhone(searchQuery); //if true run the searchByPhone method with the searchQuery passed through
		} else { //if none of the prior cases are true then they must want to search by email so this is the final else statement
			return searchByEmail(searchQuery); //runs the searchByEmail method with searchQuery as the argument
		}
	}

	private List<Entry> searchByFirstName(String firstName) { //This is the search for firstName passes in the firstName variable and the Entry List
		List<Entry> answers = new ArrayList<>(); //initializes the ArrayList of Entry objects
		for (Entry entryObj : this.entriesList) { //for each entryObj in THIS ArrayList "entriesList"
			if (entryObj.getFirstName().contains(firstName)) { //if the entryObj firstName contains the firstName variable
				answers.add(entryObj); //add the entryObj to answers Array List
			}
		}
		return answers; //returns the answers list
	}

	private List<Entry> searchByLastName(String lastName) { //This is a search method for lastName. Passes in the Entry List once again as well as the lastName variable
		List<Entry> answers = new ArrayList<>(); //Initializes the list so we can add to it during the method
		for (Entry entryObj : this.entriesList) { //for each entryObj in THIS entriesList
			if (entryObj.getLastName().contains(lastName)) { //if the entryObj lastName contains the lastName variable
				answers.add(entryObj); //add the entryObj to the answers list
			}
		}
		return answers; //returns the answers list
	}

	private List<Entry> searchByPhone(String phone) { //Search by phone number method. Passes in the entry list once again as well as the phone number variable
		List<Entry> answers = new ArrayList<>(); //initializes the answers list so we can add to it during the method call
		for (Entry entryObj : this.entriesList) { //for each entryObj in THIS entriesList
			if (entryObj.getPhoneNum().contains(phone)) { //if entryObj phone number contains the phone variable
				answers.add(entryObj); //add the entryObj to the answers list
			}
		}
		return answers; //returns the answers list
	}

	private List<Entry> searchByEmail(String email) { // search by email method. Passes in the entry list and the email variable
		for (Entry entryObj : this.entriesList) { //for each entryObj in THIS entriesList
			if (entryObj.getEmail().equals(email)) { //if entryObj email contains the email variable (using the .equals() method built into java)
				return Arrays.asList(entryObj); //returns the Array -as a list- for the required entryObj matching the email variable
			}
		}
		return new ArrayList<>(); //returns a new list with the search object information
	}

	public void printAddressBook() { //method to print the entire address book
		if (this.entriesList.size() == 0) //verifies whether or not there are actually entries in the address book
			System.out.println("Address book is empty."); //prints an error if the address book is empty
		else { //If the requirements are met follow through with this else statement
			System.out.println("Printing Entries in Address Book:");
			System.out.println(this.toString()); //Prints the entriesList to a string for the user to see
		}
		System.out.println(); //break line
	}

	public void deleteAddressBook() { //method to delete all of the address book entries
		this.entriesList.clear(); //clears the address book entries that are held in entriesList
		System.out.println("Address book deleted\n"); //confirms deletion to user
	}

	@Override
	public String toString() { //to string method to print the arrayLists out properly
		StringJoiner letsJoin = new StringJoiner("\n"); //wraps a break space
		for (Entry entryObj : this.entriesList) { //for each entryObj in THIS entriesList
			letsJoin.add(entryObj.toString()); //using the string joiner and the toString method on the entryObj
		}
		return letsJoin.toString(); //Prints out the proper format of the info from the lists
	}
}
