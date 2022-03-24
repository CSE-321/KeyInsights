import React from 'react';
import '../CSS/Dropdown.css';

const Dropdown = (serverList) => {
    const [serverListing, setServerListing] = React.useState(serverList);


    return(
        <>
            <div className="dropdown">
                    <button className="dropdown-button">
                        
                        <div className="dropdown-inner-button">
                            <p className="dropdown-text">ABCDEFGHIJKL</p>
                            <div className='dropdown-icon button-arrow down'> </div>
                        </div>

                        
                    </button>
                    <div className="dropdown-content">
                        <ul className='dropdown-list'>
                            <li className='dropdown-list-item'> 
                                <a href="#">Link 1</a>
                            </li>
                            <li className='dropdown-list-item'> 
                                <a href="#">Link 1</a>
                            </li>
                            <li className='dropdown-list-item'> 
                                <a href="#">Link 1</a>
                            </li>
                        </ul>
                    </div>
            
                
                
                
            </div>
            
        </>
    );
}

export default Dropdown;