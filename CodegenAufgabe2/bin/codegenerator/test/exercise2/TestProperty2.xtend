package codegenerator.test.exercise2

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestProperty2 {

	extension UMLFactory factory = UMLFactory.eINSTANCE

    /**
	 * Der Name eines Attributs soll nicht durch die Klasse beeinflusst werden.
	 */
	@Test def test00_PropertyInClass() {
		val property = createProperty => [
			name = "testProp"
			type = createPrimitiveType => [name = "int"]
		]
		createClass => [
			name = "TestClass"
			ownedAttributes += property
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("int testProp;", code)
	}

    /**
	 * Parameter mit einem upper-Bound größer als 1 sollen als Arrays generiert werden.
	 */
	@Test def test01_SimpleArrayParameter() {
		val property = createProperty => [
			name = "testFloat"
			type = createPrimitiveType => [name = "float"]
			upper = 2
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("float testFloat[2];", code)
	}

    /**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test02_SimpleArrayParameter2() {
		val property = createProperty => [
			name = "testIntArray"
			type = createPrimitiveType => [name = "uint32_t"]
			upper = 15
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("uint32_t testIntArray[15];", code)
	}

    /**
	 * Für Klassen führt dies zu einem Array von Pointern.
	 */
	@Test def test03_ObjectArrayParameter() {
		val property = createProperty => [
			name = "keyboard"
			type = createClass => [name = "Key"]
			upper = 81
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Key* keyboard[81];", code)
	}

    /**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test04_ObjectArrayParameter2() {
		val property = createProperty => [
			name = "pacman"
			type = createClass => [name = "Ghost"]
			upper = 4
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Ghost* pacman[4];", code)
	}

    /**
	 * Ein Upper-Bound von -1 bedeutet, dass die Anzahl unbegrenzt ist. In diesem Fall soll ein Pointer statt Array generiert werden.
	 */
	@Test def test05_UnlimitedListParameter() {
		val property = createProperty => [
			name = "floatList"
			type = createPrimitiveType => [name = "float"]
			upper = -1
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("float* floatList;", code)
	}

    /**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test06_UnlimitedListParameter2() {
		val property = createProperty => [
			name = "listOfIteger"
			type = createPrimitiveType => [name = "uint32_t"]
			upper = -1
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("uint32_t* listOfIteger;", code)
	}

    /**
	 * Bei Klassen führt ein unbegrenzter Upper-Bound zu einem Pointer-of-Pointer.
	 */
	@Test def test07_UnlimitedObjectListParameter() {
		val property = createProperty => [
			name = "desktop"
			type = createClass => [name = "Icon"]
			upper = -1
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Icon** desktop;", code)
	}

    /**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test08_UnlimitedObjectListParameter2() {
		val property = createProperty => [
			name = "software"
			type = createClass => [name = "Bug"]
			upper = -1
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Bug** software;", code)
	}

    /**
	 * Enumerationen sollen wie primitive Typen gehandhabt werden, was Pointer angeht.
	 */
	@Test def test09_EnumProperty() {
		val property = createProperty => [
			name = "testEnum"
			type = createEnumeration => [name = "AnEnum"]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("AnEnum testEnum;", code)
	}

    /**
	 * Upper-Bound + Enum = Array.
	 */
	@Test def test10_ArrayProperty2() {
		val property = createProperty => [
			name = "testEnumArray"
			type = createEnumeration => [name = "Aufzaehlung"]
			upper = 15
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("Aufzaehlung testEnumArray[15];", code)
	}

    /**
	 * Wenn ein Enum in einem Namespace liegt, muss dieser im Typename auftauchen.
	 */
	@Test def test11_EnumInProperty() {
		val enumeration = createEnumeration => [name = "Aufzaehlung"]
		createPackage => [
			name = "pack"
			ownedTypes += enumeration
		]
		
		val property = createProperty => [
			name = "testEnumArray"
			type = enumeration
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("pack_Aufzaehlung testEnumArray;", code)
	}
}