package brewhot.weasel;

public interface Person<T extends Number> {

	T getId();

	String getName();
}
