import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setActiveServer } from '../Components/Auth/serverSlice';
import BodyHeader from '../Components/BodyHeader';
import ProjectCard from '../Components/ProjectCard';
import { getAllProjects } from '../Util/Networking';
import { Project } from '../Util/Project';

const ProjectsPage = () => {
  const activeServer = useSelector((state) => state.server.activeServer);
  const [projects, setProjects] = useState([]);
  console.log(activeServer);
  let arrayOfProjects = [];

  for (let i = 0; i < 10; i++) {
    const project = {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${i}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    };

    arrayOfProjects.push(project);
  }
  const headerTitle = activeServer
    ? 'Projects in ' + activeServer.name
    : 'Projects (no server selected)';
  return (
    <>
      <BodyHeader
        title={headerTitle}
        subtext="Access your available project insights"
        showButton={true}></BodyHeader>
      <div className="flex flex-col mx-7 my-5">
        <p className="text-gray-600"> Home/Server/Projects</p>
        <div className="flex flex-col sm:grid sm:grid-flow-row gap-5 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5">
          {arrayOfProjects.map((project) => (
            <ProjectCard key={project.id} project={project} />
          ))}
        </div>
      </div>
    </>
  );
};

export default ProjectsPage;
