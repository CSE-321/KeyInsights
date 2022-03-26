import React from 'react';
import { useDispatch } from 'react-redux';
import { signinUser, signoutUser } from '../Components/Auth/userSlice';

export function signIn(email, password) {
  //TODO: Replace mock data with actual data from the server (i.e. API call)
  const user = {
    id: '12345',
    name: 'test user',
    email: 'user@wd.com',
    role: 'admin',
  };

  let mockPromise = new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(user);
      //reject(new Error('Error 500 - Internal Server Error'));
      //reject(new Error('Error 401 - Unauthorized'));
    }, 1000);
  });

  return mockPromise;
}

export function signOut() {
  const dispatch = useDispatch();
  //possibly make call to server if backend needs to store sessions
  dispatch(signoutUser({}));
}
