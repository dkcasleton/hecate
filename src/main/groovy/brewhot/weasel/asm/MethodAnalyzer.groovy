package brewhot.weasel.asm

import static org.objectweb.asm.Type.getObjectType
import static org.objectweb.asm.Type.getType

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
		newAnnotationAnalyzer()
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {

		// add the annotation type as a dependency
		addDependency getType(desc)

		newAnnotationAnalyzer()
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {

		/*
		 * TODO: is this redundant?  does visitannotation do the same thing?
		 */
		// add the annotation type as dependency
		addDependency getType(desc)

		newAnnotationAnalyzer()
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
		// no-op
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {

		Type ownerType = getObjectType(owner)

		// Unless the owner is the class that is being visited
		if (ownerType.className != this.visitedClassName) {

			// add the class that owns the field
			addDependency ownerType

			// add the field's class type
			addDependency getType(desc)
		}
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
	public void visitLdcInsn(Object cst) {

		// if the constant is a class
		if (cst instanceof Type) {

			// add the class as a dependency
			addDependency cst as Type
		}
	}

	@Override
	public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2) {
		// no-op
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {

		Type ownerType = getObjectType(owner)

		// Unless the owner is the class that is being visited
		if (ownerType.className != this.visitedClassName) {

			// add the class that owns the method
			addDependency ownerType
		}
	}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims) {
		/*
		 * TODO: Do we care about the type of array being allocated?  Is it already a dependency?
		 */
	}

	@Override
	public void visitTableSwitchInsn(int arg0, int arg1, Label arg2, Label[] arg3) {
		// no-op
	}

	@Override
	public void visitTypeInsn(int opcode, String type) {
		addDependency getObjectType(type)
	}

	@Override
	public void visitVarInsn(int arg0, int arg1) {
		// no-op
	}

	@Override
	public void visitLabel(Label arg0) {
		// no-op
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {

		// if catching specific exceptions (not a finally block)
		if (type != null) {

			/*
			 * TODO: Is this a list of exceptions (i.e., version >= java 1.7?)
			 */
			// add the exception as a dependency
			addDependency getObjectType(type)
		}
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {

		/*
		 * TODO: check signature for generic types?
		 */

		// add the variable type as a dependency
		addDependency getType(desc)
	}

	@Override
	public void visitLineNumber(int arg0, Label arg1) {
		// no-op
	}

	@Override
	public void visitMaxs(int arg0, int arg1) {
		// no-op
	}

	@Override
	public void visitEnd() {
		// no-op
	}
}
