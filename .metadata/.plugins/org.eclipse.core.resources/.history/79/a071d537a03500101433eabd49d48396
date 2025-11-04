package codegenerator.test.exercise4

import codegenerator.Uml2C
import org.eclipse.uml2.uml.UMLFactory
import org.junit.Assert
import org.junit.Test

class TestArtifact {

	extension UMLFactory factory = UMLFactory.eINSTANCE

	/*
	 * Wird ein Artifact als Type verwendet, wird der Name generiert.
	 */
	@Test def test00_ArtifactAsType() {
		
		val artifact = createArtifact => [
			name = "uint32_t"
			fileName = "cstdint.h"
		]
		
		Assert.assertEquals("uint32_t", (new Uml2C).generateCode(artifact, "type"))
	}

	/*
	 * Gleicher Test mit anderen Namen.
	 */
	@Test def test01_ArtifactAsType2() {
		
		val artifact = createArtifact => [
			name = "time_t"
			fileName = "ctime.h"
		]
		
		Assert.assertEquals("time_t", (new Uml2C).generateCode(artifact, "type"))
	}

	/*
	 * Wird ein Artifact in eienr Klasse verwendet, wird der FileName im Header der Klasse included.
	 */
	@Test def test02_ArtifactInclude() {
		
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

	/*
	 * Beginnt und endet der FileName mit < und >, werden keine Anführungszeichen generiert.
	 */
	@Test def test03_ArtifactInclude3() {
		
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

	/*
	 * Hat ein Artifact keinen FileName, wird auch kein include generiert.
	 */
	@Test def test04_ArtifactWithNoFile() {
		
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