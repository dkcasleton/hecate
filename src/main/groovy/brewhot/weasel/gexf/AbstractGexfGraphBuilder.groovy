package brewhot.weasel.gexf

import it.uniroma1.dis.wsngroup.gexf4j.core.EdgeType
import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf
import it.uniroma1.dis.wsngroup.gexf4j.core.Graph
import it.uniroma1.dis.wsngroup.gexf4j.core.Node
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.GexfImpl
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Color
import brewhot.weasel.DependencyContext
import brewhot.weasel.JavaJar

abstract class AbstractGexfGraphBuilder<T> implements GexfGraphBuilder {

	private Gexf gexf

	protected AbstractGexfGraphBuilder() {
		this.gexf = new GexfImpl().setVisualization(true)

		this.gexf.getGraph().setDefaultEdgeType(EdgeType.DIRECTED)
	}

	@Override
	public Gexf buildGraph(DependencyContext context, Map<JavaJar, Color> jarColors) {
		def jars = context.getJars()

		buildEdges(buildNodes(jars, jarColors))

		return gexf
	}

	protected abstract T buildNodes(Collection<JavaJar> jars, Map<JavaJar, Color> jarColors)

	protected abstract void buildEdges(T nodes)

	protected Graph getGraph() {
		gexf.graph
	}

	protected Node createNode(String nodeLabel, Color nodeColor, float nodeSize) {
		println "Creating node: $nodeLabel"

		graph.createNode().setLabel(nodeLabel).setColor(nodeColor).setSize(nodeSize)
	}

	protected void createEdge(Node from, Node to, float thickness) {
		from.connectTo(to).setEdgeType(EdgeType.DIRECTED).setThickness(thickness)
	}
}
