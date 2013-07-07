package brewhot.weasel

class JavaClass extends AbstractComponent<JavaClass> {

	final JavaPackage javaPackage

	JavaClass(String name, JavaPackage javaPackage) {
		super(name)
		this.javaPackage = javaPackage
	}
}
