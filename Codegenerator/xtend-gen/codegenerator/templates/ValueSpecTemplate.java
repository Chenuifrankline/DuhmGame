package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralNull;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.ValueSpecification;

@SuppressWarnings("all")
public class ValueSpecTemplate implements Template<ValueSpecification> {
  @Override
  public String generateCode(final CodegenInterface it, final ValueSpecification umlLiteralSpec, final String context) {
    boolean _matched = false;
    if (umlLiteralSpec instanceof LiteralInteger) {
      _matched=true;
      return String.valueOf(((LiteralInteger)umlLiteralSpec).integerValue());
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralUnlimitedNatural) {
        _matched=true;
        return String.valueOf(((LiteralUnlimitedNatural)umlLiteralSpec).integerValue());
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralReal) {
        _matched=true;
        return String.valueOf(((LiteralReal)umlLiteralSpec).realValue());
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralString) {
        _matched=true;
        String _elvis = null;
        String _stringValue = ((LiteralString)umlLiteralSpec).stringValue();
        if (_stringValue != null) {
          _elvis = _stringValue;
        } else {
          _elvis = "";
        }
        final String string = _elvis;
        String _replace = string.replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r").replace("\"", "\\\"");
        String _plus = ("\"" + _replace);
        return (_plus + "\"");
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralNull) {
        _matched=true;
        return "NULL";
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof LiteralBoolean) {
        _matched=true;
        String _xifexpression = null;
        boolean _booleanValue = ((LiteralBoolean)umlLiteralSpec).booleanValue();
        if (_booleanValue) {
          _xifexpression = "1";
        } else {
          _xifexpression = "0";
        }
        return _xifexpression;
      }
    }
    if (!_matched) {
      if (umlLiteralSpec instanceof InstanceValue) {
        _matched=true;
        InstanceSpecification _instance = ((InstanceValue)umlLiteralSpec).getInstance();
        boolean _tripleEquals = (_instance == null);
        if (_tripleEquals) {
          return "0";
        }
        final String instanceName = it.generate(((InstanceValue)umlLiteralSpec).getInstance(), "name");
        return ("&" + instanceName);
      }
    }
    return umlLiteralSpec.stringValue();
  }
}
