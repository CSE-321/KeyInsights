import React, { useEffect, useMemo } from 'react';
import OverviewCard from './OverviewCard';
import Table from '../Table/Table';
import SelectColumnFilter from '../Table/SelectColumnFilter';
import PropTypes from 'prop-types';
import Skeleton from 'react-loading-skeleton';
import { round } from './MathUtil';
import {
  getKPI_1,
  getTopJiraClosedTeam,
  getPercentageJiraClosed,
  getTopTeamsByJiraClosed,
  convertToGraphData,
  convertToKeys,
  convertToGraph,
  getCriticalNotCompleted,
  getPercentageOfBugs,
  converPercentageBugsToGraphData,
} from './KPIService';

const OverviewBody = ({ projectName }) => {
  const [kpi1_List, setKpi1_List] = React.useState([]);
  const [topTeamsList, setTopTeamsList] = React.useState([]);
  const [needsAttentionList, setNeedsAttentionList] = React.useState([]);
  const [tableColumns, setTableColumns] = React.useState([]);
  //make api call
  useEffect(() => {
    getKPI_1(projectName).then((data) => {
      data.forEach((kpi) => {
        for (var key in kpi) {
          if (key === 'id' || key === 'teamType') {
            continue;
          }
          kpi[key] = round(kpi[key], 0);
        }
      });
      setKpi1_List(data);
      setTopTeamsList(getTopTeamsByJiraClosed(data));
      setNeedsAttentionList(getCriticalNotCompleted(data));
      var cols = [];

      for (var key in data[0]) {
        if (key !== 'teamType') {
          cols.push({
            Header: key,
            accessor: key,
          });
        } else {
          cols.push({
            Header: key,
            accessor: key,
            Filter: SelectColumnFilter,
          });
        }
      }

      setTableColumns(cols);
      console.log(data);
    });
  }, []);

  useEffect(() => {}, [kpi1_List]);

  //calls to parse the data once the KPI's are fetched, updates when changed

  const tableData = useMemo(() => kpi1_List, [kpi1_List]);

  return (
    <>
      <h1 className="text-lg font-medium text-black md:text-xl lg:text-2xl">
        Quick Key Insights
      </h1>

      <div className="w-full h-[1px] bg-gray-300 rounded-xl"></div>
      <div
        id="Insights Main Area"
        className="grid grid-cols-6 grid-flow-row space-y-10 space-x-5">
        <div className="col-span-6 ">
          <OverviewCard
            cardTitle="Issue Completion"
            cardText={
              <>
                <p>
                  This project has completed{' '}
                  <span className="text-[#5DD39E]">
                    {getPercentageJiraClosed(kpi1_List) || <Skeleton />}%
                  </span>{' '}
                  of all active issues.
                  <br />
                </p>
              </>
            }
            graphData={getTopJiraClosedTeam(kpi1_List)}
            graphType={'Pie'}
            colors={['#F3A582', '#5DD39E']}></OverviewCard>
        </div>
        <div className="col-span-6 sm:col-start-1 sm:col-end-4">
          <OverviewCard
            cardTitle="Top Issue Type Completion"
            cardText={
              <>
                {(topTeamsList.length > 0 && (
                  <>
                    {(
                      <div className="flex flex-col space-y-5">
                        <p>🥇 {topTeamsList[0].team}</p>
                        <p>🥈 {topTeamsList[1].team}</p>
                        <p>🥉 {topTeamsList[2].team}</p>
                      </div>
                    ) || <Skeleton count={3} />}
                  </>
                )) || <Skeleton count={3} />}
              </>
            }
            graphType={'Bar'}
            graphData={convertToGraphData(getTopTeamsByJiraClosed(kpi1_List))}
            //graphKeys={convertToKeys(getTopTeamsByJiraClosed(kpi1_List))}
            forIndexBy={'teamType'}
            colors={['#5DD39E', '#5DD39E']}></OverviewCard>
        </div>
        <div className="col-span-6 sm:col-start-4 sm:col-end-7">
          <OverviewCard
            cardTitle="Top Percentage of Critical Issues Not Completed"
            cardText={
              <>
                {(needsAttentionList.length > 0 && (
                  <div className="flex flex-col space-y-5">
                    <div className="flex flex-row">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-red-500"
                        viewBox="0 0 20 20"
                        fill="currentColor">
                        <path
                          fillRule="evenodd"
                          d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z"
                          clipRule="evenodd"
                        />
                      </svg>{' '}
                      <p>1. {needsAttentionList[0].team}</p>
                    </div>
                    <div className="flex flex-row">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-yellow-500"
                        viewBox="0 0 20 20"
                        fill="currentColor">
                        <path
                          fillRule="evenodd"
                          d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z"
                          clipRule="evenodd"
                        />
                      </svg>{' '}
                      <p>2. {needsAttentionList[1].team}</p>
                    </div>
                    <div className="flex flex-row">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-yellow-500"
                        viewBox="0 0 20 20"
                        fill="currentColor">
                        <path
                          fillRule="evenodd"
                          d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z"
                          clipRule="evenodd"
                        />
                      </svg>{' '}
                      <p>3. {needsAttentionList[2].team}</p>
                    </div>
                  </div>
                )) || <Skeleton count={3} />}
              </>
            }
            graphType={'Bar'}
            graphData={convertToGraph(getCriticalNotCompleted(kpi1_List))}
            //graphKeys={convertToKeys(getTopTeamsByJiraClosed(kpi1_List))}
            forIndexBy={'teamType'}
            colors={['#FF0000', '#F3A582', '#5DD39E']}></OverviewCard>
        </div>
        <div className="col-span-6">
          <OverviewCard
            cardTitle="Percentage of bugs"
            cardText={
              <>
                <p>
                  <span className="text-[#5DD39E]">
                    {getPercentageOfBugs(kpi1_List) || <Skeleton></Skeleton>}%
                  </span>
                  &nbsp;Of all issues are bugs.
                </p>
              </>
            }
            graphType={'Pie'}
            graphData={converPercentageBugsToGraphData(kpi1_List)}
            colors={['#F3A582', '#5DD39E']}></OverviewCard>
        </div>
      </div>
      <div className="mt-5">
        {(tableData.length > 0 && (
          <>
            <div className="max-w-[100%] mx-0">
              <Table columns={tableColumns} data={tableData} />
            </div>
          </>
        )) || <Skeleton count={5} />}
      </div>
    </>
  );
};

OverviewBody.propTypes = {
  projectName: PropTypes.string.isRequired,
};

export default OverviewBody;
