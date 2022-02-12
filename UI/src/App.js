import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from './Components/Pages/Home'
import Navbar from "./Components/NavBar";
import AdminHome from "./Components/Pages/AdminHome";
import Locations from "./Components/Pages/Locations";
import Specialties from "./Components/Pages/Specialties";
import Clinics from "./Components/Pages/Clinics";
import Prepaids from "./Components/Pages/Prepaids";
import ClinicPrepaids from './Components/Pages/ClinicPrepaids';
import Doctors from "./Components/Pages/Doctors";
import userDoctorClinics from "./Components/Pages/UserDoctorClinics";
import WrappedLogin from "./Components/Pages/Login";
import { useTranslation } from "react-i18next";
import "../src/i18n/i18n"
import DoctorHome from "./Components/Pages/DoctorHome";
import DoctorClinics from "./Components/Pages/DoctorClinics";
import DoctorClinicSchedule from "./Components/Pages/DoctorClinicSchedule";

function App() {

    const isAuth = () => localStorage.getItem('role') !== null;

  return (
    <div className="App">
        <div className="App-header">
            <Router>
                <Navbar isAuth={isAuth}/>
                <Routes>
                    <Route path='/' exact element={<Home/>}/>
                    {/*<Route path='/:license/doctorClinics' element={<userDoctorClinics />}/>*/}
                    <Route path="/doctor" element={<DoctorHome />} />
                    <Route path="/doctor/clinics" element={<DoctorClinics/>} />
                    <Route path="/doctor/:license/clinics/:id/schedule" element={<DoctorClinicSchedule />} />
                    <Route path='admin/' exact element={<AdminHome />}/>
                    <Route path='admin/locations' element={<Locations />}/>
                    <Route path='admin/specialties' element={<Specialties />}/>
                    <Route path='admin/clinics' element={<Clinics />} />
                    <Route path='admin/prepaids' element={<Prepaids />}/>
                    <Route path='admin/clinics/:id/prepaids' element={<ClinicPrepaids/>}/>
                    <Route path='admin/doctors' element={<Doctors/>}/>
                    <Route path='login' element={<WrappedLogin />}/>
                </Routes>
            </Router>
        </div>
    </div>
  );
}

export default App;
