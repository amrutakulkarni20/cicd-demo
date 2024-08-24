-- Create the department table
CREATE TABLE department (
    id long AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR,
    location VARCHAR
);

-- Create the employee table with foreign key reference to department
CREATE TABLE employee (
    id long AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR,
    email VARCHAR,
    position VARCHAR,
    salary DOUBLE,
    department_id long,
    FOREIGN KEY (department_id) REFERENCES department(id)
);