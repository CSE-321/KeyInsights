import React, { useState, useEffect } from 'react';
import { getData } from './kpi2Service';
import SelectColumnFilter from '../Table/SelectColumnFilter';
import Table from '../Table/Table';
import PropTypes from 'prop-types';
import Skeleton from 'react-loading-skeleton';

const RequestComposition = ({ projectName }) => {
  const [data, setData] = React.useState([]);
  const [columns, setcolumns] = React.useState([]);

  useEffect(() => {
    //call api to get data
    getData(projectName, 'KPI3').then((data) => {
      console.log('data', data);
      setData(data.splice(16, data.length));
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
      setcolumns(cols);
    });
  }, []);
  return (
    <>
      <div>
        <div className="max-w-[100%] mx-0">
          {(data.length > 0 && (
            <>
              <div className="max-w-[100%] mx-0">
                <Table columns={columns} data={data} />
              </div>
            </>
          )) || <Skeleton count={10} />}
        </div>
      </div>
    </>
  );
};

RequestComposition.propTypes = {
  projectName: PropTypes.string,
};

export default RequestComposition;
