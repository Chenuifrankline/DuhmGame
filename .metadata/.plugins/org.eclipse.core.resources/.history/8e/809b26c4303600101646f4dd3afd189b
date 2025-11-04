package codegenerator.test.exercise4

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestArtifact {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	@Test def testArtifactAsType() {
		
		val artifact = createArtifact => [
			name = "uint32_t"
			fileName = "cstdint.h"
		]
		
		Assert.assertEquals("uint32_t", (new Uml2C).generateCode(artifact, "type"))
	}

	@Test def testArtifactAsType2() {
		
		val artifact = createArtifact => [
			name = "time_t"
			fileName = "ctime.h"
		]
		
		Assert.assertEquals("time_t", (new Uml2C).generateCode(artifact, "type"))
	}

	@Test def testArtifactInclude() {
		
		val artifact = createArtifact => [
			name = "uint32_t"
			fileName = "cstdint.h"
		]
		val cls = createClass => [
			name = "MyClass"
			ownedAttributes += createProperty => [
				name = "number"
				type = artifact
			]
		]
		
		Assert.assertEquals(
			'''
			#ifndef MYCLASS_H
			#define MYCLASS_H
			
			#include "cstdint.h"
			
			typedef struct MyClass_struct {
				uint32_t number;
			} MyClass;
			
			#endif
			'''.toString,
			(new Uml2C).generateCode(cls, "declaration"))
	}

	@Test def testArtifactInclude2() {
		
		val artifact = createArtifact => [
			name = "uint32_t"
			fileName = "\"cstdint.h\""
		]
		val cls = createClass => [
			name = "MyClass"
			ownedAttributes += createProperty => [
				name = "number"
				type = artifact
			]
		]
		
		Assert.assertEquals(
			'''
			#ifndef MYCLASS_H
			#define MYCLASS_H
			
			#include "cstdint.h"
			
			typedef struct MyClass_struct {
				uint32_t number;
			} MyClass;
			
			#endif
			'''.toString,
			(new Uml2C).generateCode(cls, "declaration"))
	}

	@Test def testArtifactInclude3() {
		
		val artifact = createArtifact => [
			name = "uint32_t"
			fileName = "<cstdint.h>"
		]
		val cls = createClass => [
			name = "MyClass"
			ownedAttributes += createProperty => [
				name = "number"
				type = artifact
			]
		]
		
		Assert.assertEquals(
			'''
			#ifndef MYCLASS_H
			#define MYCLASS_H
			
			#include <cstdint.h>
			
			typedef struct MyClass_struct {
				uint32_t number;
			} MyClass;
			
			#endif
			'''.toString,
			(new Uml2C).generateCode(cls, "declaration"))
	}

	@Test def testArtifactWithNoFile() {
		
		val artifact = createArtifact => [
			name = "float"
		]
		val cls = createClass => [
			name = "MyClass"
			ownedAttributes += createProperty => [
				name = "number"
				type = artifact
			]
		]
		
		Assert.assertEquals(
			'''
			#ifndef MYCLASS_H
			#define MYCLASS_H
			
			typedef struct MyClass_struct {
				float number;
			} MyClass;
			
			#endif
			'''.toString,
			(new Uml2C).generateCode(cls, "declaration"))
	}
}