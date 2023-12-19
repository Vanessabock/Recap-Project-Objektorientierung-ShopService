import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IdServiceTest {
    @Test
    void generateIdTest_WhenGenerateId_ThenNoEmptyString() {
        //GIVEN
        IdService idService = new IdService();

        //WHEN
        String actual = idService.generateId();

        //THEN
        assertFalse(actual.isEmpty());
    }

}