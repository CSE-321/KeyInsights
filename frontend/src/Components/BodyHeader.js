import React, { useState, useEffect, Fragment } from 'react';
import PropTypes from 'prop-types';
import '../CSS/BodyHeader.css';
//import Dropdown from './Dropdown';
import Modal from './Modal';

/**
 * The body header component is used to display the header of the body. It contains the title,
 * subtitle, and drop down option.The bar is mobile responsive.
 * @param {string} title
 * @param {string} subtext
 * @param {bool} showServer
 * @returns {JSX} black bar with title and subtext
 */
const BodyHeader = ({ title, subtext, showButton, setModalOn }) => {
  const [headerTitle, setHeaderTitle] = React.useState(title);
  const [headerSubtext, setHeaderSubtext] = React.useState(subtext);
  const [isButtonActive, setIsButtonActive] = React.useState(false);

  React.useEffect(() => {
    setHeaderTitle(title);
    setHeaderSubtext(subtext);
    setIsButtonActive(showButton);
  }, [title, subtext, showButton]);

  return (
    <>
      <div className="body-header">
        <div id="header-description">
          <h1
            className="header-text text-2xl sm:text-2xl md:text-3xl lg:text-4xl"
            key={headerTitle}>
            {headerTitle}
          </h1>
          <p className="header-text"> {headerSubtext} </p>
        </div>

        {isButtonActive && (
          // <Fragment>
          //   <div className="w-10v h-10v">
          //     <Dropdown />
          //   </div>
          // </Fragment>
          <button
            className="rounded-lg bg-primary-purple text-white ml-2 h-10 w-32 text-xs sm:w-28 sm:h-12 md:h-12 md:w-24 lg:h-12 lg:w-32 sm:text-sm md:text-md lg:text-lg"
            onClick={() => setModalOn(true)}>
            {' '}
            Select Project
          </button>
        )}
      </div>
    </>
  );
};

BodyHeader.propTypes = {
  title: PropTypes.string,
  subtext: PropTypes.string,
  showButton: PropTypes.bool,
  setModalOn: PropTypes.func,
};

export default BodyHeader;
