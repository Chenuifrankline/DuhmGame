package codegenerator.test

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestLiteralSpec {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	@Test def testEmptyLiteralInteger() {

		val literal = createLiteralInteger

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("0", code)
	}

	@Test def testNullLiteralInteger() {

		val literal = createLiteralInteger => [value = 0]

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("0", code)
	}

	@Test def testPositiveLiteralInteger() {

		val literal = createLiteralInteger => [value = 2147483647]

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("2147483647", code)
	}

	@Test def testNegativeLiteralInteger() {

		val literal = createLiteralInteger => [value = -2147483647]

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("-2147483647", code)
	}

	@Test def testEmptyLiteralBoolean() {

		val literal = createLiteralBoolean

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("0", code)
	}

	@Test def testFalseLiteralBoolean() {

		val literal = createLiteralBoolean => [value = false]

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("0", code)
	}

	@Test def testTrueLiteralBoolean() {

		val literal = createLiteralBoolean => [value = true]

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("1", code)
	}

	@Test def testLiteralNull() {

		val literal = createLiteralNull

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("NULL", code)
	}

	@Test def testEmptyLiteralReal() {

		val literal = createLiteralReal

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("0.0", code)
	}

	@Test def testLiteralReal() {

		val literal = createLiteralReal => [value = 0.3]

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("0.3", code)
	}

	@Test def testEmptyLiteralString() {

		val literal = createLiteralString

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals("\"\"", code)
	}

	@Test def testLiteralString() {

		val literal = createLiteralString => [
			value = "According to all known laws of aviation, there is no way that a bee should be able to fly. Its wings are too small to get its fat little body off the ground.\n The bee, of course, flies anyways. Because bees don't care what humans think is impossible."
		]

		val code = (new Uml2C).generateCode(literal, "value")

		Assert.assertEquals(
			"\"According to all known laws of aviation, there is no way that a bee should be able to fly. Its wings are too small to get its fat little body off the ground.\\n The bee, of course, flies anyways. Because bees don't care what humans think is impossible.\"",
			code)
	}
}
