package codegenerator.test.exercise1

import codegenerator.Uml2C
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestParameter {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * Ein einfacher Parameter mit einem Namen und Datentyp.
	 */
	@Test def test00_SimpleTypedParameter() {
		val parameter = createParameter => [
			name = "testParameter"
			type = createPrimitiveType => [name = "uint32"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("uint32 testParameter", code)
	}

	/**
	 * Ein einfacher Parameter mit einem anderen Namen und Datentyp als vorher.
	 */
	@Test def test01_SimpleTypedParameter2() {
		val parameter = createParameter => [
			name = "simpleFloatParameter"
			type = createPrimitiveType => [name = "float"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("float simpleFloatParameter", code)
	}

	/**
	 * Wenn ein Parameter keinen Datentyp hat, soll "void*" generiert werden.
	 */
	@Test def test02_UntypedParameter() {
		val parameter = createParameter => [
			name = "untypedParameter"
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("void* untypedParameter", code)
	}

	/**
	 * Gleiches wie vorher, mit anderem Namen.
	 */
	@Test def test03_UntypedParameter2() {
		val parameter = createParameter => [
			name = "typelessParameter"
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("void* typelessParameter", code)
	}

	/**
	 * Return-Parameter können durch das "direction" Attribut utnerschieden werden.
	 * Der Standardwert für direction ist "ParameterDirectionKind.IN_LITERAL"
	 * Für einen Return-Parameter soll kein Name generiert werden, nur der Datentyp.
	 */
	@Test def test04_PrimitiveReturnParameter() {
		val parameter = createParameter => [
			name = "primitiveReturnParameter"
			direction = ParameterDirectionKind.RETURN_LITERAL
			type = createPrimitiveType => [name = "uint32"]
		]

		val code = (new Uml2C).generateCode(parameter, "return")

		Assert.assertEquals("uint32", code)
	}

	/**
	 * Wie vorher, mit geändertem Datentyp.
	 */
	@Test def test05_PrimitiveReturnParameter2() {
		val parameter = createParameter => [
			name = "primitiveReturnParameter"
			direction = ParameterDirectionKind.RETURN_LITERAL
			type = createPrimitiveType => [name = "string"]
		]

		val code = (new Uml2C).generateCode(parameter, "return")

		Assert.assertEquals("string", code)
	}

	/**
	 * Diesmal ist der Datentyp eine Klasse statt ein primitiver Typ.
	 * Es soll ein Pointer auf den Typ generiert werden.
	 */
	@Test def test06_ComplexTypedParameter() {
		val parameter = createParameter => [
			name = "complexParameter"
			type = createClass => [name = "ComplexType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexType* complexParameter", code)
	}

	/**
	 * Wie vorher, mit geändertem Namen und Datentyp.
	 */
	@Test def test07_ComplexTypedParameter2() {
		val parameter = createParameter => [
			name = "evenMoreComplexParameter"
			type = createClass => [name = "ComplexestType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexestType* evenMoreComplexParameter", code)
	}

	/**
	 * Für Klassen sollen auch bei Return-Parametern ein Pointer generiert werden.
	 */
	@Test def test08_ComplexReturnParameter() {
		val parameter = createParameter => [
			name = "complexReturnParameter"
			direction = ParameterDirectionKind.RETURN_LITERAL
			type = createClass => [name = "ComplexType"]
		]

		val code = (new Uml2C).generateCode(parameter, "return")

		Assert.assertEquals("ComplexType*", code)
	}

	/**
	 * Wie vorher, mit geändertem Datentyp.
	 */
	@Test def test09_ComplexReturnParameter2() {
		val parameter = createParameter => [
			name = "ReturnOfTheComplexParameter"
			direction = ParameterDirectionKind.RETURN_LITERAL
			type = createClass => [name = "ComplexClass"]
		]

		val code = (new Uml2C).generateCode(parameter, "return")

		Assert.assertEquals("ComplexClass*", code)
	}

	/**
	 * Bei In-Out Paramertern soll auch für primitive Datentypen ein Pointer generiert werden.
	 */
	@Test def test10_PrimitiveInOutParameter() {
		val parameter = createParameter => [
			name = "primitiveInOutParameter"
			direction = ParameterDirectionKind.INOUT_LITERAL
			type = createPrimitiveType => [name = "uint32"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("uint32* primitiveInOutParameter", code)
	}

	/**
	 * Wie vorher, mit geändertem Namen und Datentyp.
	 */
	@Test def test11_PrimitiveInOutParameter2() {
		val parameter = createParameter => [
			name = "primitiveOutInParameter"
			direction = ParameterDirectionKind.INOUT_LITERAL
			type = createPrimitiveType => [name = "f32"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("f32* primitiveOutInParameter", code)
	}

	/**
	 * Für Klassen soll bei In-Out Paramatern ein doppelter Pointer generiert werden.
	 */
	@Test def test12_ComplexInOutParameter() {
		val parameter = createParameter => [
			name = "complexInOutParameter"
			direction = ParameterDirectionKind.INOUT_LITERAL
			type = createClass => [name = "ComplexType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexType** complexInOutParameter", code)
	}

	/**
	 * Wie vorher, mit geändertem Namen und Datentyp.
	 */
	@Test def test13_ComplexInOutParameter2() {
		val parameter = createParameter => [
			name = "complexOutInParameter"
			direction = ParameterDirectionKind.INOUT_LITERAL
			type = createClass => [name = "ClassType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ClassType** complexOutInParameter", code)
	}

	/**
	 * Bei Out Parameter soll auch für primitive Datentypen ein Pointer generiert werden.
	 */
	@Test def test14_PrimitiveOutParameter() {
		val parameter = createParameter => [
			name = "primitiveOutParameter"
			direction = ParameterDirectionKind.OUT_LITERAL
			type = createPrimitiveType => [name = "int16_t"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("int16_t* primitiveOutParameter", code)
	}

	/**
	 * Wie vorher, mit geändertem Namen und Datentyp.
	 */
	@Test def test15_PrimitiveOutParameter2() {
		val parameter = createParameter => [
			name = "primitiveOutArgument"
			direction = ParameterDirectionKind.OUT_LITERAL
			type = createPrimitiveType => [name = "f64"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("f64* primitiveOutArgument", code)
	}

	/**
	 * Bei Out Parametern soll für Klassen ein doppelter Pointer generiert werden.
	 */
	@Test def test16_ComplexOutParameter() {
		val parameter = createParameter => [
			name = "complexOutParameter"
			direction = ParameterDirectionKind.OUT_LITERAL
			type = createClass => [name = "ComplexType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexType** complexOutParameter", code)
	}

	/**
	 * Wie vorher, mit geändertem Namen und Datentyp.
	 */
	@Test def test17_ComplexOutParameter2() {
		val parameter = createParameter => [
			name = "complexOutArgument"
			direction = ParameterDirectionKind.OUT_LITERAL
			type = createClass => [name = "ComplexClassType"]
		]

		val code = (new Uml2C).generateCode(parameter, "parameter")

		Assert.assertEquals("ComplexClassType** complexOutArgument", code)
	}
}
