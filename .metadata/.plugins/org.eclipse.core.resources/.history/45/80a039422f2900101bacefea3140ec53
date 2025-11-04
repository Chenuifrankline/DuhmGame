package codegenerator

import codegenerator.templates.ClassTemplate
import codegenerator.templates.EnumLiteralTemplate
import codegenerator.templates.EnumTemplate
import codegenerator.templates.NameTemplate
import codegenerator.templates.NullTemplate
import codegenerator.templates.OpaqueBehaviorTemplate
import codegenerator.templates.OperationTemplate
import codegenerator.templates.ParameterTemplate
import codegenerator.templates.PropertyTemplate
import codegenerator.templates.StructuredClassifierTemplate
import codegenerator.templates.TypeTemplate
import codegenerator.templates.ValueSpecTemplate
import java.nio.file.Path
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.LinkedList
import java.util.Map
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.uml2.uml.Behavior
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.EnumerationLiteral
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.StructuredClassifier
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.ValueSpecification

/**
 * Code generator that takes UML Elements and generates C code from them.
 */
class Uml2C {

	/**
	 * Collection of all templates known to the code generator.
	 * Templates are registered for a combination of type and context.
	 * When generating code, templates are chosen by matching the context
	 * string exactly, and searching the type hierarchy of the given object
	 * until a type is found for which a template is registered.
	 */
	val templates = new HashMap<Pair<java.lang.Class<?>, String>, Template<?>>();

	/**
	 * This cache is filled at run-time each time a template is found by
	 * searching the the type hierarchy. This way subsequent template lookups
	 * are faster.
	 */
	val templatesCache = new HashMap<Pair<java.lang.Class<?>, String>, Template<?>>();

	/**
	 * This set contains all context strings that templates are registered for.
	 */
	val contexts = new HashSet<String>();

	/**
	 * The CodegenInterface that is passed to all templates.
	 */
	var CodegenInterface generatorInterface;

	/**
	 * Constructor, here all templates should be registered.
	 */
	new(Map<String, Object> options) {
		generatorInterface = new Uml2CInterface(this, options)

		registerTemplate(new ClassTemplate(), typeof(Class), "implementation")
		registerTemplate(new ClassTemplate(), typeof(Class), "declaration")
		registerTemplate(new NullTemplate(), typeof(Behavior), "implementation")
		registerTemplate(new NullTemplate(), typeof(Behavior), "declaration")
		registerTemplate(new EnumLiteralTemplate(), typeof(EnumerationLiteral), "enumliteral")
		registerTemplate(new EnumTemplate(), typeof(Enumeration), "typedefinition")
		registerTemplate(new EnumTemplate(), typeof(Enumeration), "declaration")
		registerTemplate(new EnumTemplate(), typeof(Enumeration), "implementation")
		registerTemplate(new ValueSpecTemplate(), typeof(ValueSpecification), "value")
		registerTemplate(new NameTemplate(), typeof(NamedElement), "name")
		registerTemplate(new OpaqueBehaviorTemplate(), typeof(OpaqueBehavior), "behavior")
		registerTemplate(new OperationTemplate(), typeof(Operation), "implementation")
		registerTemplate(new OperationTemplate(), typeof(Operation), "declaration")
		registerTemplate(new ParameterTemplate(), typeof(Parameter), "parameter")
		registerTemplate(new ParameterTemplate(), typeof(Parameter), "return")
		registerTemplate(new ParameterTemplate(), null, "return")
		registerTemplate(new PropertyTemplate(), typeof(Property), "attribute")
		registerTemplate(new StructuredClassifierTemplate(), typeof(StructuredClassifier), "typedefinition")
		registerTemplate(new TypeTemplate(), typeof(Type), "type")
		registerTemplate(new TypeTemplate(), null, "type")
	}
	new() {
		this(Collections.emptyMap)
	}

