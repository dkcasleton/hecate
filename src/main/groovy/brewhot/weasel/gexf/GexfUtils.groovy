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
import brewhot.weasel.JavaJar
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
				Node n = graph.createNode().setLabel(p.getName()).setSize(p.getAfferentCouplings().size() + 1)

				if (p.isCoreJava()) {
					n.setColor(SUN)
				}

				packageNodes.put(p, n)
			}
		}

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

		writeGraph(gexf, fileName)
	}

	public static void writeJarGraph(DependencyContext context, String fileName) {
		Gexf gexf = new GexfImpl().setVisualization(true)

		Graph graph = gexf.getGraph().setDefaultEdgeType(EdgeType.DIRECTED)

		Map<JavaJar, Node> jarNodes = [:]

		def jars = context.getJars()

		jars.each { j ->
			Color jarColor = new ColorImpl(getRandom8Bit(), getRandom8Bit(), getRandom8Bit())

			jarNodes.put(j, graph.createNode().setLabel(j.getName()).setColor(jarColor).setSize(j.getAfferentCouplings().size()))
		}

		jars.each { jar ->

			Node n = jarNodes[jar]

			jar.getEfferentCouplings().each { coupling ->
				n.connectTo(jarNodes[coupling.getComponent()]).setEdgeType(EdgeType.DIRECTED).setThickness((float) coupling.getCouplingCount())
			}
		}

		writeGraph(gexf, fileName)
	}

	private static void writeGraph(Gexf gexfGraph, String fileName) {
		StaxGraphWriter graphWriter = new StaxGraphWriter();
		File f = new File(fileName);
		Writer out;
		try {
			out =  new FileWriter(f);
			graphWriter.writeToStream(gexfGraph, out, "UTF-8");
			System.out.println(f.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close()
		}
	}

	private static int getRandom8Bit() {
		return RANDOM_NUMBER_GENERATOR.nextInt(256)
	}
}
