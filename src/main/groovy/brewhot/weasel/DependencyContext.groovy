package brewhot.weasel

import org.apache.commons.lang3.StringUtils



class DependencyContext {

	private Map<String, JavaJar> jars = [:].withDefault { jarName -> new JavaJar(jarName) }

	private Map<String, JavaPackage> packages = [:].withDefault {packageName -> new JavaPackage(packageName) }

	private Map<JavaPackage, Collection<JavaJar>> packageAppearances = [:]

	public void addClass(String jarName, String qualifiedClassName) {
		JavaClass c = getJavaClass(qualifiedClassName)

		JavaJar jar = getJar(jarName)

		jar.addPackage(c.getJavaPackage())

		/*
		 * Can't use the 'withDefault' closure since it introduces the possibility of concurrent modification when building the jar dependencies
		 */
		packageAppearances.get(c.getJavaPackage(), []) << jar
	}

	/**
	 * Declares that {@code dependentClassName} depends upon {@code responsibleClassName}.
	 * @param dependentClassName fully qualified name of the dependent class
	 * @param responsibleClassName fully qualified name of the responsible (depended upon) class
	 */
	public void addDependency(String dependentClassName, String responsibleClassName) {

		JavaClass dependentClass = getJavaClass(dependentClassName)
		JavaClass responsibleClass = getJavaClass(responsibleClassName)

		dependentClass.dependsOn(responsibleClass);

		dependentClass.javaPackage.dependsOn(responsibleClass.javaPackage)
	}

	/*
	 * TODO: replace this with a method that completes the current graph and builds out the component analysis
	 */
	public Collection<JavaJar> getJars() {

		populateJarDependencies()

		return jars.values()
	}

	public Collection<JavaPackage> getPackages() {
		return packages.values()
	}

	private JavaClass getJavaClass(String qualifiedClassName) {

		String packageName = getPackageName(qualifiedClassName)

		JavaPackage p = getPackage(packageName)

		String className = getClassName(qualifiedClassName)

		return p.getPackagedClass(className)
	}

	private JavaPackage getPackage(String packageName) {
		return packages[packageName]
	}

	private JavaJar getJar(String jarName) {
		return jars[jarName]
	}

	/*
	 * TODO: handle inner classes?
	 */
	private String getPackageName(String qualifiedClassName) {
		return StringUtils.substringBeforeLast(qualifiedClassName, ".")
	}

	/*
	 * TODO: handle inner classes?
	 */
	private String getClassName(String qualifiedClassName) {
		return StringUtils.substringAfterLast(qualifiedClassName, ".")
	}

	private void populateJarDependencies() {
		packageAppearances.each { p, jars ->
			p.getEfferentCouplings().each { coupling ->
				packageAppearances[coupling.component].each { efferentJar ->
					jars.each {
						it.dependsOn(efferentJar)
					}
				}
			}
		}
	}
}
