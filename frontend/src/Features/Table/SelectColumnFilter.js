import React from 'react';
import propType from 'prop-types';

/** Creates a Filter component from a column and sets the passed in filter
 *
 * @param {*} filterValue
 * @param {*} preFilteredRows
 * @param {*} setFilter
 * @param {*} id
 * @returns
 */
function SelectColumnFilter({
  column: { filterValue, setFilter, preFilteredRows, id },
}) {
  // Calculate the options for filtering
  // using the preFilteredRows
  const options = React.useMemo(() => {
    const options = new Set();
    preFilteredRows.forEach((row) => {
      options.add(row.values[id]);
    });
    return [...options.values()];
  }, [id, preFilteredRows]);

  // Render a multi-select box
  return (
    <select
      name={id}
      id={id}
      value={filterValue}
      onChange={(e) => {
        setFilter(e.target.value || undefined);
      }}>
      <option value="">All</option>
      {options.map((option, i) => (
        <option key={i} value={option}>
          {option}
        </option>
      ))}
    </select>
  );
}

SelectColumnFilter.propTypes = {
  column: propType.object,
};

export default SelectColumnFilter;
