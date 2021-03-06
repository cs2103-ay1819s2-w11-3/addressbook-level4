package seedu.travel.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.travel.commons.core.Messages;
import seedu.travel.logic.CommandHistory;
import seedu.travel.model.Model;
import seedu.travel.model.place.NameContainsKeywordsPredicate;

/**
 * Finds and lists all places in travel book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";
    public static final String COMMAND_ALIAS = "se";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all places whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " national museum singapore";

    private final NameContainsKeywordsPredicate predicate;

    public SearchCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPlaceList(predicate);
        return new CommandResult(constructFeedbackToUser(model));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && predicate.equals(((SearchCommand) other).predicate)); // state check
    }

    /**
     * Constructs the message to be displayed upon successful SearchCommand
     * @param model Model component
     * @return A string showing a successful search that will be displayed on the GUI
     */
    public String constructFeedbackToUser(Model model) {
        StringBuilder feedbackToUser = new StringBuilder();
        feedbackToUser.append(COMMAND_WORD);
        feedbackToUser.append(" ");
        feedbackToUser.append(predicate.getKeywords());
        feedbackToUser.append(": ");
        feedbackToUser.append(String.format(Messages.MESSAGE_PLACES_LISTED_OVERVIEW,
                model.getFilteredPlaceList().size()));
        return feedbackToUser.toString();
    }
}
