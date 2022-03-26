import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import '../CSS/BodyHeader.css';
//import Dropdown from './Dropdown';

/**
 * The body header component is used to display the header of the body. It contains the title,
 * subtitle, and drop down option.The bar is mobile responsive.
 * @param {string} title
 * @param {string} subtext
 * @param {bool} showServer
 * @returns {JSX} black bar with title and subtext
 */
const BodyHeader = ({ title, subtext, showServer }) => {
  const [headerTitle, setHeaderTitle] = React.useState(title);
  const [headerSubtext, setHeaderSubtext] = React.useState(subtext);

  React.useEffect(() => {
    setHeaderTitle(title);
    setHeaderSubtext(subtext);
  }, [title, subtext]);

  const servers = ['STM', 'STM2', 'STM3'];

  return (
    <>
      <div className="body-header">
        <div id="header-description">
          <h1
            className="header-text text-2xl sm:text-3xl md:text-4xl"
            key={headerTitle}>
            {headerTitle}
          </h1>
          <p className="header-text"> {headerSubtext} </p>
        </div>
      </div>
    </>
  );
};

BodyHeader.propTypes = {
  title: PropTypes.string,
  subtext: PropTypes.string,
  showServer: PropTypes.bool,
};

export default BodyHeader;
