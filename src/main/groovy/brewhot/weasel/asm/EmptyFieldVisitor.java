package brewhot.weasel.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;

public class EmptyFieldVisitor implements FieldVisitor {

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// no-op
		return new EmptyAnnotationVisitor();
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
