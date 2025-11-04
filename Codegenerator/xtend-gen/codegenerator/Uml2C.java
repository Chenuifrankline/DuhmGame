package codegenerator;

import codegenerator.templates.ArtifactTemplate;
import codegenerator.templates.ClassTemplate;
import codegenerator.templates.EnumLiteralTemplate;
import codegenerator.templates.EnumTemplate;
import codegenerator.templates.MainTemplate;
import codegenerator.templates.NameTemplate;
import codegenerator.templates.NullTemplate;
import codegenerator.templates.OpaqueBehaviorTemplate;
import codegenerator.templates.OperationTemplate;
import codegenerator.templates.ParameterTemplate;
import codegenerator.templates.PropertyTemplate;
import codegenerator.templates.StructuredClassifierTemplate;
import codegenerator.templates.TypeTemplate;
import codegenerator.templates.ValueSpecTemplate;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

/**
 * Code generator that takes UML Elements and generates C code from them.
 */
@SuppressWarnings("all")
public class Uml2C {
  /**
   * Collection of all templates known to the code generator.
   * Templates are registered for a combination of type and context.
   * When generating code, templates are chosen by matching the context
   * string exactly, and searching the type hierarchy of the given object
   * until a type is found for which a template is registered.
   */
  private final HashMap<Pair<Class<?>, String>, Template<?>> templates = new HashMap<Pair<Class<?>, String>, Template<?>>();

  /**
   * This cache is filled at run-time each time a template is found by
   * searching the the type hierarchy. This way subsequent template lookups
   * are faster.
   */
  private final HashMap<Pair<Class<?>, String>, Template<?>> templatesCache = new HashMap<Pair<Class<?>, String>, Template<?>>();

  /**
   * This set contains all context strings that templates are registered for.
   */
  private final HashSet<String> contexts = new HashSet<String>();

  /**
   * The CodegenInterface that is passed to all templates.
   */
  private CodegenInterface generatorInterface;

