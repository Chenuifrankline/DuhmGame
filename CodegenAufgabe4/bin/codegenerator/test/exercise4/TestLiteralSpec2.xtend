package codegenerator.test.exercise4

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestLiteralSpec2 {
	
	extension UMLFactory factory = UMLFactory.eINSTANCE

	/*
	 * Eine InstanceValue ohne instance ist ein nullpointer und wird als 0 generiert.
	 */
	@Test def test00_EmptyInstanceValue() {

		val literal = createInstanceValue

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("0", code)
	}

	/*
	 * Eine InstanceValue mit instance nimmt die Adresse des Objekts.
	 */
	@Test def test01_InstanceValue() {

		val class = createClass => [name = "TestClass"]
		val inst = createInstanceSpecification => [
			name = "testInstance"
			classifiers += class
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += inst
		]
		val literal = createInstanceValue => [
			instance = inst
		]

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("&Model_testInstance", code)
	}
}