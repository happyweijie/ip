package jimjam.storage;

import jimjam.parser.Parser;
import jimjam.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a local file.
 * This class ensures that task data persists between application sessions.
 */
public class Storage {
	private final String filePath;

	/**
	 * Initializes the Storage object with a specific file path.
	 *
	 * @param filePath The path to the file where tasks are stored (e.g., "./data/tasks.txt").
	 */
	public Storage(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Loads tasks from the file system.
	 * If the file does not exist, it returns an empty list.
	 * Corrupted lines within the file are skipped.
	 *
	 * @return A list of {@link Task} objects loaded from the file.
	 */
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
					Task task = Parser.parseFileLine(line);
					if (task == null) {
						continue;
					}

					tasks.add(task);
				} catch (Exception e) {
					System.out.println("Skipping corrupted line: " + line);
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		return tasks;
	}

	/**
	 * Saves the current list of tasks to the file system.
	 * Automatically creates the necessary parent directories if they do not exist.
	 *
	 * @param tasks The list of {@link Task} objects to persist.
	 */
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
