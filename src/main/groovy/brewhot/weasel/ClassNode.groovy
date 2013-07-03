package brewhot.weasel

class ClassNode {

	private String className

	private String artifactName

	private Map<String, ClassNode> dependencies = [:]

	boolean processed

	public ClassNode(String className) {
		this.className = className
	}

	public String getClassName() {
		return className
	}

	public String getArtifactName() {
		return artifactName
	}

	public Collection<ClassNode> getDependencies() {
		return dependencies.values()
	}

	public void dependsOn(ClassNode node) {
		if (!dependencies.containsKey(node.getClassName())) {
			dependencies.put(node.getClassName(), node)
		}
	}
}
