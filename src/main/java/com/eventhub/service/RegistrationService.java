package com.eventhub.service;

import com.eventhub.entity.Registration;
import com.eventhub.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // Save registration
    public Registration saveRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    // Get all registrations
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    // Get registrations by user
    public List<Registration> getRegistrationsByUser(Long userId) {
        return registrationRepository.findByUserId(userId);
    }

    // Delete registrations by user
    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

}
