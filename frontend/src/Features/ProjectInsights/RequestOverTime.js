import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import Table from '../Table/Table';
import SelectColumnFilter from '../Table/SelectColumnFilter';
import { getData } from './kpi2Service';
import { round } from './MathUtil';
import Skeleton from 'react-loading-skeleton';

const RequestOverTime = ({ projectName }) => {
  const [kpi_list, setKpi1_List] = React.useState([]);
  const [tableColumns, setTableColumns] = useState([]);

  useEffect(() => {
    //call api to get data
    getData(projectName, 'KPI2').then((data) => {
      console.log('data', data);
      data.forEach((kpi) => {
        for (var key in kpi) {
          if (key === 'id' || key === 'teamType') {
            continue;
          }
          kpi[key] = round(kpi[key], 0) + ' Days';
        }
      });

      setKpi1_List(data);

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
    });
  }, []);

  return (
    <>
      <div className=""></div>
      <div>
        {(kpi_list.length > 0 && (
          <>
            <div className="max-w-[100%] mx-0">
              <Table columns={tableColumns} data={kpi_list} />
            </div>
          </>
        )) || <Skeleton count={10} />}
      </div>
    </>
  );
};

RequestOverTime.propTypes = {
  projectName: PropTypes.string,
};

export default RequestOverTime;
