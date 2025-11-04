package codegenerator.test.exercise3

import codegenerator.Uml2C
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestClass {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/**
	 * Eine Header-Datei für eine Klasse enthält immer Header-Guards und ein struct.
	 */
	@Test def test00_EmptyClassHeader() {
		val class = createClass => [
			name = "TestClass"
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTCLASS_H
			#define TESTCLASS_H
			
			typedef struct TestClass_struct {
			} TestClass;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderem Name.
	 */
	@Test def test01_EmptyClassHeader2() {
		val class = createClass => [
			name = "Klasse"
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef KLASSE_H
			#define KLASSE_H
			
			typedef struct Klasse_struct {
			} Klasse;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Attribute der Klasse werden im Struct gelistet.
	 */
	@Test def test02_ClassHeaderWithProperties() {
		val class = createClass => [
			name = "Vec2"
			ownedAttributes += createProperty => [
				name = "x"
				type = createPrimitiveType => [name = "float"]
			]
			ownedAttributes += createProperty => [
				name = "y"
				type = createPrimitiveType => [name = "float"]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef VEC2_H
			#define VEC2_H
			
			typedef struct Vec2_struct {
				float x;
				float y;
			} Vec2;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Für alle Operationen der Klasse werden im header die Signaturen aufgeführt.
	 */
	@Test def test03_ClassHeaderWithPropertyAndOperation() {
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				upper = -1;
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

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTCLASS_H
			#define TESTCLASS_H
			
			typedef struct TestClass_struct {
				int* testProperty;
			} TestClass;
			
			void TestClass_testOperation(TestClass* const me);
			
			#endif
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test04_ClassHeaderWithPropertyAndOperation2() {
		val int_t = createPrimitiveType => [name = "int"]
		val uint8_t = createPrimitiveType => [name = "uint8_t"]
		val class = createClass => [
			name = "World"
			ownedAttributes += createProperty => [
				name = "tiles"
				upper = 8*8;
				type = uint8_t
			]
			ownedOperations += createOperation => [
				name = "getTileAt"
				ownedParameters += createParameter => [
					name = "result"
					type = uint8_t
					direction = ParameterDirectionKind.RETURN_LITERAL
				]
				ownedParameters += createParameter => [
					name = "x"
					type = int_t
				]
				ownedParameters += createParameter => [
					name = "y"
					type = int_t
				]
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "return me->tiles[x + y * 8];"
				]
			]
			ownedOperations += createOperation => [
				name = "setTileAt"
				ownedParameters += createParameter => [
					name = "x"
					type = int_t
				]
				ownedParameters += createParameter => [
					name = "y"
					type = int_t
				]
				ownedParameters += createParameter => [
					name = "tile"
					type = uint8_t
				]
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "me->tiles[x + y * 8] = tile;"
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef WORLD_H
			#define WORLD_H
			
			typedef struct World_struct {
				uint8_t tiles[64];
			} World;
			
			uint8_t World_getTileAt(World* const me, int x, int y);
			
			void World_setTileAt(World* const me, int x, int y, uint8_t tile);
			
			#endif
		'''.toString, code)
	}

	/**
	 * Eine C-Datei einer Klasse importiert immer die dazugehörige H-Datei.
	 * Für alle Operationen der Klasse werden die Implementationen gelistet.
	 */
	@Test def test05_ClassImplementationWithPropertyAndOperation() {
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				upper = -1;
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

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			void TestClass_testOperation(TestClass* const me) {
				/* hier koennte Ihre Werbung stehen */
			}
		'''.toString, code)
	}


	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test06_ClassImplementationWithPropertyAndOperation2() {
		val int_t = createPrimitiveType => [name = "int"]
		val uint8_t = createPrimitiveType => [name = "uint8_t"]
		val class = createClass => [
			name = "World"
			ownedAttributes += createProperty => [
				name = "tiles"
				upper = 8*8;
				type = uint8_t
			]
			ownedOperations += createOperation => [
				name = "getTileAt"
				ownedParameters += createParameter => [
					name = "result"
					type = uint8_t
					direction = ParameterDirectionKind.RETURN_LITERAL
				]
				ownedParameters += createParameter => [
					name = "x"
					type = int_t
				]
				ownedParameters += createParameter => [
					name = "y"
					type = int_t
				]
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "return me->tiles[x + y * 8];"
				]
			]
			ownedOperations += createOperation => [
				name = "setTileAt"
				ownedParameters += createParameter => [
					name = "x"
					type = int_t
				]
				ownedParameters += createParameter => [
					name = "y"
					type = int_t
				]
				ownedParameters += createParameter => [
					name = "tile"
					type = uint8_t
				]
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "me->tiles[x + y * 8] = tile;"
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "World.h"
			
			uint8_t World_getTileAt(World* const me, int x, int y) {
				return me->tiles[x + y * 8];
			}
			
			void World_setTileAt(World* const me, int x, int y, uint8_t tile) {
				me->tiles[x + y * 8] = tile;
			}
		'''.toString, code)
	}

	/**
	 * For Properties mit einem nicht-primitiven Type soll der Type included werden.
	 */
	@Test def test07_ClassHeaderWithIncludeFromProperty() {
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createClass => [name = "OtherClass"]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTCLASS_H
			#define TESTCLASS_H
			
			#include "OtherClass.h"
			
			typedef struct TestClass_struct {
				OtherClass* testProperty;
			} TestClass;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test08_ClassHeaderWithIncludeFromProperty2() {
		val class = createClass => [
			name = "Object"
			ownedAttributes += createProperty => [
				name = "texture"
				type = createClass => [name = "Texture"]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef OBJECT_H
			#define OBJECT_H
			
			#include "Texture.h"
			
			typedef struct Object_struct {
				Texture* texture;
			} Object;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Auch für nicht-primitive Parameter sollen includes generiert werden.
	 */
	@Test def test09_ClassHeaderWithIncludeFromParameter() {
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
				ownedParameters += createParameter => [
					name = "param"
					type = createClass => [name = "OtherClass"]
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTCLASS_H
			#define TESTCLASS_H
			
			#include "OtherClass.h"
			
			typedef struct TestClass_struct {
				int testProperty;
			} TestClass;
			
			void TestClass_testOperation(TestClass* const me, OtherClass* param);
			
			#endif
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test10_ClassHeaderWithIncludeFromParameter2() {
		val int_t = createPrimitiveType => [name = "int"]
		val class = createClass => [
			name = "Texture"
			ownedAttributes += createProperty => [
				name = "color_data"
				upper = -1
				type = createPrimitiveType => [name = "uint8_t"]
			]
			ownedAttributes += createProperty => [
				name = "width"
				type = int_t
			]
			ownedAttributes += createProperty => [
				name = "height"
				type = int_t
			]
			ownedOperations += createOperation => [
				name = "getColorAt"
				ownedParameters += createParameter => [
					name = "result"
					type = createClass => [name = "Color"]
					direction = ParameterDirectionKind.RETURN_LITERAL
				]
				ownedParameters += createParameter => [
					name = "x"
					type = int_t
				]
				ownedParameters += createParameter => [
					name = "y"
					type = int_t
				]
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += '''
						Color *result = Color_New();
						result->r = me->color_data[(x + y * me->width) * 3 + 0];
						result->g = me->color_data[(x + y * me->width) * 3 + 1];
						result->b = me->color_data[(x + y * me->width) * 3 + 2];
						return result;
					'''
					
				]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TEXTURE_H
			#define TEXTURE_H
			
			#include "Color.h"
			
			typedef struct Texture_struct {
				uint8_t* color_data;
				int width;
				int height;
			} Texture;
			
			Color* Texture_getColorAt(Texture* const me, int x, int y);
			
			#endif
		'''.toString, code)
	}

	/**
	 * Für Klassen in einem Namespace muss der Name des Namespace an den korrekten Stellen eingefügt werden.
	 */
	@Test def test11_ClassHeaderInNamespace() {
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
		createPackage => [
			name = "TestPackage"
			ownedTypes += class
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTPACKAGE_TESTCLASS_H
			#define TESTPACKAGE_TESTCLASS_H
			
			typedef struct TestPackage_TestClass_struct {
				int testProperty;
			} TestPackage_TestClass;
			
			void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me);
			
			#endif
		'''.toString, code)
	}


	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test12_ClassHeaderInNamespace2() {
		val class = createClass => [
			name = "NestedClass"
			ownedAttributes += createProperty => [
				name = "someProperty"
				type = createPrimitiveType => [name = "float"]
			]
			ownedOperations += createOperation => [
				name = "anOperation"
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "/* hier koennte Ihre Werbung stehen */"
				]
			]
		]
		createPackage => [
			name = "ParentPackage"
			ownedTypes += class
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef PARENTPACKAGE_NESTEDCLASS_H
			#define PARENTPACKAGE_NESTEDCLASS_H
			
			typedef struct ParentPackage_NestedClass_struct {
				float someProperty;
			} ParentPackage_NestedClass;
			
			void ParentPackage_NestedClass_anOperation(ParentPackage_NestedClass* const me);
			
			#endif
		'''.toString, code)
	}

	/**
	 * Gleicher Test, aber C-Datei statt H-Datei.
	 */
	@Test def test13_ClassImplementationInNamespace() {
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
		createPackage => [
			name = "TestPackage"
			ownedTypes += class
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "TestClass.h"
			
			void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me) {
				/* hier koennte Ihre Werbung stehen */
			}
		'''.toString, code)
	}

	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test14_ClassImplementationInNamespace2() {
		val class = createClass => [
			name = "NestedClass"
			ownedAttributes += createProperty => [
				name = "someProperty"
				type = createPrimitiveType => [name = "float"]
			]
			ownedOperations += createOperation => [
				name = "anOperation"
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "/* hier koennte Ihre Werbung stehen */"
				]
			]
		]
		createPackage => [
			name = "ParentPackage"
			ownedTypes += class
		]

		val code = (new Uml2C).generateCode(class, "implementation")

		Assert.assertEquals('''
			#include "NestedClass.h"
			
			void ParentPackage_NestedClass_anOperation(ParentPackage_NestedClass* const me) {
				/* hier koennte Ihre Werbung stehen */
			}
		'''.toString, code)
	}
	
	/**
	 * Includes müssen den Pfad zur includeten Datei korrekt auflösen.
	 */
	@Test def test15_ClassHeaderWithIncludeInOtherNamespace() {
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
				ownedParameters += createParameter => [
					name = "param"
					type = createClass => [name = "OtherClass"]
				]
			]
		]
		createPackage => [
			name = "TestPackage"
			ownedTypes += class
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTPACKAGE_TESTCLASS_H
			#define TESTPACKAGE_TESTCLASS_H
			
			#include "../OtherClass.h"
			
			typedef struct TestPackage_TestClass_struct {
				int testProperty;
			} TestPackage_TestClass;
			
			void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me, OtherClass* param);
			
			#endif
		'''.toString, code)
	}
	
	/**
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test16_ClassHeaderWithIncludeInOtherNamespace2() {
		val otherClass = createClass => [name = "OtherClass"]
		
		createPackage => [
			name = "OtherPackage"
			ownedTypes += otherClass
		]

		val anEnum = createEnumeration => [name = "AnEnum"]
		
		createPackage => [
			name = "OtherPackage2"
			ownedTypes += anEnum
		]
		
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createPrimitiveType => [name = "int"]
			]
			ownedAttributes += createProperty => [
				name = "anotherProperty"
				type = anEnum
			]
			ownedOperations += createOperation => [
				name = "testOperation"
				methods += createOpaqueBehavior => [
					languages += "C"
					bodies += "/* hier koennte Ihre Werbung stehen */"
				]
				ownedParameters += createParameter => [
					name = "param"
					type = otherClass
				]
			]
		]
		
		createPackage => [
			name = "TestPackage"
			ownedTypes += class
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTPACKAGE_TESTCLASS_H
			#define TESTPACKAGE_TESTCLASS_H
			
			#include "../OtherPackage/OtherClass.h"
			#include "../OtherPackage2/AnEnum.h"
			
			typedef struct TestPackage_TestClass_struct {
				int testProperty;
				OtherPackage2_AnEnum anotherProperty;
			} TestPackage_TestClass;
			
			void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me, OtherPackage_OtherClass* param);
			
			#endif
		'''.toString, code)
	}

	/**
	 * Include einer Klasse im selben Namespace.
	 */
	@Test def test17_ClassHeaderWithIncludeInSameNamespace() {
		val otherClass = createClass => [name = "OtherClass"]
		
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
				ownedParameters += createParameter => [
					name = "param"
					type = otherClass
				]
			]
		]
		
		createPackage => [
			name = "TestPackage"
			ownedTypes += otherClass
			ownedTypes += class
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTPACKAGE_TESTCLASS_H
			#define TESTPACKAGE_TESTCLASS_H
			
			#include "OtherClass.h"
			
			typedef struct TestPackage_TestClass_struct {
				int testProperty;
			} TestPackage_TestClass;
			
			void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me, TestPackage_OtherClass* param);
			
			#endif
		'''.toString, code)
	}

	/**
	 * Weiterer Test für relative include-Pfade.
	 */
	@Test def test18_ClassHeaderWithIncludeInDeeperNamespace() {
		val otherClass = createClass => [name = "OtherClass"]
		
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
				ownedParameters += createParameter => [
					name = "param"
					type = otherClass
				]
			]
		]
		
		createPackage => [
			name = "TestPackage"
			nestedPackages += createPackage => [
				name = "NestedPackage"
				ownedTypes += otherClass
			]
			ownedTypes += class
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTPACKAGE_TESTCLASS_H
			#define TESTPACKAGE_TESTCLASS_H
			
			#include "NestedPackage/OtherClass.h"
			
			typedef struct TestPackage_TestClass_struct {
				int testProperty;
			} TestPackage_TestClass;
			
			void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me, TestPackage_NestedPackage_OtherClass* param);
			
			#endif
		'''.toString, code)
	}

	/**
	 * Namespaces können beliebig tief geschachtelt sein.
	 */
	@Test def test19_ClassHeaderWithMapyIncludes() {
		val class = createClass => [
			name = "MainClass"
			ownedAttributes += createProperty => [
				name = "f"
				type = createClass => [name = "F"]
			]
			ownedAttributes += createProperty => [
				name = "b"
				type = createClass => [name = "B"]
			]
			ownedAttributes += createProperty => [
				name = "c"
				type = createClass => [name = "C"]
			]
			ownedAttributes += createProperty => [
				name = "a"
				type = createClass => [name = "A"]
			]
			ownedAttributes += createProperty => [
				name = "e"
				type = createClass => [name = "E"]
			]
			ownedAttributes += createProperty => [
				name = "d"
				type = createClass => [name = "D"]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef MAINCLASS_H
			#define MAINCLASS_H
			
			#include "A.h"
			#include "B.h"
			#include "C.h"
			#include "D.h"
			#include "E.h"
			#include "F.h"
			
			typedef struct MainClass_struct {
				F* f;
				B* b;
				C* c;
				A* a;
				E* e;
				D* d;
			} MainClass;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Nicht nur Klassen, auch Enumerationen müssen included werden.
	 */
	@Test def test20_ClassHeaderWithEnumIncludeFromProperty() {
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createEnumeration => [name = "AnEnum"]
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTCLASS_H
			#define TESTCLASS_H
			
			#include "AnEnum.h"
			
			typedef struct TestClass_struct {
				AnEnum testProperty;
			} TestClass;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Auch Enums können in in anderen Namespaces liegen.
	 */
	@Test def test21_ClassHeaderWithEnumInOtherNamespaceIncludeFromProperty() {
		val enu = createEnumeration => [name = "AnEnum"]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = enu
			]
		]
		createModel => [
			name = "wurzel"
			packagedElements += createPackage => [
				name ="kiste"
				packagedElements += enu
			]
			packagedElements += createPackage => [
				name ="karton"
				packagedElements += class
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef WURZEL_KARTON_TESTCLASS_H
			#define WURZEL_KARTON_TESTCLASS_H
			
			#include "../kiste/AnEnum.h"
			
			typedef struct wurzel_karton_TestClass_struct {
				wurzel_kiste_AnEnum testProperty;
			} wurzel_karton_TestClass;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Dependencies können verwendet werden um includes zu erzeugen, auch wenn der Type nicht in einem Paramete roder Property auftaucht der Klasse.
	 * Tip: umlClass.relationships.filter(Dependency)
	 */
	@Test def test22_ClassHeaderWithDependencyInclude() {
		val otherClass = createClass => [
			name = "OtherClass"
		]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = createEnumeration => [name = "AnEnum"]
			]
		]
		createDependency => [
			name = "dep"
			clients += class
			suppliers += otherClass
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef TESTCLASS_H
			#define TESTCLASS_H
			
			#include "AnEnum.h"
			#include "OtherClass.h"
			
			typedef struct TestClass_struct {
				AnEnum testProperty;
			} TestClass;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Includes durch Dependencies müssen auch relative Pfade erzeugen.
	 */
	@Test def test23_ClassHeaderWithDependencyInOtherNamespaceInclude() {
		val otherClass = createClass => [
			name = "OtherClass"
		]
		val enu = createEnumeration => [name = "AnEnum"]
		val class = createClass => [
			name = "TestClass"
			ownedAttributes += createProperty => [
				name = "testProperty"
				type = enu
			]
		]
		createModel => [
			name = "wurzel"
			packagedElements += createPackage => [
				name ="kiste"
				packagedElements += enu
			]
			packagedElements += createPackage => [
				name ="karton"
				packagedElements += class
			]
			packagedElements += createPackage => [
				name ="packet"
				packagedElements += otherClass
			]
			packagedElements += createDependency => [
				name = "dep"
				clients += class
				suppliers += otherClass
			]
		]

		val code = (new Uml2C).generateCode(class, "declaration")

		Assert.assertEquals('''
			#ifndef WURZEL_KARTON_TESTCLASS_H
			#define WURZEL_KARTON_TESTCLASS_H
			
			#include "../kiste/AnEnum.h"
			#include "../packet/OtherClass.h"
			
			typedef struct wurzel_karton_TestClass_struct {
				wurzel_kiste_AnEnum testProperty;
			} wurzel_karton_TestClass;
			
			#endif
		'''.toString, code)
	}

	/**
	 * Includes in einer H-Datei sollen alphabetisch sortiert sein.
	 */
	@Test def test24_ClassHeaderWithSortedIncludes() {
		val d = createClass => [name = "D"]
		val b = createClass => [name = "B"]
		val a = createClass => [name = "A"]
		val c = createClass => [
			name = "C"
			ownedAttributes += createProperty => [
				name = "pa"
				type = a
			]
			ownedAttributes += createProperty => [
				name = "pb"
				type = b
			]
			ownedAttributes += createProperty => [
				name = "pb2"
				type = b
			]
			ownedAttributes += createProperty => [
				name = "pd"
				type = d
			]
		]
		createModel => [
			name = "r"
			packagedElements += createPackage => [
				name ="z"
				packagedElements += a
			]
			packagedElements += createPackage => [
				name ="y"
				packagedElements += b
			]
			packagedElements += createPackage => [
				name ="x"
				packagedElements += c
			]
			packagedElements += createPackage => [
				name ="w"
				packagedElements += d
			]
		]

		val code = (new Uml2C).generateCode(c, "declaration")

		Assert.assertEquals('''
			#ifndef R_X_C_H
			#define R_X_C_H
			
			#include "../w/D.h"
			#include "../y/B.h"
			#include "../z/A.h"
			
			typedef struct r_x_C_struct {
				r_z_A* pa;
				r_y_B* pb;
				r_y_B* pb2;
				r_w_D* pd;
			} r_x_C;
			
			#endif
		'''.toString, code)
	}
}

