import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router';
import { signIn } from './Networking';
import { signinUser } from '../../App/Slices/userSlice';
import { setActiveServer } from '../../App/Slices/serverSlice';

/**
 * A component that displays the login card, which allows the user to
 * sign in to a JIRA server's insights. The component modifies the state of the user
 * slice to store the user's login JWT, and the server slice to store the active server
 * @returns {JSX.Element}
 */
const LoginCard = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [serverURL, setServerURL] = useState('');
  const [usernameText, setUsernameText] = useState('');
  const [passwordText, setPasswordText] = useState('');
  const [invalidUsername, setInvalidUsername] = useState(false);
  const [signInRejected, setSignInRejected] = useState(false);
  const [signInError, setSignInError] = useState('');

  const validateUsername = (username) => {};

  const server = {
    id: '1',
    name: 'Cloud-STM',
    url: 'http://jira.cloud-stm.com:8080',
    port: '8080',
  };

  const onSubmit = () => {
    if (serverURL === '') {
      setSignInRejected(true);
      setSignInError('Please enter a server URL');
      return;
    }

    if (usernameText === '') {
      setSignInRejected(true);
      setSignInError('Please enter a username');
      return;
    }

    if (passwordText === '') {
      setSignInRejected(true);
      setSignInError('Please enter a password');
      return;
    }

    signIn(usernameText, passwordText, serverURL)
      .then((user) => {
        setSignInRejected(false);
        setInvalidUsername(false);
        dispatch(signinUser(user));
        dispatch(setActiveServer(server));
        navigate('/projects', { replace: true });
      })
      .catch((error) => {
        setSignInRejected(true);
        setSignInError(error.message);
      });
  };

  return (
    <>
      <div id="LoginCard" className="w-full h-full bg-white drop-shadow-2xl">
        <div className="flex flex-col mx-5 py-5 space-y-5 sm:mx-10 md:mx-20 ">
          <h1 className="text-2xl font-bold sm:text-3xl"> Login</h1>
          <form>
            <h2 className="text-lg sm:text-xl"> Server Url </h2>
            <input
              className="w-full h-10 rounded-lg drop-shadow-md"
              type="url"
              placeholder="http://jira.yourserver.com:port"
              value={serverURL}
              onChange={(e) => setServerURL(e.target.value)}></input>
          </form>
          <h2 className="text-lg sm:text-xl"> Username </h2>

          <form className="">
            {invalidUsername ? (
              <p className="text-rose-500"> Invalid Username </p>
            ) : null}

            <input
              className="w-full h-10 rounded-lg drop-shadow-md"
              type="text"
              placeholder="WdJiraUser"
              value={usernameText}
              onInput={(e) => setUsernameText(e.target.value)}
              onBlur={(e) => validateUsername(e.target.value)}
            />
          </form>
          <h2 className="text-lg sm:text-xl"> Password</h2>
          <form>
            <input
              className="w-full h-10 rounded-lg drop-shadow-md"
              type="password"
              placeholder="   ****"
              value={passwordText}
              onInput={(e) => setPasswordText(e.target.value)}
            />
            <p className="text-rose-500">
              {signInRejected ? signInError : null}
            </p>
          </form>
          <button
            onClick={onSubmit}
            className="bg-black h-10 w-1/2 text-white rounded-xl self-end hover:text-primary-purple sm:w-2/5 sm:center md:1/4">
            Login
          </button>
        </div>
      </div>
    </>
  );
};

export default LoginCard;
