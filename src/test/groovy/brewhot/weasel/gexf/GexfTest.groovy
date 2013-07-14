package brewhot.weasel.gexf

import org.junit.Ignore
import org.junit.Test

import brewhot.weasel.DependencyContext
import brewhot.weasel.JarProcessor

@Ignore
class GexfTest {

	@Test
	public void test() {
		String jarPath = "D:\\Users\\SupremeCheez\\Development\\commons-lang3-3.1.jar"
		DependencyContext context = new DependencyContext()
		JarProcessor jarProcessor = new JarProcessor(context)
		jarProcessor.process(jarPath)

//		GexfUtils.writePackageGraph(context, "gexf_test-color.gexf", true)
	}
}
