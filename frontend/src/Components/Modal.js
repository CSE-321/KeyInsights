import React, { Fragment } from 'react';
import PropTypes from 'prop-types';

const Modal = ({
  setModalOn,
  setProject,
  listOfProjects,
  setIsProjectSelected,
  setDefaultValues,
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
            {/* Displays all projects from Jira server */}
            {listOfProjects.map((project) => (
              <li key={project.name}>
                <button
                  className="text-2xl transition duration-500 hover:scale-125 transform-gpu mb-5"
                  key={project.name}
                  onClick={() => {
                    setModalOn(false);
                    setProject(project.name);
                    setIsProjectSelected(true);
                    setDefaultValues();
                  }}>
                  {project.name}
                </button>
              </li>
            ))}
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
  setDefaultValues: PropTypes.func,
};

export default Modal;
