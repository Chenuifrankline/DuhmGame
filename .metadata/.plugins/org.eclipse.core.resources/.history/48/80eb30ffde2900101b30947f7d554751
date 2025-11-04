package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.uml2.uml.Type

class TypeTemplate implements Template<Type> {

	override generateCode(CodegenInterface it, Type umlType, String context) {
		switch umlType {
			PrimitiveType:
				generate(umlType, "name")
			Class: '''«generate(umlType, "name")»*'''
			case null:
				"void*"
			default: '''<codegenerator for type "«umlType.eClass.name»" is not yet implemented>'''
		}
	}

}
