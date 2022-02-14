export const TRANSLATION_EN = {
    NAVBAR : {
        favourites: "Favorites",
        appointments: "Appointments",
        profile: "Profile",
        logout: "Logout",
        login: "Login",
        signUp: "SignUp",
        clinics: "Clinics"
    },
    USER: {
        seeProfile: "See Profile",
        dataProfile: "Doctor's Information",
        emptyFavorites: "No favorite doctors yet"
    },
    ADMIN: {
        doctors: "Doctors",
        seeDocs: "See doctors",
        clinics: "Clinics",
        seeClinics: "See clinics",
        locations: "Locations",
        seeLocations: "See locations",
        specialties: "Specialties",
        seeSpecialties: "See specialties",
        prepaids: "Prepaids",
        seePrepaids: "See prepaids",
        location: "Location",
        specialty: "Specialty",
        prepaid: "Prepaid"
    },
    DOC: {
        license: "License",
        addDoc: "Add doctor",
        price: "Price",
        subscribeClinic: "Subscribe to Clinic",
        editPrice: "Edit Price",
        image: "Image"
    },
    CLINIC: {
        addClinic: "Add Clinic",
        clinic: "Clinic",
        addPrepaid: "Add Prepaid to Clinic",
        edit: "Edit Profile"
    },
    FORM: {
        firstName: "First Name",
        enterFirstName: "Enter first name",
        lastName: "Last Name",
        enterLastName: "Enter last name",
        document: "Document",
        enterDocument: "Enter document",
        specialty: "Specialty: ",
        selectSpecialty: "Select specialty",
        enterLicense: "Enter license",
        phoneNumber: "Phone Number",
        enterPhone: "Enter phone number",
        email: "Email",
        enterEmail: "Enter email",
        password: "Password",
        enterPassword: "Enter Password",
        repeatPassword: "Repeat Password",
        prepaid: "Prepaid",
        prepaidNumber: "Prepaid number",
        enterPrepaidNumber: "enter prepaid number",
        name: "Name",
        enterName: "Enter name",
        address: "Address",
        enterAddress: "Enter address",
        location: "Location: ",
        selectLocation: "Select location",
        selectPrepaid: "Select prepaid",
        selectClinic: "Select clinic",
        enterPrice: "Enter price",
        day: "Day: ",
        hour: "Hour: ",
        schedule: "Schedule",
        daySelect: "Select day",
        hourSelect: "Select hour",
        none: "None",
        signUp: "Sign up",
        maxPrice: "Maximum Consult Price",
        dateTime: "Date and Time",
        selectDateTime: "Select date and time"
    },
    errors: {
        licenseInUse: "Licence already registered",
        emailInUse: "Email already registered",
        specialtyNotFound: "Specialty chosen does not exist",
        passwordMismatch: "Passwords where mismatched",
        doctorNotFound: "No doctor found to delete",
        locationNotFound: "Location chosen does not exist",
        clinicExists: "Clinic already exists",
        clinicNotFoundEdit: "No clinic found to edit",
        clinicNotFoundDelete: "No clinic found to delete",
        locationNotFoundDelete: "No location found to delete",
        clinicDependency: "Could not delete location: One or more clinics are still in this location",
        locationExists: "Location already exists",
        specialtyNotFoundDelete: "No specialty found to delete",
        doctorDependency: "Could not delete specialty: One or more doctors still have this specialty",
        specialtyExists: "Specialty already exists",
        prepaidNotFoundDelete: "No prepaid found to delete",
        prepaidExists: "Prepaid already exists",
        clinicNotFound: "Clinic does not exist",
        prepaidNotFound: "Prepaid chosen does not exist",
        clinicPrepaidNotFoundDelete: "No prepaid in clinic found to delete",
        docLoggedNotFound: "No logged in doctor found",
        doctorNotFoundEdit: "No doctor found to edit",
        noLoggedDoc: "No logged email found, try logging out and logging in again",
        noDocEmail: "No doctor found with logged email",
        required: "This field is required",
        numeric: "This field only allows numbers",
        passwordTooShort: "Password is too short",
        alphabetic: "This field only allows letters",
        invalidEmail: "Not a valid email",
        invalidDocumentLength: "document length must be 8 digits",
        prepaidNumberTooLong: "Prepaid number too long",
        docClinicNotFound: "No doctor in clinic found",
        imageBroken: "Image or image data missing, try uploading the image again",
        notSupported: "Image type not supported. Supported types: JPEG, JPG, PNG",
        scheduleExists: "You already have scheduled this time and hour for this or other clinic",
        scheduleNotFoundDelete: "No schedule found to delete",
        scheduleOtherClinicDelete: "The schedule to delete belongs to another clinic",
        selectTime: "Select clinic, date and time to make an appointment",
        datePast: "Selected date is in the past, select a future date",
        outOfSchedule: "Selected date is out of doctor's schedule",
        appointmentExists: "The selected date already has an appointment, select another date"
    },
    actions: {
        add: "Add",
        edit: "Edit",
        upload: "Upload",
        delete: "Delete",
        makeApp: "Make Appointment"
    },
    CAL : {
        hour: "Hour",
        mon: "Monday",
        tue: "Tuesday",
        wed: "Wednesday",
        thu: "Thursday",
        fri: "Friday",
        sat: "Saturday",
        sun: "Sunday",
        jan: "January",
        feb: "February",
        mar: "March",
        apr: "April",
        may: "May",
        jun: "June",
        jul: "July",
        aug: "August",
        sep: "September",
        oct: "October",
        nov: "November",
        dec: "December"
    },
    deleteButton: "Delete",
    prevButton: "Prev",
    nextButton: "Next",
    closeButton: "Close",
    fieldRequired: "Field Required",
    scheduleButton: "Schedule",
    welcome: "Welcome",
    editProfileButton: "Edit Profile",
    changeImgButton: "Change Image",
    deleteImgButton: "Delete Image",
}