import React, { useState } from 'react';
import propType from 'prop-types';

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
      <div
        id="switch-root"
        className="flex flex-row h-full w-32 bg-stone-200 rounded-md ">
        <div
          id="left-switch"
          className="h-full w-1/2 border-r-2 border-white flex flex-row justify-center self-center">
          <button
            className={
              isGridViewActive
                ? 'bg-white rounded-md shadow-lg'
                : 'bg-transparent rounded-md '
            }
            onClick={handleGridViewClick}>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              aria-hidden="true"
              role="img"
              width="35"
              height="35"
              preserveAspectRatio="xMidYMid meet"
              viewBox="0 0 24 24">
              <rect
                x="0"
                y="0"
                width="24"
                height="24"
                fill="none"
                stroke="none"
              />
              <g
                fill="none"
                stroke="currentColor"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2">
                <rect width="7" height="7" x="3" y="3" rx="1" />
                <rect width="7" height="7" x="3" y="14" rx="1" />
                <rect width="7" height="7" x="14" y="3" rx="1" />
                <rect width="7" height="7" x="14" y="14" rx="1" />
              </g>
            </svg>
          </button>
        </div>
        <div
          id="right-switch"
          className="h-full w-1/2 p-2 flex flex-row justify-center self-center">
          <button
            className={
              isListViewActive
                ? 'bg-white rounded-md shadow-lg'
                : 'bg-inherit rounded-md'
            }
            onClick={handleListViewClick}>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              aria-hidden="true"
              role="img"
              width="35"
              height="35"
              preserveAspectRatio="xMidYMid meet"
              viewBox="0 0 1024 1024">
              <rect
                x="0"
                y="0"
                width="1024"
                height="1024"
                fill="none"
                stroke="none"
              />
              <path
                fill="currentColor"
                d="M912 192H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 284H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 284H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zM104 228a56 56 0 1 0 112 0a56 56 0 1 0-112 0zm0 284a56 56 0 1 0 112 0a56 56 0 1 0-112 0zm0 284a56 56 0 1 0 112 0a56 56 0 1 0-112 0z"
              />
            </svg>
          </button>
        </div>
      </div>
    </>
  );
};

SwitchButton.propTypes = {
  updateLayout: propType.func.isRequired,
};
export default SwitchButton;
