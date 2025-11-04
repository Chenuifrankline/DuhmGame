package codegenerator.test.exercise4

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestClass2 {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * Für Instanzen einer Klasse werden globale Variablen in der Header Datei deklariert.
	 */
	@Test def test00_EmptyClassHeader() {
		val class = createClass => [
			name = "TestClass"
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTMODEL_TESTCLASS_H
			#define TESTMODEL_TESTCLASS_H
			
			typedef struct TestModel_TestClass_struct {
			} TestModel_TestClass;
			
			extern TestModel_TestClass TestModel_testInstance;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test01_EmptyClassHeader2() {
		val class = createClass => [
			name = "ClassWithInstance"
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MODEL_CLASSWITHINSTANCE_H
			#define MODEL_CLASSWITHINSTANCE_H
			
			typedef struct Model_ClassWithInstance_struct {
			} Model_ClassWithInstance;
			
			extern Model_ClassWithInstance Model_object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Instanzen einer Klasse werden in der C-Datei der Klasse initialisiert.
	 */
	@Test def test02_EmptyClassImpl() {
		val class = createClass => [
			name = "TestClass"
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
			};
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test03_EmptyClassImpl2() {
		val class = createClass => [
			name = "ClassWithInstance"
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
			};
		'''.toString, code)
	}

	/**
	 * Attribute einer Klasse haben keinen Einfluss auf die Objekt-Variable in der H-Datei.
	 */
	@Test def test04_ClassWithAttributeHeader() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTMODEL_TESTCLASS_H
			#define TESTMODEL_TESTCLASS_H
			
			typedef struct TestModel_TestClass_struct {
				int attr;
			} TestModel_TestClass;
			
			extern TestModel_TestClass TestModel_testInstance;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test05_ClassWithAttributeHeader2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MODEL_CLASSWITHINSTANCE_H
			#define MODEL_CLASSWITHINSTANCE_H
			
			typedef struct Model_ClassWithInstance_struct {
				float attr;
			} Model_ClassWithInstance;
			
			extern Model_ClassWithInstance Model_object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Ohne Slots in Objekt haben Attribute einer Klasse haben keinen Einfluss auf die Inilialisierung in der C-Datei.
	 */
	@Test def test06_ClassWithAttributeImpl() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
			};
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test07_ClassWithAttributeImpl2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
			};
		'''.toString, code)
	}

	/**
	 * Auch mit Slots im Objekt ändert sich die H-Datei nicht.
	 */
	@Test def test08_ClassWithAttributeAndSlotHeader() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralInteger => [value = 1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTMODEL_TESTCLASS_H
			#define TESTMODEL_TESTCLASS_H
			
			typedef struct TestModel_TestClass_struct {
				int attr;
			} TestModel_TestClass;
			
			extern TestModel_TestClass TestModel_testInstance;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test09_ClassWithAttributeAndSlotHeader2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralReal => [value = 42.0]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MODEL_CLASSWITHINSTANCE_H
			#define MODEL_CLASSWITHINSTANCE_H
			
			typedef struct Model_ClassWithInstance_struct {
				float attr;
			} Model_ClassWithInstance;
			
			extern Model_ClassWithInstance Model_object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Slots mit Value werden in der Initialisierung des Objekts abgebildet.
	 */
	@Test def test10_ClassWithAttributeAndSlotImpl() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralInteger => [value = 1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
				.attr = 1
			};
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test11_ClassWithAttributeAndSlotImpl2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralReal => [value = 42.0]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
				.attr = 42.0
			};
		'''.toString, code)
	}

	/**
	 * Jeder Slot mit Value bekommt seine eigene Zeile. Slots sind mit Komma getrennt.
	 */
	@Test def test12_ClassWithAttributesAndSlotsImpl() {
		val attribute0 = createProperty => [
			name = "attr0"
			type = createPrimitiveType => [name = "int"]
		]
		val attribute1 = createProperty => [
			name = "attr1"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute0
			ownedAttributes += attribute1
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute0
					values += createLiteralInteger => [value = 1]
				]
				slots += createSlot => [
					definingFeature = attribute1
					values += createLiteralReal => [value = 123.4]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
				.attr0 = 1,
				.attr1 = 123.4
			};
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test13_ClassWithAttributesAndSlotsImpl2() {
		val attribute0 = createProperty => [
			name = "attr0"
			type = createPrimitiveType => [name = "float"]
		]
		val attribute1 = createProperty => [
			name = "attr1"
			type = createPrimitiveType => [name = "int"]
		]
		val attribute2 = createProperty => [
			name = "attr2"
			type = createPrimitiveType => [name = "char"]
		]
		val attribute3 = createProperty => [
			name = "attr3"
			type = createPrimitiveType => [name = "float"]
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute0
			ownedAttributes += attribute1
			ownedAttributes += attribute2
			ownedAttributes += attribute3
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute0
					values += createLiteralReal => [value = 42.0]
				]
				slots += createSlot => [
					definingFeature = attribute3
					values += createLiteralReal => [value = 32.0]
				]
				slots += createSlot => [
					definingFeature = attribute1
					values += createLiteralInteger => [value = 3]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
				.attr0 = 42.0,
				.attr3 = 32.0,
				.attr1 = 3
			};
		'''.toString, code)
	}

	/**
	 * Die Initialisierung von Arrays verläuft über mehrere Zeilen.
	 */
	@Test def test14_ClassWithArrayAttributeAndSlotImpl() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "int"]
			upper = 4
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		createModel => [
			name = "TestModel"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralInteger => [value = 1]
					values += createLiteralInteger => [value = 2]
					values += createLiteralInteger => [value = 3]
					values += createLiteralInteger => [value = 4]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
				.attr = {
					1,
					2,
					3,
					4
				}
			};
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test15_ClassWithArrayAttributeAndSlotImpl2() {
		val attribute = createProperty => [
			name = "attr"
			type = createPrimitiveType => [name = "float"]
			upper = 2
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		createModel => [
			name = "Model"
			packagedElements += class
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createLiteralReal => [value = 42.0]
					values += createLiteralReal => [value = 79.1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
				.attr = {
					42.0,
					79.1
				}
			};
		'''.toString, code)
	}

	/**
	 * Slots mit InstanceValue als Value nehmen die Addresse des Objekts.
	 */
	@Test def test16_ClassWithObjectAttributeAndSlotImpl() {
		val otherClass = createClass => [
			name = "OtherClass"
		]
		val attribute = createProperty => [
			name = "attr"
			type = otherClass
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += attribute
		]
		val otherInstance = createInstanceSpecification => [
			name = "otherInstance"
			classifiers += otherClass
		]
		createModel => [
			name = "TestModel"
			packagedElements += otherClass
			packagedElements += class
			packagedElements += otherInstance
			packagedElements += createInstanceSpecification => [
				name = "testInstance"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createInstanceValue => [instance = otherInstance]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			TestModel_TestClass TestModel_testInstance = {
				.attr = &TestModel_otherInstance
			};
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test17_ClassWithObjectAttributeAndSlotImpl2() {
		val otherClass = createClass => [
			name = "TestClass"
		]
		val attribute = createProperty => [
			name = "attr"
			type = otherClass
			upper = 2
		]
		val class = createClass => [
			name = "ClassWithInstance"
			ownedAttributes += attribute
		]
		val otherInstance0 = createInstanceSpecification => [
			name = "testInstance"
			classifiers += otherClass
		]
		val otherInstance1 = createInstanceSpecification => [
			name = "testInstance2"
			classifiers += otherClass
		]
		createModel => [
			name = "Model"
			packagedElements += otherClass
			packagedElements += class
			packagedElements += otherInstance0
			packagedElements += otherInstance1
			packagedElements += createInstanceSpecification => [
				name = "object"
				classifiers += class
				slots += createSlot => [
					definingFeature = attribute
					values += createInstanceValue => [instance = otherInstance0]
					values += createInstanceValue => [instance = otherInstance1]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "ClassWithInstance.h"
			
			Model_ClassWithInstance Model_object = {
				.attr = {
					&Model_testInstance,
					&Model_testInstance2
				}
			};
		'''.toString, code)
	}
}
