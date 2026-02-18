package jimjam.logic;

import java.time.LocalDate;

import jimjam.exception.JimjamException;
import jimjam.TaskStub;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseFileLine_validDate_success() throws JimjamException {
        assertEquals(
                LocalDate.of(2026, 1, 28),
                Parser.parseDate("2026-01-28")
        );
    }

    @Test
    public void parseFileLine_invalidDateFormat_exceptionThrown() {
        assertThrows(
                JimjamException.class,
                () -> Parser.parseDate("28-01-2026")
        );
    }

    @Test
    public void parseFileLine_invalidString_exceptionThrown() {
        assertThrows(
                JimjamException.class,
                () -> Parser.parseDate("not-a-date")
        );
    }

    @Test
    public void taskToFileLine_unmarkedTask_success() {
        assertEquals(
                "S | 0 | test",
                Parser.taskToFileLine(new TaskStub("test"))
        );
    }

    @Test
    public void taskToFileLine_markedTask_success() {
        assertEquals(
                "S | 1 | test",
                Parser.taskToFileLine(new TaskStub("test", true))
        );
    }
}
