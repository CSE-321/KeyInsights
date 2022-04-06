import React, { Fragment, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ToggleSwitch from '../Components/ToggleSwitch';
import BodyHeader from '../Components/BodyHeader';
import Dropdown from '../Components/Dropdown';

const projectList = ['BBX4', 'BBX8', 'ZB11'];

const NotificationsPage = () => {
  const [toggled, setToggled] = useState(false);

  return (
    <>
      <BodyHeader
        title={'Settings For Project'}
        subtext={'Adjust email preferences for specific projects'}
        showButton={true}
      />
      <br></br>
      <div className="h-full w-99/100 border-solid border border-black block relative mt-1 mr-7 ml-7 rounded-lg p-7">
        <div className="block">
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
          />
          <h1 className="text-2xl">
            Notify me if ticket status critical/unchanged after{' '}
          </h1>
          <br></br>
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
          />
          <h1 className="text-2xl">
            Notify me if sprint status unchanged after
          </h1>
          <br></br>
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
          />
          <h1 className="text-2xl">
            Notify me if ticket(s) unfinished at end of sprint{' '}
          </h1>
          <br></br>
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
          />
          <h1 className="text-2xl">Send me a project digest report every</h1>
          <br></br>
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
          />
          <h1 className="text-2xl">Send me a workload digest report every</h1>
        </div>
      </div>
      <br></br>
    </>
  );
};

export default NotificationsPage;
