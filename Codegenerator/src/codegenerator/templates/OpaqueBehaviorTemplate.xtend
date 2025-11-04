package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.OpaqueBehavior

class OpaqueBehaviorTemplate implements Template<OpaqueBehavior> {
	
	override generateCode(CodegenInterface it, OpaqueBehavior umlBehavior, String context) {
		return umlBehavior.bodies.head
	}
	
}