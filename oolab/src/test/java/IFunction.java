
/* Funkcja przyjmujaca 2 parametry */
public interface IFunction<TOne, TTwo, R>
{
    public R apply(TOne one, TTwo two);
}
