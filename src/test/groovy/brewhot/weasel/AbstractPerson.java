package brewhot.weasel;

import java.io.IOException;

public abstract class AbstractPerson<T extends Number> implements Person<T> {

	private String name;

	@Override
	public String getName() {
		return name;
	}

	@MetaCrap
	public void setName(String name) {
		this.name = name;
	}

	public void blowup() throws IOException {

	}

	class Thing {

		private Long insideThing;


	}
}
