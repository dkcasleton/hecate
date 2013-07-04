package brewhot.weasel

import org.apache.commons.lang3.Validate

class JavaJar extends AbstractComponent<JavaJar> {

	Map<String, JavaPackage> packages = [:]

	JavaJar(String name) {
		super(name)
	}

	void addPackage(JavaPackage p) {
		Validate.notNull(p)

		packages.put(p.getName(), p)
	}

	Collection<JavaPackage> getPackages() {
		return packages.values()
	}
}
