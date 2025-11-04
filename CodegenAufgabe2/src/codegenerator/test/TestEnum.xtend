package codegenerator.test

import org.junit.Test
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import codegenerator.Uml2C

class TestEnum {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	@Test def testEmptyEnum() {

		val enumeration = createEnumeration => [
			name = "TestEnum"
		]

		val code = (new Uml2C).generateCode(enumeration, "typedefinition")

		Assert.assertEquals('''
			typedef enum {
			} TestEnum;
		'''.toString, code)
	}

	@Test def testEnumWithOneLiteral() {

		val enumeration = createEnumeration => [
			name = "TestEnum"
			ownedLiterals += createEnumerationLiteral => [
				name = "literalTest"
			]
		]

		val code = (new Uml2C).generateCode(enumeration, "typedefinition")

		Assert.assertEquals('''
			typedef enum {
				TestEnum_literalTest
			} TestEnum;
		'''.toString, code)
	}

	@Test def testEnumWithOneLiteralWithValue() {

		val enumeration = createEnumeration => [
			name = "EnumTest"
			ownedLiterals += createEnumerationLiteral => [
				name = "testLiteral"
				specification = createLiteralInteger => [value = 1234]
			]
		]

		val code = (new Uml2C).generateCode(enumeration, "typedefinition")

		Assert.assertEquals('''
			typedef enum {
				EnumTest_testLiteral = 1234
			} EnumTest;
		'''.toString, code)
	}

	@Test def testEnumWithMoreLiterals() {

		val enumeration = createEnumeration => [
			name = "TestEnumeration"
			ownedLiterals += createEnumerationLiteral => [
				name = "literal0"
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literal1"
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literalZwei"
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literalThree"
			]
		]

		val code = (new Uml2C).generateCode(enumeration, "typedefinition")

		Assert.assertEquals('''
			typedef enum {
				TestEnumeration_literal0,
				TestEnumeration_literal1,
				TestEnumeration_literalZwei,
				TestEnumeration_literalThree
			} TestEnumeration;
		'''.toString, code)
	}

	@Test def testEnumWithMoreLiteralsWithValues() {

		val enumeration = createEnumeration => [
			name = "TestEnumeration"
			ownedLiterals += createEnumerationLiteral => [
				name = "literalNull"
				specification = createLiteralInteger => [value = 24601]
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literalEins"
				specification = createLiteralInteger => [value = 1]
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literal2"
				specification = createLiteralInteger => [value = -1]
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literalIII"
				specification = createLiteralInteger => [value = 420]
			]
		]

		val code = (new Uml2C).generateCode(enumeration, "typedefinition")

		Assert.assertEquals('''
			typedef enum {
				TestEnumeration_literalNull = 24601,
				TestEnumeration_literalEins = 1,
				TestEnumeration_literal2 = -1,
				TestEnumeration_literalIII = 420
			} TestEnumeration;
		'''.toString, code)
	}

	@Test def testEnumHeaderWithMoreLiteralsWithValues() {

		val enumeration = createEnumeration => [
			name = "TestEnumeration"
			ownedLiterals += createEnumerationLiteral => [
				name = "literalNull"
				specification = createLiteralInteger => [value = 24601]
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literalEins"
				specification = createLiteralInteger => [value = 1]
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literal2"
				specification = createLiteralInteger => [value = -1]
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literalIII"
				specification = createLiteralInteger => [value = 420]
			]
		]

		val code = (new Uml2C).generateCode(enumeration, "declaration")

		Assert.assertEquals('''
			#ifndef TESTENUMERATION_H
			#define TESTENUMERATION_H
			
			typedef enum {
				TestEnumeration_literalNull = 24601,
				TestEnumeration_literalEins = 1,
				TestEnumeration_literal2 = -1,
				TestEnumeration_literalIII = 420
			} TestEnumeration;
			
			extern TestEnumeration TestEnumeration_Literals[4];
			
			#endif
		'''.toString, code)
	}

	@Test def testEnumImplementationWithMoreLiteralsWithValues() {

		val enumeration = createEnumeration => [
			name = "TestEnumeration"
			ownedLiterals += createEnumerationLiteral => [
				name = "literalNull"
				specification = createLiteralInteger => [value = 24601]
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literalEins"
				specification = createLiteralInteger => [value = 1]
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literal2"
				specification = createLiteralInteger => [value = -1]
			]
			ownedLiterals += createEnumerationLiteral => [
				name = "literalIII"
				specification = createLiteralInteger => [value = 420]
			]
		]

		val code = (new Uml2C).generateCode(enumeration, "implementation")

		Assert.assertEquals('''
			#include "TestEnumeration.h"
			
			TestEnumeration TestEnumeration_Literals[4] = {
				TestEnumeration_literalNull,
				TestEnumeration_literalEins,
				TestEnumeration_literal2,
				TestEnumeration_literalIII
			};
		'''.toString, code)
	}
}
