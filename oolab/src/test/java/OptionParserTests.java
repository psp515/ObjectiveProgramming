import agh.ics.oop.Enums.MoveDirection;
import agh.ics.oop.Tools.OptionsParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionParserTests {

    @Test
    void parserTestOne()
    {
        boolean exception = false;
        try
        {
            new OptionsParser().parse(new String[]{"f", "r", "b","l", "left","right","backward","forward"});
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }

        assertFalse(exception);
    }

    @Test
    void parserTestTwo()
    {
        boolean exception = false;
        try
        {
            new OptionsParser().parse(new String[]{"a"});
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }

        assertTrue(exception);
    }

    @Test
    void parserTestThree()
    {
        boolean exception = false;
        try
        {
            new OptionsParser().parse(new String[]{"f", "r", "b","l", "Exception"});
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }

        assertTrue(exception);
    }


}
