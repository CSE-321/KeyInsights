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
      <div className="bg-zinc-200 opacity-70 fixed inset-0 z-500">
        <div className="flex h-screen justify-center items-center">
          <div className="flex justify-center bg-white w-[400px] h-[500px] py-12 px-24 border-4 rounded-xl">
            <div className="flex-col text-2xl text-zinc-600 mb-10">
              Select A Project
              <br></br>
              <div className="flex-col justify-left">
                <ul>
                  <li>
                    <button
                      onClick={() => {
                        setModalOn(false);
                        setProject(listOfProjects[0]);
                        setIsProjectSelected(true);
                      }}>
                      {listOfProjects[0]}
                    </button>
                  </li>
                  <li>
                    <button
                      onClick={() => {
                        setModalOn(false);
                        setProject(listOfProjects[1]);
                        setIsProjectSelected(true);
                      }}>
                      {listOfProjects[1]}
                    </button>
                  </li>
                  <li>
                    <button
                      onClick={() => {
                        setModalOn(false);
                        setProject(listOfProjects[2]);
                        setIsProjectSelected(true);
                      }}>
                      {listOfProjects[2]}
                    </button>
                  </li>
                </ul>
              </div>
            </div>
          </div>
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
