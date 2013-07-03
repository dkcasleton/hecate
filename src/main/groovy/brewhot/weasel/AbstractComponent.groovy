package brewhot.weasel



abstract class AbstractComponent<T extends AbstractComponent> implements Component<T> {

	private String name

	private Component<?> parent

	private Map<String, Coupling<T>> afferentCouplings = [:]

	private Map<String, Coupling<T>> efferentCouplings = [:]

	AbstractComponent(String name, Component<?> parent) {
		this.name = name
		this.parent = parent
	}

	@Override
	public String getName() {
		return name
	}

	@Override
	public Component<?> getParent() {
		return parent
	}

	@Override
	public Set<Coupling<T>> getAfferentCouplings() {
		return afferentCouplings.values()
	}

	@Override
	public Set<Coupling<T>> getEfferentCouplings() {
		return efferentCouplings.values()
	}

	@Override
	public void dependsOn(T component) {
		if (component == null) {
			return
		}

		if (efferentCouplings.containsKey(component.getName())) {
			efferentCouplings.get(component.getName()).increaseCoupling()
		} else {
			efferentCouplings.put(component.getName(), new Coupling<T>(component))
		}

		component.responsibleFor(this)

		if (getParent() != null) {
			getParent().dependsOn(component.getParent())
		}
	}

	@Override
	public void responsibleFor(T component) {
		if (component == null) {
			return
		}

		if (afferentCouplings.containsKey(component.getName())) {
			afferentCouplings.get(component.getName()).increaseCoupling()
		} else {
			afferentCouplings.put(component.getName(), new Coupling<T>(component))
		}
	}
}
