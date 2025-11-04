package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.NamedElement

class NameTemplate implements Template<NamedElement> {

	override generateCode(CodegenInterface it, NamedElement elem, String context) {
		if (null === elem) {
			return "<null>"
		}
		if (null !== elem.namespace) {
			return '''«generate(elem.namespace, "name")»_«elem.name»'''
		}
		return elem.name
	}

}
