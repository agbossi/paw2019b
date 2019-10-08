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
    email VARCHAR(25) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS doctors (
    license VARCHAR(20) PRIMARY KEY,
    specialty VARCHAR(50) REFERENCES specialties(name) ON UPDATE CASCADE ON DELETE SET NULL,
    email VARCHAR(25) REFERENCES users(email) ON UPDATE CASCADE ON DELETE CASCADE,
    phoneNumber VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS clinics (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(20),
    location VARCHAR(30) REFERENCES locations(name) ON UPDATE CASCADE ON DELETE SET NULL,
    address VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS prepaids (
    name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS clinicPrepaids(
    clinicid INTEGER REFERENCES clinics(id) ON UPDATE CASCADE ON DELETE CASCADE,
    prepaid VARCHAR(30) REFERENCES prepaids ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS doctorclinics (
    doctorLicense VARCHAR(20) REFERENCES doctors(license) ON UPDATE CASCADE ON DELETE CASCADE,
    clinicid INTEGER REFERENCES clinics(id) ON UPDATE CASCADE ON DELETE CASCADE,
    consultPrice INTEGER,
    PRIMARY KEY(doctorLicense, clinicid)
);

CREATE TABLE IF NOT EXISTS schedule (
    day INTEGER,
    hour INTEGER,
    doctor VARCHAR(20),
    clinic INTEGER,
    PRIMARY KEY (day, hour, doctor),
    FOREIGN KEY (doctor, clinic) REFERENCES doctorclinics(doctorLicense, clinicid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS patients (
    email VARCHAR(25) PRIMARY KEY REFERENCES users(email) ON UPDATE CASCADE ON DELETE CASCADE,
    id varchar(8),
    prepaid VARCHAR(20) REFERENCES prepaids(name),
    prepaidNumber varchar(20) 
);

CREATE TABLE IF NOT EXISTS appointments (
    doctor VARCHAR(20),
    clinic INTEGER,
    patient VARCHAR(25) REFERENCES users(email) ON UPDATE CASCADE ON DELETE CASCADE,
    date TIMESTAMP,
    PRIMARY KEY (doctor, date),
    FOREIGN KEY (doctor, clinic) REFERENCES doctorclinics(doctorLicense, clinicid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS images (
    id IDENTITY PRIMARY KEY,
    doctor VARCHAR(20) REFERENCES doctors(license) ON UPDATE CASCADE ON DELETE CASCADE,
    image binary
);

INSERT INTO users VALUES('admin','','admin','admin@test.com');

INSERT INTO locations VALUES ('location');
INSERT INTO specialties VALUES ('specialty');
INSERT INTO users VALUES ('docFirstName', 'docLastName', 'password', 'doctor@mail.com');
INSERT INTO users VALUES ('docFirstName2', 'docLastName2', 'password', 'doctor2@mail.com');
INSERT INTO users VALUES ('patFirstName', 'patLastName', 'password', 'patient@mail.com');
INSERT INTO users VALUES ('patFirstName2', 'patLastName2', 'password', 'patient2@mail.com');
INSERT INTO doctors VALUES ('1', 'specialty', 'doctor@mail.com', '1234567890');
INSERT INTO doctors VALUES ('2', 'specialty', 'doctor2@mail.com', '1234567890');
INSERT INTO clinics VALUES (1, 'clinic', 'location', 'address');
INSERT INTO doctorclinics VALUES ('1', 1, 1);
INSERT INTO doctorclinics VALUES ('2', 1, 1);
INSERT INTO prepaids VALUES('prepaid');
INSERT INTO clinicPrepaids VALUES (1, 'prepaid');
INSERT INTO schedule VALUES (3, 8, '1', 1);
INSERT INTO patients VALUES ('patient@mail.com', '12345678', 'prepaid', '111');
INSERT INTO appointments VALUES ('1', 1, 'patient@mail.com', TIMESTAMP '2019-10-01 08:00:00');
