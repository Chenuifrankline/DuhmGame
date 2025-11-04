package codegenerator.test.exercise3;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestClass {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Eine Header-Datei für eine Klasse enthält immer Header-Guards und ein struct.
   */
  @Test
  public void test00_EmptyClassHeader() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestClass_struct {");
    _builder.newLine();
    _builder.append("} TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderem Name.
   */
  @Test
  public void test01_EmptyClassHeader2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("Klasse");
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef KLASSE_H");
    _builder.newLine();
    _builder.append("#define KLASSE_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct Klasse_struct {");
    _builder.newLine();
    _builder.append("} Klasse;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Attribute der Klasse werden im Struct gelistet.
   */
  @Test
  public void test02_ClassHeaderWithProperties() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("Vec2");
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
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef VEC2_H");
    _builder.newLine();
    _builder.append("#define VEC2_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct Vec2_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float x;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float y;");
    _builder.newLine();
    _builder.append("} Vec2;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Für alle Operationen der Klasse werden im header die Signaturen aufgeführt.
   */
  @Test
  public void test03_ClassHeaderWithPropertyAndOperation() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("testProperty");
        it_1.setUpper((-1));
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
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int* testProperty;");
    _builder.newLine();
    _builder.append("} TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void TestClass_testOperation(TestClass* const me);");
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
  public void test04_ClassHeaderWithPropertyAndOperation2() {
    PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
    final Procedure1<PrimitiveType> _function = (PrimitiveType it) -> {
      it.setName("int");
    };
    final PrimitiveType int_t = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function);
    PrimitiveType _createPrimitiveType_1 = this.factory.createPrimitiveType();
    final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it) -> {
      it.setName("uint8_t");
    };
    final PrimitiveType uint8_t = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType_1, _function_1);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("World");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_3 = (Property it_1) -> {
        it_1.setName("tiles");
        it_1.setUpper((8 * 8));
        it_1.setType(uint8_t);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_3);
      _ownedAttributes.add(_doubleArrow);
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_4 = (Operation it_1) -> {
        it_1.setName("getTileAt");
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_5 = (Parameter it_2) -> {
          it_2.setName("result");
          it_2.setType(uint8_t);
          it_2.setDirection(ParameterDirectionKind.RETURN_LITERAL);
        };
        Parameter _doubleArrow_1 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_5);
        _ownedParameters.add(_doubleArrow_1);
        EList<Parameter> _ownedParameters_1 = it_1.getOwnedParameters();
        Parameter _createParameter_1 = this.factory.createParameter();
        final Procedure1<Parameter> _function_6 = (Parameter it_2) -> {
          it_2.setName("x");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_1, _function_6);
        _ownedParameters_1.add(_doubleArrow_2);
        EList<Parameter> _ownedParameters_2 = it_1.getOwnedParameters();
        Parameter _createParameter_2 = this.factory.createParameter();
        final Procedure1<Parameter> _function_7 = (Parameter it_2) -> {
          it_2.setName("y");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_3 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_2, _function_7);
        _ownedParameters_2.add(_doubleArrow_3);
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_8 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          _bodies.add("return me->tiles[x + y * 8];");
        };
        OpaqueBehavior _doubleArrow_4 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_8);
        _methods.add(_doubleArrow_4);
      };
      Operation _doubleArrow_1 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_4);
      _ownedOperations.add(_doubleArrow_1);
      EList<Operation> _ownedOperations_1 = it.getOwnedOperations();
      Operation _createOperation_1 = this.factory.createOperation();
      final Procedure1<Operation> _function_5 = (Operation it_1) -> {
        it_1.setName("setTileAt");
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_6 = (Parameter it_2) -> {
          it_2.setName("x");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_6);
        _ownedParameters.add(_doubleArrow_2);
        EList<Parameter> _ownedParameters_1 = it_1.getOwnedParameters();
        Parameter _createParameter_1 = this.factory.createParameter();
        final Procedure1<Parameter> _function_7 = (Parameter it_2) -> {
          it_2.setName("y");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_3 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_1, _function_7);
        _ownedParameters_1.add(_doubleArrow_3);
        EList<Parameter> _ownedParameters_2 = it_1.getOwnedParameters();
        Parameter _createParameter_2 = this.factory.createParameter();
        final Procedure1<Parameter> _function_8 = (Parameter it_2) -> {
          it_2.setName("tile");
          it_2.setType(uint8_t);
        };
        Parameter _doubleArrow_4 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_2, _function_8);
        _ownedParameters_2.add(_doubleArrow_4);
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_9 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          _bodies.add("me->tiles[x + y * 8] = tile;");
        };
        OpaqueBehavior _doubleArrow_5 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_9);
        _methods.add(_doubleArrow_5);
      };
      Operation _doubleArrow_2 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation_1, _function_5);
      _ownedOperations_1.add(_doubleArrow_2);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_2);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef WORLD_H");
    _builder.newLine();
    _builder.append("#define WORLD_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct World_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("uint8_t tiles[64];");
    _builder.newLine();
    _builder.append("} World;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("uint8_t World_getTileAt(World* const me, int x, int y);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void World_setTileAt(World* const me, int x, int y, uint8_t tile);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Eine C-Datei einer Klasse importiert immer die dazugehörige H-Datei.
   * Für alle Operationen der Klasse werden die Implementationen gelistet.
   */
  @Test
  public void test05_ClassImplementationWithPropertyAndOperation() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("testProperty");
        it_1.setUpper((-1));
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
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"TestClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void TestClass_testOperation(TestClass* const me) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/* hier koennte Ihre Werbung stehen */");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test06_ClassImplementationWithPropertyAndOperation2() {
    PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
    final Procedure1<PrimitiveType> _function = (PrimitiveType it) -> {
      it.setName("int");
    };
    final PrimitiveType int_t = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function);
    PrimitiveType _createPrimitiveType_1 = this.factory.createPrimitiveType();
    final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it) -> {
      it.setName("uint8_t");
    };
    final PrimitiveType uint8_t = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType_1, _function_1);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("World");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_3 = (Property it_1) -> {
        it_1.setName("tiles");
        it_1.setUpper((8 * 8));
        it_1.setType(uint8_t);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_3);
      _ownedAttributes.add(_doubleArrow);
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_4 = (Operation it_1) -> {
        it_1.setName("getTileAt");
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_5 = (Parameter it_2) -> {
          it_2.setName("result");
          it_2.setType(uint8_t);
          it_2.setDirection(ParameterDirectionKind.RETURN_LITERAL);
        };
        Parameter _doubleArrow_1 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_5);
        _ownedParameters.add(_doubleArrow_1);
        EList<Parameter> _ownedParameters_1 = it_1.getOwnedParameters();
        Parameter _createParameter_1 = this.factory.createParameter();
        final Procedure1<Parameter> _function_6 = (Parameter it_2) -> {
          it_2.setName("x");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_1, _function_6);
        _ownedParameters_1.add(_doubleArrow_2);
        EList<Parameter> _ownedParameters_2 = it_1.getOwnedParameters();
        Parameter _createParameter_2 = this.factory.createParameter();
        final Procedure1<Parameter> _function_7 = (Parameter it_2) -> {
          it_2.setName("y");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_3 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_2, _function_7);
        _ownedParameters_2.add(_doubleArrow_3);
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_8 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          _bodies.add("return me->tiles[x + y * 8];");
        };
        OpaqueBehavior _doubleArrow_4 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_8);
        _methods.add(_doubleArrow_4);
      };
      Operation _doubleArrow_1 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_4);
      _ownedOperations.add(_doubleArrow_1);
      EList<Operation> _ownedOperations_1 = it.getOwnedOperations();
      Operation _createOperation_1 = this.factory.createOperation();
      final Procedure1<Operation> _function_5 = (Operation it_1) -> {
        it_1.setName("setTileAt");
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_6 = (Parameter it_2) -> {
          it_2.setName("x");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_6);
        _ownedParameters.add(_doubleArrow_2);
        EList<Parameter> _ownedParameters_1 = it_1.getOwnedParameters();
        Parameter _createParameter_1 = this.factory.createParameter();
        final Procedure1<Parameter> _function_7 = (Parameter it_2) -> {
          it_2.setName("y");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_3 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_1, _function_7);
        _ownedParameters_1.add(_doubleArrow_3);
        EList<Parameter> _ownedParameters_2 = it_1.getOwnedParameters();
        Parameter _createParameter_2 = this.factory.createParameter();
        final Procedure1<Parameter> _function_8 = (Parameter it_2) -> {
          it_2.setName("tile");
          it_2.setType(uint8_t);
        };
        Parameter _doubleArrow_4 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_2, _function_8);
        _ownedParameters_2.add(_doubleArrow_4);
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_9 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          _bodies.add("me->tiles[x + y * 8] = tile;");
        };
        OpaqueBehavior _doubleArrow_5 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_9);
        _methods.add(_doubleArrow_5);
      };
      Operation _doubleArrow_2 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation_1, _function_5);
      _ownedOperations_1.add(_doubleArrow_2);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_2);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"World.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("uint8_t World_getTileAt(World* const me, int x, int y) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return me->tiles[x + y * 8];");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void World_setTileAt(World* const me, int x, int y, uint8_t tile) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("me->tiles[x + y * 8] = tile;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * For Properties mit einem nicht-primitiven Type soll der Type included werden.
   */
  @Test
  public void test07_ClassHeaderWithIncludeFromProperty() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("testProperty");
        org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
        final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it_2) -> {
          it_2.setName("OtherClass");
        };
        org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"OtherClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("OtherClass* testProperty;");
    _builder.newLine();
    _builder.append("} TestClass;");
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
  public void test08_ClassHeaderWithIncludeFromProperty2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("Object");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("texture");
        org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
        final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it_2) -> {
          it_2.setName("Texture");
        };
        org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef OBJECT_H");
    _builder.newLine();
    _builder.append("#define OBJECT_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"Texture.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct Object_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Texture* texture;");
    _builder.newLine();
    _builder.append("} Object;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Auch für nicht-primitive Parameter sollen includes generiert werden.
   */
  @Test
  public void test09_ClassHeaderWithIncludeFromParameter() {
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
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_4 = (Parameter it_2) -> {
          it_2.setName("param");
          org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
          final Procedure1<org.eclipse.uml2.uml.Class> _function_5 = (org.eclipse.uml2.uml.Class it_3) -> {
            it_3.setName("OtherClass");
          };
          org.eclipse.uml2.uml.Class _doubleArrow_2 = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_5);
          it_2.setType(_doubleArrow_2);
        };
        Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_4);
        _ownedParameters.add(_doubleArrow_2);
      };
      Operation _doubleArrow_1 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_2);
      _ownedOperations.add(_doubleArrow_1);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"OtherClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int testProperty;");
    _builder.newLine();
    _builder.append("} TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void TestClass_testOperation(TestClass* const me, OtherClass* param);");
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
  public void test10_ClassHeaderWithIncludeFromParameter2() {
    PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
    final Procedure1<PrimitiveType> _function = (PrimitiveType it) -> {
      it.setName("int");
    };
    final PrimitiveType int_t = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("Texture");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_2 = (Property it_1) -> {
        it_1.setName("color_data");
        it_1.setUpper((-1));
        PrimitiveType _createPrimitiveType_1 = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_3 = (PrimitiveType it_2) -> {
          it_2.setName("uint8_t");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType_1, _function_3);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_2);
      _ownedAttributes.add(_doubleArrow);
      EList<Property> _ownedAttributes_1 = it.getOwnedAttributes();
      Property _createProperty_1 = this.factory.createProperty();
      final Procedure1<Property> _function_3 = (Property it_1) -> {
        it_1.setName("width");
        it_1.setType(int_t);
      };
      Property _doubleArrow_1 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_1, _function_3);
      _ownedAttributes_1.add(_doubleArrow_1);
      EList<Property> _ownedAttributes_2 = it.getOwnedAttributes();
      Property _createProperty_2 = this.factory.createProperty();
      final Procedure1<Property> _function_4 = (Property it_1) -> {
        it_1.setName("height");
        it_1.setType(int_t);
      };
      Property _doubleArrow_2 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_2, _function_4);
      _ownedAttributes_2.add(_doubleArrow_2);
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_5 = (Operation it_1) -> {
        it_1.setName("getColorAt");
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_6 = (Parameter it_2) -> {
          it_2.setName("result");
          org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
          final Procedure1<org.eclipse.uml2.uml.Class> _function_7 = (org.eclipse.uml2.uml.Class it_3) -> {
            it_3.setName("Color");
          };
          org.eclipse.uml2.uml.Class _doubleArrow_3 = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_7);
          it_2.setType(_doubleArrow_3);
          it_2.setDirection(ParameterDirectionKind.RETURN_LITERAL);
        };
        Parameter _doubleArrow_3 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_6);
        _ownedParameters.add(_doubleArrow_3);
        EList<Parameter> _ownedParameters_1 = it_1.getOwnedParameters();
        Parameter _createParameter_1 = this.factory.createParameter();
        final Procedure1<Parameter> _function_7 = (Parameter it_2) -> {
          it_2.setName("x");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_4 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_1, _function_7);
        _ownedParameters_1.add(_doubleArrow_4);
        EList<Parameter> _ownedParameters_2 = it_1.getOwnedParameters();
        Parameter _createParameter_2 = this.factory.createParameter();
        final Procedure1<Parameter> _function_8 = (Parameter it_2) -> {
          it_2.setName("y");
          it_2.setType(int_t);
        };
        Parameter _doubleArrow_5 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_2, _function_8);
        _ownedParameters_2.add(_doubleArrow_5);
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_9 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Color *result = Color_New();");
          _builder.newLine();
          _builder.append("result->r = me->color_data[(x + y * me->width) * 3 + 0];");
          _builder.newLine();
          _builder.append("result->g = me->color_data[(x + y * me->width) * 3 + 1];");
          _builder.newLine();
          _builder.append("result->b = me->color_data[(x + y * me->width) * 3 + 2];");
          _builder.newLine();
          _builder.append("return result;");
          _builder.newLine();
          _bodies.add(_builder.toString());
        };
        OpaqueBehavior _doubleArrow_6 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_9);
        _methods.add(_doubleArrow_6);
      };
      Operation _doubleArrow_3 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_5);
      _ownedOperations.add(_doubleArrow_3);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TEXTURE_H");
    _builder.newLine();
    _builder.append("#define TEXTURE_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"Color.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct Texture_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("uint8_t* color_data;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int width;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int height;");
    _builder.newLine();
    _builder.append("} Texture;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Color* Texture_getColorAt(Texture* const me, int x, int y);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Für Klassen in einem Namespace muss der Name des Namespace an den korrekten Stellen eingefügt werden.
   */
  @Test
  public void test11_ClassHeaderInNamespace() {
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
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("TestPackage");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(class_);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestPackage_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int testProperty;");
    _builder.newLine();
    _builder.append("} TestPackage_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me);");
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
  public void test12_ClassHeaderInNamespace2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("NestedClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("someProperty");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("float");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_2 = (Operation it_1) -> {
        it_1.setName("anOperation");
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
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("ParentPackage");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(class_);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef PARENTPACKAGE_NESTEDCLASS_H");
    _builder.newLine();
    _builder.append("#define PARENTPACKAGE_NESTEDCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct ParentPackage_NestedClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("float someProperty;");
    _builder.newLine();
    _builder.append("} ParentPackage_NestedClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void ParentPackage_NestedClass_anOperation(ParentPackage_NestedClass* const me);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test, aber C-Datei statt H-Datei.
   */
  @Test
  public void test13_ClassImplementationInNamespace() {
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
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("TestPackage");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(class_);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"TestClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/* hier koennte Ihre Werbung stehen */");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test14_ClassImplementationInNamespace2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("NestedClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("someProperty");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("float");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_2 = (Operation it_1) -> {
        it_1.setName("anOperation");
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
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("ParentPackage");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(class_);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    final String code = new Uml2C().generateCode(class_, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"NestedClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void ParentPackage_NestedClass_anOperation(ParentPackage_NestedClass* const me) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/* hier koennte Ihre Werbung stehen */");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Includes müssen den Pfad zur includeten Datei korrekt auflösen.
   */
  @Test
  public void test15_ClassHeaderWithIncludeInOtherNamespace() {
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
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_4 = (Parameter it_2) -> {
          it_2.setName("param");
          org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
          final Procedure1<org.eclipse.uml2.uml.Class> _function_5 = (org.eclipse.uml2.uml.Class it_3) -> {
            it_3.setName("OtherClass");
          };
          org.eclipse.uml2.uml.Class _doubleArrow_2 = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_5);
          it_2.setType(_doubleArrow_2);
        };
        Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_4);
        _ownedParameters.add(_doubleArrow_2);
      };
      Operation _doubleArrow_1 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_2);
      _ownedOperations.add(_doubleArrow_1);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("TestPackage");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(class_);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"../OtherClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestPackage_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int testProperty;");
    _builder.newLine();
    _builder.append("} TestPackage_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me, OtherClass* param);");
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
  public void test16_ClassHeaderWithIncludeInOtherNamespace2() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("OtherClass");
    };
    final org.eclipse.uml2.uml.Class otherClass = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("OtherPackage");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(otherClass);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function_2 = (Enumeration it) -> {
      it.setName("AnEnum");
    };
    final Enumeration anEnum = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_2);
    org.eclipse.uml2.uml.Package _createPackage_1 = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_3 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("OtherPackage2");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(anEnum);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage_1, _function_3);
    org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_4 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_5 = (Property it_1) -> {
        it_1.setName("testProperty");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_6 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_6);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_5);
      _ownedAttributes.add(_doubleArrow);
      EList<Property> _ownedAttributes_1 = it.getOwnedAttributes();
      Property _createProperty_1 = this.factory.createProperty();
      final Procedure1<Property> _function_6 = (Property it_1) -> {
        it_1.setName("anotherProperty");
        it_1.setType(anEnum);
      };
      Property _doubleArrow_1 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_1, _function_6);
      _ownedAttributes_1.add(_doubleArrow_1);
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_7 = (Operation it_1) -> {
        it_1.setName("testOperation");
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_8 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          _bodies.add("/* hier koennte Ihre Werbung stehen */");
        };
        OpaqueBehavior _doubleArrow_2 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_8);
        _methods.add(_doubleArrow_2);
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_9 = (Parameter it_2) -> {
          it_2.setName("param");
          it_2.setType(otherClass);
        };
        Parameter _doubleArrow_3 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_9);
        _ownedParameters.add(_doubleArrow_3);
      };
      Operation _doubleArrow_2 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_7);
      _ownedOperations.add(_doubleArrow_2);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_4);
    org.eclipse.uml2.uml.Package _createPackage_2 = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_5 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("TestPackage");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(class_);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage_2, _function_5);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"../OtherPackage/OtherClass.h\"");
    _builder.newLine();
    _builder.append("#include \"../OtherPackage2/AnEnum.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestPackage_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int testProperty;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("OtherPackage2_AnEnum anotherProperty;");
    _builder.newLine();
    _builder.append("} TestPackage_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me, OtherPackage_OtherClass* param);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Include einer Klasse im selben Namespace.
   */
  @Test
  public void test17_ClassHeaderWithIncludeInSameNamespace() {
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
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_3 = (Operation it_1) -> {
        it_1.setName("testOperation");
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_4 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          _bodies.add("/* hier koennte Ihre Werbung stehen */");
        };
        OpaqueBehavior _doubleArrow_1 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_4);
        _methods.add(_doubleArrow_1);
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_5 = (Parameter it_2) -> {
          it_2.setName("param");
          it_2.setType(otherClass);
        };
        Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_5);
        _ownedParameters.add(_doubleArrow_2);
      };
      Operation _doubleArrow_1 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_3);
      _ownedOperations.add(_doubleArrow_1);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_1);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_2 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("TestPackage");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(otherClass);
      EList<Type> _ownedTypes_1 = it.getOwnedTypes();
      _ownedTypes_1.add(class_);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_2);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"OtherClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestPackage_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int testProperty;");
    _builder.newLine();
    _builder.append("} TestPackage_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me, TestPackage_OtherClass* param);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Weiterer Test für relative include-Pfade.
   */
  @Test
  public void test18_ClassHeaderWithIncludeInDeeperNamespace() {
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
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      Operation _createOperation = this.factory.createOperation();
      final Procedure1<Operation> _function_3 = (Operation it_1) -> {
        it_1.setName("testOperation");
        EList<Behavior> _methods = it_1.getMethods();
        OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
        final Procedure1<OpaqueBehavior> _function_4 = (OpaqueBehavior it_2) -> {
          EList<String> _languages = it_2.getLanguages();
          _languages.add("C");
          EList<String> _bodies = it_2.getBodies();
          _bodies.add("/* hier koennte Ihre Werbung stehen */");
        };
        OpaqueBehavior _doubleArrow_1 = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_4);
        _methods.add(_doubleArrow_1);
        EList<Parameter> _ownedParameters = it_1.getOwnedParameters();
        Parameter _createParameter = this.factory.createParameter();
        final Procedure1<Parameter> _function_5 = (Parameter it_2) -> {
          it_2.setName("param");
          it_2.setType(otherClass);
        };
        Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_5);
        _ownedParameters.add(_doubleArrow_2);
      };
      Operation _doubleArrow_1 = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_3);
      _ownedOperations.add(_doubleArrow_1);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_1);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_2 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("TestPackage");
      EList<org.eclipse.uml2.uml.Package> _nestedPackages = it.getNestedPackages();
      org.eclipse.uml2.uml.Package _createPackage_1 = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_3 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("NestedPackage");
        EList<Type> _ownedTypes = it_1.getOwnedTypes();
        _ownedTypes.add(otherClass);
      };
      org.eclipse.uml2.uml.Package _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage_1, _function_3);
      _nestedPackages.add(_doubleArrow);
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(class_);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_2);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTPACKAGE_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"NestedPackage/OtherClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestPackage_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int testProperty;");
    _builder.newLine();
    _builder.append("} TestPackage_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void TestPackage_TestClass_testOperation(TestPackage_TestClass* const me, TestPackage_NestedPackage_OtherClass* param);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Namespaces können beliebig tief geschachtelt sein.
   */
  @Test
  public void test19_ClassHeaderWithMapyIncludes() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("MainClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("f");
        org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
        final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it_2) -> {
          it_2.setName("F");
        };
        org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
      EList<Property> _ownedAttributes_1 = it.getOwnedAttributes();
      Property _createProperty_1 = this.factory.createProperty();
      final Procedure1<Property> _function_2 = (Property it_1) -> {
        it_1.setName("b");
        org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
        final Procedure1<org.eclipse.uml2.uml.Class> _function_3 = (org.eclipse.uml2.uml.Class it_2) -> {
          it_2.setName("B");
        };
        org.eclipse.uml2.uml.Class _doubleArrow_1 = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_3);
        it_1.setType(_doubleArrow_1);
      };
      Property _doubleArrow_1 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_1, _function_2);
      _ownedAttributes_1.add(_doubleArrow_1);
      EList<Property> _ownedAttributes_2 = it.getOwnedAttributes();
      Property _createProperty_2 = this.factory.createProperty();
      final Procedure1<Property> _function_3 = (Property it_1) -> {
        it_1.setName("c");
        org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
        final Procedure1<org.eclipse.uml2.uml.Class> _function_4 = (org.eclipse.uml2.uml.Class it_2) -> {
          it_2.setName("C");
        };
        org.eclipse.uml2.uml.Class _doubleArrow_2 = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_4);
        it_1.setType(_doubleArrow_2);
      };
      Property _doubleArrow_2 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_2, _function_3);
      _ownedAttributes_2.add(_doubleArrow_2);
      EList<Property> _ownedAttributes_3 = it.getOwnedAttributes();
      Property _createProperty_3 = this.factory.createProperty();
      final Procedure1<Property> _function_4 = (Property it_1) -> {
        it_1.setName("a");
        org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
        final Procedure1<org.eclipse.uml2.uml.Class> _function_5 = (org.eclipse.uml2.uml.Class it_2) -> {
          it_2.setName("A");
        };
        org.eclipse.uml2.uml.Class _doubleArrow_3 = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_5);
        it_1.setType(_doubleArrow_3);
      };
      Property _doubleArrow_3 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_3, _function_4);
      _ownedAttributes_3.add(_doubleArrow_3);
      EList<Property> _ownedAttributes_4 = it.getOwnedAttributes();
      Property _createProperty_4 = this.factory.createProperty();
      final Procedure1<Property> _function_5 = (Property it_1) -> {
        it_1.setName("e");
        org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
        final Procedure1<org.eclipse.uml2.uml.Class> _function_6 = (org.eclipse.uml2.uml.Class it_2) -> {
          it_2.setName("E");
        };
        org.eclipse.uml2.uml.Class _doubleArrow_4 = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_6);
        it_1.setType(_doubleArrow_4);
      };
      Property _doubleArrow_4 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_4, _function_5);
      _ownedAttributes_4.add(_doubleArrow_4);
      EList<Property> _ownedAttributes_5 = it.getOwnedAttributes();
      Property _createProperty_5 = this.factory.createProperty();
      final Procedure1<Property> _function_6 = (Property it_1) -> {
        it_1.setName("d");
        org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
        final Procedure1<org.eclipse.uml2.uml.Class> _function_7 = (org.eclipse.uml2.uml.Class it_2) -> {
          it_2.setName("D");
        };
        org.eclipse.uml2.uml.Class _doubleArrow_5 = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_7);
        it_1.setType(_doubleArrow_5);
      };
      Property _doubleArrow_5 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_5, _function_6);
      _ownedAttributes_5.add(_doubleArrow_5);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef MAINCLASS_H");
    _builder.newLine();
    _builder.append("#define MAINCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"A.h\"");
    _builder.newLine();
    _builder.append("#include \"B.h\"");
    _builder.newLine();
    _builder.append("#include \"C.h\"");
    _builder.newLine();
    _builder.append("#include \"D.h\"");
    _builder.newLine();
    _builder.append("#include \"E.h\"");
    _builder.newLine();
    _builder.append("#include \"F.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct MainClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("F* f;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("B* b;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("C* c;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("A* a;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("E* e;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("D* d;");
    _builder.newLine();
    _builder.append("} MainClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Nicht nur Klassen, auch Enumerationen müssen included werden.
   */
  @Test
  public void test20_ClassHeaderWithEnumIncludeFromProperty() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_1 = (Property it_1) -> {
        it_1.setName("testProperty");
        Enumeration _createEnumeration = this.factory.createEnumeration();
        final Procedure1<Enumeration> _function_2 = (Enumeration it_2) -> {
          it_2.setName("AnEnum");
        };
        Enumeration _doubleArrow = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_2);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_1);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"AnEnum.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("AnEnum testProperty;");
    _builder.newLine();
    _builder.append("} TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Auch Enums können in in anderen Namespaces liegen.
   */
  @Test
  public void test21_ClassHeaderWithEnumInOtherNamespaceIncludeFromProperty() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("AnEnum");
    };
    final Enumeration enu = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_2 = (Property it_1) -> {
        it_1.setName("testProperty");
        it_1.setType(enu);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_2);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("wurzel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_3 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("kiste");
        EList<PackageableElement> _packagedElements_1 = it_1.getPackagedElements();
        _packagedElements_1.add(enu);
      };
      org.eclipse.uml2.uml.Package _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_3);
      _packagedElements.add(_doubleArrow);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      org.eclipse.uml2.uml.Package _createPackage_1 = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_4 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("karton");
        EList<PackageableElement> _packagedElements_2 = it_1.getPackagedElements();
        _packagedElements_2.add(class_);
      };
      org.eclipse.uml2.uml.Package _doubleArrow_1 = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage_1, _function_4);
      _packagedElements_1.add(_doubleArrow_1);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef WURZEL_KARTON_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define WURZEL_KARTON_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"../kiste/AnEnum.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct wurzel_karton_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("wurzel_kiste_AnEnum testProperty;");
    _builder.newLine();
    _builder.append("} wurzel_karton_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Dependencies können verwendet werden um includes zu erzeugen, auch wenn der Type nicht in einem Paramete roder Property auftaucht der Klasse.
   * Tip: umlClass.relationships.filter(Dependency)
   */
  @Test
  public void test22_ClassHeaderWithDependencyInclude() {
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
        Enumeration _createEnumeration = this.factory.createEnumeration();
        final Procedure1<Enumeration> _function_3 = (Enumeration it_2) -> {
          it_2.setName("AnEnum");
        };
        Enumeration _doubleArrow = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_3);
        it_1.setType(_doubleArrow);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_2);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_1);
    Dependency _createDependency = this.factory.createDependency();
    final Procedure1<Dependency> _function_2 = (Dependency it) -> {
      it.setName("dep");
      EList<NamedElement> _clients = it.getClients();
      _clients.add(class_);
      EList<NamedElement> _suppliers = it.getSuppliers();
      _suppliers.add(otherClass);
    };
    ObjectExtensions.<Dependency>operator_doubleArrow(_createDependency, _function_2);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"AnEnum.h\"");
    _builder.newLine();
    _builder.append("#include \"OtherClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("AnEnum testProperty;");
    _builder.newLine();
    _builder.append("} TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Includes durch Dependencies müssen auch relative Pfade erzeugen.
   */
  @Test
  public void test23_ClassHeaderWithDependencyInOtherNamespaceInclude() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("OtherClass");
    };
    final org.eclipse.uml2.uml.Class otherClass = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function_1 = (Enumeration it) -> {
      it.setName("AnEnum");
    };
    final Enumeration enu = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_1);
    org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_3 = (Property it_1) -> {
        it_1.setName("testProperty");
        it_1.setType(enu);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_3);
      _ownedAttributes.add(_doubleArrow);
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_2);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_3 = (Model it) -> {
      it.setName("wurzel");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_4 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("kiste");
        EList<PackageableElement> _packagedElements_1 = it_1.getPackagedElements();
        _packagedElements_1.add(enu);
      };
      org.eclipse.uml2.uml.Package _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_4);
      _packagedElements.add(_doubleArrow);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      org.eclipse.uml2.uml.Package _createPackage_1 = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_5 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("karton");
        EList<PackageableElement> _packagedElements_2 = it_1.getPackagedElements();
        _packagedElements_2.add(class_);
      };
      org.eclipse.uml2.uml.Package _doubleArrow_1 = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage_1, _function_5);
      _packagedElements_1.add(_doubleArrow_1);
      EList<PackageableElement> _packagedElements_2 = it.getPackagedElements();
      org.eclipse.uml2.uml.Package _createPackage_2 = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_6 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("packet");
        EList<PackageableElement> _packagedElements_3 = it_1.getPackagedElements();
        _packagedElements_3.add(otherClass);
      };
      org.eclipse.uml2.uml.Package _doubleArrow_2 = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage_2, _function_6);
      _packagedElements_2.add(_doubleArrow_2);
      EList<PackageableElement> _packagedElements_3 = it.getPackagedElements();
      Dependency _createDependency = this.factory.createDependency();
      final Procedure1<Dependency> _function_7 = (Dependency it_1) -> {
        it_1.setName("dep");
        EList<NamedElement> _clients = it_1.getClients();
        _clients.add(class_);
        EList<NamedElement> _suppliers = it_1.getSuppliers();
        _suppliers.add(otherClass);
      };
      Dependency _doubleArrow_3 = ObjectExtensions.<Dependency>operator_doubleArrow(_createDependency, _function_7);
      _packagedElements_3.add(_doubleArrow_3);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_3);
    final String code = new Uml2C().generateCode(class_, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef WURZEL_KARTON_TESTCLASS_H");
    _builder.newLine();
    _builder.append("#define WURZEL_KARTON_TESTCLASS_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"../kiste/AnEnum.h\"");
    _builder.newLine();
    _builder.append("#include \"../packet/OtherClass.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct wurzel_karton_TestClass_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("wurzel_kiste_AnEnum testProperty;");
    _builder.newLine();
    _builder.append("} wurzel_karton_TestClass;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Includes in einer H-Datei sollen alphabetisch sortiert sein.
   */
  @Test
  public void test24_ClassHeaderWithSortedIncludes() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("D");
    };
    final org.eclipse.uml2.uml.Class d = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    org.eclipse.uml2.uml.Class _createClass_1 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("B");
    };
    final org.eclipse.uml2.uml.Class b = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_1, _function_1);
    org.eclipse.uml2.uml.Class _createClass_2 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("A");
    };
    final org.eclipse.uml2.uml.Class a = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_2, _function_2);
    org.eclipse.uml2.uml.Class _createClass_3 = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_3 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("C");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      Property _createProperty = this.factory.createProperty();
      final Procedure1<Property> _function_4 = (Property it_1) -> {
        it_1.setName("pa");
        it_1.setType(a);
      };
      Property _doubleArrow = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_4);
      _ownedAttributes.add(_doubleArrow);
      EList<Property> _ownedAttributes_1 = it.getOwnedAttributes();
      Property _createProperty_1 = this.factory.createProperty();
      final Procedure1<Property> _function_5 = (Property it_1) -> {
        it_1.setName("pb");
        it_1.setType(b);
      };
      Property _doubleArrow_1 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_1, _function_5);
      _ownedAttributes_1.add(_doubleArrow_1);
      EList<Property> _ownedAttributes_2 = it.getOwnedAttributes();
      Property _createProperty_2 = this.factory.createProperty();
      final Procedure1<Property> _function_6 = (Property it_1) -> {
        it_1.setName("pb2");
        it_1.setType(b);
      };
      Property _doubleArrow_2 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_2, _function_6);
      _ownedAttributes_2.add(_doubleArrow_2);
      EList<Property> _ownedAttributes_3 = it.getOwnedAttributes();
      Property _createProperty_3 = this.factory.createProperty();
      final Procedure1<Property> _function_7 = (Property it_1) -> {
        it_1.setName("pd");
        it_1.setType(d);
      };
      Property _doubleArrow_3 = ObjectExtensions.<Property>operator_doubleArrow(_createProperty_3, _function_7);
      _ownedAttributes_3.add(_doubleArrow_3);
    };
    final org.eclipse.uml2.uml.Class c = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass_3, _function_3);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_4 = (Model it) -> {
      it.setName("r");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_5 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("z");
        EList<PackageableElement> _packagedElements_1 = it_1.getPackagedElements();
        _packagedElements_1.add(a);
      };
      org.eclipse.uml2.uml.Package _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_5);
      _packagedElements.add(_doubleArrow);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      org.eclipse.uml2.uml.Package _createPackage_1 = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_6 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("y");
        EList<PackageableElement> _packagedElements_2 = it_1.getPackagedElements();
        _packagedElements_2.add(b);
      };
      org.eclipse.uml2.uml.Package _doubleArrow_1 = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage_1, _function_6);
      _packagedElements_1.add(_doubleArrow_1);
      EList<PackageableElement> _packagedElements_2 = it.getPackagedElements();
      org.eclipse.uml2.uml.Package _createPackage_2 = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_7 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("x");
        EList<PackageableElement> _packagedElements_3 = it_1.getPackagedElements();
        _packagedElements_3.add(c);
      };
      org.eclipse.uml2.uml.Package _doubleArrow_2 = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage_2, _function_7);
      _packagedElements_2.add(_doubleArrow_2);
      EList<PackageableElement> _packagedElements_3 = it.getPackagedElements();
      org.eclipse.uml2.uml.Package _createPackage_3 = this.factory.createPackage();
      final Procedure1<org.eclipse.uml2.uml.Package> _function_8 = (org.eclipse.uml2.uml.Package it_1) -> {
        it_1.setName("w");
        EList<PackageableElement> _packagedElements_4 = it_1.getPackagedElements();
        _packagedElements_4.add(d);
      };
      org.eclipse.uml2.uml.Package _doubleArrow_3 = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage_3, _function_8);
      _packagedElements_3.add(_doubleArrow_3);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_4);
    final String code = new Uml2C().generateCode(c, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef R_X_C_H");
    _builder.newLine();
    _builder.append("#define R_X_C_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"../w/D.h\"");
    _builder.newLine();
    _builder.append("#include \"../y/B.h\"");
    _builder.newLine();
    _builder.append("#include \"../z/A.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef struct r_x_C_struct {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("r_z_A* pa;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("r_y_B* pb;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("r_y_B* pb2;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("r_w_D* pd;");
    _builder.newLine();
    _builder.append("} r_x_C;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }
}
