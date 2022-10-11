package agh.ics.oop;

import static java.lang.System.out;

public class World
{
    public static void main(String[] args)
    {
        out.println("\n****system wystartował****");
        out.println("** Część 1 **");
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        run(cars);

        out.println("** Część 2 **");
        Direction[] directions = ParseMoves(args);
        run(directions);

        out.println("** Część 3* **");

        out.println("\n****system zakończył działanie****\n");
    }

    public static void run(String[] arguments){
        out.println("zwierzak idzie do przdou");
        for(int i = 0; i < arguments.length; i++)
        {
            out.print(arguments[i]);
            if(i != arguments.length-1)
                out.print(",");
        }
        out.print("\n");
    }

    public static void run(Direction[] directions)
    {
        if(directions == null || directions.length == 0)
        {
            out.println("Brak ruchów.");
            return;
        }

        for(Direction direction : directions)
        {
            out.println(GetDirectionDescription((direction)));
        }
    }

    private static String GetDirectionDescription(Direction direction)
    {
        return switch (direction)
        {
            case LEFT -> "zwierzak idzie w lewo";
            case BACKWARD -> "zwierzak idzie do tylu";
            case RIGHT -> "zwierzak idzie w prawo";
            case FORWARD -> "zwierzak idzie do przodu";
        };
    }

    public static Direction[] ParseMoves(String[] args)
    {
        if(args.length==0)
            return null;

        int directions_cout = 0;

        // counting only directions array length
        for(String arg : args)
        {
            if(arg.equals("f") ||arg.equals("b") ||arg.equals("r") ||arg.equals("l"))
                directions_cout += 1;
        }

        Direction[] directions = new Direction[directions_cout];

        int i = 0;

        for(String arg : args)
        {
            switch (arg) {
                case "f" -> {
                    directions[i] = Direction.FORWARD;
                    i += 1;
                }
                case "b" -> {
                    directions[i] = Direction.BACKWARD;
                    i += 1;
                }
                case "r" -> {
                    directions[i] = Direction.RIGHT;
                    i += 1;
                }
                case "l" -> {
                    directions[i] = Direction.LEFT;
                    i += 1;
                }
                default -> {
                }
            }
        }

        return directions;
    }

}
