package com.allentom.youtrack.message;

/**
 * Message Types
 * @author Takayamaaren
 */

public enum MessageTypes {
    // home display country update
    HOME_DISPLAY_COUNTRY_UPDATE("homeDisplayCountryUpdate");

    private String value;

    MessageTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
