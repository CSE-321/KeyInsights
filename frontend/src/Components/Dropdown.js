import React, { Fragment, useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { setActiveServer } from '../Components/Auth/serverSlice';
import PropTypes from 'prop-types';
import '../CSS/Dropdown.css';

const Dropdown = () => {
  //TODO: Make API call to get server list, and update server state
  const [showDropdown, setShowDropdown] = React.useState(false);

  const dispatch = useDispatch();
  const serverList = useSelector((state) => state.server.servers);

  const enableDropdown = () => {
    setShowDropdown(!showDropdown);
  };

  const onServerSelect = (indexPath) => {
    dispatch(setActiveServer(serverList[indexPath]));
  };

  const itemList = serverList?.map((server, index) => {
    console.log(server.name + ' ' + index);
    return (
      <li
        key={index}
        onClick={() => onServerSelect(index)}
        className="hover:text-primary-purple">
        {server.name}
      </li>
    );
  });

  console.log(serverList);

  return (
    <>
      <div
        id="dropdown"
        className="flex flex-col w-full h-full text-lg sm:text-xl md:text-2xl">
        <button id="dropdown-button" className="h-full w-full">
          <div
            id="dropdown-inner-button"
            className="flex flex-row justify-between items-center "
            onMouseEnter={enableDropdown}>
            <p className="text-white flex-grow">Servers</p>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              aria-hidden="true"
              role="img"
              width="10"
              height="10"
              preserveAspectRatio="xMidYMid meet"
              viewBox="0 0 24 24">
              <path
                fill="white"
                d="M11.178 19.569a.998.998 0 0 0 1.644 0l9-13A.999.999 0 0 0 21 5H3a1.002 1.002 0 0 0-.822 1.569l9 13z"
              />
            </svg>
          </div>
          {showDropdown && (
            <Fragment>
              <div
                id="dropdown-content"
                className="text-white bg-black text-base  xl:text-lg z-50 relative"
                onMouseLeave={enableDropdown}>
                <ul>{itemList}</ul>
              </div>
            </Fragment>
          )}
        </button>
      </div>
    </>
  );
};

Dropdown.propTypes = {
  serverList: PropTypes.array,
};

export default Dropdown;
