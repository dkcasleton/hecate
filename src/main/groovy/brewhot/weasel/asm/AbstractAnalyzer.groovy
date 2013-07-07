package brewhot.weasel.asm

import org.objectweb.asm.Type

import brewhot.weasel.DependencyContext

abstract class AbstractAnalyzer {

	DependencyContext context;

	String visitedClassName

	AbstractAnalyzer(DependencyContext context) {
		this.context = context
	}

	protected void addDependency(Type javaType) {
		if (javaType.sort == Type.OBJECT) {
			context.addDependency(visitedClassName, javaType.className)
		}
	}
}
