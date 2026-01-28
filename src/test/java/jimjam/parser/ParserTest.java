package jimjam.parser;

import jimjam.exception.JimjamException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;

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
}
