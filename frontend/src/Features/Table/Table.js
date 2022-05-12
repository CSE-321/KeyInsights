/* eslint-disable react/jsx-key */
import React from 'react';
import propType from 'prop-types';
import GlobalFilter from './GlobalFilter';
//import SelectColumnFilter from './SelectColumnFilter';
import { ChevronLeftIcon, ChevronRightIcon } from '@heroicons/react/solid';
import {
  useTable,
  useFilters,
  useGlobalFilter,
  useSortBy,
  usePagination,
} from 'react-table';

/**
 *  The table component is used to display a table UI from passed in data.
 * @param {Array} columns
 * @param {Array} data
 * @returns {JSX} react-table
 */
function Table({ columns, data, onRowClick }) {
  // UseTable returns props that can be used to build the table UI
  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    prepareRow,
    page,
    canPreviousPage,
    canNextPage,
    pageOptions,
    pageCount,
    gotoPage,
    nextPage,
    previousPage,
    setPageSize,

    state,
    preGlobalFilteredRows,
    setGlobalFilter,
  } = useTable(
    {
      columns,
      data,
    },
    useFilters,
    useGlobalFilter,
    useSortBy,
    usePagination,
  );

  return (
    <>
      <div
        id="Filter-options-root"
        className="flex flex-row justify-center items-baseline space-x-20 shadow-lg drop-shadow-xl border-1 border-gray-400">
        <GlobalFilter
          preGlobalFilteredRows={preGlobalFilteredRows}
          globalFilter={state.globalFilter}
          setGlobalFilter={setGlobalFilter}
        />
        {headerGroups.map((headerGroup) =>
          headerGroup.headers.map((column) =>
            column.Filter ? (
              <div key={column.id}>
                <label htmlFor={column.id}>{column.render('Header')}: </label>
                {column.render('Filter')}
              </div>
            ) : null,
          ),
        )}
      </div>
      <div
        id="table-root"
        className="bg-white shadow drop-shadow-md text-xl sm:rounded-lg border-b border-gray-500 block overflow-auto">
        <table
          {...getTableProps()}
          border="1"
          className="min-w-full divide-y divide-gray-200">
          <thead className=" border-b-2 broder-b-slate-100 text-gray-500 bg-gray-50">
            {headerGroups.map((headerGroup) => (
              <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map((column) => (
                  <th
                    {...column.getHeaderProps(column.getSortByToggleProps())}
                    className="px-6 py-5 text-left text-base font-medium text-gray-500 uppercase tracking-wider">
                    <div className="flex flex-row items-center">
                      {column.render('Header')}

                      <span className="ml-2">
                        {column.isSorted ? (
                          column.isSortedDesc ? (
                            <>
                              <svg
                                xmlns="http://www.w3.org/2000/svg"
                                className="h-6 w-6"
                                fill="none"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                                strokeWidth={2}>
                                <path
                                  strokeLinecap="round"
                                  strokeLinejoin="round"
                                  d="M3 4h13M3 8h9m-9 4h9m5-4v12m0 0l-4-4m4 4l4-4"
                                />
                              </svg>
                            </>
                          ) : (
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              className="h-6 w-6"
                              fill="none"
                              viewBox="0 0 24 24"
                              stroke="currentColor"
                              strokeWidth={2}>
                              <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                d="M3 4h13M3 8h9m-9 4h6m4 0l4-4m0 0l4 4m-4-4v12"
                              />
                            </svg>
                          )
                        ) : (
                          ''
                        )}
                      </span>
                    </div>
                  </th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody
            {...getTableBodyProps()}
            className="bg-white divide-y divide-gray-200">
            {page.map((row, i) => {
              prepareRow(row);
              return (
                <tr
                  onClick={() =>
                    onRowClick(row.original.name, row.original.numIssues)
                  }
                  {...row.getRowProps()}
                  className=" border-2 border-b-slate-100 hover:cursor-pointer">
                  {row.cells.map((cell) => {
                    return (
                      <td
                        {...cell.getCellProps()}
                        className="px-6 py-4 whitespace-nowrap">
                        {cell.column.Cell.name === 'defaultRenderer' ? (
                          <div className="text-sm text-gray-500">
                            {cell.render('Cell')}
                          </div>
                        ) : (
                          cell.render('Cell')
                        )}
                      </td>
                    );
                  })}
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
      <div className="pagination flex flex-row justify-center items-center shadow-lg drop-shadow-lg">
        <button
          onClick={() => previousPage()}
          disabled={!canPreviousPage}
          className="hover:bg-gray-100 shadow drop-shadow-lg border border-gray-300 rounded-lg">
          <div className="flex h-10 w-10 text-gray-500 space-x-0">
            <ChevronLeftIcon className=""></ChevronLeftIcon>
          </div>
        </button>
        <div className="w-32 shadow drop-shadow-md rounded-lg">
          <input
            type="number"
            className="w-full h-full outline-none appearance-none text-center text-gray-500 rounded-lg "
            placeholder={`Page ${state.pageIndex + 1} of ${pageOptions.length}`}
            onChange={(e) => {
              gotoPage(parseInt(e.target.value) - 1);
            }}
            onBlur={(e) => {
              e.target.value = '';
            }}></input>
        </div>
        <button
          onClick={() => nextPage()}
          disabled={!canNextPage}
          className="hover:bg-gray-100 shadow drop-shadow-lg border border-gray-300 rounded-lg">
          <div className="flex h-10 w-10 text-gray-500 space-x-0">
            <ChevronRightIcon></ChevronRightIcon>
          </div>
        </button>
      </div>
    </>
  );
}

Table.propTypes = {
  columns: propType.array,
  data: propType.array,
  onRowClick: propType.func,
};

export default Table;
