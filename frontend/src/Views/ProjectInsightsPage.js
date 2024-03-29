import React, { useEffect } from 'react';
import { useParams } from 'react-router';
import BodyHeader from '../Components/BodyHeader';
import KpiNavBar from '../Features/ProjectInsights/KpiNavBar';
import OverviewBody from '../Features/ProjectInsights/OverviewBody';
import ResourcesBody from '../Features/ProjectInsights/ResourcesBody';
import RequestComposition from '../Features/ProjectInsights/RequestComposition';
import RequestOverTime from '../Features/ProjectInsights/RequestOverTime';
import PropTypes from 'prop-types';
import { getProjectNameFromUrl } from '../Features/ProjectInsights/MathUtil';

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
        title={`Project Insights - ${getProjectNameFromUrl(name)}`}
        subtext="View key insights for the current project"
        showButton={false}
      />
      <div className="ml-5 mt-5">
        <p className="text-gray-600">
          <a href="/">Home</a>
          <a href="/projects">/Projects</a>
          <a className="text-black">/{name.split('=').pop()}</a>
        </p>
      </div>
      <div className="flex flex-col p-5 md:flex-row md:space-x-5">
        <div className="w-85v md:w-20v flex-grow-0">
          <KpiNavBar completionHandler={onInsightsTypeChanged} />
        </div>
        <div className="w-[80%] flex-grow-1">
          {activeInsights === 0 && (
            <>
              <OverviewBody projectName={name} />
            </>
          )}
          {activeInsights === 1 && (
            <>
              <RequestComposition projectName={name} />
            </>
          )}
          {activeInsights === 3 && (
            <>
              <ResourcesBody projectName={name} />
            </>
          )}
          {activeInsights === 4 && (
            <>
              <RequestOverTime projectName={name} />
            </>
          )}
        </div>
      </div>
    </div>
  );
};

ProjectInsightsPage.propTypes = {};

export default ProjectInsightsPage;
