package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class OperationTemplate implements Template<Operation> {
  @Override
  public String generateCode(final CodegenInterface it, final Operation umlOperation, final String context) {
    String _xblockexpression = null;
    {
      final String name = umlOperation.getName();
      final org.eclipse.uml2.uml.Class klasse = umlOperation.getClass_();
      final EList<Parameter> param = umlOperation.getOwnedParameters();
      final boolean isStatic = umlOperation.isStatic();
      final Function1<Parameter, Boolean> _function = (Parameter it_1) -> {
        String _literal = it_1.getDirection().getLiteral();
        return Boolean.valueOf(Objects.equal(_literal, "return"));
      };
      final Parameter returnParam = IterableExtensions.<Parameter>findFirst(param, _function);
      String _xifexpression = null;
      if ((returnParam != null)) {
        _xifexpression = it.generate(returnParam, "return");
      } else {
        _xifexpression = "void";
      }
      final String returnType = _xifexpression;
      Iterable<Parameter> _xifexpression_1 = null;
      if ((param != null)) {
        final Function1<Parameter, Boolean> _function_1 = (Parameter it_1) -> {
          String _literal = it_1.getDirection().getLiteral();
          return Boolean.valueOf((!Objects.equal(_literal, "return")));
        };
        _xifexpression_1 = IterableExtensions.<Parameter>filter(param, _function_1);
      } else {
        _xifexpression_1 = CollectionLiterals.<Parameter>newArrayList();
      }
      final Iterable<Parameter> inputParams = _xifexpression_1;
      final Function1<Parameter, String> _function_2 = (Parameter p) -> {
        return it.generate(p, "parameter");
      };
      final String paramString = IterableExtensions.join(IterableExtensions.<Parameter, String>map(inputParams, _function_2), ", ");
      String _xifexpression_2 = null;
      org.eclipse.uml2.uml.Package _package = klasse.getPackage();
      boolean _tripleNotEquals = (_package != null);
      if (_tripleNotEquals) {
        String _generate = it.generate(klasse.getPackage(), "name");
        String _plus = (_generate + "_");
        String _name = klasse.getName();
        _xifexpression_2 = (_plus + _name);
      } else {
        _xifexpression_2 = klasse.getName();
      }
      final String prefix = _xifexpression_2;
      String paramList = "";
      if ((!isStatic)) {
        paramList = (prefix + "* const me");
        boolean _isEmpty = paramString.isEmpty();
        boolean _not = (!_isEmpty);
        if (_not) {
          String _paramList = paramList;
          paramList = (_paramList + (", " + paramString));
        }
      } else {
        String _xifexpression_3 = null;
        boolean _isEmpty_1 = paramString.isEmpty();
        boolean _not_1 = (!_isEmpty_1);
        if (_not_1) {
          _xifexpression_3 = paramString;
        } else {
          _xifexpression_3 = "void";
        }
        paramList = _xifexpression_3;
      }
      if (((!umlOperation.getMethods().isEmpty()) && (!context.equals("declaration")))) {
        final Behavior method = umlOperation.getMethods().get(0);
        if ((method instanceof OpaqueBehavior)) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(returnType);
          _builder.append(" ");
          _builder.append(prefix);
          _builder.append("_");
          _builder.append(name);
          _builder.append("(");
          _builder.append(paramList);
          _builder.append(") {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _get = ((OpaqueBehavior)method).getBodies().get(0);
          _builder.append(_get, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          return _builder.toString();
        }
      }
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append(returnType);
      _builder_1.append(" ");
      _builder_1.append(prefix);
      _builder_1.append("_");
      _builder_1.append(name);
      _builder_1.append("(");
      _builder_1.append(paramList);
      _builder_1.append(");");
      _xblockexpression = _builder_1.toString();
    }
    return _xblockexpression;
  }
}
