import { round, getProjectNameFromUrl } from './MathUtil';
export async function getKPI_1(projectName) {
  var myHeaders = new Headers();
  myHeaders.append('Content-Type', 'application/json');

  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow',
    mode: 'no-cors',
  };
  console.log(getProjectNameFromUrl(projectName));

  return fetch(
    `/api/v1/KPI1/${getProjectNameFromUrl(projectName)}`,
    requestOptions,
  )
    .then((response) => response.json())
    .catch((error) => console.log('error', error));
}

export function getPercentageJiraClosed(kpi1_List) {
  if (kpi1_List.length <= 0) {
    return 0;
  }
  var totalJira = kpi1_List[0].totalJiraCount;
  var totalJiraClosed = kpi1_List[0].closedJiraCount;
  console.log(totalJira);
  console.log(totalJiraClosed);
  return round((totalJiraClosed / totalJira) * 100, 2);
}

export function getTopTeamsByJiraClosed(kpi1_List) {
  var teamList = [];
  for (var i = 1; i < kpi1_List.length; i++) {
    var kpi = kpi1_List[i];
    teamList.push({
      team: kpi.teamType,
      closedJiraCount: kpi.closedJiraCount,
    });
  }

  teamList.sort((a, b) => {
    return b.closedJiraCount - a.closedJiraCount;
  });

  return teamList.slice(0, 3);
}

export function convertToGraphData(topTeamsList) {
  var graphData = [];

  topTeamsList.forEach((elem) => {
    graphData.push({
      teamType: elem.team,
      value: elem.closedJiraCount,
    });
  });

  return graphData;
}

export function convertToKeys(topTeamsList) {
  var keys = [];

  topTeamsList.forEach((elem) => {
    keys.push(elem.team);
  });

  return keys;
}

export function getTopJiraClosedTeam(kpi1_List) {
  if (kpi1_List.length <= 0) {
    return [
      {
        id: 'Total',
        label: 'Total Issues',
        value: 0,
        color: 'hsl(46, 70%, 50%)',
      },
      {
        id: 'Closed',
        label: 'Closed Issues',
        value: 0,
        color: 'hsl(56, 70%, 50%)',
      },
    ];
  }
  var totalJira = kpi1_List[0].totalJiraCount;
  var totalJiraClosed = kpi1_List[0].closedJiraCount;

  return [
    {
      id: 'Total',
      label: 'Total Issues',
      value: totalJira,
      color: 'hsl(46, 70%, 50%)',
    },
    {
      id: 'Closed',
      label: 'Closed Issues',
      value: totalJiraClosed,
      color: 'hsl(56, 70%, 50%)',
    },
  ];
}

export function getCriticalNotCompleted(KPI1_List) {
  var criticalNotCompleted = [];
  // KPI1_List.splice(0, 1);
  KPI1_List.forEach((kpi) => {
    criticalNotCompleted.push({
      team: kpi.teamType,
      percentageCriticalIssuesNotCompleted: round(
        kpi.percentageCriticalIssuesNotCompleted,
        2,
      ),
    });
  });

  criticalNotCompleted.sort((a, b) => {
    return (
      b.percentageCriticalIssuesNotCompleted -
      a.percentageCriticalIssuesNotCompleted
    );
  });

  return criticalNotCompleted.slice(0, 3);
}

export function convertToGraph(criticalNotCompletedList) {
  var graphData = [];

  criticalNotCompletedList.forEach((elem) => {
    graphData.push({
      teamType: elem.team,
      value: elem.percentageCriticalIssuesNotCompleted,
    });
  });

  return graphData;
}
