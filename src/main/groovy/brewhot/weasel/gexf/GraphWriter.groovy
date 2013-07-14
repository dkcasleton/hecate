package brewhot.weasel.gexf

import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.StaxGraphWriter
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.viz.ColorImpl
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Color
import brewhot.weasel.JavaJar


class GraphWriter {

	private static final Random RANDOM_NUMBER_GENERATOR = new Random()

	private static final Map<GraphType, GexfGraphBuilder> STANDARD_GRAPH_BUILDERS = [
		(GraphType.JAR):new JarGraphBuilder(),
		(GraphType.PACKAGE):new PackageGraphBuilder(),
		(GraphType.CLASS):new ClassGraphBuilder()
	]

	private GraphWriter() {
	}

	static void writeGraphs(GraphContext graphContext) {

		Map<JavaJar, Color> jarColors = [:]

		graphContext.dependencyContext.getJars().each { j ->
			jarColors.put(j, newColor())
		}

		graphContext.graphTypes.each { type ->

			Gexf gexf = null

			GexfGraphBuilder graphBuilder = STANDARD_GRAPH_BUILDERS[type]

			if (graphBuilder != null) {
				gexf = graphBuilder.buildGraph(graphContext.dependencyContext, jarColors)

				writeGraph(gexf, new File(graphContext.destinationDirectory as String, "$graphContext.outputBaseName-${type.name()}.gexf"))
			}
		}
	}

	private static Color newColor() {
		return new ColorImpl(getRandom8Bit(), getRandom8Bit(), getRandom8Bit())
	}

	private static int getRandom8Bit() {
		return RANDOM_NUMBER_GENERATOR.nextInt(256)
	}

	private static void writeGraph(Gexf gexfGraph, File file) {
		StaxGraphWriter graphWriter = new StaxGraphWriter();
		Writer out;
		try {
			out =  new FileWriter(file);
			graphWriter.writeToStream(gexfGraph, out, "UTF-8");
			System.out.println(file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close()
		}
	}
}
