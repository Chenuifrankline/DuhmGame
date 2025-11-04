package codegenerator.test.exercise2

import org.junit.Test
import org.eclipse.uml2.uml.UMLFactory
import codegenerator.Uml2C
import org.junit.Assert

class TestStructuredClassifier {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * Für eine Klasse soll ein struct generiert werden.
	 * Das Struct wird von einem Typedef umschlossen, um einen kürzeren Namen zu ermöglichen.
	 */
	@Test def test00_EmptyClassStruct() {
		val class = createClass => [
			name = "TestClass"
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct TestClass_struct {
			} TestClass;
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test01_EmptyClassStruct2() {
		val class = createClass => [
			name = "ClassTest"
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct ClassTest_struct {
			} ClassTest;
		'''.toString, code)
	}

	/**
	 * Properties einer Klasse sollen im Struct als Attribute auftauchen.
	 */
	@Test def test02_ClassWithPropertyStruct() {
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createPrimitiveType => [name = "int"]
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct TestClass_struct {
				int testProperty;
			} TestClass;
		'''.toString, code)
	}

	/**
	 * Features wie Arrays sollten bereits vom PropertyTemplate behandelt werden.
	 */
	@Test def test03_ClassWithProperty2Struct() {
		val class = createClass => [
			name = "PropertyClass"
			ownedAttributes += createProperty => [
				name = "floatAttribute"
				type = createPrimitiveType => [name = "float"]
				upper = 2
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct PropertyClass_struct {
				float floatAttribute[2];
			} PropertyClass;
		'''.toString, code)
	}

	/**
	 * Jedes Attribut bekommt eine eigene Zeile.
	 */
	@Test def test04_ClassWithMorePropertiesStruct() {
		val class = createClass => [
			name = "Vector3"
			ownedAttributes += createProperty => [
				name = "x"
				type = createPrimitiveType => [name = "float"]
			]
			ownedAttributes += createProperty => [
				name = "y"
				type = createPrimitiveType => [name = "float"]
			]
			ownedAttributes += createProperty => [
				name = "z"
				type = createPrimitiveType => [name = "float"]
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct Vector3_struct {
				float x;
				float y;
				float z;
			} Vector3;
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test05_ClassWithMoreProperties2Struct() {
		val class = createClass => [
			name = "Texture"
			ownedAttributes += createProperty => [
				name = "width"
				type = createPrimitiveType => [name = "int"]
			]
			ownedAttributes += createProperty => [
				name = "height"
				type = createPrimitiveType => [name = "int"]
			]
			ownedAttributes += createProperty => [
				name = "data"
				type = createPrimitiveType => [name = "color_t"]
				upper = -1
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct Texture_struct {
				int width;
				int height;
				color_t* data;
			} Texture;
		'''.toString, code)
	}

	/**
	 * Die Existenz von Methoden in der Klasse hat keine Answirkungen auf das Struct.
	 */
	@Test def test06_ClassWithOperationStruct() {
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createPrimitiveType => [name = "int"]
			]
			ownedOperations += createOperation => [
				name = "testOperation"
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "/* hier koennte Ihre Werbung stehen */"
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct TestClass_struct {
				int testProperty;
			} TestClass;
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test07_ClassWithOperationStruct2() {
		val otherClass = createClass => [
			name = "OtherClass"
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createPrimitiveType => [name = "int"]
			]
			ownedAttributes += createProperty => [
				name = "classProperty"
				type = otherClass
			]
			ownedOperations += createOperation => [
				name = "testOperation"
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "/* hier koennte Ihre Werbung stehen */"
				]
			]
		]
		createPackage => [
			name = "SomePacket"
			packagedElements += class
			packagedElements += otherClass
		]

		val code = (new Uml2C).generateCode(class, "typedefinition")

		Assert.assertEquals('''
			typedef struct SomePacket_TestClass_struct {
				int testProperty;
				SomePacket_OtherClass* classProperty;
			} SomePacket_TestClass;
		'''.toString, code)
	}

}
