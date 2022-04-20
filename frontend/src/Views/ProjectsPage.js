/* eslint-disable react/prop-types */
import React, { useState, useMemo } from 'react';
import { useSelector } from 'react-redux';
import BodyHeader from '../Components/BodyHeader';
import ProjectCard from '../Features/Projects/ProjectCard';
import SwitchButton from '../Features/Projects/SwitchButton';
import Table from '../Features/Table/Table';
import SelectColumnFilter from '../Features/Table/SelectColumnFilter';

const ProjectsPage = () => {
  const activeServer = useSelector((state) => state.server.activeServer);

  const [isViewGrid, setIsViewGrid] = useState(true);
  console.log(activeServer);
  let arrayOfProjects = [];

  const updateLayout = (gridIsChosen) => {
    setIsViewGrid(gridIsChosen);
  };

  const data = useMemo((arrayOfProjects) => [
    {
      id: Math.floor(Math.random() * 9999),
      name: `BBX${Math.floor(Math.random() * 10)}`,
      type: 'Software',
      lead: 'John Doe',
      image:
        'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80 ',

      created: '2020-01-01',
      issues: Math.floor(Math.random() * 1000),
      server: 'cloud-stm',
    },
  ]);

  const columns = React.useMemo(
    () => [
      {
        Header: 'Lead',
        accessor: 'lead',
        // eslint-disable-next-line react/prop-types
        Cell: (tableProps) => (
          <div className="flex">
            <img
              src={tableProps.row.original.image}
              className="h-10 w-10 rounded-full object-cover"
            />
            <div className="ml-4">{tableProps.row.original.lead}</div>
          </div>
        ),
      },
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
        Header: 'Issues',
        accessor: 'issues',
        Filter: SelectColumnFilter,
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
            <div className="flex flex-col sm:grid sm:grid-flow-row gap-5 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-3 xl:grid-cols-4">
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
