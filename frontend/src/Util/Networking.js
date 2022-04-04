import { type } from '@testing-library/user-event/dist/type';
import React from 'react';
import { useDispatch } from 'react-redux';
import { signinUser, signoutUser } from '../Components/Auth/userSlice';

export function signIn(username, password, serverURL) {
  //TODO: Replace mock data with actual data from the server (i.e. API call)
  const user = {
    id: '12345',
    name: 'test user',
    email: 'user@wd.com',
    role: 'admin',
  };

  const reqBody = {
    username: username,
    password: password,
    serverURL: serverURL,
  };

  // await fetch('/login', {});

  let mockPromise = new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(user);
      // reject(new Error('Error 500 - Internal Server Error'));
      // reject(new Error('Error 401 - Unauthorized'));
    }, 1000);
  });

  return mockPromise;
}

export function signOut() {
  const dispatch = useDispatch();
  //possibly make call to server if backend needs to store sessions
  dispatch(signoutUser({}));
}

export async function getAllProjects(serverURL, endpoint, email, password) {
  var myHeaders = new Headers();
  myHeaders.append('Authorization', 'Basic YXJvbWFuMjc6UmFjaGVhbDI=');
  myHeaders.append(
    'Cookie',
    'JSESSIONID=C2B6A21E74F1F654EBC114D3638172A8; atlassian.xsrf.token=ANOT-3S39-KOCL-6XXI_0c83f0e402512894418d518343b7f0f05c2ec03f_lin',
  );

  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow',
  };

  fetch('http://jira.cloud-stm.com:8080/rest/api/2/project/', {
    method: 'GET',
    headers: myHeaders,
    mode: 'no-cors',
  })
    .then((response) => response.text())
    .then((result) => console.log(result))
    .catch((error) => console.log('error', error));
}