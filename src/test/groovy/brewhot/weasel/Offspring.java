package brewhot.weasel;


@MetaCrap
public class Offspring extends AbstractPerson<Integer> {

	private Parent parent;

	private SomeType someType = SomeType.THING1;

	private Person<?> cousin;

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public SomeType getSomeType() {
		return someType;
	}

	public void setSomeType(SomeType someType) {
		this.someType = someType;
	}
}
