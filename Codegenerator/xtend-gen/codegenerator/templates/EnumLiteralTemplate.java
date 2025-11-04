package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class EnumLiteralTemplate implements Template<EnumerationLiteral> {
  @Override
  public String generateCode(final CodegenInterface it, final EnumerationLiteral umlEnumLiteral, final String context) {
    String _xblockexpression = null;
    {
      final String name = it.generate(umlEnumLiteral, "name");
      String _xifexpression = null;
      ValueSpecification _specification = umlEnumLiteral.getSpecification();
      boolean _tripleNotEquals = (null != _specification);
      if (_tripleNotEquals) {
        String _xblockexpression_1 = null;
        {
          final String value = it.generate(umlEnumLiteral.getSpecification(), "value");
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(name);
          _builder.append(" = ");
          _builder.append(value);
          _xblockexpression_1 = _builder.toString();
        }
        _xifexpression = _xblockexpression_1;
      } else {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(name);
        _xifexpression = _builder.toString();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
