package brewhot.weasel

import java.util.jar.JarFile

import org.apache.commons.lang3.StringUtils
import org.objectweb.asm.ClassReader


class JarProcessor {

	private DependencyContext context;

	public JarProcessor(DependencyContext context) {
		this.context = context
	}

	public void process(String jarPath) {

		File file = new File(jarPath)

		JarFile jarFile = new JarFile(file)

		jarFile.entries().each {jarEntry ->
			if (jarEntry.name.endsWith('.class')) {
				String className = getSimpleClassName(jarEntry.name)

				context.addClass(file.getName(), className)

				new ClassReader(jarFile.getInputStream(jarEntry)).accept(new ClassProcessor(context), 0)
			}
		}
	}

	private String getSimpleClassName(String jarClassName) {
		return StringUtils.removeEnd(jarClassName, '.class').replaceAll("/", ".")
	}
}