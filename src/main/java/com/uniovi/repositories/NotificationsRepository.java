package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Notification;


public interface NotificationsRepository extends CrudRepository<Notification, Long>{

	public List<Notification> findAll();
}
