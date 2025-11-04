package codegenerator

import java.nio.file.Path

interface CodegenInterface {

	def String generate(Object object, String context)

	def void logInfo(String message, Object object, String context)

	def void logWarning(String message, Object object, String context)

	def void logError(String message, Object object, String context)

	def void logInfo(String message, Exception e, Object object, String context)

	def void logWarning(String message, Exception e, Object object, String context)

	def void logError(String message, Exception e, Object object, String context)

	def Path getPath(Object obj, String context)

	def Object getOption(String option)
}
