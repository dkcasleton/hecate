package brewhot.weasel.gexf

import brewhot.weasel.DependencyContext

class GraphContext {

	DependencyContext dependencyContext

	Set<GraphType> graphTypes = GraphType.values()

	String destinationDirectory

	String outputBaseName
}
