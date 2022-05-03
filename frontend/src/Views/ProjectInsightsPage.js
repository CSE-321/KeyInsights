import React, { useEffect } from 'react';
import { useParams } from 'react-router';
import BodyHeader from '../Components/BodyHeader';
import KpiNavBar from '../Features/ProjectInsights/KpiNavBar';
import OverviewBody from '../Features/ProjectInsights/OverviewBody';
const ProjectInsightsPage = () => {
  const [activeInsights, setActiveInsights] = React.useState(0);
  //const [kpi1_List, setKpi1_List] = React.useState([]);

  const onInsightsTypeChanged = (index) => {
    setActiveInsights(index);
  };

  let { name } = useParams();
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
        <div className="w-[80%] flex-grow-1">
          <p>{name}</p>
          {activeInsights === 0 && (
            <>
              <OverviewBody projectName={name} />
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProjectInsightsPage;
