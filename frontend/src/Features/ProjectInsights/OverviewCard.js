import React, { useMemo, useEffect } from 'react';
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
  const [isLoading, setIsLoading] = React.useState(false);

  useEffect(() => {
    if (data) {
      setIsLoading(true);
    }
  }, []);
  return (
    <>
      <div className="bg-white shadow-lg drop-shadow-lg rounded-lg">
        <div className="grid grid-flow-row grid-cols-6 p-5">
          <div className="col-span-5">
            <h1 className="text-xl font-bold">{cardTitle}</h1>
          </div>
          <div className="col-span-1 text-gray-400 justify-self-end flex flex-row items-center"></div>
          <div className="col-span-2">{cardText}</div>
          <div className="col-span-4 w-full h-32 justify-self-center self-center">
            {graphType === 'Pie' && (
              <ResponsivePie
                data={data}
                colors={['#F3A582', '#5DD39E']}
                margin={{ top: 20, right: 20, bottom: 20, left: 20 }}
                innerRadius={0.5}
                arcLinkLabelsStraightLength={10}
                arcLinkLabelsDiagonalLength={0}
              />
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
