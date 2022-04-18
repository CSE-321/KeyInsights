import React from 'react';
import { useParams } from 'react-router';
import BodyHeader from '../Components/BodyHeader';

const ProjectInsightsPage = () => {
  let { id } = useParams();
  return (
    <div>
      <BodyHeader
        title="Project Insights Page"
        subtext="View keyinsights for project"
        showButton={false}
      />
      <h1>Project Insights Page</h1>
      <p>Project ID: {id}</p>
    </div>
  );
};

export default ProjectInsightsPage;
