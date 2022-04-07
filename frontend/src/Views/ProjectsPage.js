import React, { useState, useEffect, useMemo } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setActiveServer } from '../Components/Auth/serverSlice';
import BodyHeader from '../Components/BodyHeader';
import ProjectCard from '../Components/ProjectCard';
import SwitchButton from '../Components/SwitchButton';
import Table, { SelectColumnFilter } from '../Components/Table';
import { getAllProjects } from '../Util/Networking';
import { Project } from '../Util/Project';

const ProjectsPage = () => {
  const activeServer = useSelector((state) => state.server.activeServer);
  const [projects, setProjects] = useState([]);
  const [isViewGrid, setIsViewGrid] = useState(true);
  console.log(activeServer);
  let arrayOfProjects = [];

  const updateLayout = (gridIsChosen) => {
    setIsViewGrid(gridIsChosen);
  };

  const data = useMemo(() => [
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Hardware',
      lead: 'Alex doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Hardware',
      lead: 'Alex doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Hardware',
      lead: 'Alex doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Hardware',
      lead: 'Alex doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'Jane doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Hardware',
      lead: 'Alex doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      serever: 'cloud-stm',
    },
  ]);
  // for (let i = 0; i < 10; i++) {
  //   const project = {
  //     id: Math.floor(Math.random() * 9999),
  //     name: `BBX${i}`,
  //     type: 'Software',
  //     lead: 'Jane doe',
  //     image:
  //       'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80',
  //     created: '2020-01-01',
  //     issues: Math.floor(Math.random() * 1000),
  //     serever: 'cloud-stm',
  //   };

  //   arrayOfProjects.push(project);
  // }

  const columns = React.useMemo(
    () => [
      {
        Header: 'Project Name',
        accessor: 'name',
      },
      {
        Header: 'Type',
        accessor: 'type',
        Filter: SelectColumnFilter,
        filter: 'includes',
      },
      {
        Header: 'Lead',
        accessor: 'lead',
      },
      {
        Header: 'Issues',
        accessor: 'issues',
      },
    ],
    [],
  );

  const headerTitle = activeServer
    ? 'Projects in ' + activeServer.name
    : 'Projects (no server selected)';
  return (
    <>
      <BodyHeader
        title={headerTitle}
        subtext="Access your available project insights"
        showButton={false}></BodyHeader>
      <div className="flex flex-col mx-7 my-5">
        <div className="flex flex-row justify-between items-center ">
          <p className="text-gray-600"> Home/Server/Projects</p>

          <SwitchButton isViewGrid={isViewGrid} updateLayout={updateLayout} />
        </div>

        {isViewGrid ? (
          <>
            <div className="flex flex-col sm:grid sm:grid-flow-row gap-5 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5">
              {data.map((project) => (
                <ProjectCard key={project.id} project={project} />
              ))}
            </div>
          </>
        ) : (
          <>
            <Table columns={columns} data={data} />
          </>
        )}
      </div>
    </>
  );
};

export default ProjectsPage;
