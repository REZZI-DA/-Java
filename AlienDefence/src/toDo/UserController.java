package toDo;

import model.persistence.IUserPersistence;

/**
 * controller for user
 * @author Clara Zufall
 * TODO implement this class
 */
public class UserController {

	private IUserPersistence userPersistance;
	
	public UserController(IUserPersistence userPersistance) {
		this.userPersistance = userPersistance;
	}
	
	public void createUser(User user) {
		
	}
	
	/**
	 * liest einen User aus der Persistenzschicht und gibt das Userobjekt zurück
	 * @param username eindeutige Loginname
	 * @param passwort das richtige Passwort
	 * @return Userobjekt, null wenn der User nicht existiert
	 */
	public User readUser(String username, String passwort) {
		return null;
	}
	
	public void changeUser(User user) {
		
	}
	
	public void deleteUser(User user) {
		
	}
	
	public boolean checkPassword(String username, String passwort) {
		return false;
	}
}