  /**
   * Constructor, here all templates should be registered.
   */
  public Uml2C(final Map<String, Object> options) {
    Uml2CInterface _uml2CInterface = new Uml2CInterface(this, options);
    this.generatorInterface = _uml2CInterface;
    ClassTemplate _classTemplate = new ClassTemplate();
    this.<org.eclipse.uml2.uml.Class>registerTemplate(_classTemplate, org.eclipse.uml2.uml.Class.class, "implementation");
    ClassTemplate _classTemplate_1 = new ClassTemplate();
    this.<org.eclipse.uml2.uml.Class>registerTemplate(_classTemplate_1, org.eclipse.uml2.uml.Class.class, "declaration");
    NullTemplate _nullTemplate = new NullTemplate();
    this.<Behavior>registerTemplate(_nullTemplate, Behavior.class, "implementation");
    NullTemplate _nullTemplate_1 = new NullTemplate();
    this.<Behavior>registerTemplate(_nullTemplate_1, Behavior.class, "declaration");
    EnumLiteralTemplate _enumLiteralTemplate = new EnumLiteralTemplate();
    this.<EnumerationLiteral>registerTemplate(_enumLiteralTemplate, EnumerationLiteral.class, "enumliteral");
    EnumTemplate _enumTemplate = new EnumTemplate();
    this.<Enumeration>registerTemplate(_enumTemplate, Enumeration.class, "typedefinition");
    EnumTemplate _enumTemplate_1 = new EnumTemplate();
    this.<Enumeration>registerTemplate(_enumTemplate_1, Enumeration.class, "declaration");
    EnumTemplate _enumTemplate_2 = new EnumTemplate();
    this.<Enumeration>registerTemplate(_enumTemplate_2, Enumeration.class, "implementation");
    ValueSpecTemplate _valueSpecTemplate = new ValueSpecTemplate();
    this.<ValueSpecification>registerTemplate(_valueSpecTemplate, ValueSpecification.class, "value");
    NameTemplate _nameTemplate = new NameTemplate();
    this.<NamedElement>registerTemplate(_nameTemplate, NamedElement.class, "name");
    OpaqueBehaviorTemplate _opaqueBehaviorTemplate = new OpaqueBehaviorTemplate();
    this.<OpaqueBehavior>registerTemplate(_opaqueBehaviorTemplate, OpaqueBehavior.class, "behavior");
    OperationTemplate _operationTemplate = new OperationTemplate();
    this.<Operation>registerTemplate(_operationTemplate, Operation.class, "implementation");
    OperationTemplate _operationTemplate_1 = new OperationTemplate();
    this.<Operation>registerTemplate(_operationTemplate_1, Operation.class, "declaration");
    ParameterTemplate _parameterTemplate = new ParameterTemplate();
    this.<Parameter>registerTemplate(_parameterTemplate, Parameter.class, "parameter");
    ParameterTemplate _parameterTemplate_1 = new ParameterTemplate();
    this.<Parameter>registerTemplate(_parameterTemplate_1, Parameter.class, "return");
    ParameterTemplate _parameterTemplate_2 = new ParameterTemplate();
    this.<Parameter>registerTemplate(_parameterTemplate_2, null, "return");
    PropertyTemplate _propertyTemplate = new PropertyTemplate();
    this.<Property>registerTemplate(_propertyTemplate, Property.class, "attribute");
    StructuredClassifierTemplate _structuredClassifierTemplate = new StructuredClassifierTemplate();
    this.<StructuredClassifier>registerTemplate(_structuredClassifierTemplate, StructuredClassifier.class, "typedefinition");
    TypeTemplate _typeTemplate = new TypeTemplate();
    this.<Type>registerTemplate(_typeTemplate, Type.class, "type");
    TypeTemplate _typeTemplate_1 = new TypeTemplate();
    this.<Type>registerTemplate(_typeTemplate_1, null, "type");
    ArtifactTemplate _artifactTemplate = new ArtifactTemplate();
    this.<Artifact>registerTemplate(_artifactTemplate, Artifact.class, "type");
    ArtifactTemplate _artifactTemplate_1 = new ArtifactTemplate();
    this.<Artifact>registerTemplate(_artifactTemplate_1, Artifact.class, "include");
    ArtifactTemplate _artifactTemplate_2 = new ArtifactTemplate();
    this.<Artifact>registerTemplate(_artifactTemplate_2, Artifact.class, "name");
    MainTemplate _mainTemplate = new MainTemplate();
    this.<Model>registerTemplate(_mainTemplate, Model.class, "main");
  }

  public Uml2C() {
    this(Collections.<String, Object>emptyMap());
  }

