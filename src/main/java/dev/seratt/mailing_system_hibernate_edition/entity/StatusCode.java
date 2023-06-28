package dev.seratt.mailing_system_hibernate_edition.entity;

import java.util.stream.Stream;

public enum StatusCode {
    SUCCESS('G'), FAILURE('R'), PARTIAL_SUCCESS('Y');

    private char code;

    private StatusCode(char code) {
        this.code = code;
    }

    public Character getCode() {
        return code;
    }
}
