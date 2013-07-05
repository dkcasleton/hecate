package brewhot.weasel

import org.apache.commons.lang3.Validate


/**
 * Representation of a java package.  No direct reference is made to the containing artifact since it is possible (but very bad) for a single package to exist across multiple artifacts.
 * @author SupremeCheez
 *
 */
class JavaPackage extends AbstractComponent<JavaPackage> {

	Map<String, JavaClass> classes = [:]

	JavaPackage(String name) {
		super(name)
	}

	/**
	 * Gets a JavaClass named {@code className} associated with this package instance.  May result in the creation of a new JavaClass instance.
	 * @param className the name of the class included in the package.
	 * @return the JavaClass representation of {@code className}.
	 */
	JavaClass getPackagedClass(String className) {
		Validate.notBlank(className)

		JavaClass c = classes[className]

		if (c == null) {
			c = new JavaClass(className)

			c.javaPackage = this

			classes.put(className, c)
		}

		return c
	}

	Collection<JavaClass> getPackagedClasses() {
		return classes.values()
	}

	boolean isCoreJava() {
		return name.startsWith("java.") || name.startsWith("javax.")
	}
}
