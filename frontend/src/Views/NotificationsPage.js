import React, { Fragment, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ToggleSwitch from '../Components/ToggleSwitch';
import BodyHeader from '../Components/BodyHeader';
import Modal from '../Components/Modal';

const NotificationsPage = () => {
  const [toggled, setToggled] = useState(false);
  const [isProjectSelected, setIsProjectSelected] = useState(false);

  // These values for the textboxes in topdown order
  const [val, setVal] = useState(0);
  const [val2, setVal2] = useState(0);
  const [val3, setVal3] = useState(0);
  const [val4, setVal4] = useState(0);
  const [val5, setVal5] = useState(0);

  return (
    <>
      <BodyHeader
        title={'Settings For Project'}
        subtext={'Adjust email preferences for specific projects'}
        showButton={true}
      />
      <br></br>
      {!isProjectSelected && (
        <>
          <p className="ml-7 text-rose-500 text-lg">No Project Selected!</p>
          <br></br>
        </>
      )}
      <div className="h-full w-99/100 border-solid border border-black block relative mb-1 mt-1 mr-7 ml-7 rounded-lg p-7">
        <div className="inline-block align-middle">
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
            label="toggle1"
          />
          <h1 className="inline text-2xl">
            Notify me if ticket status critical/unchanged after{' '}
          </h1>
          <input
            type="text"
            className="borer-solid border border-black w-16 rounded-sm"
            pattern="[0-9]*"
            value={val}
            onChange={(e) =>
              setVal((v) => (e.target.validity.valid ? e.target.value : v))
            }></input>
          <h1 className="inline text-2xl"> days </h1>
          <br></br>
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
            label="toggle2"
          />
          <h1 className="inline text-2xl">
            Notify me if sprint status unchanged after{' '}
          </h1>
          <input
            type="text"
            className="borer-solid border border-black w-16 rounded-sm"
            pattern="[0-9]*"
            value={val2}
            onChange={(e) =>
              setVal2((v) => (e.target.validity.valid ? e.target.value : v))
            }></input>
          <h1 className="inline text-2xl"> days </h1>
          <br></br>
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
            label="toggle3"
          />
          <h1 className="inline text-2xl">
            Notify me if ticket(s) unfinished at end of sprint{' '}
          </h1>
          <input
            type="text"
            className="borer-solid border border-black w-16 rounded-sm"
            pattern="[0-9]*"
            value={val3}
            onChange={(e) =>
              setVal3((v) => (e.target.validity.valid ? e.target.value : v))
            }></input>
          <h1 className="inline text-2xl"> days </h1>
          <br></br>
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
            label="toggle4"
          />
          <h1 className="inline text-2xl">
            Send me a project digest report every{' '}
          </h1>
          <input
            type="text"
            className="borer-solid border border-black w-16 rounded-sm"
            pattern="[0-9]*"
            value={val4}
            onChange={(e) =>
              setVal4((v) => (e.target.validity.valid ? e.target.value : v))
            }></input>
          <h1 className="inline text-2xl"> days </h1>
          <br></br>
          <ToggleSwitch
            onChange={(event) => setToggled(event.target.checked)}
            label="toggle5"
          />
          <h1 className="inline text-2xl">
            Send me a workload digest report every{' '}
          </h1>
          <input
            type="text"
            className="borer-solid border border-black w-16 rounded-sm"
            pattern="[0-9]*"
            value={val5}
            onChange={(e) =>
              setVal5((v) => (e.target.validity.valid ? e.target.value : v))
            }></input>
          <h1 className="inline text-2xl"> days </h1>
        </div>
      </div>
      <br></br>
    </>
  );
};

export default NotificationsPage;
