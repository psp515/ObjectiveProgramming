package agh.ics.oop.Tools;

import agh.ics.oop.Enums.MoveDirection;

import java.util.Arrays;

public class OptionsParser
{
    public MoveDirection[] parse(String[] optionsToParse) throws IllegalArgumentException
    {
        // ma pojawic sie wyjatek jezeli bedzie niepoprawny argument
        String[] parsed = Arrays.stream(optionsToParse).filter(OptionsParser::IsValidDirectionString).toArray(String[]::new);
        return Arrays.stream(parsed).map(OptionsParser::StringToDirection).toArray(MoveDirection[]::new);
    }

    private static MoveDirection StringToDirection(String x)
    {
        // Parser fix - lab 4
        return switch (x)
                {
                    case "l", "left" -> MoveDirection.LEFT;
                    case "r", "right" -> MoveDirection.RIGHT;
                    case "b", "backward" -> MoveDirection.BACKWARD;
                    default -> MoveDirection.FORWARD;
                };
    }
    private static boolean IsValidDirectionString(String x) throws IllegalArgumentException
    {
        if(x.equals("f") ||
                x.equals("b") ||
                x.equals("l") ||
                x.equals("r") ||
                x.equals("forward") ||
                x.equals("backward") ||
                x.equals("left") ||
                x.equals("right"))
            return true;

        throw new IllegalArgumentException(x + " is not legal move specification");
    }
}
