package model.persistenceDummy;

import java.time.LocalDate;

import model.persistence.IUserPersistence;
import toDo.User;

/**
 * Dummyklasse zum Testen
 * @author Tim Tenbusch
 
 */
public class UserDummy implements IUserPersistence {

	public User readUser(String username) {
		return new User(1, "Dummy", "Persistenz", LocalDate.now(), "Dummystr.", "12C", "11111", "Nowhere", username, "pass", 12000, "gefangen", 1.58);
	}
	
}
