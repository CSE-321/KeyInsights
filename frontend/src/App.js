import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';

import NavBar from './Components/NavBar';
import HomePage from './Views/HomePage';
import ProjectsPage from './Views/ProjectsPage';
import ProjectInsightsPage from './Views/ProjectInsightsPage';
import Footer from './Components/Footer';

function App() {
  return (
    <Router>
      <div className="App">
        <NavBar isUserSignedIn={false} />
      </div>

      <div className="PageBody">
        <Routes>
          <Route exact path="/" element={<HomePage />} />
          <Route exact path="/projects" element={<ProjectsPage />} />
          <Route exact path="/projects/:id" element={<ProjectInsightsPage />} />
        </Routes>
      </div>
      <Footer />
    </Router>
  );
}

export default App;
