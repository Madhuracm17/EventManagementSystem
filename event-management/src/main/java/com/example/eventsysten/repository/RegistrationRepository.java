package com.example.eventsysten.repository;

import com.example.eventsysten.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {}
