package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ClassTemplate implements Template<org.eclipse.uml2.uml.Class> {
  @Override
  public String generateCode(final CodegenInterface it, final org.eclipse.uml2.uml.Class umlClass, final String context) {
    String _switchResult = null;
    if (context != null) {
      switch (context) {
        case "declaration":
          _switchResult = this.genH(it, umlClass);
          break;
        case "implementation":
          _switchResult = this.genC(it, umlClass);
          break;
        default:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("unbekannter Kontext ");
          _builder.append(context);
          _switchResult = _builder.toString();
          break;
      }
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("unbekannter Kontext ");
      _builder.append(context);
      _switchResult = _builder.toString();
    }
    return _switchResult;
  }

  public String genC(final CodegenInterface it, final org.eclipse.uml2.uml.Class umlClass) {
    String _name = null;
    if (umlClass!=null) {
      _name=umlClass.getName();
    }
    final String className = _name;
    Model _model = umlClass.getModel();
    EList<PackageableElement> _packagedElements = null;
    if (_model!=null) {
      _packagedElements=_model.getPackagedElements();
    }
    Iterable<InstanceSpecification> _filter = null;
    if (_packagedElements!=null) {
      _filter=Iterables.<InstanceSpecification>filter(_packagedElements, InstanceSpecification.class);
    }
    Iterable<InstanceSpecification> _filter_1 = null;
    if (_filter!=null) {
      final Function1<InstanceSpecification, Boolean> _function = (InstanceSpecification i) -> {
        return Boolean.valueOf(((i.getClassifiers().size() == 1) && Objects.equal(IterableExtensions.<Classifier>head(i.getClassifiers()), umlClass)));
      };
      _filter_1=IterableExtensions.<InstanceSpecification>filter(_filter, _function);
    }
    final Iterable<InstanceSpecification> instances = _filter_1;
    Iterable<String> _map = null;
    if (instances!=null) {
      final Function1<InstanceSpecification, String> _function_1 = (InstanceSpecification i) -> {
        String _xblockexpression = null;
        {
          final String typeName = it.generate(umlClass, "name");
          final String instanceName = it.generate(i, "name");
          final Function1<Slot, String> _function_2 = (Slot slot) -> {
            String _xblockexpression_1 = null;
            {
              final StructuralFeature attr = slot.getDefiningFeature();
              String _name_1 = null;
              if (attr!=null) {
                _name_1=attr.getName();
              }
              final String attrName = _name_1;
              if (((attrName == null) || slot.getValues().isEmpty())) {
                return null;
              }
              String _xifexpression = null;
              int _size = slot.getValues().size();
              boolean _greaterThan = (_size > 1);
              if (_greaterThan) {
                String _xblockexpression_2 = null;
                {
                  final Function1<ValueSpecification, String> _function_3 = (ValueSpecification v) -> {
                    return it.generate(v, "value");
                  };
                  final List<String> valueList = ListExtensions.<ValueSpecification, String>map(slot.getValues(), _function_3);
                  StringConcatenation _builder = new StringConcatenation();
                  _builder.append("\t");
                  _builder.append(".");
                  _builder.append(attrName, "\t");
                  _builder.append(" = {");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t\t");
                  String _join = IterableExtensions.join(valueList, ",\n");
                  _builder.append(_join, "\t\t");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("}");
                  _xblockexpression_2 = _builder.toString();
                }
                _xifexpression = _xblockexpression_2;
              } else {
                String _xblockexpression_3 = null;
                {
                  final String singleValue = it.generate(IterableExtensions.<ValueSpecification>head(slot.getValues()), "value");
                  StringConcatenation _builder = new StringConcatenation();
                  _builder.append("\t");
                  _builder.append(".");
                  _builder.append(attrName, "\t");
                  _builder.append(" = ");
                  _builder.append(singleValue, "\t");
                  _xblockexpression_3 = _builder.toString();
                }
                _xifexpression = _xblockexpression_3;
              }
              _xblockexpression_1 = _xifexpression;
            }
            return _xblockexpression_1;
          };
          final List<String> slotInits = ListExtensions.<Slot, String>map(i.getSlots(), _function_2);
          String _xifexpression = null;
          boolean _isEmpty = slotInits.isEmpty();
          if (_isEmpty) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(typeName);
            _builder.append(" ");
            _builder.append(instanceName);
            _builder.append(" = {");
            _builder.newLineIfNotEmpty();
            _builder.append("};");
            _xifexpression = _builder.toString();
          } else {
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append(typeName);
            _builder_1.append(" ");
            _builder_1.append(instanceName);
            _builder_1.append(" = {");
            _builder_1.newLineIfNotEmpty();
            String _join = IterableExtensions.join(slotInits, ",\n");
            _builder_1.append(_join);
            _builder_1.newLineIfNotEmpty();
            _builder_1.append("};");
            _xifexpression = _builder_1.toString();
          }
          _xblockexpression = _xifexpression;
        }
        return _xblockexpression;
      };
      _map=IterableExtensions.<InstanceSpecification, String>map(instances, _function_1);
    }
    final Iterable<String> instanceDefs = _map;
    final Function1<Operation, String> _function_2 = (Operation op) -> {
      return it.generate(op, "implementation");
    };
    final List<String> operations = ListExtensions.<Operation, String>map(umlClass.getOwnedOperations(), _function_2);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"");
    _builder.append(className);
    _builder.append(".h\"");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _join = IterableExtensions.join(operations, "\n\n");
    _builder.append(_join);
    _builder.newLineIfNotEmpty();
    {
      if (((instanceDefs != null) && (!IterableExtensions.isEmpty(instanceDefs)))) {
        String _join_1 = IterableExtensions.join(instanceDefs, "\n\n");
        _builder.append(_join_1);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }

  public String genH(final CodegenInterface it, final org.eclipse.uml2.uml.Class umlClass) {
    String _xifexpression = null;
    org.eclipse.uml2.uml.Package _package = umlClass.getPackage();
    boolean _tripleNotEquals = (_package != null);
    if (_tripleNotEquals) {
      String _generate = it.generate(umlClass.getPackage(), "name");
      _xifexpression = (_generate + "_");
    } else {
      _xifexpression = "";
    }
    final String packageName = _xifexpression;
    String _name = umlClass.getName();
    final String className = (packageName + _name).toUpperCase();
    final String structCode = it.generate(umlClass, "typedefinition");
    final String includes = this.generateIncludes(it, umlClass).trim();
    Model _model = umlClass.getModel();
    EList<PackageableElement> _packagedElements = null;
    if (_model!=null) {
      _packagedElements=_model.getPackagedElements();
    }
    Iterable<InstanceSpecification> _filter = null;
    if (_packagedElements!=null) {
      _filter=Iterables.<InstanceSpecification>filter(_packagedElements, InstanceSpecification.class);
    }
    Iterable<InstanceSpecification> _filter_1 = null;
    if (_filter!=null) {
      final Function1<InstanceSpecification, Boolean> _function = (InstanceSpecification i) -> {
        return Boolean.valueOf(((i.getClassifiers().size() == 1) && Objects.equal(IterableExtensions.<Classifier>head(i.getClassifiers()), umlClass)));
      };
      _filter_1=IterableExtensions.<InstanceSpecification>filter(_filter, _function);
    }
    final Iterable<InstanceSpecification> instances = _filter_1;
    final Function1<Operation, String> _function_1 = (Operation op) -> {
      return it.generate(op, "declaration");
    };
    final List<String> operations = ListExtensions.<Operation, String>map(umlClass.getOwnedOperations(), _function_1);
    Iterable<String> _map = null;
    if (instances!=null) {
      final Function1<InstanceSpecification, String> _function_2 = (InstanceSpecification i) -> {
        String _xblockexpression = null;
        {
          final String classTypeName = it.generate(umlClass, "name");
          final String instanceName = it.generate(i, "name");
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("extern ");
          _builder.append(classTypeName);
          _builder.append(" ");
          _builder.append(instanceName);
          _builder.append(";");
          _xblockexpression = _builder.toString();
        }
        return _xblockexpression;
      };
      _map=IterableExtensions.<InstanceSpecification, String>map(instances, _function_2);
    }
    final Iterable<String> instanceDecls = _map;
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef ");
    _builder.append(className);
    _builder.append("_H");
    _builder.newLineIfNotEmpty();
    _builder.append("#define ");
    _builder.append(className);
    _builder.append("_H");
    _builder.newLineIfNotEmpty();
    {
      boolean _isEmpty = includes.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.newLine();
        _builder.append(includes);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append(structCode);
    {
      boolean _isEmpty_1 = operations.isEmpty();
      boolean _not_1 = (!_isEmpty_1);
      if (_not_1) {
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        String _join = IterableExtensions.join(operations, "\n\n");
        _builder.append(_join);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      if (((instanceDecls != null) && (!IterableExtensions.isEmpty(instanceDecls)))) {
        String _join_1 = IterableExtensions.join(instanceDecls, "\n");
        _builder.append(_join_1);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    _builder.append("#endif");
    _builder.newLine();
    return _builder.toString();
  }

  public String generateIncludes(final CodegenInterface it, final org.eclipse.uml2.uml.Class umlClass) {
    String _xblockexpression = null;
    {
      final HashSet<Type> types = new HashSet<Type>();
      final HashSet<Artifact> artifacts = new HashSet<Artifact>();
      EList<Property> _ownedAttributes = umlClass.getOwnedAttributes();
      for (final Property property : _ownedAttributes) {
        if ((((property.getType() != null) && (!(property.getType() instanceof PrimitiveType))) && (property.getType() != umlClass))) {
          if (((property.getType() instanceof org.eclipse.uml2.uml.Class) || (property.getType() instanceof Enumeration))) {
            types.add(property.getType());
          } else {
            Type _type = property.getType();
            if ((_type instanceof Artifact)) {
              Type _type_1 = property.getType();
              artifacts.add(((Artifact) _type_1));
            }
          }
        }
      }
      EList<Operation> _ownedOperations = umlClass.getOwnedOperations();
      for (final Operation operation : _ownedOperations) {
        {
          if ((((operation.getType() != null) && (!(operation.getType() instanceof PrimitiveType))) && (operation.getType() != umlClass))) {
            if (((operation.getType() instanceof org.eclipse.uml2.uml.Class) || (operation.getType() instanceof Enumeration))) {
              types.add(operation.getType());
            } else {
              Type _type_2 = operation.getType();
              if ((_type_2 instanceof Artifact)) {
                Type _type_3 = operation.getType();
                artifacts.add(((Artifact) _type_3));
              }
            }
          }
          EList<Parameter> _ownedParameters = operation.getOwnedParameters();
          for (final Parameter parameter : _ownedParameters) {
            if ((((parameter.getType() != null) && (!(parameter.getType() instanceof PrimitiveType))) && (parameter.getType() != umlClass))) {
              if (((parameter.getType() instanceof org.eclipse.uml2.uml.Class) || (parameter.getType() instanceof Enumeration))) {
                types.add(parameter.getType());
              } else {
                Type _type_4 = parameter.getType();
                if ((_type_4 instanceof Artifact)) {
                  Type _type_5 = parameter.getType();
                  artifacts.add(((Artifact) _type_5));
                }
              }
            }
          }
        }
      }
      Iterable<Dependency> _filter = Iterables.<Dependency>filter(umlClass.getRelationships(), Dependency.class);
      for (final Dependency rel : _filter) {
        {
          final Dependency dep = ((Dependency) rel);
          EList<NamedElement> _suppliers = dep.getSuppliers();
          for (final NamedElement supplier : _suppliers) {
            if (((supplier instanceof org.eclipse.uml2.uml.Class) || (supplier instanceof Enumeration))) {
              types.add(((Type) supplier));
            } else {
              if ((supplier instanceof Artifact)) {
                artifacts.add(((Artifact) supplier));
              }
            }
          }
        }
      }
      final Function1<Type, String> _function = (Type type) -> {
        return this.generatePath(it, umlClass, type);
      };
      final Function1<String, Boolean> _function_1 = (String p) -> {
        return Boolean.valueOf(((p != null) && (!p.startsWith("ERROR_PATH_NOT_FOUND"))));
      };
      final List<String> sortedIncludePaths = IterableExtensions.<String>sort(IterableExtensions.<String>filter(IterableExtensions.<Type, String>map(types, _function), _function_1));
      final Function1<Artifact, String> _function_2 = (Artifact a) -> {
        return it.generate(a, "include");
      };
      final Function1<String, Boolean> _function_3 = (String inc) -> {
        return Boolean.valueOf((inc != null));
      };
      final List<String> artifactIncludes = IterableExtensions.<String>sort(IterableExtensions.<String>filter(IterableExtensions.<Artifact, String>map(artifacts, _function_2), _function_3));
      StringConcatenation _builder = new StringConcatenation();
      {
        boolean _hasElements = false;
        for(final String pathString : sortedIncludePaths) {
          if (!_hasElements) {
            _hasElements = true;
          }
          _builder.append("#include \"");
          _builder.append(pathString);
          _builder.append("\"");
          _builder.newLineIfNotEmpty();
        }
        if (_hasElements) {
          _builder.append("\n");
        }
      }
      {
        boolean _hasElements_1 = false;
        for(final String inc : artifactIncludes) {
          if (!_hasElements_1) {
            _hasElements_1 = true;
          }
          _builder.append(inc);
          _builder.newLineIfNotEmpty();
        }
        if (_hasElements_1) {
          _builder.append("\n");
        }
      }
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }

  /**
   * def String generateIncludes(CodegenInterface it, Class umlClass) {
   * val types = new HashSet<Type>()
   * 
   * for (property : umlClass.ownedAttributes) {
   * if (property.type !== null && !(property.type instanceof PrimitiveType) && property.type !== umlClass) {
   * if (property.type instanceof Class || property.type instanceof Enumeration) {
   * types.add(property.type)
   * }
   * }
   * }
   * 
   * for (operation : umlClass.ownedOperations) {
   * if (operation.type !== null && !(operation.type instanceof PrimitiveType) && operation.type !== umlClass) {
   * if (operation.type instanceof Class || operation.type instanceof Enumeration) {
   * types.add(operation.type)
   * }
   * }
   * for (parameter : operation.ownedParameters) {
   * if (parameter.type !== null && !(parameter.type instanceof PrimitiveType) && parameter.type !== umlClass) {
   * if (parameter.type instanceof Class || parameter.type instanceof Enumeration) {
   * types.add(parameter.type)
   * }
   * }
   * }
   * }
   * 
   * for (rel : umlClass.relationships.filter(Dependency)) {
   * val dep = rel as Dependency
   * for (supplier : dep.suppliers) {
   * if (supplier instanceof Class || supplier instanceof Enumeration) {
   * types.add(supplier as Type)
   * }
   * }
   * }
   * 
   * val sortedIncludePaths = types.map[type |generatePath(it, umlClass, type)  ]
   * .filter[p | p !== null && !p.startsWith("ERROR_PATH_NOT_FOUND")].sort
   * 
   * 
   * '''
   * «FOR pathString : sortedIncludePaths AFTER '\n'»
   * #include "«pathString»"
   * «ENDFOR»
   * '''
   * 
   * 
   * }
   */
  public String generatePath(final CodegenInterface it, final NamedElement from, final NamedElement to) {
    final Path fromPath = it.getPath(from, "declaration");
    final Path toPath = it.getPath(to, "declaration");
    Path _elvis = null;
    Path _parent = fromPath.getParent();
    Path _relativize = null;
    if (_parent!=null) {
      _relativize=_parent.relativize(toPath);
    }
    if (_relativize != null) {
      _elvis = _relativize;
    } else {
      _elvis = toPath;
    }
    final Path relPath = _elvis;
    return IterableExtensions.join(relPath, "/");
  }

  @Override
  public Path getPath(final org.eclipse.uml2.uml.Class umlClass, final String context) {
    LinkedList<String> path = new LinkedList<String>();
    if (context != null) {
      switch (context) {
        case "declaration":
          String _name = umlClass.getName();
          String _plus = (_name + ".h");
          path.addFirst(_plus);
          break;
        case "implementation":
          String _name_1 = umlClass.getName();
          String _plus_1 = (_name_1 + ".c");
          path.addFirst(_plus_1);
          break;
        default:
          return null;
      }
    } else {
      return null;
    }
    Namespace parent = umlClass.getNamespace();
    while ((null != parent)) {
      {
        path.addFirst(parent.getName());
        parent = parent.getNamespace();
      }
    }
    return Paths.get(IterableExtensions.<String>head(path), ((String[])Conversions.unwrapArray(IterableExtensions.<String>tail(path), String.class)));
  }
}
