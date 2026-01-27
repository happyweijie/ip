import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Parser {

	public static Task parseFileFileLine(String line) throws JimjamException {
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

	public static LocalDate parseDate(String dateStr) throws JimjamException {
		try {
			return LocalDate.parse(dateStr.trim());
		} catch (DateTimeParseException e) {
			throw new JimjamException("Invalid date format! Please use YYYY-MM-DD");
		}
	}

	public static String taskToFileLine(Task t) {
		String status = t.isDone() ? "1" : "0";
		String type = t.getIcon(); // Assuming "T", "D", or "E"
		String base = type + " | " + status + " | " + t.getDescription();

		if (t instanceof Deadline d) {
			return base + " | " + d.getBy();
		} else if (t instanceof Event e) {
			return base + " | " + e.getStart() + " | " + e.getEnd();
		}
		return base;
	}
}