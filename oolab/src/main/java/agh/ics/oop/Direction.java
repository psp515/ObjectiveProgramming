package agh.ics.oop;

public enum Direction
{
    FORWARD("f"),
    BACKWARD("b"),
    RIGHT("r"),
    LEFT("l");

    private String shortForm;
    Direction(String shortForm) {
        this.shortForm = shortForm;
    }
}
