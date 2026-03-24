package com.eventhub.controller;

import com.eventhub.entity.Registration;
import com.eventhub.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // POST
    @PostMapping
    public Registration createRegistration(@RequestBody Registration registration) {
        return registrationService.saveRegistration(registration);
    }

    // GET
    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    // GET id
    @GetMapping("/user/{userId}")
    public List<Registration> getRegistrationsByUser(@PathVariable Long userId) {
        return registrationService.getRegistrationsByUser(userId);
    }

    // DELETE by registration id
    @DeleteMapping("/{id}")
    public void deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
    }

}
