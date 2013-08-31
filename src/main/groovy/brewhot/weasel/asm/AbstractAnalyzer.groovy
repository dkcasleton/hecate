package brewhot.weasel.asm

import org.objectweb.asm.Type

import brewhot.weasel.DependencyContext

abstract class AbstractAnalyzer {

	final DependencyContext context;

	String visitedClassName

	AbstractAnalyzer(DependencyContext context) {
		this.context = context
	}

	protected final void addDependency(Type javaType) {
		if (javaType.sort == Type.OBJECT) {
			context.addDependency(visitedClassName, javaType.className)
		}
	}

	protected final AnnotationAnalyzer newAnnotationAnalyzer() {
		new AnnotationAnalyzer(context, visitedClassName)
	}
}
