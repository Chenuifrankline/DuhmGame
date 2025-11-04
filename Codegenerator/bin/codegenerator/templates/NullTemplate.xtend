package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template

class NullTemplate implements Template<Object> {

	override generateCode(CodegenInterface it, Object object, String context) {
		""
	}

}
