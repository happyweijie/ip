package jimjam.task;

import jimjam.TaskStub;
import jimjam.exception.JimjamException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

	@Test
	public void addTask_validTask_listSizeIncreases() {
		TaskList tasks = new TaskList();
		tasks.addTask(new TaskStub("test"));

		assertEquals(1, tasks.getSize());
	}

	@Test
	public void delete_correctIndex_listSizeDecreases() throws JimjamException {
		TaskList tasks = new TaskList();
		tasks.addTask(new TaskStub("delete"));
		tasks.deleteTask(1);

		assertEquals(0, tasks.getSize());
	}

	@Test
	public void deleteTask_correctIndexString_listSizeDecreases() throws JimjamException {
		TaskList tasks = new TaskList();
		tasks.addTask(new TaskStub("delete"));
		tasks.deleteTask("1");

		assertEquals(0, tasks.getSize());
	}

	@Test
	public void deleteTask_blankString_exceptionThrown() {
		TaskList tasks = new TaskList();
		tasks.addTask(new TaskStub("delete"));

		assertThrows(
				JimjamException.class,
				() -> tasks.deleteTask("")
		);
	}

	@Test
	public void deleteTask_invalidIndex_exceptionThrown() {
		TaskList tasks = new TaskList();
		tasks.addTask(new TaskStub("delete"));

		assertThrows(
				JimjamException.class,
				() -> tasks.deleteTask("100")
		);
	}
}