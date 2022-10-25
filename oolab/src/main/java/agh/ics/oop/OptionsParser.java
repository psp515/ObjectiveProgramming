package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser
{
    public MoveDirection[] parse(String[] optionsToParse)
    {
        String[] parsed = Arrays.stream(optionsToParse).filter(OptionsParser::IsValidDirectionString).toArray(String[]::new);
        return Arrays.stream(parsed).map(OptionsParser::StringToDirection).toArray(MoveDirection[]::new);
    }

    private static MoveDirection StringToDirection(String x)
    {
        return switch (x)
                {
                    case "l" -> MoveDirection.LEFT;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    default -> MoveDirection.FORWARD;
                };
    }
    private static boolean IsValidDirectionString(String x)
    {
        return x.equals("f") || x.equals("b") || x.equals("l") || x.equals("r");
    }
}
