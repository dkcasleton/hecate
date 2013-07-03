package brewhot.weasel

class JavaJar extends AbstractComponent<JavaJar> {

	Set<JavaPackage> packages = []

	JavaJar(String name) {
		super(name, null)
	}
}
