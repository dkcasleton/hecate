package brewhot.weasel

class JavaPackage extends AbstractComponent<JavaPackage> {

	Set<JavaClass> classes = []

	JavaPackage(String name, JavaJar parent) {
		super(name, parent)
	}
}
