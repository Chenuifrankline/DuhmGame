package codegenerator;

import java.nio.file.Path;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class Uml2CInterface implements CodegenInterface {
  private final Uml2C generator;

  private final Map<String, Object> options;

  public Uml2CInterface(final Uml2C generator, final Map<String, Object> options) {
    this.generator = generator;
    this.options = options;
  }

  @Override
  public String generate(final Object object, final String context) {
    return this.generator.generateCode(object, context);
  }

  @Override
  public void logInfo(final String message, final Object object, final String context) {
    this.logInfo(message, null, object, context);
  }

  @Override
  public void logWarning(final String message, final Object object, final String context) {
    this.logWarning(message, null, object, context);
  }

  @Override
  public void logError(final String message, final Object object, final String context) {
    this.logError(message, null, object, context);
  }

  @Override
  public void logInfo(final String message, final Exception e, final Object object, final String context) {
    InputOutput.<String>println(((("Info for object " + object) + " and context ") + context));
    InputOutput.<String>println(message);
    if ((null != e)) {
      e.printStackTrace();
    }
  }

  @Override
  public void logWarning(final String message, final Exception e, final Object object, final String context) {
    InputOutput.<String>println(((("Info for object " + object) + " and context ") + context));
    InputOutput.<String>println(message);
    if ((null != e)) {
      e.printStackTrace();
    }
  }

  @Override
  public void logError(final String message, final Exception e, final Object object, final String context) {
    InputOutput.<String>println(((("Info for object " + object) + " and context ") + context));
    InputOutput.<String>println(message);
    if ((null != e)) {
      e.printStackTrace();
    }
  }

  @Override
  public Path getPath(final Object obj, final String context) {
    return this.generator.getPath(obj, context);
  }

  @Override
  public Object getOption(final String option) {
    return this.options.get(option);
  }
}
