package brewhot.weasel.asm

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type

import brewhot.weasel.DependencyContext

class ClassProcessor implements ClassVisitor {

	private DependencyContext context;

	private String visitedClassName

	public ClassProcessor(DependencyContext context) {
		this.context = context
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

		visitedClassName = Type.getObjectType(name).className

		/*
		 * TODO: Add handling for dependencies within the signature
		 */

		/*
		 * Add superclass type
		 */
		addDependency(Type.getObjectType(superName))

		/*
		 * Add each interface type
		 */
		interfaces.each {
			addDependency(Type.getObjectType(it))
		}
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {

		/*
		 * Add annotation type
		 */
		addDependency(Type.getType(desc))

		return new EmptyAnnotationVisitor();
	}

	@Override
	public void visitAttribute(Attribute attr) {
		// no-op
	}

	@Override
	public void visitEnd() {
		// no-op
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {

		/*
		 * TODO: Do we care about the signature?  What is 'value' anyway?
		 */

		/*
		 * Add field type
		 */
		addDependency(Type.getType(desc))

		return new EmptyFieldVisitor();
	}

	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access) {
		//		println "->Visited inner class '$name' with outer $outerName and inner $innerName"

		//addConnection(Type.getObjectType(name).className)
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		//List<String> argumentClassNames = Type.getArgumentTypes(desc).collect {"'$it.className'"}

		//println "Visited method '$name' with return type '${Type.getReturnType(desc).className}' and argument type(s) [${argumentClassNames.join(', ')}] and signature $signature"

		/*
		 * Add the method return type
		 */
		addDependency(Type.getReturnType(desc))

		/*
		 * Add each argument type
		 */
		Type.getArgumentTypes(desc).each {
			addDependency(it)
		}

		/*
		 * Add each exception type
		 */
		exceptions.each {
			addDependency(Type.getObjectType(it))
		}

		return new EmptyMethodVisitor();
	}

	@Override
	public void visitOuterClass(String owner, String name, String desc) {
		//		println "->Visited outer class '$name' with owner $owner and descriptor $desc"
	}

	@Override
	public void visitSource(String source, String debug) {
		// no-op
	}

	private void addDependency(Type javaType) {
		if (javaType.sort == Type.OBJECT) {
			context.addDependency(visitedClassName, javaType.className)
		}
	}
}
