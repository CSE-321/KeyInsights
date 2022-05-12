import { type } from '@testing-library/user-event/dist/type';
import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { signinUser, signoutUser } from '../../App/Slices/userSlice';

export async function signIn(username, password, serverURL) {
  //TODO: Change the serer urls to request parameter instead of body
  //TODO: Remove http from server
  var myHeaders = new Headers();
  myHeaders.append('Content-Type', 'application/json');

  var raw = JSON.stringify({
    username: username,
    password: password,
    serverUrl: serverURL,
  });

  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow',
    mode: 'no-cors',
  };

  return await fetch('/login', requestOptions);
}

export function signOut() {
  const dispatch = useDispatch();
  //possibly make call to server if backend needs to store sessions
  dispatch(signoutUser({}));
}

export function requireAuth(nexState, replace, next) {
  const accessToken = useSelector((state) => state.tokens.accessToken);
  const refreshToken = useSelector((state) => state.tokens.refreshToken);

  if (accessToken === '') {
    replace({
      pathname: '/',
      state: { nextPathname: next.location.pathname },
    });
  }

  next();
}
