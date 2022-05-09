// This file makes HTTP requests to the Spring Boot backend for the Notifications page

// This is a GET request that makes a call to get the previous notification settings from the backend
// to properly display the correct values on the notifications page after a project is selected
export const getNotificationsFromApiAsync = async (request) => {
  try {
    const response = await fetch('api/v1/notification', {
      headers: {
        Accept: 'application/json',
        'Content-type': 'application/json',
      },
      body: JSON.stringify(request),
      mode: 'no-cors',
    });
    const json = await response.json();
    return json;
  } catch (error) {
    console.error(error);
  }
};

// POST request made after user clicks on 'Save Changes' button on Notifications page
// This sends the current settings to the backend to store data on the backend
export const sendSettingsDataToBackend = async (data) => {
  try {
    const response = await fetch('api/v1/notification', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-type': 'application/json',
      },
      body: JSON.stringify(data),
      mode: 'no-cors',
    });
    const json = await response.json();
    return json;
  } catch (error) {
    console.error(error);
  }
};
