package model.persistenceDummy;

import model.persistence.IAttemptPersistence;
import model.persistence.ILevelPersistence;
import model.persistence.IPersistence;
import model.persistence.ITargetPersistence;
import model.persistence.IUserPersistence;

public class PersistenceDummy implements IPersistence{

	private LevelDummy levelDummy;
	private UserDummy userDummy;
	private AttemptDummy attemptDummy;
	private TargetDummy targetDummy;

	public PersistenceDummy() {

		this.levelDummy = new LevelDummy();
		this.userDummy = new UserDummy();
		this.attemptDummy = new AttemptDummy();
		this.targetDummy = new TargetDummy();
	}

	@Override
	public IAttemptPersistence getAttemptPersistance() {
		return this.attemptDummy;
	}

	@Override
	public ILevelPersistence getLevelPersistance() {
		return this.levelDummy;
	}

	@Override
	public ITargetPersistence getTargetPersistance() {
		return this.targetDummy;
	}

	@Override
	public IUserPersistence getUserPersistance() {
		return this.userDummy;
	}


}
