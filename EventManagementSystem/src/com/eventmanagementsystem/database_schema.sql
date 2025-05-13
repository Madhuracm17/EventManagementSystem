-- Switch to the event_management database
USE event_management;

-- Create the events table
CREATE TABLE events (
    eventId INT NOT NULL PRIMARY KEY,
    eventName VARCHAR(100) NOT NULL,
    description TEXT,
    date DATE,
    location VARCHAR(255),
    totalSeats INT,
    organizerId INT
);

-- Create the feedback table
CREATE TABLE feedback (
    feedbackId INT NOT NULL PRIMARY KEY,
    userId INT,
    eventId INT,
    rating INT,
    comment TEXT,
    FOREIGN KEY (userId) REFERENCES users(user_id),
    FOREIGN KEY (eventId) REFERENCES events(eventId)
);

-- Create the payments table
CREATE TABLE payments (
    paymentId INT NOT NULL PRIMARY KEY,
    ticketId INT,
    paymentDate DATE,
    amount DOUBLE,
    paymentMethod VARCHAR(50),
    FOREIGN KEY (ticketId) REFERENCES tickets(ticketId)
);

-- Create the tickets table
CREATE TABLE tickets (
    ticketId INT NOT NULL PRIMARY KEY,
    eventId INT,
    userId INT,
    quantity INT,
    price DOUBLE,
    FOREIGN KEY (eventId) REFERENCES events(eventId),
    FOREIGN KEY (userId) REFERENCES users(user_id)
);

-- Create the users table
CREATE TABLE users (
    user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50),
    phone_number VARCHAR(15)
);
