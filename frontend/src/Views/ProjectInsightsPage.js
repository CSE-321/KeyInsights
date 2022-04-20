import React from 'react';
import { useParams } from 'react-router';
import BodyHeader from '../Components/BodyHeader';
import KpiNavBar from '../Components/KpiNavBar';

const ProjectInsightsPage = () => {
  let { id } = useParams();
  return (
    <div>
      <BodyHeader
        title="Project Insights Page"
        subtext="View keyinsights for project"
        showButton={false}
      />
      <div className="flex flex-col p-5 md:flex-row md:space-x-5">
        <div className="w-85v md:w-20v">
          <KpiNavBar className="" />
        </div>
        <div>
          <h1>Project Insights Page</h1>
          <p>Project ID: {id}</p>
        </div>
      </div>
    </div>
  );
};

export default ProjectInsightsPage;
