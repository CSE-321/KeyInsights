import React, { useEffect, useRef } from 'react';
import PropType from 'prop-types';

const KpiNavBar = ({ completionHandler }) => {
  const barRef = useRef();
  const rowOneRef = useRef();
  const rowTwoRef = useRef();
  //const rowThreeRef = useRef();
  const rowFourRef = useRef();
  const rowFiveRef = useRef();

  const [rowSelectedForRef, setRowSelectedForRef] = React.useState(rowOneRef);
  const [barYOffset, setBarYOffset] = React.useState(0);

  const [barHeight, setBarHeight] = React.useState();
  const handleRowSelected = (rowRef) => {
    setRowSelectedForRef(rowRef);
  };

  useEffect(() => {
    const y =
      rowSelectedForRef.current.getBoundingClientRect().top -
      barRef.current.getBoundingClientRect().top;
    setBarYOffset(barYOffset + y);
    const height = rowSelectedForRef.current.getBoundingClientRect().height;
    //setBarHeight(height);
  }, [rowSelectedForRef]);

  return (
    <>
      <div className="flex flex-col justify-evenly items-start  bg-black w-full h-96 rounded-lg shadow-lg drop-shadow-lg text-white sm:text-lg md:text-xl lg:text-2xl ">
        <div
          className="flex flex-row items-center"
          ref={rowOneRef}
          onClick={() => {
            handleRowSelected(rowOneRef);
            completionHandler(0);
          }}>
          <div
            className={'bg-[#5DD39E] transition-all w-1 h-10'}
            style={{
              transform: `translateY(${barYOffset}px)`,
              height: barHeight,
            }}
            ref={barRef}></div>
          {rowSelectedForRef === rowOneRef ? (
            <>
              <h1 className="ml-5 text-[#5DD39E] cursor-pointer">Overview </h1>
            </>
          ) : (
            <>
              <h1 className="ml-5 hover:text-[#5DD39E] cursor-pointer">
                Overview{' '}
              </h1>
            </>
          )}
        </div>
        <div
          className="flex flex-row items-center"
          ref={rowTwoRef}
          onClick={() => {
            handleRowSelected(rowTwoRef);
            completionHandler(1);
          }}>
          {rowSelectedForRef === rowTwoRef ? (
            <>
              <h1 className="ml-5 text-[#5DD39E] cursor-pointer">
                Request Composition{' '}
              </h1>
            </>
          ) : (
            <>
              <h1 className="ml-5 hover:text-[#5DD39E] cursor-pointer">
                Request Composition{' '}
              </h1>
            </>
          )}
        </div>

        <div
          className="flex flex-row items-center"
          ref={rowFourRef}
          onClick={() => {
            handleRowSelected(rowFourRef);
            completionHandler(3);
          }}>
          {rowSelectedForRef === rowFourRef ? (
            <>
              <h1 className="ml-5 text-[#5DD39E] cursor-pointer">
                {' '}
                Resources{' '}
              </h1>
            </>
          ) : (
            <>
              <h1 className="ml-5 hover:text-[#5DD39E] cursor-pointer">
                Resources{' '}
              </h1>
            </>
          )}
        </div>
        <div
          className="flex flex-row items-center"
          ref={rowFiveRef}
          onClick={() => {
            handleRowSelected(rowFiveRef);
            completionHandler(4);
          }}>
          {rowSelectedForRef === rowFiveRef ? (
            <>
              <h1 className="ml-5 text-[#5DD39E] cursor-pointer">
                {' '}
                Requests Over Time{' '}
              </h1>
            </>
          ) : (
            <>
              <h1 className="ml-5 hover:text-[#5DD39E] cursor-pointer">
                Requests Over Time{' '}
              </h1>
            </>
          )}
        </div>
      </div>
    </>
  );
};

KpiNavBar.propTypes = {
  completionHandler: PropType.func.isRequired,
};

export default KpiNavBar;
