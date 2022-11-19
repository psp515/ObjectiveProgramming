package agh.ics.oop;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Vector2d
{
    public final int x;
    public final int y;

    public Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    //region Publics

    //aktualny punkt poprzedza ten przekazany w funkcji
    public boolean precedes(Vector2d other)
    {
        return this.x <= other.x && this.y <= other.y;
    }

    // aktualny punkt nastepuje po tym przekazanym w funkcji
    public boolean follows(Vector2d other)
    {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d add(Vector2d other)
    {
        return new Vector2d(other.x+this.x, other.y+this.y);
    }

    public Vector2d subtract(Vector2d other)
    {
        return new Vector2d(this.x-other.x, this.y-other.y);
    }

    public Vector2d upperRight(Vector2d other)
    {
        return new Vector2d(Math.max(other.x,this.x), Math.max(other.y,this.y));
    }

    public Vector2d lowerLeft(Vector2d other)
    {
        return new Vector2d(Math.min(other.x,this.x), Math.min(other.y,this.y));
    }

    public Vector2d opposite()
    {
        return new Vector2d(-this.x, -this.y);
    }

    public static Vector2d randomVector(int start, int stop)
    {
        int x = ThreadLocalRandom.current().nextInt(start, stop + 1);
        int y = ThreadLocalRandom.current().nextInt(start, stop + 1);
        return new Vector2d(x, y);
    }

    //endregion

    //region Overrides
    @Override
    public String toString()
    {
        return String.format("(%s,%s)",this.x, this.y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    //endregion
}
