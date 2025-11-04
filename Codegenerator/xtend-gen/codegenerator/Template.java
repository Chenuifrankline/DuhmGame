package codegenerator;

import java.nio.file.Path;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public interface Template<T extends Object> {
  /**
   * Takes an object of type T and generates code in form of a String from it.
   * 
   * @param it      The parent code generator that is used to call other templates
   * @param object  The input object of some generic type T.
   * @param context The context for which this template was called.
   * 
   * @return Code in form of a String, may be empty or null.
   */
  String generateCode(final CodegenInterface it, final T object, final String context);

  /**
   * Tells whether or not this template generates the contents of a whole
   * file, given some object.
   * By default, getPath is called and false is returned if the path is empty.
   * 
   * @param object  The input object of some generic type T.
   * @param context The context for which this template was called.
   * 
   * @return True if the template fills a file, false if the template
   * generates a sub-expression.
   */
  default boolean isRoot(final T object, final String context) {
    final Path path = this.getPath(object, context);
    return ((null != path) && (!IterableExtensions.isEmpty(path)));
  }

  /**
   * Returns the path of the file that this template generates, if it is a
   * root template, null otherwise.
   * 
   * @param object  The input object of some generic type T.
   * @param context The context for which this template was called.
   * 
   * @return File path of the object.
   */
  default Path getPath(final T object, final String context) {
    return null;
  }
}
