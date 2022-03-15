import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import './App.css';

import HomePage from './Views/HomePage';

function App() {
  return (
    <Router>
      <div className="App">
        
      </div>

      <div className="PageBody">
        <Routes>
          <Route path="/" element={<HomePage />} />
        </Routes>

      </div>
    </Router>
    
  );
}



export default App;
