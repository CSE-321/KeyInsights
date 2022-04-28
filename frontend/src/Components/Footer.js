import React from 'react';

/**
 * The component renders the footer for the application for all pages.
 * @returns {JSX.Element}
 */
const Footer = () => {
  return (
    <>
      <footer className="bg-black w-screen border-t-1 border-white text-white flex flex-col text-xs relative  h-10v">
        <div className="flex flex-row justify-center space-x-2">
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
        <div className="flex flex-row justify-center">
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
