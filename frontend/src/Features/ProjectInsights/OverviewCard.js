import React, { useMemo } from 'react';
import { ResponsiveLine } from '@nivo/line';
import { ResponsivePie } from '@nivo/pie';
import { ResponsiveBar } from '@nivo/bar';
import PropTypes from 'prop-types';
import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
const OverviewCard = ({
  cardTitle,
  cardText,
  graphData,
  graphType,
  graphKeys,
  forIndexBy,
}) => {
  const data = useMemo(() => graphData, [graphData]);

  return (
    <>
      <div className="bg-white shadow-lg drop-shadow-lg rounded-lg">
        <div className="grid grid-flow-row grid-cols-6 p-5">
          <div className="col-span-5">
            <h1 className="text-xl font-bold">{cardTitle}</h1>
          </div>
          <div className="col-span-1 text-gray-400 justify-self-end flex flex-row items-center">
            <span> See More</span>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              aria-hidden="true"
              role="img"
              width="30"
              height="30"
              preserveAspectRatio="xMidYMid meet"
              viewBox="0 0 16 16"
              className="flex-shrink-0">
              <path
                fill="none"
                stroke="#5DD39E"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="1.5"
                d="m8.75 3.25l4.5 4.5l-4.5 4.5m-6-4.5h10.5"
              />
            </svg>
          </div>
          <div className="col-span-2">{cardText}</div>
          <div className="col-span-4 w-full h-32 justify-self-center self-center">
            {graphType === 'Pie' && graphData.length != 0 && (
              <>
                {(
                  <ResponsivePie
                    data={data}
                    colors={['#F3A582', '#5DD39E']}
                    margin={{ top: 20, right: 20, bottom: 20, left: 20 }}
                    innerRadius={0.5}
                    arcLinkLabelsStraightLength={10}
                    arcLinkLabelsDiagonalLength={0}
                  />
                ) || <Skeleton circle={true} />}
              </>
            )}
            {graphType === 'Bar' && (
              <>
                <ResponsiveBar
                  data={data}
                  keys={graphKeys}
                  indexBy={forIndexBy}
                  margin={{ top: 20, right: 20, bottom: 20, left: 20 }}
                  padding={0.3}
                  colors={['#F3A582', '#5DD39E']}
                  borderRadius={5}
                  enableGridX={false}
                  enableGridY={false}
                  layout="vertical"
                  axisLeft={false}
                />
              </>
            )}
          </div>
        </div>
      </div>
    </>
  );
};

OverviewCard.propTypes = {
  cardTitle: PropTypes.string.isRequired,
  cardText: PropTypes.object.isRequired,
  graphType: PropTypes.string.isRequired,
  graphData: PropTypes.array,
  graphKeys: PropTypes.array,
  forIndexBy: PropTypes.string,
};

export default OverviewCard;
