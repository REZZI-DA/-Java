package model.persistence;

public interface IPersistence {

	IAttemptPersistence getAttemptPersistance();
	ILevelPersistence getLevelPersistance();
	ITargetPersistence getTargetPersistance();
	IUserPersistence getUserPersistance();
	
}
