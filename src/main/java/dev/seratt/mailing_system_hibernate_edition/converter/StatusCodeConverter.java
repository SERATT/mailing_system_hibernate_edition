package dev.seratt.mailing_system_hibernate_edition.converter;

import dev.seratt.mailing_system_hibernate_edition.entity.StatusCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusCodeConverter implements AttributeConverter<StatusCode, Character> {
    @Override
    public Character convertToDatabaseColumn(StatusCode statusCode) {
        if (statusCode == null) {
            return null;
        }
        return statusCode.getCode();
    }

    @Override
    public StatusCode convertToEntityAttribute(Character code) {
        if (code == null) {
            return null;
        }

        return Stream.of(StatusCode.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
