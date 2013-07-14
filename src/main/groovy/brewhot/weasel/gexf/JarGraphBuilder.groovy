package brewhot.weasel.gexf

import java.util.Collection;

import it.uniroma1.dis.wsngroup.gexf4j.core.EdgeType
import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf
import it.uniroma1.dis.wsngroup.gexf4j.core.Graph
import it.uniroma1.dis.wsngroup.gexf4j.core.Node
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Color
import brewhot.weasel.Component
import brewhot.weasel.DependencyContext
import brewhot.weasel.JavaJar

class JarGraphBuilder extends AbstractGexfGraphBuilder<Map<JavaJar, Node>> {

	protected Map<JavaJar, Node> buildNodes(Collection<JavaJar> jars, Map<JavaJar, Color> jarColors) {
		Map<JavaJar, Node> jarNodes = [:]

		jars.each { j ->
			Color jarColor = jarColors[j]

			jarNodes.put(j, createNode(j.name, jarColor, j.getAfferentCouplings().size()))
		}

		jarNodes
	}

	protected void buildEdges(Map<JavaJar, Node> jarNodes) {
		jarNodes.each { jar, node ->
			jar.getEfferentCouplings().each { coupling ->
				createEdge(node, jarNodes[coupling.component], coupling.couplingCount)
			}
		}
	}
}
