package repository;

import model.User;

public interface UserRepository {
    User findById(int buyerId);
}