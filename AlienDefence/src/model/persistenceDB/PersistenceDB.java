package model.persistenceDB;

import model.persistence.IAttemptPersistence;
import model.persistence.ILevelPersistence;
import model.persistence.IPersistence;
import model.persistence.ITargetPersistence;
import model.persistence.IUserPersistence;
import toDo.UserDB;

public class PersistenceDB implements IPersistence{

	private LevelDB levelDB;
	private UserDB userDB;
	private AttemptDB attemptDB;
	private TargetDB targetDB;

	public PersistenceDB() {
		AccessDB dbAccess = new AccessDB();
		this.levelDB = new LevelDB(dbAccess);
		this.userDB = new UserDB(dbAccess);
		this.attemptDB = new AttemptDB(dbAccess);
		this.targetDB = new TargetDB(dbAccess);
	}

	@Override
	public IAttemptPersistence getAttemptPersistance() {
		return this.attemptDB;
	}

	@Override
	public ILevelPersistence getLevelPersistance() {
		return this.levelDB;
	}

	@Override
	public ITargetPersistence getTargetPersistance() {
		return this.targetDB;
	}

	@Override
	public IUserPersistence getUserPersistance() {
		return this.userDB;
	}
}
