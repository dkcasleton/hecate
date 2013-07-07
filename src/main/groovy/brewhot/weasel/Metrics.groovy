package brewhot.weasel

import org.apache.commons.lang3.Validate

class Metrics {

	private Component<?> component;

	public Metrics (Component<?> component) {
		Validate.notNull(component)

		this.component = component
	}

	/**
	 * The afferent coupling measure (Ca) of this component.
	 *
	 * @return the component's afferent coupling
	 *
	 * @see <a href="http://www.objectmentor.com/resources/articles/oodmetrc.pdf">Martin Fowler's 1994 white paper on quality metrics</a>
	 */
	public int getAfferentCoupling() {
		return component.getAfferentCouplings().size()
	}

	/**
	 * The efferent coupling measure (Ce) of this component.
	 *
	 * @return the component's efferent coupling.
	 *
	 * @see <a href="http://www.objectmentor.com/resources/articles/oodmetrc.pdf">Martin Fowler's 1994 white paper on quality metrics</a>
	 */
	public int getEfferentCoupling() {
		return component.getEfferentCouplings().size()
	}

	/**
	 * The instability measure (I) of this component in the range [0,1]. This is based on the afferent and efferent coupling metrics. 0 is maximally stable; 1 is maximally instable.
	 *
	 * @return the component's instability.
	 *
	 * @see <a href="http://www.objectmentor.com/resources/articles/oodmetrc.pdf">Martin Fowler's 1994 white paper on quality metrics</a>
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

	//	/**
	//	 * The abstractness of this component in the range [0,1].  This is the ratio of abstractions within the component.
	//	 *
	//	 * @return the component's abstractness.
	//	 *
	//	 * @see <a href="http://www.objectmentor.com/resources/articles/oodmetrc.pdf">Martin Fowler's 1994 white paper on quality metrics</a>
	//	 */
	//	public float getAbstractness() {
	//		float abstractions = (float) getAbstractionCount();
	//		float totalComponents = (float) getComponentCount();
	//
	//		/*
	//		 * An empty package will have zero components, so be careful.
	//		 */
	//		return totalComponents > 0 ? abstracts/totalComponents : 0
	//	}
}
