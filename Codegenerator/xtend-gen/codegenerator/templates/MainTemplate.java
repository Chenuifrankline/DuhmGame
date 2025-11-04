package codegenerator.templates;

import codegenerator.CodegenInterface;
import codegenerator.Template;
import com.google.common.collect.Iterables;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class MainTemplate implements Template<Model> {
  @Override
  public String generateCode(final CodegenInterface it, final Model umlModel, final String context) {
    String _xblockexpression = null;
    {
      final Function1<InstanceSpecification, Boolean> _function = (InstanceSpecification i) -> {
        return Boolean.valueOf((((i.getClassifiers().size() == 1) && (IterableExtensions.<Classifier>head(i.getClassifiers()) instanceof org.eclipse.uml2.uml.Class)) && (null != ((org.eclipse.uml2.uml.Class) IterableExtensions.<Classifier>head(i.getClassifiers())).getClassifierBehavior())));
      };
      final Iterable<InstanceSpecification> instances = IterableExtensions.<InstanceSpecification>filter(Iterables.<InstanceSpecification>filter(umlModel.allOwnedElements(), InstanceSpecification.class), _function);
      final Function1<InstanceSpecification, EList<Classifier>> _function_1 = (InstanceSpecification it_1) -> {
        return it_1.getClassifiers();
      };
      final Function1<org.eclipse.uml2.uml.Class, Path> _function_2 = (org.eclipse.uml2.uml.Class cls) -> {
        return it.getPath(cls, "declaration");
      };
      final Iterable<Path> includes = IterableExtensions.<org.eclipse.uml2.uml.Class, Path>map(IterableExtensions.<org.eclipse.uml2.uml.Class>toSet(Iterables.<org.eclipse.uml2.uml.Class>filter(IterableExtensions.<InstanceSpecification, Classifier>flatMap(instances, _function_1), org.eclipse.uml2.uml.Class.class)), _function_2);
      StringConcatenation _builder = new StringConcatenation();
      {
        List<Path> _sort = IterableExtensions.<Path>sort(includes);
        for(final Path path : _sort) {
          _builder.append("#include \"");
          _builder.append(path);
          _builder.append("\"");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.append("int main() {");
      _builder.newLine();
      {
        for(final InstanceSpecification inst : instances) {
          _builder.append("\t");
          Classifier _head = IterableExtensions.<Classifier>head(inst.getClassifiers());
          String _generate = it.generate(((org.eclipse.uml2.uml.Class) _head).getClassifierBehavior(), "name");
          _builder.append(_generate, "\t");
          _builder.append("(&");
          String _generate_1 = it.generate(inst, "name");
          _builder.append(_generate_1, "\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("return 0;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }

  @Override
  public Path getPath(final Model object, final String context) {
    return Paths.get("main.c");
  }
}
