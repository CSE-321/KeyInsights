import React , { useState, useEffect} from 'react';
import '../CSS/BodyHeader.css';


const BodyHeader = ({title, subtext}) => {

        const [headerTitle, setHeaderTitle] = React.useState(title);
        const [headerSubtext, setHeaderSubtext] = React.useState(subtext);

        React.useEffect(() => {
            setHeaderTitle(title);
            setHeaderSubtext(subtext);
        }, [headerTitle, headerSubtext]);

        return(
            <>
                <div className="body-header">
                    <div id='header-description'>
                        <h1 className='header-text'> {headerTitle} </h1>
                        <p className='header-text'> {headerSubtext} </p>
                    </div>
                </div>
            </>
        );
        
}

export default BodyHeader;