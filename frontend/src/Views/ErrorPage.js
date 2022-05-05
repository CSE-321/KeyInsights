import React, { useEffect } from 'react';
import { useParams } from 'react-router';
import BodyHeader from '../Components/BodyHeader';
import Lottie from 'lottie-web';
import searchPageAnimation from '../Assets/search_page_animation.json';
//an error object that handles incoming codes
let Error = {
  1000: {
    title: 'Error - No Issues',
    description: 'No issues are available for this project',
    suggestion:
      'We are working hard to find available issues, please check back later for your insights.',
  },
};

const ErrorPage = () => {
  const [isLottieAnimationLoaded, setIsLottieAnimationLoaded] =
    React.useState(false);

  let { code } = useParams();

  useEffect(() => {
    if (!isLottieAnimationLoaded) {
      setIsLottieAnimationLoaded(true);
      Lottie.loadAnimation({
        container: document.getElementById('lottie'),
        animationData: searchPageAnimation,
        renderer: 'svg',
        loop: true,
        autoplay: true,
      });
    }
  }, []);
  return (
    <>
      <BodyHeader
        title={Error[code].title}
        subtext={Error[code].description}
        showButton={false}
      />
      <div className="flex flex-col justify-center items-center mb-5">
        <div id="lottie" className="w-1/4 h-1/4" />
        <h1 className="text-lg">{Error[code].suggestion}</h1>
      </div>
    </>
  );
};
export default ErrorPage;
