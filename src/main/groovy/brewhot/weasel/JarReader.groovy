package brewhot.weasel

import java.util.jar.JarFile

import org.apache.commons.lang3.StringUtils
import org.objectweb.asm.ClassReader


class JarReader {

	public static Collection<ClassNode> processClasses(String jarPath) {

		File jarFile = new File(jarPath)

		String artifactName = StringUtils.removeEnd(jarFile.getName(), '.jar')

		Map<String, ClassNode> registry = [:]

		JarFile jar = new JarFile(jarFile)

		jar.entries().each {jarEntry ->
			if (jarEntry.name.endsWith('.class')) {
				String className = StringUtils.removeEnd(jarEntry.name, '.class').replaceAll("/", ".")

				println "Checking $className"

				ClassNode node = null

				if (!registry.containsKey(className)) {
					node = new ClassNode(className)

					registry.put(className, node)
				} else {
					node = registry.get(className)
				}

				node.artifactName = artifactName

				if (node.processed == false) {
					new ClassReader(jar.getInputStream(jarEntry)).accept(new SingleClassVisitor(node, registry), 0)
				}
			}
		}

		return registry.values()
	}

}
