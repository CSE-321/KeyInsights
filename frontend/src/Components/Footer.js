import React from 'react';

const Footer = () => {
  return (
    <>
      <footer className="bg-black h-full w-screen border-t-1 border-white text-white flex flex-col mt-5 pt-5 pl-5 text-xs space-y-2 sm:mt-0 ">
        <div className="flex flex-row justify-start space-x-2">
          <a href="#">
            <p> Privacy Policy </p>
          </a>
          <a href="#">
            <p> Accessibility </p>
          </a>
          <a href="#">
            <p> Site Map </p>
          </a>
        </div>
        <div>
          <p>
            © 2022 Western Digital Corporation or its affiliates, All Rights
            Reserved
          </p>
        </div>
      </footer>
    </>
  );
};

export default Footer;
