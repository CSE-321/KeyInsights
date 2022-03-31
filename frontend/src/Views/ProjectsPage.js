import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setActiveServer } from '../Components/Auth/serverSlice';
import BodyHeader from '../Components/BodyHeader';
import ProjectCard from '../Components/ProjectCard';
import { getAllProjects } from '../Util/Networking';

const project = {
  id: `${Math.floor(Math.random() * 9999)}`,
  name: 'BBX8',
  type: 'Software',
  lead: 'Jane doe',
  image:
    'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
  created: '2020-01-01',
  issues: '999999999999999999999999999999999999999999999',
  server: 'cloud-stm',
};

const ProjectsPage = () => {
  const activeServer = useSelector((state) => state.server.activeServer);
  const [projects, setProjects] = useState([]);
  console.log(activeServer);

  getAllProjects(
    'http://jira.cloud-stm.com:8080',
    '/rest/api/latest/project',
    'aroman27',
    'Racheal2',
  );
  const headerTitle = activeServer
    ? 'Projects in ' + activeServer.name
    : 'Projects (no server selected)';
  return (
    <>
      <BodyHeader
        title={headerTitle}
        subtext="Access your available project insights"
        showServer={false}
      />
      <div className="flex flex-col mx-7 my-5">
        <p className="text-gray-600"> Home/Server/Projects</p>
        <div className="flex flex-col sm:grid sm:grid-flow-row gap-5 sm:grid-cols-2 xl:grid-cols-3">
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
          <ProjectCard project={project} />
        </div>
      </div>
    </>
  );
};

export default ProjectsPage;
