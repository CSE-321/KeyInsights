export function getData(projectName, endpoint) {
  var myHeaders = new Headers();
  projectName = projectName.split('=').pop();
  myHeaders.append('Content-Type', 'application/json');

  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow',
    mode: 'no-cors',
  };

  return fetch(`/api/v1/${endpoint}/${projectName}`, requestOptions)
    .then((response) => response.json())
    .catch((error) => console.log('error', error));
}
