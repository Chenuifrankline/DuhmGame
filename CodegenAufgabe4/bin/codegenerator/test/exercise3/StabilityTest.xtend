package codegenerator.test.exercise3

import codegenerator.Uml2C
import java.util.ArrayList
import java.util.Arrays
import java.util.List
import org.eclipse.uml2.uml.AggregationKind
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Test

class StabilityTest {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	def <T> randomPick(List<T> l) {
		return l.get((l.length * Math.random()) as int)
	}

	@Test def void testType() {
		val contexts = Arrays.asList("type", "name")
		for (var i = 0; i < 100; i += 1) {
			val umlObj = getRandomType(10)
			(new Uml2C).generateCode(umlObj, randomPick(contexts))
		}
	}

	@Test def void testProperty() {
		val contexts = Arrays.asList("attribute", "name")
		for (var i = 0; i < 100; i += 1) {
			val umlObj = createRandomProperty(10)
			(new Uml2C).generateCode(umlObj, randomPick(contexts))
		}
	}

	@Test def void testParameter() {
		val contexts = Arrays.asList("parameter", "name")
		for (var i = 0; i < 100; i += 1) {
			val umlObj = createRandomParameter(10)
			(new Uml2C).generateCode(umlObj, randomPick(contexts))
		}
	}

	@Test def void testOperations() {
		val contexts = Arrays.asList("declaration", "implementation", "name")
		for (var i = 0; i < 100; i += 1) {
			val umlObj = createRandomClass(10)
			(new Uml2C).generateCode(umlObj, randomPick(contexts))
		}
	}

	@Test def void testClass() {
		val contexts = Arrays.asList("declaration", "implementation", "name")
		for (var i = 0; i < 100; i += 1) {
			val umlObj = createRandomClass(10)
			(new Uml2C).generateCode(umlObj, randomPick(contexts))
		}
	}

	static var int counter = 0;
	static val List<Type> types = new ArrayList()

	def createIdentifier() {
		val id = "identifier" + counter
		counter += 1;
		return id
	}

	def getRandomType(int level) {
		if (0 < level) {
			if (!types.empty && Math.random() < 0.5) {
				return randomPick(types)
			}
			if (Math.random() < 0.5) {
				return createRandomPrimitiveType()
			}
			if (Math.random() < 0.5) {
				return createRandomEnum()
			}
			if (Math.random() < 0.5) {
				return createRandomClass(level - 1)
			}
		}
		return null
	}

	def createRandomParameter(int level) {
		return createParameter() => [
			name = createIdentifier()
			type = getRandomType(level - 1)
			val dir = Math.random()
			if (dir < 0.25)     direction = ParameterDirectionKind.IN_LITERAL
			else if (dir < 0.5) direction = ParameterDirectionKind.OUT_LITERAL
			else                direction = ParameterDirectionKind.INOUT_LITERAL
		]
	}

	def createRandomProperty(int level) {
		return createProperty() => [
			name = createIdentifier()
			type = getRandomType(level - 1)
			val dir = Math.random()
			if (dir < 0.5) aggregation = AggregationKind.NONE_LITERAL
			else           aggregation = AggregationKind.COMPOSITE_LITERAL
			isStatic = (Math.random() < 0.25)
		]
	}

	def createRandomOperation(int level) {
		return createOperation() => [
			name = createIdentifier()
			val numParams = (Math.random() * 10) as int
			for (var i = 0; i < numParams; i += 1) {
				ownedParameters += createRandomParameter(level - 1)
			}
			isStatic = (Math.random() < 0.25)
			methods += createOpaqueBehavior() => [
				languages += "C"
				bodies += "/*impl*/"
			]
		]
	}

	def createRandomPrimitiveType() {
		val primitive = createPrimitiveType() => [
			name = createIdentifier()
		]
		types += primitive
		return primitive
	}

	def createRandomEnum() {
		val enum = createEnumeration() => [
			name = createIdentifier()
			val numLits = (Math.random() * 10) as int
			for (var i = 0; i < numLits; i += 1) {
				ownedLiterals += createEnumerationLiteral => [
					name = createIdentifier()
				]
			}
		]
		types += enum
		return enum
	}

	def Package createNamespace(int level) {
		val pack = createPackage() => [
			name = createIdentifier()
		]
		if (0 < level && Math.random() < 0.1) {
			createNamespace(level - 1) => [
				ownedMembers += pack
			]
		}
		return pack
	}

	def Class createRandomClass(int level) {
		val clazz = createClass() => [
			name = createIdentifier()
			val numProps = (Math.random() * 10) as int
			for (var i = 0; i < numProps; i += 1) {
				ownedAttributes += createRandomProperty(level - 1)
			}
			val numOps = (Math.random() * 10) as int
			for (var i = 0; i < numOps; i += 1) {
				val op = createRandomOperation(level - 1)
				ownedOperations += op
				ownedBehaviors += op.methods
			}
		]
		types += clazz
		return clazz
	}

}