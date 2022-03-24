import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import '../CSS/BodyHeader.css';
//import Dropdown from './Dropdown';
const BodyHeader = ({ title, subtext, showServer }) => {
  const [headerTitle, setHeaderTitle] = React.useState(title);
  const [headerSubtext, setHeaderSubtext] = React.useState(subtext);
  const [isServerComponentActive, setisServerComponentActive] =
    React.useState(showServer);

  React.useEffect(() => {
    setHeaderTitle(title);
    setHeaderSubtext(subtext);
    setisServerComponentActive(showServer);
  }, [headerTitle, headerSubtext, showServer]);

  const servers = ['STM', 'STM2', 'STM3'];

  return (
    <>
      <div className="body-header">
        <div id="header-description">
          <h1 className="header-text"> {headerTitle} </h1>
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
