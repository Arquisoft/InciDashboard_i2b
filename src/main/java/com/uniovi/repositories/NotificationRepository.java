package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long>{

}
