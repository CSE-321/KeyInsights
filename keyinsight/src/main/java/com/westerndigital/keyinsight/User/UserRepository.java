package com.westerndigital.keyinsight.User;

import com.westerndigital.keyinsight.User.User;

public interface UserRepository {

    // get all users from the database
    Iterable<User> getAll();
    
}
