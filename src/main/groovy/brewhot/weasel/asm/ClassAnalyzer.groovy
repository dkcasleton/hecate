package brewhot.weasel.asm

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type

import brewhot.weasel.DependencyContext

class ClassAnalyzer extends AbstractAnalyzer implements ClassVisitor {

	ClassAnalyzer(DependencyContext context) {
		super(context)
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

		/*
		 * TODO: Use this to determine the abstractness of components (interfaces and abstract classes have the ACC_ABSTRACT flag set)
		 */
		//		boolean isAbstract = (access & Opcodes.ACC_ABSTRACT) != 0

		visitedClassName = Type.getObjectType(name).className

		/*
		 * TODO: Add handling for (generic) dependencies within the signature
		 */

		/*
		 * Add superclass type
		 */
		addDependency Type.getObjectType(superName)

		/*
		 * Add each interface type
		 */
		interfaces.each {
			addDependency Type.getObjectType(it)
		}
	}

	@Override
	public void visitSource(String source, String debug) {
		// no-op
	}

	@Override
	public void visitOuterClass(String owner, String name, String desc) {
		// no-op
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {

		/*
		 * Add annotation type
		 */
		addDependency Type.getType(desc)

		/*
		 * Check for other dependencies within the annotation (like other annotations)
		 */
		newAnnotationAnalyzer()
	}

	@Override
	public void visitAttribute(Attribute attr) {
		// no-op
	}

	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access) {

		/*
		 * TODO: Do something useful with inner classes?
		 * 		(1) the dependencies of private inner classes could be considered dependencies of the enclosing class
		 * 		(2) public static nested classes could remain completely separate since they can be defined without an instance of the enclosing class
		 */

		//		boolean isPrivate = (access & Opcodes.ACC_PRIVATE) != 0
		//		boolean isStatic = (access & Opcodes.ACC_STATIC) != 0
		//
		//		if (isPrivate) println "$name is a private nested class of $visitedClassName"
		//		if (isStatic) println "$name is a static nested class of $visitedClassName"

		//		addDependency(Type.getObjectType(name))
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {

		/*
		 * TODO: Ignore synthetic fields?
		 */
		//		boolean isSynthetic = (access & Opcodes.ACC_SYNTHETIC) != 0

		/*
		 * Add field type
		 */
		addDependency Type.getType(desc)

		/*
		 * TODO: Analyze the signature for generic types
		 */

		return new FieldAnalyzer(context, visitedClassName);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

		/*
		 * TODO: Ignore synthetic methods?
		 */

		/*
		 * Add the method return type
		 */
		addDependency Type.getReturnType(desc)

		/*
		 * Add each argument type
		 */
		Type.getArgumentTypes(desc).each { addDependency it }

		/*
		 * TODO: Analyze the signature for generic types
		 */

		/*
		 * Add each exception type
		 */
		exceptions.each {
			addDependency Type.getObjectType(it)
		}

		return new MethodAnalyzer(context, visitedClassName);
	}

	@Override
	public void visitEnd() {
		// no-op
	}
}
