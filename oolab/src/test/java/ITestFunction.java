public interface ITestFunction<TOne, TTwo, R>
{
    public R apply(TOne one, TTwo two);
}
