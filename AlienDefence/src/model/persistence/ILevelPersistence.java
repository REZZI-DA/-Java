package model.persistence;

import java.util.List;

import model.Level;

public interface ILevelPersistence {

	/**
	 * legt in der Datenbank ein neues Level an
	 * 
	 * @return level_id f�r das neue Level
	 */
	int createLevel(String levelname, String backgroundUrl, long duration);

	/**
	 * gibt alle Level aus der Datenbank als Liste zur�ck
	 * 
	 * @return Liste aller Level
	 */
	List<Level> readAllLevel();

	/**
	 * aktualisiert die Daten eines Levels
	 * 
	 * @param lvl
	 *            ein Levelobjekt
	 */
	void updateLevel(Level lvl);

	/**
	 * l�scht ein Level aus der Datenbank
	 * 
	 * @param P_level_id
	 */
	void deleteLevel(int level_id);

}