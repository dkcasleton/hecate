package brewhot.weasel;

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle



abstract class AbstractComponent<T extends AbstractComponent<T>> implements Component<T> {

	private String name

	private Map<String, Coupling<T>> afferentCouplings = [:]

	private Map<String, Coupling<T>> efferentCouplings = [:]

	AbstractComponent(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name
	}

	@Override
	public Collection<Coupling<T>> getAfferentCouplings() {
		return afferentCouplings.values()
	}

	@Override
	public Collection<Coupling<T>> getEfferentCouplings() {
		return efferentCouplings.values()
	}

	@Override
	public void dependsOn(T component) {
		if (component == null) {
			return;
		}

		increaseCoupling(efferentCouplings, component)

		component.responsibleFor((T) this);
	}

	@Override
	public int getAfferentCoupling() {
		return afferentCouplings.size()
	}

	@Override
	public int getEfferentCoupling() {
		return efferentCouplings.size()
	}

	@Override
	public float getInstability() {
		float ca = (float) getAfferentCoupling()
		float ce = (float) getEfferentCoupling()

		float totalCouplings = ca + ce

		/*
		 * An unused and empty class/interface will have no couplings, so check for division by 0.
		 */
		return totalCouplings > 0 ? ce/totalCouplings : 0
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("name", name).append("Ca", getAfferentCoupling()).append("Ce", getEfferentCoupling()).append("I", getInstability()).toString()
	}

	/**
	 * Sets {@code component} as an afferent coupling on this component.
	 *
	 * @param component
	 *            the afferently coupled component.
	 */
	protected void responsibleFor(T component) {
		if (component == null) {
			return;
		}

		increaseCoupling(afferentCouplings, component)
	}

	private void increaseCoupling(Map<String, Coupling<T>> couplings, T coupledComponent) {
		Coupling<T> coupling = couplings[coupledComponent.getName()]

		if (coupling == null) {
			coupling = new Coupling<T>(coupledComponent)
			couplings.put(coupledComponent.getName(), coupling)
		}

		coupling.increaseCoupling()
	}
}
