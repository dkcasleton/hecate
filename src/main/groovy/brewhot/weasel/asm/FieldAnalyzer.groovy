package brewhot.weasel.asm;

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Type

import brewhot.weasel.DependencyContext

public class FieldAnalyzer extends AbstractAnalyzer implements FieldVisitor {

	FieldAnalyzer(DependencyContext context, String visitedClassName) {
		super(context)
		this.visitedClassName = visitedClassName
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {

		/*
		 * Add annotation type
		 */
		addDependency(Type.getType(desc))

		/*
		 * Check for other dependencies within the annotation (like other annotations)
		 */
		return new AnnotationAnalyzer(context, visitedClassName)
	}

	@Override
	public void visitAttribute(Attribute arg0) {
		// no-op
	}

	@Override
	public void visitEnd() {
		// no-op
	}
}
