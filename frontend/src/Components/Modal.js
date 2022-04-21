import React, { useState, useEffect, Fragment } from 'react';
import PropTypes from 'prop-types';
import { useLinkClickHandler } from 'react-router-dom';

const Modal = ({
  setModalOn,
  setProject,
  listOfProjects,
  setIsProjectSelected,
}) => {
  return (
    <>
      <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 backdrop-blur-sm">
        <div className="bg-white w-[400px] h-[450px] p-5 rounded-lg">
          <h1 className="font-bold text-2xl text-primary-purple">
            Select a Project
          </h1>
          <br></br>
          <ul>
            <li>
              <button
                className="text-2xl transition duration-500 hover:scale-125 transform-gpu"
                onClick={() => {
                  setModalOn(false);
                  setProject(listOfProjects[0]);
                  setIsProjectSelected(true);
                }}>
                {listOfProjects[0]}
              </button>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
};

Modal.propTypes = {
  setModalOn: PropTypes.func,
  setProject: PropTypes.func,
  setIsProjectSelected: PropTypes.func,
  listOfProjects: PropTypes.array,
};

export default Modal;
