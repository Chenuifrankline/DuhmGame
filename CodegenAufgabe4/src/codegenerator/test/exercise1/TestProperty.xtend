package codegenerator.test.exercise1

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test
import org.eclipse.uml2.uml.AggregationKind

class TestProperty {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * Ein einfaches Property mit Name und Datentyp.
	 */
	@Test def test00_SimpleTypedProperty() {
		val property = createProperty => [
			name = "testProperty"
			type = createPrimitiveType => [name = "uint32"]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("uint32 testProperty;", code)
	}

	/**
	 * Wie vorher, mit anderem Namen und Datentyp.
	 */
	@Test def test01_SimpleTypedProperty2() {
		val property = createProperty => [
			name = "testFloat"
			type = createPrimitiveType => [name = "float"]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("float testFloat;", code)
	}

	/**
	 * Bei Properties mit einer Klasse als Datentyp soll ein Pointer generiert werden.
	 */
	@Test def test02_ComplexTypedProperty() {
		val property = createProperty => [
			name = "propertyTest"
			type = createClass => [
				name = "TestClass"
			]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("TestClass* propertyTest;", code)
	}

	/**
	 * Wie vorher, mit geändertem Namen und Datentyp.
	 */
	@Test def test03_ComplexTypedProperty2() {
		val property = createProperty => [
			name = "complexTestProperty"
			type = createClass => [
				name = "AnotherClass"
			]
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("AnotherClass* complexTestProperty;", code)
	}

	/**
	 * Klassen können in einem Namespace liegen, dieser soll mit an den Namen der Klasse generiert werden.
	 */
	@Test def test04_NamespacedComplexTypedProperty() {
		
		val property = createProperty => [
			name = "propertyTest"
			type = createClass => [
				name = "TestClass"
			]
		]
		createPackage => [
			name = "testPackage"
			packagedElements += property.type
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("testPackage_TestClass* propertyTest;", code)
	}

	/**
	 * Wie vorher, mit geändertem Namen und Datentyp.
	 */
	@Test def test05_NamespacedComplexTypedProperty2() {
		
		val property = createProperty => [
			name = "someAttribute"
			type = createClass => [
				name = "TestType"
			]
		]
		createPackage => [
			name = "aPackage"
			packagedElements += property.type
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("aPackage_TestType* someAttribute;", code)
	}

	/**
	 * Ein Property ohne Datentyp soll "void*" als Typ generiert bekommen.
	 */
	@Test def test06_UntypedProperty() {
		val property = createProperty => [
			name = "untypedTestProperty"
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("void* untypedTestProperty;", code)
	}

	/**
	 * Ist ein Property eine Komposition, soll auch für Klassen kein Pointer generiert werden.
	 * Komposition wird durch das Attribut "aggregation" mit dem Wert "AggregationKind.COMPOSITE_LITERAL" gekennzeichnet.
	 * Der Standardwert für "aggregation" ist "AggregationKind.NONE_LITERAL".
	 */
	@Test def test07_OwnedComplexTypedProperty() {
		val property = createProperty => [
			name = "testAttribute"
			type = createClass => [
				name = "testClass"
			]
			aggregation = AggregationKind.COMPOSITE_LITERAL
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("testClass testAttribute;", code)
	}

	/**
	 * Wie vorher, mit geändertem Datentyp.
	 */
	@Test def test08_OwnedComplexTypedProperty2() {
		val property = createProperty => [
			name = "ownedAttributeTest"
			type = createClass => [
				name = "ClassTest"
			]
			aggregation = AggregationKind.COMPOSITE_LITERAL
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("ClassTest ownedAttributeTest;", code)
	}
}
