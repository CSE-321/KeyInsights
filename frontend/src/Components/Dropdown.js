import React, { Fragment } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { setActiveServer } from '../App/Slices/serverSlice';
import PropTypes from 'prop-types';
import '../CSS/Dropdown.css';

/**
 * Depracated:
 * A dropdown component that displays a list of servers.
 * @returns
 */
const Dropdown = ({
  id,
  text,
  setVal,
  setText,
  setIsSettingsChanged,
  isToggleSwitched,
}) => {
  //TODO: Make API call to get server list, and update server state
  const [showDropdown, setShowDropdown] = React.useState(false);

  // const dispatch = useDispatch();
  // const serverList = useSelector((state) => state.server.servers);

  const enableDropdown = () => {
    if (isToggleSwitched) {
      setShowDropdown(true);
    }
  };

  const disableDropdown = () => {
    setShowDropdown(false);
  };

  // const onServerSelect = (indexPath) => {
  //   dispatch(setActiveServer(serverList[indexPath]));
  // };

  // const itemList = serverList?.map((server, index) => {
  //   console.log(server.name + ' ' + index);
  //   return (
  //     <li
  //       key={index}
  //       onClick={() => onServerSelect(index)}
  //       className="hover:text-primary-purple">
  //       {server.name}
  //     </li>
  //   );
  // });

  const handleClickForTwoWeeks = () => {
    setVal(14);
    setText('2 Weeks');
    setIsSettingsChanged(true);
  };

  const handleClickForOneMonth = () => {
    setVal(30);
    setText('1 Month');
    setIsSettingsChanged(true);
  };

  return (
    <>
      <div
        id={id}
        className="inline-block align-top mb-1 flex-col w-48 h-9 border-solid border border-black rounded-sm text-lg sm:text-xl md:text-2xl"
        onMouseEnter={enableDropdown}
        onMouseLeave={disableDropdown}>
        <button id="dropdown-button" className="h-9 w-48">
          <div
            id="dropdown-inner-button"
            className="flex flex-row justify-between items-center ">
            <p className="text-black text-left text-xl flex-grow ml-1">
              {text}
            </p>
            <p className="text-black text-right flex-grow">&#9662;</p>
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
                className="text-white align-top bg-black text-base xl:text-lg z-50 relative"
                onMouseLeave={disableDropdown}>
                <ul>
                  <li
                    className="hover:text-primary-purple"
                    onClick={handleClickForTwoWeeks}>
                    2 Weeks
                  </li>
                  <li
                    className="hover:text-primary-purple"
                    onClick={handleClickForOneMonth}>
                    1 Month
                  </li>
                </ul>
              </div>
            </Fragment>
          )}
        </button>
      </div>
    </>
  );
};

Dropdown.propTypes = {
  id: PropTypes.string,
  text: PropTypes.string,
  setVal: PropTypes.func,
  setText: PropTypes.func,
  setIsSettingsChanged: PropTypes.func,
  isToggleSwitched: PropTypes.bool,
};

export default Dropdown;
