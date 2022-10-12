package agh.ics.oop;

import java.util.Arrays;

import static java.lang.System.out;

// Klasa zawiera notatki porównujące Jave do C#
public class World
{
    public static void main(String[] args)
    {
        // Zeby móc użyć out.printline pierwsze trzeba zaimportować klase out, w c# jest od razu klasa Console z metodą write
        out.println("\n****system wystartował****");
        out.println("** Część 1 **");
        // Tablice działają podobnie
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        run(cars);

        out.println("** Część 2 **");
        run(ParseMoves(args));

        out.println("** Część 3* **");
        // użycie Stream -> To taki C#-powy Linq
        runStream(StreamParseMoves(args));

        out.println("** Extended 3* **");
        // właściwie to dało się rozwiaza to w jednej linijce
        OneLiner(args);

        out.println("\n****system zakończył działanie****\n");
    }

    // Part One
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

    //Part Two

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
    public static void run(Direction[] directions)
    {
        if(directions == null || directions.length == 0)
        {
            out.println("Brak ruchów.");
            return;
        }
        out.println("Start");
        // w c# jest foreach a nie jako modyfikacja for
        for(Direction direction : directions)
        {
            out.println(GetDirectionDescription((direction)));
        }
        out.println("Stop");
    }

    // Part Three*
    public static void runStream(Direction[] directions)
    {
        if(directions == null || directions.length == 0)
        {
            out.println("Brak ruchów.");
            return;
        }

        out.println("Start");
        // bardziej skomplikowana składnia od Linq
        Arrays.stream(directions).forEach(x -> out.println(GetDirectionDescription(x)));
        out.println("Stop");

    }

    public static Direction[] StreamParseMoves(String[] args)
    {
        // bardziej skomplikowana składnia od Linq
        String[] parsed = Arrays.stream(args).filter(World::IsValidDirectionString).toArray(String[]::new);
        return Arrays.stream(parsed).map(World::StringToDirection).toArray(Direction[]::new);
    }

    //Extended

    public static void OneLiner(String[] args)
    {
        if(args == null || args.length == 0)
        {
            out.println("Brak ruchów.");
            return;
        }

        // właściwie to krok parsowania na Directions można by pominąć i działało by tak samo
        out.println("Start");
        Arrays.stream(Arrays.stream(Arrays.stream(args).
                filter(World::IsValidDirectionString).
                toArray(String[]::new)).
                map(World::StringToDirection).
                toArray(Direction[]::new)).
                forEach(x -> out.println(GetDirectionDescription(x)));
        out.println("Stop");
    }

    // Converting Data
    private static Direction StringToDirection(String x)
    {
        // to ciekawe rozwiązanie w c# jest pod nazwa switch expressions od c# 8
        return switch (x)
        {
            case "l" -> Direction.LEFT;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            default -> Direction.FORWARD;
        };
    }
    private static boolean IsValidDirectionString(String x)
    {
        // w c# po prostu == do porówynwania stringów jest ok
        return x.equals("f") || x.equals("b") || x.equals("l") || x.equals("r");
    }
    private static String GetDirectionDescription(Direction direction)
    {
        // to ciekawe rozwiązanie w c# jest pod nazwa switch expressions od c# 8
        return switch (direction)
                {
                    case LEFT -> "zwierzak idzie w lewo";
                    case BACKWARD -> "zwierzak idzie do tylu";
                    case RIGHT -> "zwierzak idzie w prawo";
                    case FORWARD -> "zwierzak idzie do przodu";
                };
    }
}
