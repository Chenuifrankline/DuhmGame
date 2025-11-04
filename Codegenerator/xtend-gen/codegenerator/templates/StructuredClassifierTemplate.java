package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class StructuredClassifierTemplate implements Template<StructuredClassifier> {
  @Override
  public String generateCode(final CodegenInterface it, final StructuredClassifier umlClassifier, final String context) {
    String _switchResult = null;
    if (context != null) {
      switch (context) {
        case "typedefinition":
          final String fullName = it.generate(umlClassifier, "name");
          final Function1<Property, String> _function = (Property attr) -> {
            return it.generate(attr, "attribute");
          };
          final String propertiesCode = IterableExtensions.join(ListExtensions.<Property, String>map(umlClassifier.getOwnedAttributes(), _function), "\n");
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("typedef struct ");
          _builder.append(fullName);
          _builder.append("_struct {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          {
            if ((propertiesCode != "")) {
              _builder.append(propertiesCode, "\t");
            }
          }
          _builder.newLineIfNotEmpty();
          _builder.append("} ");
          _builder.append(fullName);
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          return _builder.toString();
        default:
          _switchResult = "";
          break;
      }
    } else {
      _switchResult = "";
    }
    return _switchResult;
  }
}
