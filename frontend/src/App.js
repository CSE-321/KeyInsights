import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';

import NavBar from './Components/NavBar';
import HomePage from './Views/HomePage';

function App() {
  return (
    <Router>
      <div className="App">
        <NavBar isUserSignedIn={false} />
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
