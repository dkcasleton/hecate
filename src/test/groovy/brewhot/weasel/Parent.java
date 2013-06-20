package brewhot.weasel;

import java.util.HashSet;

@Deprecated
public class Parent implements Person<Long> {

	private Offspring child;

	public Offspring getChild() {
		return child;
	}

	public void setChild(Offspring child) {
		this.child = child;
	}

	@Override
	public String getName() {
		new HashSet();
		return null;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
