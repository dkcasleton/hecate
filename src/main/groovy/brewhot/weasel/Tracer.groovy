package brewhot.weasel

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type

import brewhot.weasel.asm.EmptyAnnotationVisitor
import brewhot.weasel.asm.EmptyFieldVisitor
import brewhot.weasel.asm.EmptyMethodVisitor

class Tracer implements ClassVisitor {

	ClassNode node

	Map<String, ClassNode> registry

	public Tracer (ClassNode node, Map<String, ClassNode> registry) {
		this.node = node
		this.registry = registry
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

		println "Start inspection of $name"

		/*
		 * TODO: Add handling for dependencies within the signature
		 */

		//String superClassName = Type.getObjectType(superName).className

		//println "Visiting class ${Type.getObjectType(name).className} which extends '$superClassName', implements $interfaces and has signature $signature"

		/*
		 * Add superclass type
		 */
		addConnection(Type.getObjectType(superName))

		/*
		 * Add each interface type
		 */
		interfaces.each {
			addConnection(Type.getObjectType(it))
		}
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		//println "Visiting annotation type '${Type.getType(desc).className}' with visible=$visible"

		/*
		 * Add annotation type
		 */
		addConnection(Type.getType(desc))

		return new EmptyAnnotationVisitor();
	}

	@Override
	public void visitAttribute(Attribute attr) {
		// no-op
	}

	@Override
	public void visitEnd() {
		node.processed = true

		println "*"

		node.connections.each {
			if (!it.processed) {
				new ClassReader(it.rootClass).accept(new Tracer(it, registry), 0)
			}
		}
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		//println "Visited field '$name' of type '${Type.getType(desc).className}' with signature $signature"

		/*
		 * TODO: Do we care about the signature?  What is 'value' anyway?
		 */

		/*
		 * Add field type
		 */
		addConnection(Type.getType(desc))

		return new EmptyFieldVisitor();
	}

	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access) {
		//println "Visited inner class '$name' with outer $outerName and inner $innerName"

		//addConnection(Type.getObjectType(name).className)
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		//List<String> argumentClassNames = Type.getArgumentTypes(desc).collect {"'$it.className'"}

		//println "Visited method '$name' with return type '${Type.getReturnType(desc).className}' and argument type(s) [${argumentClassNames.join(', ')}] and signature $signature"

		/*
		 * Add the method return type
		 */
		addConnection(Type.getReturnType(desc))

		/*
		 * Add each argument type
		 */
		Type.getArgumentTypes(desc).each {
			addConnection(it)
		}

		/*
		 * Add each exception type
		 */
		exceptions.each {
			addConnection(Type.getObjectType(it))
		}

		return new EmptyMethodVisitor();
	}

	@Override
	public void visitOuterClass(String owner, String name, String desc) {
		//println "Visited outer class '$name' with owner $owner and descriptor $desc"
	}

	@Override
	public void visitSource(String source, String debug) {
		// no-op
	}

	private void addConnection(Type javaType) {

		if (javaType.sort == Type.OBJECT) {

			String className = javaType.className

			ClassNode connectedNode = null
			if (registry.containsKey(className)) {
				connectedNode = registry.get(className)
			} else {
				connectedNode = new ClassNode(className)

				/*
				 * Short-circuit core libraries
				 */
				if (className.startsWith('java.')) {
					connectedNode.processed = true
				}

				registry.put(className, connectedNode)
			}

			node.connections << connectedNode

			println "\t$node.rootClass is connected to $className"
		}
	}
}
