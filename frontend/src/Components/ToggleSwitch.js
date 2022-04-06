import React from 'react';
import PropTypes from 'prop-types';
import '../CSS/ToggleSwitch.css';

const ToggleSwitch = ({ onChange }) => {
  return (
    <>
      <label
        htmlFor="toggle-switch"
        className="items-center cursor-pointer relative mb-4">
        <input
          type="checkbox"
          onChange={onChange}
          id="toggle-switch"
          className="sr-only"></input>
        <div className="toggle-bg bg-gray-200 border-2 border-gray-200 h-9 w-16 rounded-full"></div>
      </label>
    </>
  );
};

ToggleSwitch.propTypes = {
  onChange: PropTypes.bool,
};

export default ToggleSwitch;
