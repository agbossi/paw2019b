
const handleUnAuth = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('role')
    localStorage.removeItem('license')
    localStorage.removeItem('firstName')
    localStorage.removeItem('email')
    localStorage.removeItem('lastName')
    localStorage.removeItem('specialty')
    localStorage.removeItem('phone')
    window.location.replace("http://pawserver.it.itba.edu.ar/paw-2019b-4/login")
}

export default {
    handleUnAuth
}