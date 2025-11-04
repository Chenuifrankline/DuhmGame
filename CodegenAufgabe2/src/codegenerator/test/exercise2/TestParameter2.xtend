package codegenerator.test.exercise2

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestParameter2 {

	extension UMLFactory factory = UMLFactory.eINSTANCE

    /**
	 * Der Name eines Parameter soll nicht durch die Operation beeinflusst werden.
	 */
	@Test def test00_ParameterInOperation() {
		val parameter = createParameter => [
			name = "testParam"
			type = createPrimitiveType => [name = "int"]
		]
		createOperation => [
			name = "testOp"
			ownedParameters += parameter
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("int testParam", code)
	}

    /**
	 * Enumerationen sollen wie PrimitiveTypes gehandhabt werden, was das Generieren von Pointern angeht.
	 */
	@Test def test01_EnumParameter() {
		val parameter = createParameter => [
			name = "testEnum"
			type = createEnumeration => [name = "AnEnum"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("AnEnum testEnum", code)
	}

    /**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test02_EnumParameter2() {
		val parameter = createParameter => [
			name = "testEnumeration"
			type = createEnumeration => [name = "Aufzaehlung"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("Aufzaehlung testEnumeration", code)
	}

    /**
	 * Befindet sich ein Enum in einem Namespace, muss dieser im C-Namen auftauchen.
	 */
	@Test def test03_EnumInPackage() {
		val enumeration = createEnumeration => [name = "Aufzaehlung"]
		createPackage => [
			name = "pack"
			ownedTypes += enumeration
		]
		
		val parameter = createParameter => [
			name = "testEnum"
			type = enumeration
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("pack_Aufzaehlung testEnum", code)
	}

}