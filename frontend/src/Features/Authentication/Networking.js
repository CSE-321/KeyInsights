import { type } from '@testing-library/user-event/dist/type';
import React from 'react';
import { useDispatch } from 'react-redux';
import { signinUser, signoutUser } from '../../App/Slices/userSlice';

export async function signIn(username, password, serverURL) {
  //TODO: Change the serer urls to request parameter instead of body
  //TODO: Remove http from server
  const headers = {
    'Content-Type': 'application/json',
  };
  const reqBody = {
    username: username,
    password: password,
    serverURL: serverURL,
  };
  console.log('reqBody');
  const options = {
    method: 'POST',
    headers: headers,
    body: JSON.stringify(reqBody),
    mode: 'no-cors',
  };

  return fetch('/login', options);
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