  /**
   * This method looks in its internal registry for a template that matches
   * the given objects type and context. If one template is found, it is used
   * to generate code, which is then returned. If no templates for the direct
   * type of the given object is found, templates for the types super-types
   * are searched.
   */
  public String generateCode(final Object object, final String context) {
    Class<?> _class = null;
    if (object!=null) {
      _class=object.getClass();
    }
    Template<?> _findTemplateFor = this.findTemplateFor(_class, context);
    final Template<Object> template = ((Template<Object>) _findTemplateFor);
    if ((null == template)) {
      Class<?> _class_1 = null;
      if (object!=null) {
        _class_1=object.getClass();
      }
      String _plus = ("No template found for type \'" + _class_1);
      String _plus_1 = (_plus + "\' and context \'");
      String _plus_2 = (_plus_1 + context);
      String _plus_3 = (_plus_2 + "\'");
      System.err.println(_plus_3);
      return "";
    }
    try {
      return template.generateCode(this.generatorInterface, object, context);
    } catch (final Throwable _t) {
      if (_t instanceof RuntimeException) {
        final RuntimeException e = (RuntimeException)_t;
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return "";
  }

  /**
   * Checks for a template that matches the given objects type and context.
   * If a matching template exists, the call is passed on to it.
   * Otherwise an empty String is returned.
   */
  public Path getPath(final Object object, final String context) {
    Template<?> _findTemplateFor = this.findTemplateFor(object.getClass(), context);
    final Template<Object> template = ((Template<Object>) _findTemplateFor);
    if ((null != template)) {
      return template.getPath(object, context);
    }
    return null;
  }

  /**
   * This method takes a Resource containing an UML model. It searches for
   * root templates given the UML Elements. When a root template is found,
   * the code for that Element is generated recursively and stored in the Map
   * together with the path for the file generated by the root template.
   */
  public Map<Path, String> generateModel(final Resource modelResource) {
    final HashMap<Path, String> result = new HashMap<Path, String>();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(modelResource.getAllContents());
    for (final EObject obj : _iterable) {
      {
        final Class<? extends EObject> type = obj.getClass();
        for (final String context : this.contexts) {
          {
            Template<?> _findTemplateFor = this.findTemplateFor(type, context);
            final Template<Object> template = ((Template<Object>) _findTemplateFor);
            if (((null != template) && template.isRoot(obj, context))) {
              result.put(template.getPath(obj, context), template.generateCode(this.generatorInterface, obj, context));
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Helper function that adds the given template to the template registry inside this class.
   */
  private <T extends Object> boolean registerTemplate(final Template<? super T> template, final Class<T> type, final String context) {
    boolean _xblockexpression = false;
    {
      final Pair<Class<?>, String> pair = Pair.<Class<?>, String>of(((Class<?>) type), context);
      this.templates.put(pair, template);
      this.templatesCache.put(pair, template);
      _xblockexpression = this.contexts.add(context);
    }
    return _xblockexpression;
  }

  /**
   * Recursively searches the type hierarchy of the given class, until a template is found
   * that matches the type and context String.
   */
  private Template<?> findTemplateFor(final Class<?> type, final String context) {
    Template<?> result = this.templatesCache.get(Pair.<Class<?>, String>of(type, context));
    if (((null != result) || (null == type))) {
      return result;
    }
    LinkedList<Class<?>> currSearchLevel = new LinkedList<Class<?>>();
    LinkedList<Class<?>> nextSearchLevel = new LinkedList<Class<?>>();
    final HashSet<Class<?>> encounteredTypes = new HashSet<Class<?>>();
    currSearchLevel.add(type);
    while (((!currSearchLevel.isEmpty()) && (null == result))) {
      {
        final LinkedList<Pair<Class<?>, Template<?>>> candidates = new LinkedList<Pair<Class<?>, Template<?>>>();
        for (final Class<?> currentType : currSearchLevel) {
          {
            final Template<?> template = this.templates.get(Pair.<Class<?>, String>of(currentType, context));
            if ((null != template)) {
              Pair<Class<?>, Template<?>> _of = Pair.<Class<?>, Template<?>>of(currentType, template);
              candidates.add(_of);
            } else {
              final Class<?> superclass = currentType.getSuperclass();
              if (((null != superclass) && (!encounteredTypes.contains(superclass)))) {
                encounteredTypes.add(superclass);
                nextSearchLevel.add(superclass);
              }
              Class<?>[] _interfaces = currentType.getInterfaces();
              for (final Class<?> interf : _interfaces) {
                boolean _contains = encounteredTypes.contains(interf);
                boolean _not = (!_contains);
                if (_not) {
                  encounteredTypes.add(interf);
                  nextSearchLevel.add(interf);
                }
              }
            }
          }
        }
        int _size = candidates.size();
        switch (_size) {
          case 0:
            currSearchLevel.clear();
            final LinkedList<Class<?>> tmp = currSearchLevel;
            currSearchLevel = nextSearchLevel;
            nextSearchLevel = tmp;
            break;
          case 1:
            result = IterableExtensions.<Pair<Class<?>, Template<?>>>head(candidates).getValue();
            break;
          default:
            {
              System.err.println((((("Found multiple templates for type \'" + type) + "\' and context \'") + context) + "\'"));
              final Consumer<Pair<Class<?>, Template<?>>> _function = (Pair<Class<?>, Template<?>> candidate) -> {
                Class<?> _key = candidate.getKey();
                String _plus = ("  for type \'" + _key);
                String _plus_1 = (_plus + "\' template \'");
                Template<?> _value = candidate.getValue();
                String _plus_2 = (_plus_1 + _value);
                String _plus_3 = (_plus_2 + "\'");
                System.err.println(_plus_3);
              };
              candidates.forEach(_function);
            }
            break;
        }
      }
    }
    this.templatesCache.put(Pair.<Class<?>, String>of(type, context), result);
    return result;
  }
}
