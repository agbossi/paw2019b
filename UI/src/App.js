import './App.css';
import {BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import Home from './Components/Pages/Home'
import Navbar from "./Components/NavBar";
import AdminHome from "./Components/Pages/AdminHome";
import Locations from "./Components/Pages/Locations";
import Specialties from "./Components/Pages/Specialties";
import Clinics from "./Components/Pages/Clinics";
import Prepaids from "./Components/Pages/Prepaids";
import SignUp from "./Components/Pages/SignUp";
import ClinicPrepaids from './Components/Pages/ClinicPrepaids';
import Doctors from "./Components/Pages/Doctors";
import WrappedLogin from "./Components/Pages/Login";
import "../src/i18n/i18n"
import DoctorHome from "./Components/Pages/DoctorHome";
import DoctorClinics from "./Components/Pages/DoctorClinics";
import DoctorClinicSchedule from "./Components/Pages/DoctorClinicSchedule";
import UserDoctorProfile from "./Components/Pages/UserDoctorProfile";
import Favorites from "./Components/Pages/Favorites";
import Profile from "./Components/Pages/Profile";
import Appointments from "./Components/Pages/Appointments";

function App() {

    const isAuth = () => localStorage.getItem('role') !== null;

    const isAdmin = () => isAuth()? localStorage.getItem('role') === 'ROLE_ADMIN' : false;
    const isDoc = () => isAuth()? localStorage.getItem('role') === 'ROLE_DOCTOR' : false;
    const isUser = () => isAuth()? localStorage.getItem('role') === 'ROLE_USER' : false;

    function AdminRoute({ children }) {
        const auth = isAdmin();
        return auth ? children : <Navigate to="/login" />;
    }

    function DoctorRoute ({children}) {
        const auth = isDoc();
        return auth ? children : <Navigate to="/login" />;
    }

    function UserRoute ({children}) {
        const auth = isUser();
        return auth ? children : <Navigate to="/login" />;
    }

  return (
    <div className="App">
        <div className="App-header">
            <Router>
                <Navbar isAuth={isAuth}/>
                <Routes>
                    <Route path='/' exact element={<Home/>}/>
                    <Route path='/appointments' element={<UserRoute><Appointments user="patient" /></UserRoute>}/>
                    <Route path='/:license/profile' element={<UserDoctorProfile />}/>
                    <Route path="/doctor" element={<DoctorRoute><DoctorHome /></DoctorRoute>} />
                    <Route path="/doctor/clinics" element={<DoctorRoute><DoctorClinics/></DoctorRoute>} />
                    <Route path="/doctor/appointments" element={<DoctorRoute><Appointments user="doctor" /></DoctorRoute>} />
                    <Route path="/doctor/:license/clinics/:id/schedule"
                           element={<DoctorRoute><DoctorClinicSchedule /></DoctorRoute>} />
                    <Route path='admin/' exact element={<AdminRoute><AdminHome /></AdminRoute>}/>
                    <Route path='admin/locations' element={<AdminRoute><Locations /></AdminRoute>}/>
                    <Route path='admin/specialties' element={<AdminRoute><Specialties /></AdminRoute>}/>
                    <Route path='admin/clinics' element={<AdminRoute><Clinics /></AdminRoute>} />
                    <Route path='admin/prepaids' element={<AdminRoute><Prepaids /></AdminRoute>}/>
                    <Route path='admin/clinics/:id/prepaids' element={<AdminRoute><ClinicPrepaids/></AdminRoute>}/>
                    <Route path='admin/doctors' element={<AdminRoute><Doctors/></AdminRoute>}/>
                    <Route path='login' element={<WrappedLogin />}/>
                    <Route path='signUp' element={<SignUp />}/>
                    <Route path='favorites' element={<UserRoute><Favorites /></UserRoute>}/>
                    <Route path='profile' element={<UserRoute><Profile/></UserRoute>}/>
                </Routes>
            </Router>
        </div>
    </div>
  );
}

export default App;
