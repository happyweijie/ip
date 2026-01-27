public class Parser {

	public static Task parseFileFileLine(String line) {
		// Use " \\| " to split by the pipe symbol and handle surrounding spaces
		String[] parts = line.split(" \\| ");
		String type = parts[0];
		boolean isDone = parts[1].equals("1");
		String description = parts[2];

		return switch (type) {
			case "T" -> new Todo(description, isDone);
			case "D" ->
				// parts[3] contains the deadline string
					new Deadline(description, isDone, parts[3]);
			case "E" ->
				// parts[3] contains the event time string
					new Event(description, isDone, parts[3], parts[4]);
			default -> null;
		};
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