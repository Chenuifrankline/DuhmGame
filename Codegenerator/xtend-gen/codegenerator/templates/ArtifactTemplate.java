package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import org.eclipse.uml2.uml.Artifact;

@SuppressWarnings("all")
public class ArtifactTemplate implements Template<Artifact> {
  @Override
  public String generateCode(final CodegenInterface it, final Artifact umlArtifact, final String context) {
    if (context != null) {
      switch (context) {
        case "include":
          String _fileName = umlArtifact.getFileName();
          String _trim = null;
          if (_fileName!=null) {
            _trim=_fileName.trim();
          }
          final String file = _trim;
          if (((file == null) || file.trim().isEmpty())) {
            return null;
          } else {
            if ((file.startsWith("<") && file.endsWith(">"))) {
              return ("#include " + file);
            } else {
              System.out.println(file);
            }
          }
          return (("#include \"" + file) + "\"");
        case "type":
          return it.generate(umlArtifact, "name");
        case "name":
          return umlArtifact.getName();
        default:
          throw new UnsupportedOperationException(("Unsupported context: " + context));
      }
    } else {
      throw new UnsupportedOperationException(("Unsupported context: " + context));
    }
  }
}
