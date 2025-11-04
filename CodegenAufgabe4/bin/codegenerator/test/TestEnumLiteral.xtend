package codegenerator.test

import org.junit.Test
import org.eclipse.uml2.uml.UMLFactory
import codegenerator.Uml2C
import org.junit.Assert

class TestEnumLiteral {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	@Test def testSimpleLiteral() {

		val literal = createEnumerationLiteral => [
			name = "testLiteral"
		]

		val code = (new Uml2C).generateCode(literal, "enumliteral")

		Assert.assertEquals("testLiteral", code)
	}

	@Test def testLiteralInEnum() {

		val literal = createEnumerationLiteral => [
			name = "testLiteral2"
		]
		createEnumeration => [
			name = "TestEnum"
			ownedLiterals += literal
		]

		val code = (new Uml2C).generateCode(literal, "enumliteral")

		Assert.assertEquals("TestEnum_testLiteral2", code)
	}

	@Test def testSimpleLiteralWithValue() {

		val literal = createEnumerationLiteral => [
			name = "literalTest"
			specification = createLiteralInteger => [value = 42]
		]

		val code = (new Uml2C).generateCode(literal, "enumliteral")

		Assert.assertEquals("literalTest = 42", code)
	}

	@Test def testLiteralInEnumWithValue() {

		val literal = createEnumerationLiteral => [
			name = "literalTest2"
			specification = createLiteralInteger => [value = 1337]
		]
		createEnumeration => [
			name = "EnumTest"
			ownedLiterals += literal
		]

		val code = (new Uml2C).generateCode(literal, "enumliteral")

		Assert.assertEquals("EnumTest_literalTest2 = 1337", code)
	}
}
