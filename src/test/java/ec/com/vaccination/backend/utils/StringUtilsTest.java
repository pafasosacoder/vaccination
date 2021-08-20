package ec.com.vaccination.backend.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void shouldValidateEcuadorianIdWithErrors() {
        boolean result = StringUtils.isValidEcuadorianId("1234567896");
        assertEquals(false, result);
    }

    @Test
    public void shouldValidateEcuadorianIdOk() {
        boolean result = StringUtils.isValidEcuadorianId("1234567897");
        assertEquals(true, result);
    }

    @Test
    public void shouldValidateEcuadorianIdWithLengthLessThan10() {
        boolean result = StringUtils.isValidEcuadorianId("123456");
        assertEquals(false, result);
    }

    @Test
    public void shouldValidateNotValidEcuadorianId() {
        boolean result = StringUtils.isValidEcuadorianId("1103447982");
        assertEquals(false, result);
    }
}
