import React from 'react';

const getNotificationsFromApiAsync = async () => {
  try {
    const response = await fetch('api/v1/NotificationSettings');
  } catch (error) {
    console.error(error);
  }
};
