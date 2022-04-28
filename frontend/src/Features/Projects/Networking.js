import { useRowState } from 'react-table';

export async function getAllProjects() {
  var requestOptions = {
    method: 'GET',
    redirect: 'follow',
    mode: 'no-cors',
  };

  return fetch('/api/v1/project', requestOptions)
    .then((response) => response.json())
    .then((data) => data)
    .catch((error) => console.log('error', error));
}
