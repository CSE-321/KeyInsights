import React from 'react';
import BodyHeader from '../Components/BodyHeader';

const ProjectsPage = () => {
  return (
    <>
      <BodyHeader
        title="Projects In Server "
        subtext="Access your available project insights"
        showServer={false}
      />
      <div className="flex flex-col mx-7">
        <p className="text-gray-600"> Home/Server/Projects</p>
        <div className="grid grid-cols-3 grid-flow-row gap-5"></div>
      </div>
    </>
  );
};

export default ProjectsPage;
