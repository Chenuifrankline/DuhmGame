package codegenerator.templates

import codegenerator.CodegenInterface
import codegenerator.Template
import java.nio.file.Path
import java.nio.file.Paths
import java.util.LinkedList
import org.eclipse.uml2.uml.Enumeration

class EnumTemplate implements Template<Enumeration> {

	override generateCode(CodegenInterface it, Enumeration umlEnum, String context) {
		val name = generate(umlEnum, "name")
		switch (context) {
			case "typedefinition": {
				return '''
					typedef enum {
						«FOR literal : umlEnum.ownedLiterals SEPARATOR ','»
							«generate(literal, "enumliteral")»
						«ENDFOR»
					} «name»;
				'''
			}
			case "declaration": {
				return '''
					#ifndef «name.toUpperCase»_H
					#define «name.toUpperCase»_H
					
					«generate(umlEnum, "typedefinition")»
					
					extern «name» «name»_Literals[«umlEnum.ownedLiterals.size»];
					
					#endif
				'''
			}
			case "implementation": {
				return '''
					#include "«umlEnum.name».h"
					
					«name» «name»_Literals[«umlEnum.ownedLiterals.size»] = {
						«FOR literal : umlEnum.ownedLiterals SEPARATOR ','»
							«generate(literal, "name")»
						«ENDFOR»
					};
				'''
			}
		}
	}

	override Path getPath(Enumeration umlEnum, String context) {
		var path = new LinkedList<String>()
		switch context {
			case "declaration": {
				path.addFirst(umlEnum.name + ".h")
			}
			case "implementation": {
				path.addFirst(umlEnum.name + ".c")
			}
			default:
				return null
		}
		var parent = umlEnum.namespace
		while (null !== parent) {
			path.addFirst(parent.name)
			parent = parent.namespace
		}
		return Paths.get(path.head, path.tail)
	}
}
