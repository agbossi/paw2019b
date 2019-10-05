CREATE TABLE IF NOT EXISTS locations (
    name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS specialties (
    name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS users (
    firstName VARCHAR(20),
    lastName varchar(20),
    password VARCHAR(60),
    email VARCHAR(25) primary key
);

CREATE TABLE IF NOT EXISTS doctors (
    license VARCHAR(20) PRIMARY KEY,
    specialty VARCHAR(50) REFERENCES specialties(name),
    email VARCHAR(25) references users(email),
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
    day INTEGER,
    hour INTEGER,
    doctor VARCHAR(20),
    clinic INTEGER,
    PRIMARY KEY (day, hour, doctor),
    FOREIGN KEY (doctor, clinic) REFERENCES doctorclinics(doctorLicense, clinicid)
);

CREATE TABLE IF NOT EXISTS patients (
    email VARCHAR(25) PRIMARY KEY REFERENCES users(email),
    id varchar(8),
    prepaid VARCHAR(20),
    prepaidNumber varchar(20)
);

CREATE TABLE IF NOT EXISTS appointments (
    doctor VARCHAR(20),
    clinic INTEGER,
    patient VARCHAR(25) REFERENCES patients(email),
    date TIMESTAMP,
    PRIMARY KEY (doctor, clinic, date),
    FOREIGN KEY (doctor, clinic) REFERENCES doctorclinics(doctorLicense, clinicid)
);

insert into users values('admin','admin','admin','admin@test.com') on conflict do nothing;
