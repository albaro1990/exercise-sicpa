package ec.sicpa.latam.com.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest {

	public static void main(String[] args) throws ParseException {
		ZoneId zoneId = ZoneId.of("America/Guayaquil");
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
		System.out.println("Date : " + zonedDateTime.getDayOfWeek().getValue());

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		String dateInString = "17-10-2021";
		Date date = formatter.parse(dateInString);
		System.out.println(date);

		System.out.print(isValidPlaca("PDF-1987"));

	}

	@SuppressWarnings("unused")
	public static boolean isValidPlaca(String placa) {
		boolean isValid = false;

		final String PLACA_PATTERN = "(^[a-zA-Z]{3,3})*-([0-9]{3,4})";

		Pattern pat = Pattern.compile(PLACA_PATTERN);

		Matcher mat = pat.matcher(placa);

		if (pat.matcher(placa).matches()) {
			isValid = true;
		} else {
			isValid = false;
		}
		return isValid;
	}
}
