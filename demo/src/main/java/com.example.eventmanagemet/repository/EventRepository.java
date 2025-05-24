package com.eventmanagementsystem.repository;

import com.eventmanagementsystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}

