import React, { useState, useEffect, Fragment } from 'react';
import PropTypes from 'prop-types';
import '../CSS/BodyHeader.css';
import Dropdown from './Dropdown';
import {
  useTable,
  useFilters,
  useGlobalFilter,
  useSortBy,
  usePagination,
} from 'react-table';

/**
 * The body header component is used to display the header of the body. It contains the title,
 * subtitle, and drop down option.The bar is mobile responsive.
 * @param {string} title
 * @param {string} subtext
 * @param {bool} showButton
 * @returns {JSX} black bar with title and subtext
 */
const BodyHeader = ({ title, subtext, showButton }) => {
  //internal states
  const [headerTitle, setHeaderTitle] = React.useState(title);
  const [headerSubtext, setHeaderSubtext] = React.useState(subtext);
  const [isButtonActive, setIsButtonActive] = React.useState(showButton);

  //monitors changes in props and updates internal states
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
            className="header-text text-2xl sm:text-3xl md:text-4xl"
            key={headerTitle}>
            {headerTitle}
          </h1>
          <p className="header-text"> {headerSubtext} </p>
        </div>

        {isButtonActive && (
          <button className="bg-primary-purple text-white h-1/4 w-1/12">
            Show Projects
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
};

export default BodyHeader;
