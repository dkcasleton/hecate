package brewhot.weasel;

import org.junit.Test
import org.objectweb.asm.ClassReader

class AsmTest {

	@Test
	public void test() {
		try {
			ClassReader cr = new ClassReader("brewhot.hecate.Offspring");

			Map<String, ClassNode> registry = [:]

			cr.accept(new Tracer(new ClassNode("brewhot.hecate.Offspring"), registry), 0);

			registry.each { key, val ->
				println key
				val.connections.each {
					println "\t$it.rootClass"
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
