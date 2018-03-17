package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Notification;
import com.uniovi.repositories.NotificationsRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationsRepository notificationsRepository;

	public List<Notification> getAllNotifications() {
		return notificationsRepository.findAll();
	}

	public void addIncident(Notification n) {
		notificationsRepository.save(n);
	}

}
