package brewhot.weasel.gexf

import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf
import it.uniroma1.dis.wsngroup.gexf4j.core.Graph
import it.uniroma1.dis.wsngroup.gexf4j.core.Node
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Color
import brewhot.weasel.DependencyContext
import brewhot.weasel.JavaJar
import brewhot.weasel.JavaPackage

class PackageGraphBuilder extends AbstractGexfGraphBuilder<Map<JavaPackage, Collection<Node>>> {

	protected Map<JavaPackage, Collection<Node>> buildNodes(Collection<JavaJar> jars, Map<JavaJar, Color> jarColors) {
		Map<JavaPackage, Collection<Node>> packageNodes = [:]

		jars.each { j ->
			Color jarColor = jarColors[j]

			j.getPackages().each { p ->
				packageNodes.get(p, []) << createNode("${j.name}:${p.name}", jarColor, p.getAfferentCouplings().size())
			}
		}

		return packageNodes
	}

	protected void buildEdges(Map<JavaPackage, Collection<Node>> packageNodes) {
		packageNodes.each { p, nodes ->
			nodes.each { node ->
				p.getEfferentCouplings().each { coupling ->

					/*
					 * We only created nodes for the packages found within the provided jars, so some of the coupled components may not be represented in the graph.
					 */
					packageNodes[coupling.component].each { connectedNode ->
						createEdge(node, connectedNode, coupling.couplingCount)
					}
				}
			}
		}
	}
}
