package brewhot.weasel.asm

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type

import brewhot.weasel.DependencyContext

class MethodAnalyzer extends AbstractAnalyzer implements MethodVisitor {

	MethodAnalyzer(DependencyContext context, String visitedClassName) {
		super(context)
		this.visitedClassName = visitedClassName
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		return new AnnotationAnalyzer(context, visitedClassName)
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {

		/*
		 * Add annotation type
		 */
		addDependency(Type.getType(desc))

		return new AnnotationAnalyzer(context, visitedClassName);
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {

		/*
		 * Add annotation type
		 */
		addDependency(Type.getType(desc))

		return new AnnotationAnalyzer(context, visitedClassName);
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
	public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
		//		for (Object localVariable : local) {
		//			if (localVariable instanceof String) {
		//				println Type.getObjectType(localVariable).className
		//			}
		//		}
	}

	@Override
	public void visitFieldInsn(int arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitIincInsn(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitInsn(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitIntInsn(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitJumpInsn(int arg0, Label arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitLdcInsn(Object arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitMethodInsn(int arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitMultiANewArrayInsn(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitTableSwitchInsn(int arg0, int arg1, Label arg2, Label[] arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitTypeInsn(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitVarInsn(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitLabel(Label arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitTryCatchBlock(Label arg0, Label arg1, Label arg2, String arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitLocalVariable(String arg0, String arg1, String arg2, Label arg3, Label arg4, int arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitLineNumber(int arg0, Label arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitMaxs(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitEnd() {
		// no-op
	}
}
