package brewhot.weasel;

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle



abstract class AbstractComponent<T extends AbstractComponent<T>> implements Component<T> {

	private String name

	private Map<String, Coupling<T>> afferentCouplings = [:]

	private Map<String, Coupling<T>> efferentCouplings = [:]

	/*
	 * This will likely get removed
	 */
	private Metrics metrics = new Metrics(this);

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
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("name", name).append("Ca", metrics.getAfferentCoupling()).append("Ce", metrics.getEfferentCoupling()).append("I", metrics.getInstability()).toString()
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
