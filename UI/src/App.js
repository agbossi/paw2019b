import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from './Components/Pages/Home'
import Navbar from "./Components/NavBar";
import AdminHome from "./Components/Pages/AdminHome";
import Locations from "./Components/Pages/Locations";
import Specialties from "./Components/Pages/Specialties";
import Clinics from "./Components/Pages/Clinics";

const navbarItems = []
const test = 'sarasa'

function App() {
  return (
    <div className="App">
        <div className="App-header">
            <Router>
                <Navbar items={navbarItems} test={test}/>
                <Routes>
                    <Route path='/' exact element={<AdminHome />}/>
                    <Route path='/locations' element={<Locations />}/>
                    <Route path='/specialties' element={<Specialties />}/>
                    <Route path='/clinics' element={<Clinics />} />
                </Routes>
            </Router>
        </div>
    </div>
  );
}

export default App;
