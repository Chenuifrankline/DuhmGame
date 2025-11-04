package codegenerator;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class CodegenIO {
  public static Optional<ResourceSet> loadModel(final Path modelPath) {
    final ResourceSetImpl resourceSet = new ResourceSetImpl();
    Map<String, Object> _extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
    UMLResourceFactoryImpl _uMLResourceFactoryImpl = new UMLResourceFactoryImpl();
    _extensionToFactoryMap.put("uml", _uMLResourceFactoryImpl);
    resourceSet.getPackageRegistry().put(UMLPackage.eINSTANCE.getNsURI(), UMLPackage.eINSTANCE);
    final URI inUri = URI.createFileURI(modelPath.toString());
    final Resource resource = resourceSet.getResource(inUri, true);
    if ((null == resource)) {
      return Optional.<ResourceSet>empty();
    }
    return Optional.<ResourceSet>of(resourceSet);
  }

  public static Path clearFolder(final Path folder) {
    try {
      Path _xifexpression = null;
      boolean _exists = Files.exists(folder);
      if (_exists) {
        final Consumer<Path> _function = (Path p) -> {
          try {
            Files.delete(p);
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        };
        Files.walk(folder).skip(1).sorted(Comparator.<Path>reverseOrder()).forEach(_function);
      } else {
        _xifexpression = Files.createDirectories(folder);
      }
      return _xifexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public static void outputCode(final Path outPath, final String code) {
    try {
      Files.deleteIfExists(outPath);
      Files.createDirectories(outPath.getParent());
      final OutputStream outStream = Files.newOutputStream(outPath);
      outStream.write(code.getBytes());
      outStream.close();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public static void modelToCode(final ResourceSet resourceSet, final Path outputPath) {
    final Uml2C generator = new Uml2C();
    CodegenIO.clearFolder(outputPath);
    EList<Resource> _resources = resourceSet.getResources();
    for (final Resource resource : _resources) {
      {
        final Map<Path, String> codeForFiles = generator.generateModel(resource);
        Set<Path> _keySet = codeForFiles.keySet();
        for (final Path path : _keySet) {
          {
            final String code = codeForFiles.get(path);
            final Path outFilePath = outputPath.resolve(path);
            CodegenIO.outputCode(outFilePath, code);
          }
        }
      }
    }
  }

  public static Optional<String> getArgument(final String[] args, final String arg) {
    int _indexOf = ((List<String>)Conversions.doWrapArray(args)).indexOf(arg);
    final int index = (_indexOf + 1);
    int _length = args.length;
    boolean _lessEqualsThan = (_length <= index);
    if (_lessEqualsThan) {
      return Optional.<String>empty();
    }
    return Optional.<String>of(args[index]);
  }

  public static void main(final String[] args) {
    final Optional<String> src = CodegenIO.getArgument(args, "-loadModel");
    boolean _isPresent = src.isPresent();
    if (_isPresent) {
      Path srcPath = Paths.get(src.get());
      final Optional<String> dst = CodegenIO.getArgument(args, "-outputCode");
      boolean _isPresent_1 = dst.isPresent();
      if (_isPresent_1) {
        Path dstPath = Paths.get(dst.get());
        final Optional<ResourceSet> model = CodegenIO.loadModel(srcPath);
        boolean _isPresent_2 = model.isPresent();
        if (_isPresent_2) {
          CodegenIO.modelToCode(model.get(), dstPath);
        } else {
          InputOutput.<String>println("Could not load model.");
        }
        InputOutput.<String>println("Done.");
      } else {
        InputOutput.<String>println("No \'-outputCode\' parameter set.");
      }
    } else {
      InputOutput.<String>println("No \'-loadModel\' parameter set.");
    }
  }
}
