package brewhot.weasel.asm;

import org.objectweb.asm.AnnotationVisitor;

public class EmptyAnnotationVisitor implements AnnotationVisitor {

	@Override
	public AnnotationVisitor visitAnnotation(String name, String desc) {
		// no-op
		return this;
	}

	@Override
	public AnnotationVisitor visitArray(String name) {
		// no-op
		return this;
	}

	@Override
	public void visit(String arg0, Object arg1) {
		// no-op
	}

	@Override
	public void visitEnd() {
		// no-op
	}

	@Override
	public void visitEnum(String arg0, String arg1, String arg2) {
		// no-op
	}
}
