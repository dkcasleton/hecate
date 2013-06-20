package brewhot.weasel

class ClassNode {

	String rootClass

	Set<ClassNode> connections = []

	boolean processed

	public ClassNode(String rootClass) {
		this.rootClass = rootClass
	}
}
