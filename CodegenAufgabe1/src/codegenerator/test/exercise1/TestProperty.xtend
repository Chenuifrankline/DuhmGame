package codegenerator.test.exercise1

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test
import org.eclipse.uml2.uml.AggregationKind

class TestProperty {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
	 */
	@Test def test06_UntypedProperty() {
		val property = createProperty => [
			name = "untypedTestProperty"
		]

		val code = (new Uml2C).generateCode(property, "attribute")

		Assert.assertEquals("void* untypedTestProperty;", code)
	}

	/**
	 * 
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
	 * 
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
