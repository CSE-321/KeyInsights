import React from 'react';

const getNotificationsFromApiAsync = async (request) => {
  try {
    const response = await fetch('api/v1/NotificationSettings', {
      headers: {
        Accept: 'application/json',
        'Content-type': 'application/json',
      },
      body: json.stringify(request),
      mode: 'no-cors',
    });
    const json = await response.json();
    return json;
  } catch (error) {
    console.error(error);
  }
};
