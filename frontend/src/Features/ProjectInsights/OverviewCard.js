import React, { useMemo } from 'react';
import { ResponsiveLine } from '@nivo/line';
import { ResponsivePie } from '@nivo/pie';

const OverviewCard = () => {
  const [title, setTitle] = React.useState('What is Lorem Ipsum?');
  const [subtext, setSubtext] = React.useState(
    'View keyinsights for projectssssssssssssssssssssssssss',
  );
  const [priority, setPriority] = React.useState(false);

  const data = useMemo(() => [
    {
      id: 'Total',
      label: 'java',
      value: 100,
      color: 'hsl(90, 70%, 50%)',
    },
    {
      id: 'Closed',
      label: 'Closed Issues',
      value: 67,
      color: 'hsl(56, 70%, 50%)',
    },
  ]);

  return (
    <>
      <div className="bg-white shadow-lg drop-shadow-lg rounded-lg">
        <div className="grid grid-flow-row grid-cols-6 p-5">
          <div className="col-span-5">
            <h1 className="text-xl font-bold"> Issue Completion </h1>
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
          <div className="col-span-3">
            <p>
              The current ticket completion status. An increase of{' '}
              <span className="text-emerald-500">+10%</span> since last week.
            </p>
          </div>
          <div className="col-span-3 w-full h-32 justify-self-center self-center">
            <ResponsivePie
              data={data}
              margin={{ top: 20, right: 20, bottom: 20, left: 20 }}
              innerRadius={0.5}
              arcLinkLabelsStraightLength={10}
              arcLinkLabelsDiagonalLength={0}
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default OverviewCard;
