package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import org.eclipse.uml2.uml.LiteralBoolean
import org.eclipse.uml2.uml.LiteralInteger
import org.eclipse.uml2.uml.LiteralNull
import org.eclipse.uml2.uml.LiteralReal
import org.eclipse.uml2.uml.LiteralString
import org.eclipse.uml2.uml.LiteralUnlimitedNatural
import org.eclipse.uml2.uml.ValueSpecification

class ValueSpecTemplate implements Template<ValueSpecification> {

	override generateCode(CodegenInterface it, ValueSpecification umlLiteralSpec, String context) {
		switch umlLiteralSpec {
			LiteralInteger:
				return String.valueOf(umlLiteralSpec.integerValue)
			LiteralUnlimitedNatural:
				return String.valueOf(umlLiteralSpec.integerValue)
			LiteralReal:
				return String.valueOf(umlLiteralSpec.realValue)
			LiteralString: {
				val string = umlLiteralSpec.stringValue ?: ""
				return '"' + string.replace('\\', "\\\\").replace('\n', "\\n").replace('\r', "\\r").replace('"', "\\\"") + '"'
			}
			LiteralNull:
				return "NULL"
			LiteralBoolean:
				return if(umlLiteralSpec.booleanValue) "1" else "0"
		}

		return umlLiteralSpec.stringValue
	}

}
