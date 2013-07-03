package brewhot.weasel;

import java.util.Set;

/**
 * An individual, reusable component (category). A component can be coupled, afferently or efferently, to any other
 * component of the same type.
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
	 * The parent of this component or {@code null} if one does not exist.
	 * 
	 * @return the parent component.
	 */
	Component<?> getParent();

	/**
	 * The set of afferently coupled components, i.e., the components that depend upon this component.
	 * 
	 * @return the afferent couplings.
	 */
	Set<Coupling<T>> getAfferentCouplings();

	/**
	 * The set of efferently coupled components, i.e., the components that are depended upon by this component.
	 * 
	 * @return the efferent couplings.
	 */
	Set<Coupling<T>> getEfferentCouplings();

	/**
	 * Sets {@code component} as an efferent coupling on this component.
	 * 
	 * @param component
	 *            the efferently coupled component.
	 */
	void dependsOn(T component);

	/**
	 * Sets {@code component} as an afferent coupling on this component.
	 * 
	 * @param component
	 *            the afferently coupled component.
	 */
	void responsibleFor(T component);

}
