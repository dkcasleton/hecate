package brewhot.weasel.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class EmptyMethodVisitor implements MethodVisitor {

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		// no-op
		return new EmptyAnnotationVisitor();
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// no-op
		return new EmptyAnnotationVisitor();
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
		// no-op
		return new EmptyAnnotationVisitor();
	}

	@Override
	public void visitAttribute(Attribute arg0) {
		// no-op
	}

	@Override
	public void visitCode() {
		// no-op
	}

	@Override
	public void visitEnd() {
		// no-op
	}

	@Override
	public void visitFieldInsn(int arg0, String arg1, String arg2, String arg3) {
		// no-op
	}

	@Override
	public void visitFrame(int arg0, int arg1, Object[] arg2, int arg3, Object[] arg4) {
		// no-op
	}

	@Override
	public void visitIincInsn(int arg0, int arg1) {
		// no-op
	}

	@Override
	public void visitInsn(int arg0) {
		// no-op
	}

	@Override
	public void visitIntInsn(int arg0, int arg1) {
		// no-op
	}

	@Override
	public void visitJumpInsn(int arg0, Label arg1) {
		// no-op
	}

	@Override
	public void visitLabel(Label arg0) {
		// no-op
	}

	@Override
	public void visitLdcInsn(Object arg0) {
		// no-op
	}

	@Override
	public void visitLineNumber(int arg0, Label arg1) {
		// no-op
	}

	@Override
	public void visitLocalVariable(String arg0, String arg1, String arg2, Label arg3, Label arg4, int arg5) {
		// no-op
	}

	@Override
	public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2) {
		// no-op
	}

	@Override
	public void visitMaxs(int arg0, int arg1) {
		// no-op
	}

	@Override
	public void visitMethodInsn(int arg0, String arg1, String arg2, String arg3) {
		// no-op
	}

	@Override
	public void visitMultiANewArrayInsn(String arg0, int arg1) {
		// no-op
	}

	@Override
	public void visitTableSwitchInsn(int arg0, int arg1, Label arg2, Label[] arg3) {
		// no-op
	}

	@Override
	public void visitTryCatchBlock(Label arg0, Label arg1, Label arg2, String arg3) {
		// no-op
	}

	@Override
	public void visitTypeInsn(int arg0, String arg1) {
		// no-op
	}

	@Override
	public void visitVarInsn(int arg0, int arg1) {
		// no-op
	}
}
