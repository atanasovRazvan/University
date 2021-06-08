package Repositories;

import Entities.User;

public interface IUserRepository {

    Boolean findOne(User user);

}
