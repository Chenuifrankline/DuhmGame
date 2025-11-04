package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.EnumerationLiteral

class EnumLiteralTemplate implements Template<EnumerationLiteral> {

	override generateCode(CodegenInterface it, EnumerationLiteral umlEnumLiteral, String context) {
		val name = generate(umlEnumLiteral, "name")
		if (null !== umlEnumLiteral.specification) {
			val value = generate(umlEnumLiteral.specification, "value")
			'''«name» = «value»'''
		} else {
			'''«name»'''
		}
	}
}
