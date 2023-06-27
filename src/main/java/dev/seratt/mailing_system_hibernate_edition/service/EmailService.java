package dev.seratt.mailing_system_hibernate_edition.service;

import dev.seratt.mailing_system_hibernate_edition.entity.EmailDetails;

public interface EmailService {
    public boolean sendSimpleMail(EmailDetails details);
}
