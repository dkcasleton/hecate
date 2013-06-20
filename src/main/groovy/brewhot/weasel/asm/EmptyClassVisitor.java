package brewhot.weasel.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class EmptyClassVisitor implements ClassVisitor {

	private AnnotationVisitor emptyAnnotationVisitor = new EmptyAnnotationVisitor();

	private FieldVisitor emptyFieldVisitor = new EmptyFieldVisitor();

	private MethodVisitor emptyMethodVisitor = new EmptyMethodVisitor();

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return emptyAnnotationVisitor;
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		return emptyFieldVisitor;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		return emptyMethodVisitor;
	}

	@Override
	public void visit(int arg0, int arg1, String arg2, String arg3, String arg4, String[] arg5) {
		// no-op
	}

	@Override
	public void visitAttribute(Attribute arg0) {
		// no-op
	}

	@Override
	public void visitEnd() {
		// no-op
	}

	@Override
	public void visitInnerClass(String arg0, String arg1, String arg2, int arg3) {
		// no-op
	}

	@Override
	public void visitOuterClass(String arg0, String arg1, String arg2) {
		// no-op
	}

	@Override
	public void visitSource(String arg0, String arg1) {
		// no-op
	}
}
