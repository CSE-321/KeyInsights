import React, { useState } from 'react';
import propType from 'prop-types';

/**
 * A button used to switch between projects grid and list view.
 * @param {func} props.updateLayout
 * @returns {jsx}
 */
const SwitchButton = (props) => {
  const [isGridViewActive, setIsGridViewActive] = useState(true);
  const [isListViewActive, setIsListViewActive] = useState(false);

  const handleGridViewClick = () => {
    setIsGridViewActive(true);
    setIsListViewActive(false);
    props.updateLayout(true);
  };

  const handleListViewClick = () => {
    setIsGridViewActive(false);
    setIsListViewActive(true);
    props.updateLayout(false);
  };
  return (
    <>
      <div className="flex flex-row">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          className={
            isGridViewActive
              ? 'h-10 w-10 text-gray-900'
              : 'h-10 w-10 text-gray-500 hover:text-gray-900'
          }
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          strokeWidth={1.4}
          onClick={() => handleGridViewClick(true)}>
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M4 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2V6zM14 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2V6zM4 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2v-2zM14 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2v-2z"
          />
        </svg>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          className={
            isListViewActive
              ? 'h-10 w-10 text-black'
              : 'h-10 w-10 text-gray-500 hover:text-gray-900'
          }
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          strokeWidth={1.2}
          onClick={() => handleListViewClick(true)}>
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M4 6h16M4 10h16M4 14h16M4 18h16"
          />
        </svg>
      </div>
    </>
  );
};

SwitchButton.propTypes = {
  updateLayout: propType.func.isRequired,
};
export default SwitchButton;
