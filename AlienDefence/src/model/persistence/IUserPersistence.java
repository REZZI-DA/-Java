package model.persistence;

import toDo.User;

public interface IUserPersistence {

	User readUser(String username);

}