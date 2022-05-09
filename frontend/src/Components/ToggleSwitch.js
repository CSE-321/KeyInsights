import React from 'react';
import PropTypes from 'prop-types';
import '../CSS/ToggleSwitch.css';

const ToggleSwitch = ({ onChange, label, isProjectSelected, checked }) => {
  return (
    <>
      <label
        htmlFor={label}
        className="inline-block items-center cursor-pointer relative mr-3 mb-7">
        <input
          type="checkbox"
          disabled={!isProjectSelected}
          onChange={onChange}
          checked={checked}
          id={label}
          data-testid={label}
          className="sr-only"></input>
        <div className="inline-block toggle-bg bg-gray-200 border-2 border-gray-200 h-9 w-16 rounded-full"></div>
      </label>
    </>
  );
};

ToggleSwitch.propTypes = {
  onChange: PropTypes.func,
  label: PropTypes.string,
  isProjectSelected: PropTypes.bool,
  checked: PropTypes.bool,
};

export default ToggleSwitch;
