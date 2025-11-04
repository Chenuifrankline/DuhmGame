package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.base.Objects;
import javax.lang.model.type.PrimitiveType;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Property;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class PropertyTemplate implements Template<Property> {
  @Override
  public String generateCode(final CodegenInterface it, final Property umlProperty, final String context) {
    String _xblockexpression = null;
    {
      String name = umlProperty.getName();
      String pointer = "";
      String upper = "";
      int _upper = umlProperty.getUpper();
      boolean _greaterThan = (_upper > 1);
      if (_greaterThan) {
        String _string = Integer.valueOf(umlProperty.getUpper()).toString();
        String _plus = ("[" + _string);
        String _plus_1 = (_plus + "]");
        upper = _plus_1;
      } else {
        int _upper_1 = umlProperty.getUpper();
        boolean _tripleEquals = (_upper_1 == (-1));
        if (_tripleEquals) {
          pointer = "*";
        }
      }
      String type = it.generate(umlProperty.getType(), "type");
      String _xifexpression = null;
      if (((!((umlProperty.getType() instanceof PrimitiveType) || (umlProperty.getType() instanceof Artifact))) && 
        Objects.equal(umlProperty.getAggregation(), AggregationKind.COMPOSITE_LITERAL))) {
        StringConcatenation _builder = new StringConcatenation();
        String _generate = it.generate(umlProperty.getType(), "name");
        _builder.append(_generate);
        _builder.append(" ");
        _builder.append(name);
        _builder.append(upper);
        _builder.append(";");
        _xifexpression = _builder.toString();
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(type);
        _builder_1.append(pointer);
        _builder_1.append(" ");
        _builder_1.append(name);
        _builder_1.append(upper);
        _builder_1.append(";");
        _xifexpression = _builder_1.toString();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
