import React, { Fragment } from 'react';
import { useSelector } from 'react-redux';
import BodyHeader from '../Components/BodyHeader';
import Lottie from 'lottie-web';
import GraphData from '../Assets/home_page_graph.json';
import LoginCard from '../Components/LoginCard';

/**
 * HomePage component is the main page of the application. It contains the
 * header and an animated graph. It links to the projects page.
 * @returns {JSX}
 */
const HomePage = () => {
  const [isLottieAnimationLoaded, setisLottieAnimationLoaded] =
    React.useState(false);

  const isUserSignedIn = useSelector((state) => state.user.isUserSignedIn);

  const getStartedURL = isUserSignedIn ? '/projects' : '/login';

  React.useEffect(() => {
    if (!isLottieAnimationLoaded) {
      setisLottieAnimationLoaded(true);
      Lottie.loadAnimation({
        container: document.getElementById('graph-logo'),
        animationData: GraphData,
        renderer: 'svg',
      });
    }
  }, []);

  const buttonStyle = {
    backgroundColor: 'black',
    color: 'white',
    border: 'none',
    borderRadius: '15px',
    shadowColor: 'black',
    shadowOffset: { width: -2, height: 4 },
    shadowOpacity: 1,
    shadowRadius: 2,
  };
  return (
    <>
      <BodyHeader
        title={isUserSignedIn ? 'Welcome' : 'Login'}
        subtext={
          isUserSignedIn
            ? 'Get Started to see projects'
            : 'Enter your credentials'
        }
        showServer={false}></BodyHeader>
      <div className="flex flex-col items-center space-y-5 md:space-y-0 md:flex-row md:space-x-5">
        <div
          id="graph-logo"
          className="flex flex-col text-center shrink h-52 w-screen bg-primary-purple md:h-96 md:max-w-lg md: md:w-50v md:text-left">
          <p className="text-white font-bold text-xl md:pl-7 md:text-2xl">
            Key Insights
          </p>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="102.385"
            height="14.67"
            viewBox="0 0 102.385 14.67"
            className="self-center mt-2 md:self-start md:pl-7">
            <g
              id="Western_Digital_Logo_Mobile"
              data-name="Western Digital Logo Mobile"
              transform="translate(-51.413 -16.434)">
              <path
                id="Path_89"
                data-name="Path 89"
                fill="#fff"
                d="M30.747,1.708h2.218v2H34.71V5.278H32.965V9.144a1.052,1.052,0,0,0,1.073,1.165,3.34,3.34,0,0,0,.618-.055v1.369a4.827,4.827,0,0,1-1.545.24c-1.818,0-2.382-1.184-2.382-2.682Zm20.583,2H49.093v7.917h2.236V6.85c0-.943.491-1.572,1.382-1.572,1,0,1.4.74,1.4,1.572V11.6h2.255V6.314a2.588,2.588,0,0,0-2.782-2.848,2.349,2.349,0,0,0-2.236,1.2h-.036Zm-2.982-.24a2.516,2.516,0,0,0-2.655,1.517h-.036V3.706h-2v7.917h2.236V7.683a2.226,2.226,0,0,1,2.455-2.4ZM28.2,5.926l1.436-.98c-.182-.314-.945-1.48-3.182-1.48-1.673,0-3.055.869-3.055,2.4a2.58,2.58,0,0,0,2.146,2.478c.345.111.673.2.964.3.6.2,1.073.37,1.073.832,0,.425-.455.721-1.273.721A1.969,1.969,0,0,1,24.6,9.273l-1.509.851c.145.37.964,1.683,3.346,1.683,1.818,0,3.364-.777,3.364-2.516,0-1.443-1.018-2.127-2.2-2.5l-.873-.277c-.546-.166-1.109-.314-1.109-.832,0-.388.364-.629,1.018-.629A1.775,1.775,0,0,1,28.2,5.926ZM13.419.229l-2.036,8.1L9.346.229h-2.8L4.455,8.571,2.364.229H0L2.891,11.6H5.855L7.873,3.54,9.891,11.6h2.8L15.582.229ZM20.6,9.2a1.763,1.763,0,0,1-1.709,1.017,1.807,1.807,0,0,1-1.745-1.868h5.491V7.664c0-2.072-1.218-4.18-3.855-4.18a3.864,3.864,0,0,0-3.873,4.18,3.888,3.888,0,0,0,3.964,4.2,3.321,3.321,0,0,0,3.218-1.757ZM18.8,5.093a1.622,1.622,0,0,1,1.618,1.683H17.146A1.652,1.652,0,0,1,18.8,5.093ZM40.565,9.2a1.763,1.763,0,0,1-1.709,1.017A1.807,1.807,0,0,1,37.11,8.349H42.6V7.664c0-2.072-1.218-4.18-3.855-4.18a3.864,3.864,0,0,0-3.873,4.18,3.888,3.888,0,0,0,3.964,4.2,3.321,3.321,0,0,0,3.218-1.757Zm-1.8-4.106a1.622,1.622,0,0,1,1.618,1.683H37.129A1.636,1.636,0,0,1,38.765,5.093Z"
                transform="translate(51.413 16.39)"></path>
              <path
                id="Path_90"
                data-name="Path 90"
                fill="#fff"
                d="M101.872,1.663h2.218v2h1.745v1.57H104.09V9.09a1.051,1.051,0,0,0,1.072,1.164,3.7,3.7,0,0,0,.637-.055v1.367a4.832,4.832,0,0,1-1.546.24c-1.818,0-2.382-1.182-2.382-2.679ZM75,.185h3.818c3.473,0,5.655,1.737,5.655,5.691s-2.109,5.691-5.655,5.691H75Zm2.4,9.478h1.273C81.58,9.663,82,7.686,82,5.875s-.455-3.788-3.327-3.788H77.4ZM114.818.185h2.291V11.547h-2.291ZM94.381,3.658v.85h-.036a2.278,2.278,0,0,0-2.127-1.09c-2.382,0-3.255,1.977-3.255,3.991s.873,3.991,3.255,3.991a2.235,2.235,0,0,0,2.127-1.09h.036v.905a1.664,1.664,0,0,1-1.655,1.774,1.893,1.893,0,0,1-1.691-.961c-.2.111-1.509.887-1.509.887.164.333.818,1.755,3.273,1.755,2.564,0,3.8-1.663,3.8-4.009v-7ZM92.89,9.7c-1.273,0-1.509-1.256-1.509-2.291,0-1.164.327-2.291,1.509-2.291S94.4,6.245,94.4,7.409C94.381,8.425,94.126,9.7,92.89,9.7Zm13.636-4.878a3.5,3.5,0,0,1,3.055-1.4c1.927,0,3.71.868,3.71,3.122v5.007h-2.182v-.794h-.036A2.668,2.668,0,0,1,106.309,9.2c0-1.811,1.473-2.661,3.745-2.661h.982V6.356c0-.85-.637-1.349-1.636-1.349a1.928,1.928,0,0,0-1.6.794Zm2.019,4.268a1.049,1.049,0,0,0,1.127,1.072c.963,0,1.364-.813,1.364-1.755v-.48h-.873C109,7.926,108.545,8.425,108.545,9.09ZM85.635,3.658h2.291v7.908H85.635ZM86.8,0a1.267,1.267,0,0,0-1.255,1.275,1.255,1.255,0,1,0,2.509,0A1.267,1.267,0,0,0,86.8,0ZM98.127,3.658h2.291v7.908H98.127ZM99.272,0a1.267,1.267,0,0,0-1.254,1.275,1.255,1.255,0,1,0,2.51,0A1.268,1.268,0,0,0,99.272,0Z"
                transform="translate(36.688 16.434)"></path>
            </g>
          </svg>
        </div>
        {isUserSignedIn && (
          <Fragment>
            <div className="flex flex-col ml-5 mt-5 pt-5 self-start ">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="102.385"
                height="14.67"
                viewBox="0 0 102.385 14.67">
                <g
                  id="Western_Digital_Logo_Mobile"
                  data-name="Western Digital Logo Mobile"
                  transform="translate(-51.413 -16.434)">
                  <path
                    id="Path_89"
                    data-name="Path 89"
                    d="M30.747,1.708h2.218v2H34.71V5.278H32.965V9.144a1.052,1.052,0,0,0,1.073,1.165,3.34,3.34,0,0,0,.618-.055v1.369a4.827,4.827,0,0,1-1.545.24c-1.818,0-2.382-1.184-2.382-2.682Zm20.583,2H49.093v7.917h2.236V6.85c0-.943.491-1.572,1.382-1.572,1,0,1.4.74,1.4,1.572V11.6h2.255V6.314a2.588,2.588,0,0,0-2.782-2.848,2.349,2.349,0,0,0-2.236,1.2h-.036Zm-2.982-.24a2.516,2.516,0,0,0-2.655,1.517h-.036V3.706h-2v7.917h2.236V7.683a2.226,2.226,0,0,1,2.455-2.4ZM28.2,5.926l1.436-.98c-.182-.314-.945-1.48-3.182-1.48-1.673,0-3.055.869-3.055,2.4a2.58,2.58,0,0,0,2.146,2.478c.345.111.673.2.964.3.6.2,1.073.37,1.073.832,0,.425-.455.721-1.273.721A1.969,1.969,0,0,1,24.6,9.273l-1.509.851c.145.37.964,1.683,3.346,1.683,1.818,0,3.364-.777,3.364-2.516,0-1.443-1.018-2.127-2.2-2.5l-.873-.277c-.546-.166-1.109-.314-1.109-.832,0-.388.364-.629,1.018-.629A1.775,1.775,0,0,1,28.2,5.926ZM13.419.229l-2.036,8.1L9.346.229h-2.8L4.455,8.571,2.364.229H0L2.891,11.6H5.855L7.873,3.54,9.891,11.6h2.8L15.582.229ZM20.6,9.2a1.763,1.763,0,0,1-1.709,1.017,1.807,1.807,0,0,1-1.745-1.868h5.491V7.664c0-2.072-1.218-4.18-3.855-4.18a3.864,3.864,0,0,0-3.873,4.18,3.888,3.888,0,0,0,3.964,4.2,3.321,3.321,0,0,0,3.218-1.757ZM18.8,5.093a1.622,1.622,0,0,1,1.618,1.683H17.146A1.652,1.652,0,0,1,18.8,5.093ZM40.565,9.2a1.763,1.763,0,0,1-1.709,1.017A1.807,1.807,0,0,1,37.11,8.349H42.6V7.664c0-2.072-1.218-4.18-3.855-4.18a3.864,3.864,0,0,0-3.873,4.18,3.888,3.888,0,0,0,3.964,4.2,3.321,3.321,0,0,0,3.218-1.757Zm-1.8-4.106a1.622,1.622,0,0,1,1.618,1.683H37.129A1.636,1.636,0,0,1,38.765,5.093Z"
                    transform="translate(51.413 16.39)"></path>
                  <path
                    id="Path_90"
                    data-name="Path 90"
                    d="M101.872,1.663h2.218v2h1.745v1.57H104.09V9.09a1.051,1.051,0,0,0,1.072,1.164,3.7,3.7,0,0,0,.637-.055v1.367a4.832,4.832,0,0,1-1.546.24c-1.818,0-2.382-1.182-2.382-2.679ZM75,.185h3.818c3.473,0,5.655,1.737,5.655,5.691s-2.109,5.691-5.655,5.691H75Zm2.4,9.478h1.273C81.58,9.663,82,7.686,82,5.875s-.455-3.788-3.327-3.788H77.4ZM114.818.185h2.291V11.547h-2.291ZM94.381,3.658v.85h-.036a2.278,2.278,0,0,0-2.127-1.09c-2.382,0-3.255,1.977-3.255,3.991s.873,3.991,3.255,3.991a2.235,2.235,0,0,0,2.127-1.09h.036v.905a1.664,1.664,0,0,1-1.655,1.774,1.893,1.893,0,0,1-1.691-.961c-.2.111-1.509.887-1.509.887.164.333.818,1.755,3.273,1.755,2.564,0,3.8-1.663,3.8-4.009v-7ZM92.89,9.7c-1.273,0-1.509-1.256-1.509-2.291,0-1.164.327-2.291,1.509-2.291S94.4,6.245,94.4,7.409C94.381,8.425,94.126,9.7,92.89,9.7Zm13.636-4.878a3.5,3.5,0,0,1,3.055-1.4c1.927,0,3.71.868,3.71,3.122v5.007h-2.182v-.794h-.036A2.668,2.668,0,0,1,106.309,9.2c0-1.811,1.473-2.661,3.745-2.661h.982V6.356c0-.85-.637-1.349-1.636-1.349a1.928,1.928,0,0,0-1.6.794Zm2.019,4.268a1.049,1.049,0,0,0,1.127,1.072c.963,0,1.364-.813,1.364-1.755v-.48h-.873C109,7.926,108.545,8.425,108.545,9.09ZM85.635,3.658h2.291v7.908H85.635ZM86.8,0a1.267,1.267,0,0,0-1.255,1.275,1.255,1.255,0,1,0,2.509,0A1.267,1.267,0,0,0,86.8,0ZM98.127,3.658h2.291v7.908H98.127ZM99.272,0a1.267,1.267,0,0,0-1.254,1.275,1.255,1.255,0,1,0,2.51,0A1.268,1.268,0,0,0,99.272,0Z"
                    transform="translate(36.688 16.434)"></path>
                </g>
              </svg>
              <h1 className="text-2xl font-extrabold text-primary-purple">
                KeyInsights
              </h1>
            </div>
            <div className="flex flex-col justify-center gap-5 mt-5 text-center bg-opacity-0 sm:mt-0 sm:w-1/2">
              <div className="flex flex-row align-middle justify-center">
                <h1 className="text-3xl font-bold sm:w-full">
                  Discover your
                  <span className="text-primary-purple"> Project</span>
                  <span className="text-primary-purple"> Insights</span> now
                </h1>
              </div>
              <a href={getStartedURL}>
                <button
                  className="w-1/3 h-10 self-center shrink-0 shadow-lg"
                  style={buttonStyle}>
                  Get Started
                </button>
              </a>
            </div>
          </Fragment>
        )}
        {!isUserSignedIn && (
          <Fragment>
            <div className="grow h-50v w-5/6 md:h-1/3 md:w-1/2">
              <LoginCard />
            </div>
          </Fragment>
        )}
      </div>
    </>
  );
};

export default HomePage;
