CREATE TABLE IF NOT EXISTS locations (
    name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS specialties (
    name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(9) PRIMARY KEY,
    firstName VARCHAR(20),
    lastName varchar(20),
    password VARCHAR(60),
    email VARCHAR(25),
);

CREATE TABLE IF NOT EXISTS doctors (
    license VARCHAR(20) PRIMARY KEY,
    specialty VARCHAR(30) REFERENCES specialties(name),
    doctorName VARCHAR(60),
    phoneNumber VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS clinics (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20),
    location VARCHAR(30) REFERENCES locations(name)
);

CREATE TABLE IF NOT EXISTS prepaids (
    name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS doctorclinics (
    doctorLicense VARCHAR(20) REFERENCES doctors(license),
    clinicid INTEGER REFERENCES clinics(id),
    consultPrice INTEGER,
    PRIMARY KEY(doctorLicense, clinicid)
);

CREATE TABLE IF NOT EXISTS schedule (
    day VARCHAR(15),
    hour VARCHAR(10),
    doctor VARCHAR(20),
    clinic INTEGER,
    PRIMARY KEY (day, hour, doctor),
    FOREIGN KEY (doctor, clinic) REFERENCES doctorclinics(doctorLicense, clinicid)
);