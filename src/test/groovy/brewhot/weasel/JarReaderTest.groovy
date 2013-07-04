package brewhot.weasel

import org.junit.Test

import brewhot.weasel.JarReader


class JarReaderTest {

	@Test
	void test() {

		String jarPath = "D:\\Users\\SupremeCheez\\Development\\commons-lang3-3.1.jar"

		def registry = JarReader.processClasses(jarPath)

		registry.each { node ->
			if (node.artifactName != null) {
				println node.className
				node.dependencies.each {
					println "\t->${it.artifactName != null ? it.artifactName : ''}:$it.className"
				}
			}
		}
	}

}
