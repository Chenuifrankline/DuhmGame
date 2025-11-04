package codegenerator.test.exercise4;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestArtifact {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Wird ein Artifact als Type verwendet, wird der Name generiert.
   */
  @Test
  public void test00_ArtifactAsType() {
    Artifact _createArtifact = this.factory.createArtifact();
    final Procedure1<Artifact> _function = (Artifact it) -> {
      it.setName("uint32_t");
      it.setFileName("cstdint.h");
    };
    final Artifact artifact = ObjectExtensions.<Artifact>operator_doubleArrow(_createArtifact, _function);
    Assert.assertEquals("uint32_t", new Uml2C().generateCode(artifact, "type"));
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test01_ArtifactAsType2() {
    Artifact _createArtifact = this.factory.createArtifact();
    final Procedure1<Artifact> _function = (Artifact it) -> {
      it.setName("time_t");
      it.setFileName("ctime.h");
    };
    final Artifact artifact = ObjectExtensions.<Artifact>operator_doubleArrow(_createArtifact, _function);
    Assert.assertEquals("time_t", new Uml2C().generateCode(artifact, "type"));
  }

  /**
   * Wird ein Artifact in eienr Klasse verwendet, wird der FileName im Header der Klasse included.
   */
  @Test
  public void test02_ArtifactInclude() {
    Artifact _createArtifact = this.factory.createArtifact();
    final Procedure1<Artifact> _function = (Artifact it) -> {
      it.setName("uint32_t");
      it.setFileName("cstdint.h");
    };
    final Artifact artifact = ObjectExtensions.<Artifact>operator_doubleArrow(_createArtifact, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("MyClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_2 = (Property it_1) -> {
        it_1.setName("number");
        it_1.setType(artifact);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_2);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class cls = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef MYCLASS_H");
    _builder.newLine();
    _builder.append("#define MYCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"cstdint.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct MyClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("uint32_t number;");
    _builder.newLine();
    _builder.append("} MyClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(
      _builder.toString(), 
      new Uml2C().generateCode(cls, "declaration"));
  }

  /**
   * Beginnt und endet der FileName mit < und >, werden keine Anführungszeichen generiert.
   */
  @Test
  public void test03_ArtifactInclude3() {
    Artifact _createArtifact = this.factory.createArtifact();
    final Procedure1<Artifact> _function = (Artifact it) -> {
      it.setName("uint32_t");
      it.setFileName("<cstdint.h>");
    };
    final Artifact artifact = ObjectExtensions.<Artifact>operator_doubleArrow(_createArtifact, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("MyClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_2 = (Property it_1) -> {
        it_1.setName("number");
        it_1.setType(artifact);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_2);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class cls = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef MYCLASS_H");
    _builder.newLine();
    _builder.append("#define MYCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include <cstdint.h>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct MyClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("uint32_t number;");
    _builder.newLine();
    _builder.append("} MyClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(
      _builder.toString(), 
      new Uml2C().generateCode(cls, "declaration"));
  }

  /**
   * Hat ein Artifact keinen FileName, wird auch kein include generiert.
   */
  @Test
  public void test04_ArtifactWithNoFile() {
    Artifact _createArtifact = this.factory.createArtifact();
    final Procedure1<Artifact> _function = (Artifact it) -> {
      it.setName("float");
    };
    final Artifact artifact = ObjectExtensions.<Artifact>operator_doubleArrow(_createArtifact, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("MyClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_2 = (Property it_1) -> {
        it_1.setName("number");
        it_1.setType(artifact);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_2);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class cls = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef MYCLASS_H");
    _builder.newLine();
    _builder.append("#define MYCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct MyClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float number;");
    _builder.newLine();
    _builder.append("} MyClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(
      _builder.toString(), 
      new Uml2C().generateCode(cls, "declaration"));
  }
}