	/**
	 * This method looks in its internal registry for a template that matches
	 * the given objects type and context. If one template is found, it is used
	 * to generate code, which is then returned. If no templates for the direct
	 * type of the given object is found, templates for the types super-types
	 * are searched.
	 */
	def String generateCode(Object object, String context) {
		val template = findTemplateFor(object?.class, context) as Template<Object>
		if (null === template) {
			System.err.println("No template found for type '" + object?.class + "' and context '" + context + "'")
			return ""
		}
		try {
			return template.generateCode(generatorInterface, object, context)
		} catch (RuntimeException e) {
			e.printStackTrace
		}
		return ""
	}

	/**
	 * Checks for a template that matches the given objects type and context.
	 * If a matching template exists, the call is passed on to it.
	 * Otherwise an empty String is returned.
	 */
	def Path getPath(Object object, String context) {
		val template = findTemplateFor(object.class, context) as Template<Object>
		if (null !== template) {
			return template.getPath(object, context)
		}
		return null
	}

	/**
	 * This method takes a Resource containing an UML model. It searches for
	 * root templates given the UML Elements. When a root template is found,
	 * the code for that Element is generated recursively and stored in the Map
	 * together with the path for the file generated by the root template.
	 */
	def Map<Path, String> generateModel(Resource modelResource) {
		val result = new HashMap<Path, String>()
		for (obj : modelResource.allContents.toIterable) {
			val type = obj.class
			for (context : contexts) {
				val template = findTemplateFor(type, context) as Template<Object>
				if (null !== template && template.isRoot(obj, context)) {
					result.put(template.getPath(obj, context), template.generateCode(generatorInterface, obj, context))
				}
			}
		}
		return result
	}

	/**
	 * Helper function that adds the given template to the template registry inside this class.
	 */
	private def <T> registerTemplate(Template<? super T> template, java.lang.Class<T> type, String context) {
		val pair = Pair.of(type as java.lang.Class<?>, context)
		templates.put(pair, template)
		templatesCache.put(pair, template)
		contexts.add(context)
	}

	/**
	 * Recursively searches the type hierarchy of the given class, until a template is found
	 * that matches the type and context String.
	 */
	private def Template<?> findTemplateFor(java.lang.Class<?> type, String context) {
		// check the immediate type first
		var result = templatesCache.get(Pair.of(type, context))
		if (null !== result || null === type) {
			return result
		}

		// Use a breadth-first-search to find the most specific template for the given type
		var currSearchLevel = new LinkedList<java.lang.Class<?>>()
		var nextSearchLevel = new LinkedList<java.lang.Class<?>>()
		val encounteredTypes = new HashSet<java.lang.Class<?>>()
		currSearchLevel += type
		while (!currSearchLevel.empty && null === result) {
			val candidates = new LinkedList<Pair<java.lang.Class<?>, Template<?>>>()
			for (currentType : currSearchLevel) {
				val template = templates.get(Pair.of(currentType, context))
				if (null !== template) {
					candidates += Pair.of(currentType, template)
				} else {
					val superclass = currentType.superclass
					if (null !== superclass && !encounteredTypes.contains(superclass)) {
						encounteredTypes.add(superclass)
						nextSearchLevel += superclass
					}
					for (interf : currentType.interfaces) {
						if (!encounteredTypes.contains(interf)) {
							encounteredTypes.add(interf)
							nextSearchLevel += interf
						}
					}
				}
			}
			switch candidates.size {
				case 0: { // no templates found -> progress to next level down
					currSearchLevel.clear
					val tmp = currSearchLevel
					currSearchLevel = nextSearchLevel
					nextSearchLevel = tmp
				}
				case 1: { // one template found -> use that
					result = candidates.head.value
				}
				default: { // more templates found -> error
					System.err.println("Found multiple templates for type '" + type + "' and context '" + context + "'")
					candidates.forEach [ candidate |
						System.err.println("  for type '" + candidate.key + "' template '" + candidate.value + "'")
					]
				}
			}
		}

		// add the found template to the cache, so it can be found faster in the
		// future even if the result is null.
		templatesCache.put(Pair.of(type, context), result)
		return result
	}
}
