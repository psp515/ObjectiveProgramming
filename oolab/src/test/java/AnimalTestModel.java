import agh.ics.oop.Animal;
import agh.ics.oop.MapDirection;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.Vector2d;

public class AnimalTestModel
{
    public final Vector2d _validAnswer;
    public final String[] _directions;
    public final Animal _animal;
    public final MapDirection _finalOrientation;

    public AnimalTestModel(Vector2d validAnswer, String[] directions, Animal animal, MapDirection finalOrientation)
    {
        _validAnswer = validAnswer;
        _directions = directions;
        _animal = animal;
        _finalOrientation = finalOrientation;
    }
}
