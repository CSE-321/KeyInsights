import React from 'react';
import propType from 'prop-types';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';

const ProjectCard = ({ project }) => {
  const [activeProject, setActiveProject] = useState(project);

  const navigate = useNavigate();

  useEffect(() => {
    setActiveProject(project);
  }, [project]);

  const selfIsSelected = () => {
    navigate('/projects/id=' + project.id, { replace: true });
  };

  return (
    <>
      <div
        className="bg-white rounded-xl drop-shadow-2xl flex flex-col justify-between transform transition duration-500 hover:scale-110 transform-gpu relative"
        onClick={selfIsSelected}>
        <div className="flex flex-row justify-between mt-3 ml-5">
          <h1 className="flex-shrink text-xl sm:text-2xl md:text-3xl font-extrabold truncate">
            {activeProject.name}
          </h1>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            aria-hidden="true"
            role="img"
            width="30"
            height="30"
            preserveAspectRatio="xMidYMid meet"
            viewBox="0 0 16 16"
            className="flex-shrink-0">
            <path
              fill="none"
              stroke="#3423a6"
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="1.5"
              d="m8.75 3.25l4.5 4.5l-4.5 4.5m-6-4.5h10.5"
            />
          </svg>
        </div>
        <p className="flex-grow ml-5 font-semibold truncate mb-10">
          {activeProject.type}
        </p>

        <div className="grid grid-cols-8 grid-flow-row mb-5 ml-5">
          <p className="col-span-4 ">Project Lead</p>
          <p className="col-span-2"> Created at</p>
          <p className="col-span-2">Issues</p>
          <img
            src={project.image}
            className="w-10 h-10 rounded-full col-span-1 object-cover"></img>
          <p className="col-span-3 self-center truncate">{project.lead}</p>
          <p className="col-span-2 truncate">{project.created}</p>
          <p className="col-span-2 truncate">{project.issues}</p>
        </div>
      </div>
    </>
  );
};

ProjectCard.propTypes = {
  project: propType.object,
};
export default ProjectCard;
