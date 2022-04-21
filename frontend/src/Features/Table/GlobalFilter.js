/* eslint-disable react/jsx-key */
import React from 'react';
import propType from 'prop-types';
import { useAsyncDebounce } from 'react-table'; // new

/** Takes in a react state for filters and returns a new state based of the new filters
 *
 * @param {*} preGlobalFilteredRows
 * @param {*} setGlobalFilter
 * @param {*} globalFilter
 * @returns
 */
const GlobalFilter = ({
  preGlobalFilteredRows,
  globalFilter,
  setGlobalFilter,
}) => {
  const count = preGlobalFilteredRows.length;
  const [value, setValue] = React.useState(globalFilter);
  const onChange = useAsyncDebounce((value) => {
    setGlobalFilter(value || undefined);
  }, 200);

  return (
    <span className="">
      <input
        className="w-full h-10 rounded-lg drop-shadow-md"
        value={value || ''}
        onChange={(e) => {
          setValue(e.target.value);
          onChange(e.target.value);
        }}
        placeholder={`${count} Projects...`}
      />
    </span>
  );
};

GlobalFilter.propTypes = {
  preGlobalFilteredRows: propType.array,
  globalFilter: propType.string,
  setGlobalFilter: propType.func,
};

export default GlobalFilter;
