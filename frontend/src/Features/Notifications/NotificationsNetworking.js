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
