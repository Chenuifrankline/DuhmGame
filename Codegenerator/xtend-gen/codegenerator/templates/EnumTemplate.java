package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class EnumTemplate implements Template<Enumeration> {
  @Override
  public String generateCode(final CodegenInterface it, final Enumeration umlEnum, final String context) {
    final String name = it.generate(umlEnum, "name");
    if (context != null) {
      switch (context) {
        case "typedefinition":
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("typedef enum {");
          _builder.newLine();
          {
            EList<EnumerationLiteral> _ownedLiterals = umlEnum.getOwnedLiterals();
            boolean _hasElements = false;
            for(final EnumerationLiteral literal : _ownedLiterals) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(",", "\t");
              }
              _builder.append("\t");
              String _generate = it.generate(literal, "enumliteral");
              _builder.append(_generate, "\t");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("} ");
          _builder.append(name);
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          return _builder.toString();
        case "declaration":
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("#ifndef ");
          String _upperCase = name.toUpperCase();
          _builder_1.append(_upperCase);
          _builder_1.append("_H");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("#define ");
          String _upperCase_1 = name.toUpperCase();
          _builder_1.append(_upperCase_1);
          _builder_1.append("_H");
          _builder_1.newLineIfNotEmpty();
          _builder_1.newLine();
          String _generate_1 = it.generate(umlEnum, "typedefinition");
          _builder_1.append(_generate_1);
          _builder_1.newLineIfNotEmpty();
          _builder_1.newLine();
          _builder_1.append("extern ");
          _builder_1.append(name);
          _builder_1.append(" ");
          _builder_1.append(name);
          _builder_1.append("_Literals[");
          int _size = umlEnum.getOwnedLiterals().size();
          _builder_1.append(_size);
          _builder_1.append("];");
          _builder_1.newLineIfNotEmpty();
          _builder_1.newLine();
          _builder_1.append("#endif");
          _builder_1.newLine();
          return _builder_1.toString();
        case "implementation":
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("#include \"");
          String _name = umlEnum.getName();
          _builder_2.append(_name);
          _builder_2.append(".h\"");
          _builder_2.newLineIfNotEmpty();
          _builder_2.newLine();
          _builder_2.append(name);
          _builder_2.append(" ");
          _builder_2.append(name);
          _builder_2.append("_Literals[");
          int _size_1 = umlEnum.getOwnedLiterals().size();
          _builder_2.append(_size_1);
          _builder_2.append("] = {");
          _builder_2.newLineIfNotEmpty();
          {
            EList<EnumerationLiteral> _ownedLiterals_1 = umlEnum.getOwnedLiterals();
            boolean _hasElements_1 = false;
            for(final EnumerationLiteral literal_1 : _ownedLiterals_1) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
              } else {
                _builder_2.appendImmediate(",", "\t");
              }
              _builder_2.append("\t");
              String _generate_2 = it.generate(literal_1, "name");
              _builder_2.append(_generate_2, "\t");
              _builder_2.newLineIfNotEmpty();
            }
          }
          _builder_2.append("};");
          _builder_2.newLine();
          return _builder_2.toString();
      }
    }
    return null;
  }

  @Override
  public Path getPath(final Enumeration umlEnum, final String context) {
    LinkedList<String> path = new LinkedList<String>();
    if (context != null) {
      switch (context) {
        case "declaration":
          String _name = umlEnum.getName();
          String _plus = (_name + ".h");
          path.addFirst(_plus);
          break;
        case "implementation":
          String _name_1 = umlEnum.getName();
          String _plus_1 = (_name_1 + ".c");
          path.addFirst(_plus_1);
          break;
        default:
          return null;
      }
    } else {
      return null;
    }
    Namespace parent = umlEnum.getNamespace();
    while ((null != parent)) {
      {
        path.addFirst(parent.getName());
        parent = parent.getNamespace();
      }
    }
    return Paths.get(IterableExtensions.<String>head(path), ((String[])Conversions.unwrapArray(IterableExtensions.<String>tail(path), String.class)));
  }
}
