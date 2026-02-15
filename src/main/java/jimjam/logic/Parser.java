package jimjam.logic;

import jimjam.exception.JimjamException;
import jimjam.task.Deadline;
import jimjam.task.Event;
import jimjam.task.Task;
import jimjam.task.Todo;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Translates between raw strings (from user input or files) and Task objects.
 * This class handles the serialization and deserialization logic for the application.
 */
public class Parser {

	/**
	 * Parses a single line from the storage file into a {@link Task} object.
	 * @param line The formatted string from the save file.
	 * @return The corresponding Task (Todo, Deadline, or Event).
	 * @throws JimjamException If the file format is corrupted or the task type is unknown.
	 */
	public static Task parseFileLine(String line) throws JimjamException {
		// Use " \\| " to split by the pipe symbol and handle surrounding spaces
		String[] parts = line.split(" \\| ");
		String type = parts[0];
		boolean isDone = parts[1].equals("1");
		String description = parts[2];

		return switch (type) {
		case "T" -> new Todo(description, isDone);
		case "D" -> new Deadline(description,
				isDone,
				parseDate(parts[3])); // deadline date
		case "E" -> new Event(description,
				isDone,
				parseDate(parts[3]), // start date
				parseDate(parts[4])); // end date
		default -> null;
		};
	}

	/**
	 * Converts a string representation of a date into a {@link LocalDate}.
	 *
	 * @param dateStr The date string in YYYY-MM-DD format.
	 * @return The parsed LocalDate.
	 * @throws JimjamException If the date format is invalid.
	 */
	public static LocalDate parseDate(String dateStr) throws JimjamException {
		try {
			return LocalDate.parse(dateStr.trim());
		} catch (DateTimeParseException e) {
			throw new JimjamException("Invalid date format! Please use YYYY-MM-DD");
		}
	}

	/**
	 * Converts a Task object into a formatted string for file storage.
	 *
	 * @param t The task to be serialized.
	 * @return A pipe-separated string representing the task.
	 */
	public static String taskToFileLine(Task t) {
		String status = t.isDone() ? "1" : "0";
		String type = t.getIcon(); // Assuming "T", "D", or "E"
		String storageFormat = type + " | " + status + " | " + t.getDescription();

		if (t instanceof Deadline d) {
			return storageFormat + " | " + d.getBy();
		} else if (t instanceof Event e) {
			return storageFormat + " | " + e.getStart() + " | " + e.getEnd();
		}
		return storageFormat;
	}
}