package brewhot.weasel

class Coupling<T extends Component> {

	private T component

	private int couplingCount = 1

	Coupling(T component) {
		this.component = component
	}

	public T getComponent() {
		return component
	}

	public int getCouplingCount() {
		return couplingCount
	}

	void increaseCoupling() {
		couplingCount++
	}
}
