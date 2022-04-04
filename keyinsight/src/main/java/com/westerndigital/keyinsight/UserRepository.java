package com.westerndigital.keyinsight;

import com.westerndigital.keyinsight.User;

public interface UserRepository {

    // get all users from the database
    Iterable<User> getAll();
    
}
