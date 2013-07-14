package brewhot.weasel.gexf


import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Color
import brewhot.weasel.JavaClass
import brewhot.weasel.JavaJar
import brewhot.weasel.JavaPackage

class ClassGraphBuilder extends AbstractGexfGraphBuilder<Map<JavaClass, Collection<Node>>> {

	protected Map<JavaClass, Collection<Node>> buildNodes(Collection<JavaJar> jars, Map<JavaJar, Color> jarColors) {
		Map<JavaClass, Collection<Node>> classNodes = [:]

		jars.each { j ->
			Color jarColor = jarColors[j]

			j.getPackages().each { p ->
				p.getPackagedClasses().each { c ->
					classNodes.get(c, []) << createNode("$j.name:$c.name", jarColor, c.getAfferentCouplings().size())
				}
			}
		}

		return classNodes
	}

	protected void buildEdges(Map<JavaClass, Collection<Node>> classNodes) {
		classNodes.each { c, nodes ->
			nodes.each { node ->
				c.getEfferentCouplings().each { coupling ->

					/*
					 * We only created nodes for the classes found within the provided jars, so some of the coupled components may not be represented in the graph.
					 */
					classNodes[coupling.component].each { connectedNode ->
						createEdge(node, connectedNode, coupling.couplingCount)
					}
				}
			}
		}
	}
}
