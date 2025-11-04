package codegenerator.test.exercise2

import codegenerator.Uml2C
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestOperation {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * Eine einfache Operation in einer Klasse, ohne Parameter.
	 * Der me-Parameter muss für alle nicht-statischen Operationen generiert werden, der Type ist die Klasse in welcher die Operation liegt.
	 * Da keine Implementierung existiert und der Kontext zudem "declaration" ist, wird nur die Signatur generiert.
	 */
	@Test def test00_SimpleOperation() {
		val operation = createOperation => [
			name = "simpleOperation"
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_simpleOperation(TestClass* const me);", code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test01_SimpleOperation2() {
		val operation = createOperation => [
			name = "someFunction"
		]
		createClass => [
			name = "MyClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void MyClass_someFunction(MyClass* const me);", code)
	}
	
	/**
	 * Befindet sich die Klasse in einem Paket, muss dies im Namen der Funktion und Datentyp wiedergespiegelt werden.
	 */
	@Test def test02_NestedClassOperation() {
		val operation = createOperation => [
			name = "simpleOperation"
		]
		createPackage => [
			name = "TestPackage"
			ownedTypes += createClass => [
				name = "TestClass"
				ownedOperations += operation
			]
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestPackage_TestClass_simpleOperation(TestPackage_TestClass* const me);", code)
	}

	/**
	 * Der Rückganetyp einer Operation wird über einen Parameter mit der direction "RETURN_LITERAL" angegeben.
	 */
	@Test def test03_ReturningOperation() {
		val operation = createOperation => [
			name = "returningOperation"
			ownedParameters += createParameter => [
				name = "returning"
				direction = ParameterDirectionKind.RETURN_LITERAL
				type = createPrimitiveType => [name = "uint32"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("uint32 TestClass_returningOperation(TestClass* const me);", code)
	}


	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test04_ReturningOperation2() {
		val operation = createOperation => [
			name = "returningFunction"
			ownedParameters += createParameter => [
				name = "ret"
				direction = ParameterDirectionKind.RETURN_LITERAL
				type = createPrimitiveType => [name = "float"]
			]
		]
		createClass => [
			name = "SomeClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("float SomeClass_returningFunction(SomeClass* const me);", code)
	}

	/**
	 * Normale Parameter erscheinen nach dem me-Parameter. Typ, pointer und Namen sollten durch das ParameterTemplate generiert werden.
	 */
	@Test def test05_OneParameterOperation() {
		val operation = createOperation => [
			name = "oneParameterOperation"
			ownedParameters += createParameter => [
				name = "param1"
				type = createPrimitiveType => [name = "uint32"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_oneParameterOperation(TestClass* const me, uint32 param1);", code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test06_OneParameterOperation2() {
		val operation = createOperation => [
			name = "singleParameterOperation"
			ownedParameters += createParameter => [
				name = "arg"
				type = createPrimitiveType => [name = "char"]
			]
		]
		createClass => [
			name = "SomeType"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void SomeType_singleParameterOperation(SomeType* const me, char arg);", code)
	}

	/**
	 * Mehr Parameter im Modell = mehr Parameter im generierten Code.
	 */
	@Test def test07_TwoParameterOperation() {
		val operation = createOperation => [
			name = "twoParameterOperation"
			ownedParameters += createParameter => [
				name = "param1"
				type = createPrimitiveType => [name = "uint32"]
			]
			ownedParameters += createParameter => [
				name = "param2"
				type = createPrimitiveType => [name = "uint8"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_twoParameterOperation(TestClass* const me, uint32 param1, uint8 param2);",
			code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test08_TwoParameterOperation2() {
		val operation = createOperation => [
			name = "twoArgsOperation"
			ownedParameters += createParameter => [
				name = "arg1"
				type = createPrimitiveType => [name = "char"]
			]
			ownedParameters += createParameter => [
				name = "arg2"
				direction = ParameterDirectionKind.INOUT_LITERAL
				type = createPrimitiveType => [name = "int"]
			]
		]
		createClass => [
			name = "TestClass2"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass2_twoArgsOperation(TestClass2* const me, char arg1, int* arg2);",
			code)
	}

	/**
	 * Ist bei einer Operation das Attribut "isStatic" auf true gesetzt, soll kein me-Pointer generiert werden.
	 * Wenn dadurch die Parameterliste leer ist, soll "void" generiert werden.
	 */
	@Test def test09_StaticOperation() {
		val operation = createOperation => [
			name = "staticOperation"
			isStatic = true
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_staticOperation(void);", code)
	}

	/**
	 * Auch wenn es einen Return Parameter gibt soll in der Parameterliste "void stehen.
	 */
	@Test def test10_StaticOperation2() {
		val operation = createOperation => [
			name = "staticFunction"
			isStatic = true
			ownedParameters += createParameter => [
				name = "ret"
				direction = ParameterDirectionKind.RETURN_LITERAL
				type = createPrimitiveType => [name = "int"]
			]
		]
		createClass => [
			name = "TestType"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("int TestType_staticFunction(void);", code)
	}

	/**
	 * Ein einzelner Parameter ohne me-Pointer.
	 */
	@Test def test11_StaticOperationWithParam() {
		val operation = createOperation => [
			name = "staticOperation"
			isStatic = true
			ownedParameters += createParameter => [
				name = "param1"
				type = createPrimitiveType => [name = "uint32"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_staticOperation(uint32 param1);", code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test12_StaticOperationWithParam2() {
		val operation = createOperation => [
			name = "staticOperation"
			isStatic = true
			ownedParameters += createParameter => [
				name = "arg"
				type = createClass => [name = "OtherClass"]
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals("void TestClass_staticOperation(OtherClass* arg);", code)
	}

	/**
	 * Das Modell enthält eine Implementation der Operation, der Konzext ist zudem "implementation".
	 * Es gibt bereits ein OpaqueBehaviorTemplate, welches Ihnen weiterhelfen könnte.
	 */
	@Test def test13_ImplementedOperation() {
		val operation = createOperation => [
			name = "implementedOperation"
			methods += createOpaqueBehavior => [
				languages += "C"
				bodies += "/* hier koennte Ihre Werbung stehen */"
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "implementation")

		Assert.assertEquals('''
		void TestClass_implementedOperation(TestClass* const me) {
			/* hier koennte Ihre Werbung stehen */
		}'''.toString, code)
	}

	/**
	 * Operation mit Implementation und Parametern.
	 */
	@Test def test14_ImplementedOperation2() {
		val operation = createOperation => [
			name = "fibonacci"
			methods += createOpaqueBehavior => [
				languages += "C"
				bodies += "return 1 < arg ? C_fibonacci(me, arg - 1) + C_fibonacci(me, arg - 2) : 1;"
			]
			ownedParameters += createParameter => [
				name = "arg"
				type = createPrimitiveType => [name = "int"]
			]
			ownedParameters += createParameter => [
				name = "ret"
				direction = ParameterDirectionKind.RETURN_LITERAL
				type = createPrimitiveType => [name = "int"]
			]
		]
		createClass => [
			name = "C"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "implementation")

		Assert.assertEquals('''
		int C_fibonacci(C* const me, int arg) {
			return 1 < arg ? C_fibonacci(me, arg - 1) + C_fibonacci(me, arg - 2) : 1;
		}'''.toString, code)
	}

	/**
	 * Beim Kontext "declaration" soll weiterhin nur die Signatur generiert werden.
	 */
	@Test def test15_DeclaredOperation() {
		val operation = createOperation => [
			name = "implementedOperation"
			methods += createOpaqueBehavior => [
				languages += "C"
				bodies += "/* hier koennte Ihre Werbung stehen */"
			]
		]
		createClass => [
			name = "TestClass"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals('''void TestClass_implementedOperation(TestClass* const me);'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test16_DeclaredOperation2() {
		val operation = createOperation => [
			name = "fibonacci"
			methods += createOpaqueBehavior => [
				languages += "C"
				bodies += "return 1 < arg ? C_fibonacci(me, arg - 1) + C_fibonacci(me, arg - 2) : 1;"
			]
			ownedParameters += createParameter => [
				name = "arg"
				type = createPrimitiveType => [name = "int"]
			]
			ownedParameters += createParameter => [
				name = "ret"
				direction = ParameterDirectionKind.RETURN_LITERAL
				type = createPrimitiveType => [name = "int"]
			]
		]
		createClass => [
			name = "C"
			ownedOperations += operation
		]

		val code = (new Uml2C).generateCode(operation, "declaration")

		Assert.assertEquals('''int C_fibonacci(C* const me, int arg);'''.toString, code)
	}
}
