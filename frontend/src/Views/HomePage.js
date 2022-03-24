import React from 'react';
import BodyHeader from '../Components/BodyHeader';
const HomePage = () => {
    const styling = {
        color: 'white'
    }
    return(
       <>
              <BodyHeader title={"Home"} subtext={"Welcome home, lets get you started"} showServer={false}>
                  <h1 style={styling}></h1>
              </BodyHeader>
       </>
    );
    
    
};

export default HomePage;
