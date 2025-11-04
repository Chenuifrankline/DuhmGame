package codegenerator.test.exercise2;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestStructuredClassifier {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Für eine Klasse soll ein struct generiert werden.
   * Das Struct wird von einem Typedef umschlossen, um einen kürzeren Namen zu ermöglichen.
   */
  @Test
  public void test00_EmptyClassStruct() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef struct TestClass_struct {");
    _builder.newLine();
    _builder.append("} TestClass;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test01_EmptyClassStruct2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassTest");
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef struct ClassTest_struct {");
    _builder.newLine();
    _builder.append("} ClassTest;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Properties einer Klasse sollen im Struct als Attribute auftauchen.
   */
  @Test
  public void test02_ClassWithPropertyStruct() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("testProperty");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef struct TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int testProperty;");
    _builder.newLine();
    _builder.append("} TestClass;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Features wie Arrays sollten bereits vom PropertyTemplate behandelt werden.
   */
  @Test
  public void test03_ClassWithProperty2Struct() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("PropertyClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("floatAttribute");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("float");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
        it_1.setUpper(2);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef struct PropertyClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float floatAttribute[2];");
    _builder.newLine();
    _builder.append("} PropertyClass;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Jedes Attribut bekommt eine eigene Zeile.
   */
  @Test
  public void test04_ClassWithMorePropertiesStruct() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("Vector3");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("x");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("float");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
      EList<Property> _ownedAttributes_1 = it.getOwnedAttributes();
      Property _createProperty_1 = this.factory.createProperty();
      final Procedure1<Property> _function_2 = (Property it_1) -> {
        it_1.setName("y");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_3 = (PrimitiveType it_2) -> {
          it_2.setName("float");
        };
        PrimitiveType _doubleArrow_1 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_3);
        it_1.setType(_doubleArrow_1);
      };
      Property _doubleArrow_1 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_1, _function_2);
      _ownedAttributes_1.add(_doubleArrow_1);
      EList<Property> _ownedAttributes_2 = it.getOwnedAttributes();
      Property _createProperty_2 = this.factory.createProperty();
      final Procedure1<Property> _function_3 = (Property it_1) -> {
        it_1.setName("z");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_4 = (PrimitiveType it_2) -> {
          it_2.setName("float");
        };
        PrimitiveType _doubleArrow_2 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_4);
        it_1.setType(_doubleArrow_2);
      };
      Property _doubleArrow_2 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_2, _function_3);
      _ownedAttributes_2.add(_doubleArrow_2);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef struct Vector3_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float x;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float y;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float z;");
    _builder.newLine();
    _builder.append("} Vector3;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test05_ClassWithMoreProperties2Struct() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("Texture");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("width");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
      EList<Property> _ownedAttributes_1 = it.getOwnedAttributes();
      Property _createProperty_1 = this.factory.createProperty();
      final Procedure1<Property> _function_2 = (Property it_1) -> {
        it_1.setName("height");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_3 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow_1 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_3);
        it_1.setType(_doubleArrow_1);
      };
      Property _doubleArrow_1 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_1, _function_2);
      _ownedAttributes_1.add(_doubleArrow_1);
      EList<Property> _ownedAttributes_2 = it.getOwnedAttributes();
      Property _createProperty_2 = this.factory.createProperty();
      final Procedure1<Property> _function_3 = (Property it_1) -> {
        it_1.setName("data");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_4 = (PrimitiveType it_2) -> {
          it_2.setName("color_t");
        };
        PrimitiveType _doubleArrow_2 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_4);
        it_1.setType(_doubleArrow_2);
        it_1.setUpper((-1));
      };
      Property _doubleArrow_2 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_2, _function_3);
      _ownedAttributes_2.add(_doubleArrow_2);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef struct Texture_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int width;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int height;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("color_t* data;");
    _builder.newLine();
    _builder.append("} Texture;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Die Existenz von Methoden in der Klasse hat keine Answirkungen auf das Struct.
   */
  @Test
  public void test06_ClassWithOperationStruct() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("testProperty");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_2 = (Operation it_1) -> {
        it_1.setName("testOperation");
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_3 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          _bodies.add("/* hier koennte Ihre Werbung stehen */");
        };
        OpaqueBehavior _doubleArrow_1 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_3);
        _methods.add(_doubleArrow_1);
      };
      Operation _doubleArrow_1 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_2);
      _ownedOperations.add(_doubleArrow_1);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef struct TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int testProperty;");
    _builder.newLine();
    _builder.append("} TestClass;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test07_ClassWithOperationStruct2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("OtherClass");
    };
    final org.eclipse.uml2.uml.Class otherClass = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_2 = (Property it_1) -> {
        it_1.setName("testProperty");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_3 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_3);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_2);
      _ownedAttributes.add(_doubleArrow);
      EList<Property> _ownedAttributes_1 = it.getOwnedAttributes();
      Property _createProperty_1 = this.factory.createProperty();
      final Procedure1<Property> _function_3 = (Property it_1) -> {
        it_1.setName("classProperty");
        it_1.setType(otherClass);
      };
      Property _doubleArrow_1 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_1, _function_3);
      _ownedAttributes_1.add(_doubleArrow_1);
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_4 = (Operation it_1) -> {
        it_1.setName("testOperation");
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_5 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          _bodies.add("/* hier koennte Ihre Werbung stehen */");
        };
        OpaqueBehavior _doubleArrow_2 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_5);
        _methods.add(_doubleArrow_2);
      };
      Operation _doubleArrow_2 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_4);
      _ownedOperations.add(_doubleArrow_2);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_1);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_2 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("SomePacket");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      _packagedElements_1.add(otherClass);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_2);
    final String code = new Uml2C().generateCode(class_, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef struct SomePacket_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int testProperty;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("SomePacket_OtherClass* classProperty;");
    _builder.newLine();
    _builder.append("} SomePacket_TestClass;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }
}
