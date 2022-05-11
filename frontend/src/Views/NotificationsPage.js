import React, { Fragment, useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import ToggleSwitch from '../Components/ToggleSwitch';
import BodyHeader from '../Components/BodyHeader';
import Modal from '../Components/Modal';
import DropDown from '../Components/Dropdown';
import { getAllProjects } from '../Features/Projects/Networking';
import {
  getNotificationsFromApiAsync,
  sendSettingsDataToBackend,
} from '../Features/Notifications/NotificationsNetworking';

const NotificationsPage = () => {
  // Values for toggle switches in topdown order
  const [toggled, setToggled] = useState(false);
  const [toggled2, setToggled2] = useState(false);
  const [toggled3, setToggled3] = useState(false);
  const [toggled4, setToggled4] = useState(false);
  const [toggled5, setToggled5] = useState(false);

  // These values for the textboxes in topdown order
  const [val, setVal] = useState(0);
  const [val2, setVal2] = useState(0);
  // Value 3 is skipped as it is just a toggle switch
  const [val4, setVal4] = useState(0);
  const [val5, setVal5] = useState(0);

  // Determine if a project is selected
  const [isProjectSelected, setIsProjectSelected] = useState(false);

  // Stores name of selected project
  const [project, setProject] = useState('');

  // List of all projects on server
  const [projects, setProjects] = useState([]);

  // Determine if modal should be displayed or not
  const [modalOn, setModalOn] = useState(false);

  // Determine if changes have been made to settings
  const [isSettingsChanged, setIsSettingsChanged] = useState(false);

  // List of projects for testing
  useEffect(() => {
    getAllProjects().then((data) => {
      console.log(data);
      setProjects(data);
    });
  }, []);

  // Variable to hold current user
  const user = useSelector((state) => state.user.user);

  // Variabble to hold current server
  const server =
    'http://jira.cloud-stm.com:8080/rest/api/2/user?username=ucm-cse-321';
  //const server = useSelector((state) => state.server.name);

  const dropDownOptions = [
    { value: 14, label: '2 Weeks' },
    { value: 30, label: '1 Month' },
  ];

  // Create JSON object for backend
  const createJSON = () => {
    let obj = {
      userId: user,
      serverId: server,
      projectId: project,
      ticketStatusSetting: {
        notifyUser: toggled,
        notificationFrequency: val,
      },
      sprintStatusSetting: {
        notifyUser: toggled2,
        notificationFrequency: val2,
      },
      unfinishedTicketSetting: {
        notifyUser: toggled3,
      },
      projectDigestReportSetting: {
        notifyUser: toggled4,
        notificationFrequency: val4,
      },
      workloadDigestReportSetting: {
        notifyUser: toggled5,
        notificationFrequency: val5,
      },
    };

    return obj;
  };

  // Change toggle switches and textbox values after project is selected
  // This will read in value from the backend to show previous notification settings
  const setDefaultValues = () => {
    getNotificationsFromApiAsync().then((data) => {
      const settings = data[0];
      for (const setting in settings) {
        if (setting === 'ticketStatusSetting') {
          let temp = settings[setting];
          setToggled(temp['notifyUser']);
          setVal(temp['notifyFrequency']);
        } else if (setting === 'sprintStatusSetting') {
          let temp = settings[setting];
          setToggled2(temp['notifyUser']);
          setVal2(temp['notifyFrequency']);
        } else if (setting === 'unfinishedTicketSetting') {
          let temp = settings[setting];
          setToggled3(temp['notifyUser']);
        } else if (setting === 'projectDigestReportSetting') {
          let temp = settings[setting];
          setToggled4(temp['notifyUser']);
          setVal4(temp['notifyFrequency']);
        } else if (setting === 'workloadDigestReportSetting') {
          let temp = settings[setting];
          setToggled5(temp['notifyUser']);
          setVal5(temp['notifyFrequency']);
        }
      }
    });
  };

  return (
    <>
      <BodyHeader
        title={'Settings For Project'}
        subtext={'Adjust email preferences for specific projects'}
        showButton={true}
        setModalOn={setModalOn}
      />
      <br></br>
      <div className="flex w-full overflow-x-hidden m-0 p-0 mb-5 gap-x-[87px] sm:gap-x-[175px] md:gap-x-[235px] lg:gap-x-[465px]">
        {/* Show Error Message if Project not selected or show Project Name */}
        {!isProjectSelected && (
          <>
            <p className="ml-7 mt-2 inline w-[217px] text-rose-500 text-md sm:text-lg md:text-xl lg:text-2xl">
              No Project Selected!
            </p>
            <br></br>
          </>
        )}
        {isProjectSelected && (
          <>
            <p className="ml-7 mt-2 text-md sm:text-lg md:text-xl lg:text-2xl w-[215px]">
              {project}
            </p>
            <br></br>
          </>
        )}
        {isSettingsChanged && (
          <>
            <button
              className="static rounded-lg bg-primary-purple text-white h-10 w-32 text-xs sm:w-32 sm:h-12 md:h-12 md:w-64 lg:h-12 lg:w-60 sm:text-sm md:text-md lg:text-lg"
              onClick={() => {
                sendSettingsDataToBackend(createJSON());
                setIsSettingsChanged(false);
              }}>
              {' '}
              Save Changes
            </button>
          </>
        )}
        {!isSettingsChanged && (
          <>
            <button
              className="static opacity-50 rounded-lg bg-primary-purple text-white h-10 w-32 text-xs sm:w-32 sm:h-12 md:h-12 md:w-64 lg:h-12 lg:w-60 sm:text-sm md:text-md lg:text-lg"
              disabled={true}>
              {' '}
              Save Changes
            </button>
          </>
        )}
      </div>
      <div className="h-90/100 w-99/100 border-solid border border-black block relative mr-7 ml-7 rounded-lg p-6 pr-0">
        <div className="inline-block align-middle">
          {/* First Notification Option */}
          <ToggleSwitch
            onChange={(event) => {
              setToggled(event.target.checked);
              setIsSettingsChanged(true);
            }}
            label="toggle1"
            checked={toggled}
            isProjectSelected={isProjectSelected}
          />
          <h1 className="inline text-md sm:text-lg md:text-xl lg:text-2xl">
            Notify me if ticket status critical/unchanged after{' '}
          </h1>
          <input
            type="text"
            className="borer-solid border border-black w-4 rounded-sm sm:w-8 md:w-12 lg:w-16"
            disabled={!toggled}
            pattern="[0-9]*"
            value={val}
            onChange={(e) => {
              setVal((v) => (e.target.validity.valid ? e.target.value : v));
              setIsSettingsChanged(true);
            }}></input>
          <h1 className="inline text-md sm:text-lg md:text-xl lg:text-2xl">
            {' '}
            days.{' '}
          </h1>
          <br></br>

          {/* Second Notification Option */}
          <ToggleSwitch
            onChange={(event) => {
              setToggled2(event.target.checked);
              setIsSettingsChanged(true);
            }}
            label="toggle2"
            checked={toggled2}
            isProjectSelected={isProjectSelected}
          />
          <h1 className="inline text-md sm:text-lg md:text-xl lg:text-2xl">
            Notify me if sprint status unchanged after{' '}
          </h1>
          <input
            type="text"
            className="borer-solid border border-black w-4 rounded-sm sm:w-8 md:w-12 lg:w-16"
            disabled={!toggled2}
            pattern="[0-9]*"
            value={val2}
            onChange={(e) => {
              setVal2((v) => (e.target.validity.valid ? e.target.value : v));
              setIsSettingsChanged(true);
            }}></input>

          <h1 className="inline text-md sm:text-lg md:text-xl lg:text-2xl">
            {' '}
            days.{' '}
          </h1>
          <br></br>

          {/* Third Notification Option */}
          <ToggleSwitch
            onChange={(event) => {
              setToggled3(event.target.checked);
              setIsSettingsChanged(true);
            }}
            label="toggle3"
            checked={toggled3}
            isProjectSelected={isProjectSelected}
          />

          <h1 className="inline text-md sm:text-lg md:text-xl lg:text-2xl">
            Notify me if ticket(s) unfinished at end of sprint.{' '}
          </h1>
          <br></br>

          {/* Fourth Notification Option */}
          <ToggleSwitch
            onChange={(event) => {
              setToggled4(event.target.checked);
              setIsSettingsChanged(true);
            }}
            label="toggle4"
            checked={toggled4}
            isProjectSelected={isProjectSelected}
          />
          <h1 className="inline text-md sm:text-lg md:text-xl lg:text-2xl">
            Send me a project digest report every{' '}
          </h1>
          <input
            type="text"
            className="borer-solid border border-black w-4 rounded-sm sm:w-8 md:w-12 lg:w-16"
            disabled={!toggled4}
            pattern="[0-9]*"
            value={val4}
            onChange={(e) => {
              setVal4((v) => (e.target.validity.valid ? e.target.value : v));
              setIsProjectSelected(true);
            }}></input>

          <h1 className="inline text-md sm:text-lg md:text-xl lg:text-2xl">
            {' '}
            days.{' '}
          </h1>
          <br></br>

          {/* Fifth Notification Option */}
          <ToggleSwitch
            onChange={(event) => {
              setToggled5(event.target.checked);
              setIsSettingsChanged(true);
            }}
            label="toggle5"
            checked={toggled5}
            isProjectSelected={isProjectSelected}
          />
          <h1 className="inline text-md sm:text-lg md:text-xl lg:text-2xl">
            Send me a workload digest report every{' '}
          </h1>
          <input
            type="text"
            className="borer-solid border border-black rounded-sm w-4 sm:w-8 md:w-12 lg:w-16"
            disabled={!toggled5}
            pattern="[0-9]*"
            value={val5}
            onChange={(e) => {
              setVal5((v) => (e.target.validity.valid ? e.target.value : v));
              setIsProjectSelected(true);
            }}></input>
          <h1 className="inline text-md sm:text-lg md:text-xl lg:text-2xl">
            {' '}
            days.{' '}
          </h1>
        </div>
      </div>
      <br></br>
      {/* Displays modal only if modalOn is set to true which happens when Select Project button is clicked */}
      {modalOn && (
        <Modal
          setModalOn={setModalOn}
          setProject={setProject}
          setIsProjectSelected={setIsProjectSelected}
          listOfProjects={projects}
          setDefaultValues={setDefaultValues}
        />
      )}
    </>
  );
};

export default NotificationsPage;
