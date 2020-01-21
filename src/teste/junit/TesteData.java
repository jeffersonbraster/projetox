package teste.junit;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

public class TesteData {

	@Test
	public void test() {
		
		
		try {
			
			assertEquals("20012020", projeto.report.util.DateUtils.getDateAtualReportName());
			
			assertEquals("'2019-01-20'", projeto.report.util.DateUtils.formatDateSql(Calendar.getInstance().getTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}

}
