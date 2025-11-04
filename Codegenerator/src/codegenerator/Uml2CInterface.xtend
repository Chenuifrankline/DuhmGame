package codegenerator

import java.util.Map

class Uml2CInterface implements CodegenInterface {

	val Uml2C generator
	val Map<String, Object> options

	new(Uml2C generator, Map<String, Object> options) {
		this.generator = generator
		this.options = options
	}

	override generate(Object object, String context) {
		generator.generateCode(object, context)
	}

	override logInfo(String message, Object object, String context) {
		logInfo(message, null, object, context)
	}

	override logWarning(String message, Object object, String context) {
		logWarning(message, null, object, context)
	}

	override logError(String message, Object object, String context) {
		logError(message, null, object, context)
	}

	override logInfo(String message, Exception e, Object object, String context) {
		println("Info for object " + object + " and context " + context)
		println(message)
		if (null !== e) e.printStackTrace
	}

	override logWarning(String message, Exception e, Object object, String context) {
		println("Info for object " + object + " and context " + context)
		println(message)
		if (null !== e) e.printStackTrace
	}

	override logError(String message, Exception e, Object object, String context) {
		println("Info for object " + object + " and context " + context)
		println(message)
		if (null !== e) e.printStackTrace
	}

	override getPath(Object obj, String context) {
		generator.getPath(obj, context)
	}

	override Object getOption(String option) {
		return options.get(option)
	}
}
