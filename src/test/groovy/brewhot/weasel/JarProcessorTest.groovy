package brewhot.weasel

import org.junit.Test

import brewhot.weasel.gexf.GexfUtils

class JarProcessorTest {

	@Test
	void test() {
		String jarPath = "D:\\Users\\SupremeCheez\\Development\\dependency-test\\commons-lang3-3.1.jar"

		DependencyContext context = new DependencyContext()

		JarProcessor jarProcessor = new JarProcessor(context)

		jarProcessor.process(jarPath)

		printContents(context)
	}

	@Test
	void "multi jar test using spring context" () {

		DependencyContext context = new DependencyContext()

		JarProcessor jarProcessor = new JarProcessor(context)

		File springContextWithClassPath = new File("D:\\Users\\SupremeCheez\\Development\\dependency-test\\spring-context")

		for (File jar : springContextWithClassPath.listFiles()) {
			if (jar.name.endsWith(".jar")) {
				jarProcessor.process(jar)
			}
		}

		GexfUtils.writeJarGraph(context, "spring-context.gexf")
	}

	private void printContents(DependencyContext context) {
		Set<JavaPackage> printedPackages = []

		context.getJars().each { jar ->
			println "$jar.name:"

			jar.getPackages().each { p ->
				printPackage(p)

				printedPackages << p
			}

			println ""
		}

		Collection<JavaPackage> mysteryPackages = context.getPackages().findAll { !printedPackages.contains(it) && !it.isCoreJava() }

		if (!mysteryPackages.isEmpty()) {
			println 'Unknown container:'
			mysteryPackages.each { p ->
				printPackage(p)
			}
		}
	}

	private void printPackage(JavaPackage p) {
		println "\t$p"

		println "\t\tIncludes:"
		p.getPackagedClasses().each { c ->
			println "\t\t\t$c)"
		}

		println "\t\tDepends on:"
		p.getEfferentCouplings().each {
			println "\t\t\t$it"
		}
	}

}
