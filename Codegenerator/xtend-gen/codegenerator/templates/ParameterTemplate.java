package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ParameterTemplate implements Template<Parameter> {
  @Override
  public String generateCode(final CodegenInterface it, final Parameter umlParameter, final String context) {
    String _xblockexpression = null;
    {
      final String name = umlParameter.getName();
      String _xifexpression = null;
      Type _type = umlParameter.getType();
      boolean _tripleNotEquals = (_type != null);
      if (_tripleNotEquals) {
        _xifexpression = it.generate(umlParameter.getType(), "type");
      } else {
        _xifexpression = "void*";
      }
      final String type = _xifexpression;
      String _switchResult = null;
      ParameterDirectionKind _direction = umlParameter.getDirection();
      if (_direction != null) {
        switch (_direction) {
          case INOUT_LITERAL:
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(type);
            _builder.append("* ");
            _builder.append(name);
            _switchResult = _builder.toString();
            break;
          case OUT_LITERAL:
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append(type);
            _builder_1.append("* ");
            _builder_1.append(name);
            _switchResult = _builder_1.toString();
            break;
          case RETURN_LITERAL:
            StringConcatenation _builder_2 = new StringConcatenation();
            _builder_2.append(type);
            _switchResult = _builder_2.toString();
            break;
          default:
            StringConcatenation _builder_3 = new StringConcatenation();
            _builder_3.append(type);
            _builder_3.append(" ");
            _builder_3.append(name);
            _switchResult = _builder_3.toString();
            break;
        }
      } else {
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append(type);
        _builder_3.append(" ");
        _builder_3.append(name);
        _switchResult = _builder_3.toString();
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
}
