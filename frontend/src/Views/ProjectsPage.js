/* eslint-disable react/prop-types */
import React, { useState, useMemo, useEffect } from 'react';
import { useSelector } from 'react-redux';
import BodyHeader from '../Components/BodyHeader';
import ProjectCard from '../Features/Projects/ProjectCard';
import SwitchButton from '../Features/Projects/SwitchButton';
import Table from '../Features/Table/Table';
import { useNavigate } from 'react-router';
import SelectColumnFilter from '../Features/Table/SelectColumnFilter';
import { getAllProjects } from '../Features/Projects/Networking';
import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';

const ProjectsPage = () => {
  const activeServer = useSelector((state) => state.server.activeServer);
  const navigate = useNavigate();
  const [projects, setProjects] = useState([]);
  const [isViewGrid, setIsViewGrid] = useState(true);
  console.log(activeServer);
  let arrayOfProjects = [];

  const updateLayout = (gridIsChosen) => {
    setIsViewGrid(gridIsChosen);
  };

  useEffect(() => {
    getAllProjects().then((data) => {
      console.log(data);
      setProjects(data);
    });
  }, []);

  const data = useMemo(() => projects, [projects]);

  //navigates to insights page when project is clicked
  const onTableRowClick = (name, issueCount) => {
    if (issueCount <= 0) {
      navigate(`/error/${1000}`);
      return;
    }
    navigate('/projects/name=' + name, { replace: true });
  };

  const columns = React.useMemo(
    () => [
      {
        Header: 'Lead',
        accessor: 'teamLead',
        // eslint-disable-next-line react/prop-types
        Cell: (tableProps) => (
          <div className="flex">
            <img
              src={tableProps.row.original.teamLeadAvatarUrl}
              className="h-10 w-10 rounded-full object-cover"
            />
            <div className="ml-4">{tableProps.row.original.teamLead}</div>
          </div>
        ),
      },
      {
        Header: 'Project Name',
        accessor: 'name',
      },

      {
        Header: 'Issues',
        accessor: 'numIssues',
      },
      {
        Header: 'Created At',
        accessor: 'createdDate',
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
          <p className="text-gray-600">
            <a href="/">Home</a>
            <a href="/projects" className="text-black">
              /Projects
            </a>
          </p>

          <SwitchButton isViewGrid={isViewGrid} updateLayout={updateLayout} />
        </div>

        {isViewGrid ? (
          <>
            <div className="flex flex-col sm:grid sm:grid-flow-row gap-5 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-3 xl:grid-cols-4">
              {projects.map((project) => (
                <ProjectCard key={project.name} project={project} />
              ))}
            </div>
          </>
        ) : (
          <>
            {(
              <Table
                columns={columns}
                data={data}
                onRowClick={onTableRowClick}
              />
            ) || <Skeleton></Skeleton>}
          </>
        )}
      </div>
    </>
  );
};

export default ProjectsPage;
