package seedu.travel.model.place;

import static java.util.Objects.requireNonNull;
import static seedu.travel.commons.util.AppUtil.checkArgument;

/**
 * Represents a Place's description in TravelBuddy.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Description cannot be left empty. "
            + "Type 'No description' if there is no description.\n"
            + "Description should be written in one paragraph that starts with either a "
            + "lowercase or uppercase alphabet character.\n"
            + "After that, any character except the line break is acceptable. ";
    public static final String VALIDATION_REGEX = "^[a-zA-Z]+.*";

    public final String value;

    /**
     * Constructs an {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Returns if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
