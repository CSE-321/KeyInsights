import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';

import NavBar from './Components/NavBar';
import HomePage from './Views/HomePage';
import ProjectsPage from './Views/ProjectsPage';
import ProjectInsightsPage from './Views/ProjectInsightsPage';
import Footer from './Components/Footer';
import NotificationsPage from './Views/NotificationsPage';
import { requireAuth } from './Features/Authentication/Networking';

function App() {
  return (
    <Router>
      <div className="App flex flex-col justify-between h-screen">
        <div className="PageBody ">
          <NavBar isUserSignedIn={false} />
          <Routes>
            <Route exact path="/" element={<HomePage />} />
            <Route
              exact
              path="/projects"
              element={<ProjectsPage />}
              onEnter={requireAuth}
            />
            <Route
              exact
              path="/projects/:id"
              element={<ProjectInsightsPage />}
              onEnter={requireAuth}
            />
            <Route
              exact
              path="/notifications"
              element={<NotificationsPage />}
              onEnter={requireAuth}
            />
          </Routes>
        </div>
        <div>
          <Footer />
        </div>
      </div>
    </Router>
  );
}

export default App;
