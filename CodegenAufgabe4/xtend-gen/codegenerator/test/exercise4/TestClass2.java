package codegenerator.test.exercise4;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestClass2 {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Für Instanzen einer Klasse werden globale Variablen in der Header Datei deklariert.
   */
  @Test
  public void test00_EmptyClassHeader() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_1 = (Model it) -> {
      it.setName("TestModel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_2 = (InstanceSpecification it_1) -> {
        it_1.setName("testInstance");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_2);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_1);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTMODEL_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTMODEL_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestModel_TestClass_struct {");
    _builder.newLine();
    _builder.append("} TestModel_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("extern TestModel_TestClass TestModel_testInstance;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test01_EmptyClassHeader2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassWithInstance");
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_1 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_2 = (InstanceSpecification it_1) -> {
        it_1.setName("object");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_2);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_1);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef MODEL_CLASSWITHINSTANCE_H");
    _builder.newLine();
    _builder.append("#define MODEL_CLASSWITHINSTANCE_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct Model_ClassWithInstance_struct {");
    _builder.newLine();
    _builder.append("} Model_ClassWithInstance;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("extern Model_ClassWithInstance Model_object;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Instanzen einer Klasse werden in der C-Datei der Klasse initialisiert.
   */
  @Test
  public void test02_EmptyClassImpl() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_1 = (Model it) -> {
      it.setName("TestModel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_2 = (InstanceSpecification it_1) -> {
        it_1.setName("testInstance");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_2);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_1);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"TestClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("TestModel_TestClass TestModel_testInstance = {");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test03_EmptyClassImpl2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassWithInstance");
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_1 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_2 = (InstanceSpecification it_1) -> {
        it_1.setName("object");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_2);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_1);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"ClassWithInstance.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Model_ClassWithInstance Model_object = {");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Attribute einer Klasse haben keinen Einfluss auf die Objekt-Variable in der H-Datei.
   */
  @Test
  public void test04_ClassWithAttributeHeader() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("int");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("TestModel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("testInstance");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTMODEL_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTMODEL_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestModel_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int attr;");
    _builder.newLine();
    _builder.append("} TestModel_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("extern TestModel_TestClass TestModel_testInstance;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test05_ClassWithAttributeHeader2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassWithInstance");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("object");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef MODEL_CLASSWITHINSTANCE_H");
    _builder.newLine();
    _builder.append("#define MODEL_CLASSWITHINSTANCE_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct Model_ClassWithInstance_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float attr;");
    _builder.newLine();
    _builder.append("} Model_ClassWithInstance;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("extern Model_ClassWithInstance Model_object;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Ohne Slots in Objekt haben Attribute einer Klasse haben keinen Einfluss auf die Inilialisierung in der C-Datei.
   */
  @Test
  public void test06_ClassWithAttributeImpl() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("int");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("TestModel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("testInstance");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"TestClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("TestModel_TestClass TestModel_testInstance = {");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test07_ClassWithAttributeImpl2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassWithInstance");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("object");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"ClassWithInstance.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Model_ClassWithInstance Model_object = {");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Auch mit Slots im Objekt ändert sich die H-Datei nicht.
   */
  @Test
  public void test08_ClassWithAttributeAndSlotHeader() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("int");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("TestModel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("testInstance");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_4 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
          final Procedure1<LiteralInteger> _function_5 = (LiteralInteger it_3) -> {
            it_3.setValue(1);
          };
          LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_5);
          _values.add(_doubleArrow);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_4);
        _slots.add(_doubleArrow);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTMODEL_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTMODEL_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestModel_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int attr;");
    _builder.newLine();
    _builder.append("} TestModel_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("extern TestModel_TestClass TestModel_testInstance;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test09_ClassWithAttributeAndSlotHeader2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassWithInstance");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("object");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_4 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralReal _createLiteralReal = this.factory.createLiteralReal();
          final Procedure1<LiteralReal> _function_5 = (LiteralReal it_3) -> {
            it_3.setValue(42.0);
          };
          LiteralReal _doubleArrow = ObjectExtensions.<LiteralReal>operator_doubleArrow(_createLiteralReal, _function_5);
          _values.add(_doubleArrow);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_4);
        _slots.add(_doubleArrow);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef MODEL_CLASSWITHINSTANCE_H");
    _builder.newLine();
    _builder.append("#define MODEL_CLASSWITHINSTANCE_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct Model_ClassWithInstance_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float attr;");
    _builder.newLine();
    _builder.append("} Model_ClassWithInstance;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("extern Model_ClassWithInstance Model_object;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Slots mit Value werden in der Initialisierung des Objekts abgebildet.
   */
  @Test
  public void test10_ClassWithAttributeAndSlotImpl() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("int");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("TestModel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("testInstance");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_4 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
          final Procedure1<LiteralInteger> _function_5 = (LiteralInteger it_3) -> {
            it_3.setValue(1);
          };
          LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_5);
          _values.add(_doubleArrow);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_4);
        _slots.add(_doubleArrow);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"TestClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("TestModel_TestClass TestModel_testInstance = {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr = 1");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test11_ClassWithAttributeAndSlotImpl2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassWithInstance");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("object");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_4 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralReal _createLiteralReal = this.factory.createLiteralReal();
          final Procedure1<LiteralReal> _function_5 = (LiteralReal it_3) -> {
            it_3.setValue(42.0);
          };
          LiteralReal _doubleArrow = ObjectExtensions.<LiteralReal>operator_doubleArrow(_createLiteralReal, _function_5);
          _values.add(_doubleArrow);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_4);
        _slots.add(_doubleArrow);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"ClassWithInstance.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Model_ClassWithInstance Model_object = {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr = 42.0");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Jeder Slot mit Value bekommt seine eigene Zeile. Slots sind mit Komma getrennt.
   */
  @Test
  public void test12_ClassWithAttributesAndSlotsImpl() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr0");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("int");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute0 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    Property _createProperty_1 = this.factory.createProperty();
    final Procedure1<Property> _function_1 = (Property it) -> {
      it.setName("attr1");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
      it.setType(_doubleArrow);
    };
    final Property attribute1 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_1, _function_1);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute0);
      EList<Property> _ownedAttributes_1 = it.getOwnedAttributes();
      _ownedAttributes_1.add(attribute1);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_2);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_3 = (Model it) -> {
      it.setName("TestModel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_4 = (InstanceSpecification it_1) -> {
        it_1.setName("testInstance");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_5 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute0);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
          final Procedure1<LiteralInteger> _function_6 = (LiteralInteger it_3) -> {
            it_3.setValue(1);
          };
          LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_6);
          _values.add(_doubleArrow);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_5);
        _slots.add(_doubleArrow);
        EList<Slot> _slots_1 = it_1.getSlots();
        Slot _createSlot_1 = it_1.createSlot();
        final Procedure1<Slot> _function_6 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute1);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralReal _createLiteralReal = this.factory.createLiteralReal();
          final Procedure1<LiteralReal> _function_7 = (LiteralReal it_3) -> {
            it_3.setValue(123.4);
          };
          LiteralReal _doubleArrow_1 = ObjectExtensions.<LiteralReal>operator_doubleArrow(_createLiteralReal, _function_7);
          _values.add(_doubleArrow_1);
        };
        Slot _doubleArrow_1 = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot_1, _function_6);
        _slots_1.add(_doubleArrow_1);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_4);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_3);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"TestClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("TestModel_TestClass TestModel_testInstance = {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr0 = 1,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr1 = 123.4");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test13_ClassWithAttributesAndSlotsImpl2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr0");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property attribute0 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    Property _createProperty_1 = this.factory.createProperty();
    final Procedure1<Property> _function_1 = (Property it) -> {
      it.setName("attr1");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_1) -> {
        it_1.setName("int");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
      it.setType(_doubleArrow);
    };
    final Property attribute1 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_1, _function_1);
    Property _createProperty_2 = this.factory.createProperty();
    final Procedure1<Property> _function_2 = (Property it) -> {
      it.setName("attr2");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_3 = (PrimitiveType it_1) -> {
        it_1.setName("char");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_3);
      it.setType(_doubleArrow);
    };
    final Property attribute2 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_2, _function_2);
    Property _createProperty_3 = this.factory.createProperty();
    final Procedure1<Property> _function_3 = (Property it) -> {
      it.setName("attr3");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_4 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_4);
      it.setType(_doubleArrow);
    };
    final Property attribute3 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_3, _function_3);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_4 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassWithInstance");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute0);
      EList<Property> _ownedAttributes_1 = it.getOwnedAttributes();
      _ownedAttributes_1.add(attribute1);
      EList<Property> _ownedAttributes_2 = it.getOwnedAttributes();
      _ownedAttributes_2.add(attribute2);
      EList<Property> _ownedAttributes_3 = it.getOwnedAttributes();
      _ownedAttributes_3.add(attribute3);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_4);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_5 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_6 = (InstanceSpecification it_1) -> {
        it_1.setName("object");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_7 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute0);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralReal _createLiteralReal = this.factory.createLiteralReal();
          final Procedure1<LiteralReal> _function_8 = (LiteralReal it_3) -> {
            it_3.setValue(42.0);
          };
          LiteralReal _doubleArrow = ObjectExtensions.<LiteralReal>operator_doubleArrow(_createLiteralReal, _function_8);
          _values.add(_doubleArrow);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_7);
        _slots.add(_doubleArrow);
        EList<Slot> _slots_1 = it_1.getSlots();
        Slot _createSlot_1 = it_1.createSlot();
        final Procedure1<Slot> _function_8 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute3);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralReal _createLiteralReal = this.factory.createLiteralReal();
          final Procedure1<LiteralReal> _function_9 = (LiteralReal it_3) -> {
            it_3.setValue(32.0);
          };
          LiteralReal _doubleArrow_1 = ObjectExtensions.<LiteralReal>operator_doubleArrow(_createLiteralReal, _function_9);
          _values.add(_doubleArrow_1);
        };
        Slot _doubleArrow_1 = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot_1, _function_8);
        _slots_1.add(_doubleArrow_1);
        EList<Slot> _slots_2 = it_1.getSlots();
        Slot _createSlot_2 = it_1.createSlot();
        final Procedure1<Slot> _function_9 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute1);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
          final Procedure1<LiteralInteger> _function_10 = (LiteralInteger it_3) -> {
            it_3.setValue(3);
          };
          LiteralInteger _doubleArrow_2 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_10);
          _values.add(_doubleArrow_2);
        };
        Slot _doubleArrow_2 = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot_2, _function_9);
        _slots_2.add(_doubleArrow_2);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_6);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_5);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"ClassWithInstance.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Model_ClassWithInstance Model_object = {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr0 = 42.0,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr3 = 32.0,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr1 = 3");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Die Initialisierung von Arrays verläuft über mehrere Zeilen.
   */
  @Test
  public void test14_ClassWithArrayAttributeAndSlotImpl() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("int");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
      it.setUpper(4);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("TestModel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("testInstance");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_4 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
          final Procedure1<LiteralInteger> _function_5 = (LiteralInteger it_3) -> {
            it_3.setValue(1);
          };
          LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_5);
          _values.add(_doubleArrow);
          EList<ValueSpecification> _values_1 = it_2.getValues();
          LiteralInteger _createLiteralInteger_1 = this.factory.createLiteralInteger();
          final Procedure1<LiteralInteger> _function_6 = (LiteralInteger it_3) -> {
            it_3.setValue(2);
          };
          LiteralInteger _doubleArrow_1 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger_1, _function_6);
          _values_1.add(_doubleArrow_1);
          EList<ValueSpecification> _values_2 = it_2.getValues();
          LiteralInteger _createLiteralInteger_2 = this.factory.createLiteralInteger();
          final Procedure1<LiteralInteger> _function_7 = (LiteralInteger it_3) -> {
            it_3.setValue(3);
          };
          LiteralInteger _doubleArrow_2 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger_2, _function_7);
          _values_2.add(_doubleArrow_2);
          EList<ValueSpecification> _values_3 = it_2.getValues();
          LiteralInteger _createLiteralInteger_3 = this.factory.createLiteralInteger();
          final Procedure1<LiteralInteger> _function_8 = (LiteralInteger it_3) -> {
            it_3.setValue(4);
          };
          LiteralInteger _doubleArrow_3 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger_3, _function_8);
          _values_3.add(_doubleArrow_3);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_4);
        _slots.add(_doubleArrow);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"TestClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("TestModel_TestClass TestModel_testInstance = {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr = {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1,");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("2,");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("3,");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("4");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test15_ClassWithArrayAttributeAndSlotImpl2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("attr");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
      it.setUpper(2);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassWithInstance");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it_1) -> {
        it_1.setName("object");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_4 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute);
          EList<ValueSpecification> _values = it_2.getValues();
          LiteralReal _createLiteralReal = this.factory.createLiteralReal();
          final Procedure1<LiteralReal> _function_5 = (LiteralReal it_3) -> {
            it_3.setValue(42.0);
          };
          LiteralReal _doubleArrow = ObjectExtensions.<LiteralReal>operator_doubleArrow(_createLiteralReal, _function_5);
          _values.add(_doubleArrow);
          EList<ValueSpecification> _values_1 = it_2.getValues();
          LiteralReal _createLiteralReal_1 = this.factory.createLiteralReal();
          final Procedure1<LiteralReal> _function_6 = (LiteralReal it_3) -> {
            it_3.setValue(79.1);
          };
          LiteralReal _doubleArrow_1 = ObjectExtensions.<LiteralReal>operator_doubleArrow(_createLiteralReal_1, _function_6);
          _values_1.add(_doubleArrow_1);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_4);
        _slots.add(_doubleArrow);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
      _packagedElements_1.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"ClassWithInstance.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Model_ClassWithInstance Model_object = {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr = {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("42.0,");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("79.1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Slots mit InstanceValue als Value nehmen die Addresse des Objekts.
   */
  @Test
  public void test16_ClassWithObjectAttributeAndSlotImpl() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("OtherClass");
    };
    final org.eclipse.uml2.uml.Class otherClass = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function_1 = (Property it) -> {
      it.setName("attr");
      it.setType(otherClass);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
    org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_2);
    InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it) -> {
      it.setName("otherInstance");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(otherClass);
    };
    final InstanceSpecification otherInstance = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_4 = (Model it) -> {
      it.setName("TestModel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(otherClass);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      _packagedElements_1.add(class_);
      EList<PackageableElement> _packagedElements_2 = it.getPackagedElements();
      _packagedElements_2.add(otherInstance);
      EList<PackageableElement> _packagedElements_3 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification_1 = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_5 = (InstanceSpecification it_1) -> {
        it_1.setName("testInstance");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_6 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute);
          EList<ValueSpecification> _values = it_2.getValues();
          InstanceValue _createInstanceValue = this.factory.createInstanceValue();
          final Procedure1<InstanceValue> _function_7 = (InstanceValue it_3) -> {
            it_3.setInstance(otherInstance);
          };
          InstanceValue _doubleArrow = ObjectExtensions.<InstanceValue>operator_doubleArrow(_createInstanceValue, _function_7);
          _values.add(_doubleArrow);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_6);
        _slots.add(_doubleArrow);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification_1, _function_5);
      _packagedElements_3.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_4);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"TestClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("TestModel_TestClass TestModel_testInstance = {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr = &TestModel_otherInstance");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test17_ClassWithObjectAttributeAndSlotImpl2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
    };
    final org.eclipse.uml2.uml.Class otherClass = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function_1 = (Property it) -> {
      it.setName("attr");
      it.setType(otherClass);
      it.setUpper(2);
    };
    final Property attribute = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
    org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("ClassWithInstance");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(attribute);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_2);
    InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_3 = (InstanceSpecification it) -> {
      it.setName("testInstance");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(otherClass);
    };
    final InstanceSpecification otherInstance0 = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_3);
    InstanceSpecification _createInstanceSpecification_1 = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_4 = (InstanceSpecification it) -> {
      it.setName("testInstance2");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(otherClass);
    };
    final InstanceSpecification otherInstance1 = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification_1, _function_4);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_5 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(otherClass);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      _packagedElements_1.add(class_);
      EList<PackageableElement> _packagedElements_2 = it.getPackagedElements();
      _packagedElements_2.add(otherInstance0);
      EList<PackageableElement> _packagedElements_3 = it.getPackagedElements();
      _packagedElements_3.add(otherInstance1);
      EList<PackageableElement> _packagedElements_4 = it.getPackagedElements();
      InstanceSpecification _createInstanceSpecification_2 = this.factory.createInstanceSpecification();
      final Procedure1<InstanceSpecification> _function_6 = (InstanceSpecification it_1) -> {
        it_1.setName("object");
        EList<Classifier> _classifiers = it_1.getClassifiers();
        _classifiers.add(class_);
        EList<Slot> _slots = it_1.getSlots();
        Slot _createSlot = it_1.createSlot();
        final Procedure1<Slot> _function_7 = (Slot it_2) -> {
          it_2.setDefiningFeature(attribute);
          EList<ValueSpecification> _values = it_2.getValues();
          InstanceValue _createInstanceValue = this.factory.createInstanceValue();
          final Procedure1<InstanceValue> _function_8 = (InstanceValue it_3) -> {
            it_3.setInstance(otherInstance0);
          };
          InstanceValue _doubleArrow = ObjectExtensions.<InstanceValue>operator_doubleArrow(_createInstanceValue, _function_8);
          _values.add(_doubleArrow);
          EList<ValueSpecification> _values_1 = it_2.getValues();
          InstanceValue _createInstanceValue_1 = this.factory.createInstanceValue();
          final Procedure1<InstanceValue> _function_9 = (InstanceValue it_3) -> {
            it_3.setInstance(otherInstance1);
          };
          InstanceValue _doubleArrow_1 = ObjectExtensions.<InstanceValue>operator_doubleArrow(_createInstanceValue_1, _function_9);
          _values_1.add(_doubleArrow_1);
        };
        Slot _doubleArrow = ObjectExtensions.<Slot>operator_doubleArrow(_createSlot, _function_7);
        _slots.add(_doubleArrow);
      };
      InstanceSpecification _doubleArrow = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification_2, _function_6);
      _packagedElements_4.add(_doubleArrow);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_5);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"ClassWithInstance.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Model_ClassWithInstance Model_object = {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(".attr = {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("&Model_testInstance,");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("&Model_testInstance2");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }
}
