package codegenerator;

import java.nio.file.Path;

@SuppressWarnings("all")
public interface CodegenInterface {
  String generate(final Object object, final String context);

  void logInfo(final String message, final Object object, final String context);

  void logWarning(final String message, final Object object, final String context);

  void logError(final String message, final Object object, final String context);

  void logInfo(final String message, final Exception e, final Object object, final String context);

  void logWarning(final String message, final Exception e, final Object object, final String context);

  void logError(final String message, final Exception e, final Object object, final String context);

  Path getPath(final Object obj, final String context);

  Object getOption(final String option);
}
