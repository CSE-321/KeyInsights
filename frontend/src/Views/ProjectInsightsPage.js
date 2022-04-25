import React from 'react';
import { useParams } from 'react-router';
import BodyHeader from '../Components/BodyHeader';
import KpiNavBar from '../Features/ProjectInsights/KpiNavBar';
import OverviewCard from '../Features/ProjectInsights/OverviewCard';
const ProjectInsightsPage = () => {
  const [activeInsights, setActiveInsights] = React.useState(0);
  const onInsightsTypeChanged = (index) => {
    setActiveInsights(index);
  };

  let { id } = useParams();
  return (
    <div>
      <BodyHeader
        title="Project Insights Page"
        subtext="View keyinsights for project"
        showButton={false}
      />
      <div className="flex flex-col p-5 md:flex-row md:space-x-5">
        <div className="w-85v md:w-20v flex-grow-0">
          <KpiNavBar completionHandler={onInsightsTypeChanged} />
        </div>
        <div className="w-full flex-grow-1">
          {activeInsights === 0 && (
            <>
              <h1 className="text-lg font-medium text-black md:text-xl lg:text-2xl">
                Quick Key Insights
              </h1>

              <div className="w-full h-[1px] bg-gray-300 rounded-xl"></div>
              <p>Project ID: {id}</p>
              <div
                id="Insights Main Area"
                className="grid grid-cols-6 grid-flow-row">
                <div className="col-start-4 col-end-6">
                  <OverviewCard></OverviewCard>
                </div>
              </div>
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProjectInsightsPage;
