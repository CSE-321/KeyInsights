import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router';
import { signinUser } from './Auth/userSlice';
import { signIn } from '../Util/Networking';
import { setActiveServer } from './Auth/serverSlice';

const LoginCard = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [serverURL, setServerURL] = useState('');
  const [emailText, setEmailText] = useState('');
  const [passwordText, setPasswordText] = useState('');
  const [invalidEmail, setInvalidEmail] = useState(false);
  const [signInRejected, setSignInRejected] = useState(false);
  const [signInError, setSignInError] = useState('');

  const validateEmail = (email) => {
    const re =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    re.test(String(email).toLowerCase())
      ? setInvalidEmail(false)
      : setInvalidEmail(true);
  };

  const server = {
    id: '1',
    name: 'Cloud-STM',
    url: 'http://jira.cloud-stm.com:8080',
    port: '8080',
  };

  const onSubmit = () => {
    signIn(emailText, passwordText, serverURL)
      .then((user) => {
        setSignInRejected(false);
        setInvalidEmail(false);
        dispatch(signinUser(user));
        dispatch(setActiveServer(server));
        navigate('/projects', { replace: true });
      })
      .catch((error) => {
        //if the credentials were rejected, tell user
        //otherwise, show user the error (e.g. server down)
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
          <h2 className="text-lg sm:text-xl"> Email </h2>

          <form className="">
            {invalidEmail ? (
              <p className="text-rose-500"> Invalid email </p>
            ) : null}

            <input
              className="w-full h-10 rounded-lg drop-shadow-md"
              type="text"
              placeholder="   email@wd.com"
              value={emailText}
              onInput={(e) => setEmailText(e.target.value)}
              onBlur={(e) => validateEmail(e.target.value)}
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
