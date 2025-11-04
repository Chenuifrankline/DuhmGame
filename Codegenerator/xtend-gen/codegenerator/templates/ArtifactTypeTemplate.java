package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Artifact;

@SuppressWarnings("all")
public class ArtifactTypeTemplate implements Template<Artifact> {
  @Override
  public String generateCode(final CodegenInterface it, final Artifact umlArtifact, final String context) {
    if (context != null) {
      switch (context) {
        case "include":
          final String file = umlArtifact.getFileName();
          if (((file == null) || file.trim().isEmpty())) {
            return null;
          } else {
            if ((file.startsWith("<") && file.endsWith(">"))) {
              return ("#include " + file);
            } else {
              return (("#include \"" + file) + "\"");
            }
          }
        case "type":
          return it.generate(umlArtifact, "name");
        default:
          throw new UnsupportedOperationException(("Unsupported context: " + context));
      }
    } else {
      throw new UnsupportedOperationException(("Unsupported context: " + context));
    }
  }
}
