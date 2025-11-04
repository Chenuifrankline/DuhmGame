package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class NameTemplate implements Template<NamedElement> {
  @Override
  public String generateCode(final CodegenInterface it, final NamedElement elem, final String context) {
    if ((null == elem)) {
      return "<null>";
    }
    Namespace _namespace = elem.getNamespace();
    boolean _tripleNotEquals = (null != _namespace);
    if (_tripleNotEquals) {
      StringConcatenation _builder = new StringConcatenation();
      String _generate = it.generate(elem.getNamespace(), "name");
      _builder.append(_generate);
      _builder.append("_");
      String _name = elem.getName();
      _builder.append(_name);
      return _builder.toString();
    }
    return elem.getName();
  }
}
