package brewhot.weasel;

import java.util.Collection;

/**
 * An individual, reusable component (category). A component can be coupled,
 * afferently or efferently, to any other component of the same type.
 * 
 * @author Dave Casleton
 * 
 * @param <T>
 */
public interface Component<T extends Component<T>> {

	/**
	 * The name of the component.
	 * 
	 * @return the component name.
	 */
	String getName();

	/**
	 * The set of afferently coupled components, i.e., the components that
	 * depend upon this component.
	 * 
	 * @return the afferent couplings.
	 */
	Collection<Coupling<T>> getAfferentCouplings();

	/**
	 * The set of efferently coupled components, i.e., the components that are
	 * depended upon by this component.
	 * 
	 * @return the efferent couplings.
	 */
	Collection<Coupling<T>> getEfferentCouplings();

	/**
	 * Sets {@code component} as an efferent coupling on this component.
	 * 
	 * @param component
	 *            the efferently coupled component.
	 */
	void dependsOn(T component);

}
