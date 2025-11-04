package codegenerator

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Comparator
import java.util.Optional
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl
import org.eclipse.uml2.uml.UMLPackage

class CodegenIO {

	def static Optional<ResourceSet> loadModel(Path modelPath) {
		val resourceSet = new ResourceSetImpl
		resourceSet.resourceFactoryRegistry.extensionToFactoryMap.put("uml", new UMLResourceFactoryImpl)
		resourceSet.packageRegistry.put(UMLPackage.eINSTANCE.nsURI, UMLPackage.eINSTANCE)
		val inUri = URI.createFileURI(modelPath.toString)
		val resource = resourceSet.getResource(inUri, true)
		if (null === resource) {
			return Optional.empty
		}
		return Optional.of(resourceSet)
	}

	def static clearFolder(Path folder) {
		if (Files.exists(folder)) {
			Files.walk(folder).skip(1).sorted(Comparator.reverseOrder).forEach[p|Files.delete(p)]
		} else {
			Files.createDirectories(folder)
		}
	}

	def static outputCode(Path outPath, String code) {
		Files.deleteIfExists(outPath)
		Files.createDirectories(outPath.parent)
		val outStream = Files.newOutputStream(outPath)
		outStream.write(code.bytes)
		outStream.close
	}

	def static void modelToCode(ResourceSet resourceSet, Path outputPath) {
		val generator = new Uml2C
		clearFolder(outputPath)
		for (resource : resourceSet.resources) {
			val codeForFiles = generator.generateModel(resource)
			for (path : codeForFiles.keySet) {
				val code = codeForFiles.get(path)
				val outFilePath = outputPath.resolve(path)
				outputCode(outFilePath, code)
			}
		}
	}

	def static Optional<String> getArgument(String[] args, String arg) {
		val index = args.indexOf(arg) + 1
		if(args.length <= index) return Optional.empty
		return Optional.of(args.get(index))
	}

	static def void main(String[] args) {
		val src = getArgument(args, "-loadModel")
		if (src.present) {
			var srcPath = Paths.get(src.get)
			val dst = getArgument(args, "-outputCode")
			if (dst.present) {
				var dstPath = Paths.get(dst.get)

				val model = loadModel(srcPath)
				if (model.present) {
					modelToCode(model.get, dstPath)
				} else {
					println("Could not load model.")
				}
				println("Done.")
			} else {
				println("No '-outputCode' parameter set.")
			}
		} else {
			println("No '-loadModel' parameter set.")
		}
	}

}
