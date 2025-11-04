package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;

@SuppressWarnings("all")
public class NullTemplate implements Template<Object> {
  @Override
  public String generateCode(final CodegenInterface it, final Object object, final String context) {
    return "";
  }
}
