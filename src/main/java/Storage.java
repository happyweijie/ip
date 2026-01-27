import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
	private final String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	public List<Task> load() {
		List<Task> tasks = new ArrayList<>();
		File file = new File(filePath);

		if (!file.exists()) {
			return tasks; // Return empty list if file doesn't exist
		}

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				try {
					Task task = Parser.parseFileFileLine(line);
					if (task != null) {
						tasks.add(task);
					}
				} catch (Exception e) {
					// Stretch Goal: Handle corrupted lines
					System.out.println("Skipping corrupted line: " + line);
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		return tasks;
	}

	public void save(List<Task> tasks) {
		try {
			// OS-independent path handling
			Path path = Paths.get(filePath);

			// Create the parent directory (e.g., /data/) if it doesn't exist
			if (path.getParent() != null) {
				Files.createDirectories(path.getParent());
			}

			FileWriter writer = new FileWriter(filePath);
			for (Task t : tasks) {
				writer.write(Parser.taskToFileLine(t) + System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Could not save tasks: " + e.getMessage());
		}
	}
}
