package by.bsu.lab4.validator;

import by.bsu.lab4.entity.MusicStyle;

public class CompositionValidator {

    public boolean isValid(String[] fields) {
        int expectedLength;
        switch (fields[0]) {
            case "SONG":
            case "INSTRUMENTAL":
                expectedLength = 6;
                break;
            case "CLASSICAL":
                expectedLength = 7;
                break;
            default:
                return false;
        }
        if (fields.length != expectedLength) {
            return false;
        }
        if (isBlank(fields[1]) || isBlank(fields[2]) || isBlank(fields[5])) {
            return false;
        }
        if (!isPositiveInt(fields[3]) || !isStyle(fields[4])) {
            return false;
        }
        return expectedLength == 6 || isPositiveInt(fields[6]);
    }

    private boolean isBlank(String value) {
        return value.trim().isEmpty();
    }

    private boolean isPositiveInt(String value) {
        try {
            return Integer.parseInt(value.trim()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isStyle(String value) {
        try {
            MusicStyle.valueOf(value.trim());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}