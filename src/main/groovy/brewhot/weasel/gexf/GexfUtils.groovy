package brewhot.weasel.gexf

import it.uniroma1.dis.wsngroup.gexf4j.core.EdgeType
import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf
import it.uniroma1.dis.wsngroup.gexf4j.core.Graph
import it.uniroma1.dis.wsngroup.gexf4j.core.Node
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.GexfImpl
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.StaxGraphWriter
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.viz.ColorImpl
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Color
import brewhot.weasel.DependencyContext
import brewhot.weasel.JavaPackage

class GexfUtils {

	private static final Color SUN = new ColorImpl(255, 255, 0)

	private static final Random RANDOM_NUMBER_GENERATOR = new Random()

	public static void writePackageGraph(DependencyContext context, String fileName, boolean ignoreCoreJava) {
		Gexf gexf = new GexfImpl().setVisualization(true)

		Graph graph = gexf.getGraph().setDefaultEdgeType(EdgeType.DIRECTED)

		Map<JavaPackage, Node> packageNodes = [:]

		context.getPackages().each { p ->
			if (!ignoreCoreJava || !p.isCoreJava()) {
				packageNodes.put(p, graph.createNode().setLabel(p.getName()).setColor(SUN))
			}
		}

		Random random = new Random()

		context.getJars().each { jar ->

			Color jarColor = new ColorImpl(getRandom8Bit(), getRandom8Bit(), getRandom8Bit())

			jar.getPackages().each { p ->

				Node n = packageNodes[p]

				n.setColor(jarColor)

				p.getEfferentCouplings().each { coupling ->

					if (!ignoreCoreJava || !coupling.getComponent().isCoreJava()) {
						n.connectTo(packageNodes[coupling.getComponent()]).setEdgeType(EdgeType.DIRECTED).setThickness((float) coupling.getCouplingCount())
					}
				}
			}
		}

		StaxGraphWriter graphWriter = new StaxGraphWriter();
		File f = new File(fileName);
		Writer out;
		try {
			out =  new FileWriter(f);
			graphWriter.writeToStream(gexf, out, "UTF-8");
			System.out.println(f.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int getRandom8Bit() {
		return RANDOM_NUMBER_GENERATOR.nextInt(256)
	}
}
