package brewhot.weasel

class Metrics {

	private Component<?> component;

	public Metrics (Component<?> component) {
		this.component = component
	}

	/**
	 * The afferent coupling measure (Ca) of this component.
	 *
	 * @return the component's afferent coupling
	 */
	public int getAfferentCoupling() {
		return component.getAfferentCouplings().size()
	}

	/**
	 * The efferent coupling measure (Ce) of this component.
	 *
	 * @return the component's efferent coupling.
	 */
	public int getEfferentCoupling() {
		return component.getEfferentCouplings().size()
	}

	/**
	 * The instability measure (I) of this component. This is based on the afferent and efferent coupling metrics.
	 *
	 * @return the component's instability.
	 */
	public float getInstability() {
		float ca = (float) getAfferentCoupling()
		float ce = (float) getEfferentCoupling()

		float totalCouplings = ca + ce

		/*
		 * An unused and empty class/interface will have no couplings, so check for division by 0.
		 */
		return totalCouplings > 0 ? ce/totalCouplings : 0
	}
}
