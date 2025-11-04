package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class OpaqueBehaviorTemplate implements Template<OpaqueBehavior> {
  @Override
  public String generateCode(final CodegenInterface it, final OpaqueBehavior umlBehavior, final String context) {
    return IterableExtensions.<String>head(umlBehavior.getBodies());
  }
}
