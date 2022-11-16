package agh.ics.oop.tools;

import agh.ics.oop.Enums.MoveDirection;

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
        // Parser fix - lab 4
        return switch (x)
                {
                    case "l", "left" -> MoveDirection.LEFT;
                    case "r", "right" -> MoveDirection.RIGHT;
                    case "b", "backward" -> MoveDirection.BACKWARD;
                    default -> MoveDirection.FORWARD;
                };
    }
    private static boolean IsValidDirectionString(String x)
    {
        return x.equals("f") || x.equals("b") || x.equals("l") || x.equals("r") ||
                x.equals("forward") || x.equals("backward") || x.equals("left") || x.equals("right");
    }
}
