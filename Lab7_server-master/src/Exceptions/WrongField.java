package Exceptions;

/**
 * Class for exception to situation of wrong field.
 */

public class WrongField extends Error {
    public WrongField(String message) {
        super(message);
    }
}