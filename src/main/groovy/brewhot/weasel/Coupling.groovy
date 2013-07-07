package brewhot.weasel

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true)
class Coupling<T extends Component> {

	final T component

	private int couplingCount = 0

	Coupling(T component) {
		this.component = component
	}

	public int getCouplingCount() {
		return couplingCount
	}

	void increaseCoupling() {
		couplingCount++
	}
}
