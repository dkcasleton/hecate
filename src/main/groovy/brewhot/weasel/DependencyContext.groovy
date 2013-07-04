package brewhot.weasel

import org.apache.commons.lang3.StringUtils


class DependencyContext {

	Map<String, JavaJar> jars = [:]

	Map<String, JavaPackage> packages = [:]

	public void addClass(String jarName, String qualifiedClassName) {
		JavaClass c = getJavaClass(qualifiedClassName)

		getJar(jarName).addPackage(c.getJavaPackage())
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

	public Collection<JavaJar> getJars() {
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
		JavaPackage p = packages[packageName]

		if (p == null) {
			p = new JavaPackage(packageName)
			packages.put(packageName, p)
		}

		return p
	}

	private JavaJar getJar(String jarName) {
		JavaJar jar = jars[jarName]

		if (jar == null) {
			jar = new JavaJar(jarName)
			jars.put(jarName, jar)
		}

		return jar
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
}
