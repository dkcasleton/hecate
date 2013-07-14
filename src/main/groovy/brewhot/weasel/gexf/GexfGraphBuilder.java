package brewhot.weasel.gexf;

import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Color;

import java.util.Map;

import brewhot.weasel.DependencyContext;
import brewhot.weasel.JavaJar;

public interface GexfGraphBuilder {
	
	Gexf buildGraph(DependencyContext context, Map<JavaJar, Color> jarColors);

}
