import React from 'react';
import { render, screen, cleanup } from '@testing-library/react';
import NotificationsPage from '../Views/NotificationsPage';

afterEach(() => {
  cleanup();
});

test('Render only 5 toggle switches in Notifications Page', () => {
  render(<NotificationsPage />);
  const toggle1 = screen.queryByTestId('toggle1');
  const toggle2 = screen.queryByTestId('toggle2');
  const toggle3 = screen.queryByTestId('toggle3');
  const toggle4 = screen.queryByTestId('toggle4');
  const toggle5 = screen.queryByTestId('toggle5');
  const toggle6 = screen.queryByTestId('toggle6');

  expect(toggle1).toBeInTheDocument();
  expect(toggle2).toBeInTheDocument();
  expect(toggle3).toBeInTheDocument();
  expect(toggle4).toBeInTheDocument();
  expect(toggle5).toBeInTheDocument();
  expect(toggle6).not.toBeInTheDocument();
});

test('No Project Selected is default text', () => {
  render(<NotificationsPage />);
  expect(screen.queryByTestId('noProjectSelected')).toBeInTheDocument();
  expect(screen.queryByTestId('B8X4')).not.toBeInTheDocument();
  expect(screen.queryByTestId('CSE321')).not.toBeInTheDocument();
});

test('Modal not shown at default', () => {
  render(<NotificationsPage />);
  expect(screen.queryByTestId('modal')).not.toBeInTheDocument();
});
