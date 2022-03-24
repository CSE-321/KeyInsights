import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';

import NavBar from './Components/NavBar';
import HomePage from './Views/HomePage';
import Footer from './Components/Footer';

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
      <Footer />
    </Router>
  );
}

export default App;
