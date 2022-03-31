import React from 'react';
import { useParams } from 'react-router';

const ProjectInsightsPage = () => {
  let { id } = useParams();
  return (
    <div>
      <h1>Project Insights Page</h1>
      <p>Project ID: {id}</p>
    </div>
  );
};

export default ProjectInsightsPage;
