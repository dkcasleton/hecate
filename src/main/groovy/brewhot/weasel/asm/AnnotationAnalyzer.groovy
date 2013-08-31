package brewhot.weasel.asm

import brewhot.weasel.DependencyContext
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Type

public class AnnotationAnalyzer extends AbstractAnalyzer implements AnnotationVisitor {

	AnnotationAnalyzer(DependencyContext context, String visitedClassName) {
		super(context)
		this.visitedClassName = visitedClassName
	}

	@Override
	public void visit(String name, Object value) {
		// no-op
	}

	@Override
	public void visitEnum(String arg0, String arg1, String arg2) {
		// no-op
	}

	@Override
	public AnnotationVisitor visitAnnotation(String name, String desc) {

		/*
		 * Add annotation type
		 */
		addDependency Type.getType(desc)

		this
	}

	@Override
	public AnnotationVisitor visitArray(String name) {

		/*
		 * Arrays of annotations?
		 */
		this
	}

	@Override
	public void visitEnd() {
		// no-op
	}
}
