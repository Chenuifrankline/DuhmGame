package codegenerator.test.exercise2;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestOperation {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Eine einfache Operation in einer Klasse, ohne Parameter.
   * Der me-Parameter muss für alle nicht-statischen Operationen generiert werden, der Type ist die Klasse in welcher die Operation liegt.
   * Da keine Implementierung existiert und der Kontext zudem "declaration" ist, wird nur die Signatur generiert.
   */
  @Test
  public void test00_SimpleOperation() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("simpleOperation");
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void TestClass_simpleOperation(TestClass* const me);", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test01_SimpleOperation2() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("someFunction");
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("MyClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void MyClass_someFunction(MyClass* const me);", code);
  }

  /**
   * Befindet sich die Klasse in einem Paket, muss dies im Namen der Funktion und Datentyp wiedergespiegelt werden.
   */
  @Test
  public void test02_NestedClassOperation() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("simpleOperation");
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("TestPackage");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("TestClass");
        EList<Operation> _ownedOperations = it_1.getOwnedOperations();
        _ownedOperations.add(operation);
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_2);
      _ownedTypes.add(_doubleArrow);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void TestPackage_TestClass_simpleOperation(TestPackage_TestClass* const me);", code);
  }

  /**
   * Der Rückganetyp einer Operation wird über einen Parameter mit der direction "RETURN_LITERAL" angegeben.
   */
  @Test
  public void test03_ReturningOperation() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("returningOperation");
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_1 = (Parameter it_1) -> {
        it_1.setName("returning");
        it_1.setDirection(ParameterDirectionKind.RETURN_LITERAL);
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("uint32");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Parameter _doubleArrow = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_1);
      _ownedParameters.add(_doubleArrow);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("uint32 TestClass_returningOperation(TestClass* const me);", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test04_ReturningOperation2() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("returningFunction");
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_1 = (Parameter it_1) -> {
        it_1.setName("ret");
        it_1.setDirection(ParameterDirectionKind.RETURN_LITERAL);
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("float");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Parameter _doubleArrow = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_1);
      _ownedParameters.add(_doubleArrow);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("SomeClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("float SomeClass_returningFunction(SomeClass* const me);", code);
  }

  /**
   * Normale Parameter erscheinen nach dem me-Parameter. Typ, pointer und Namen sollten durch das ParameterTemplate generiert werden.
   */
  @Test
  public void test05_OneParameterOperation() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("oneParameterOperation");
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_1 = (Parameter it_1) -> {
        it_1.setName("param1");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("uint32");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Parameter _doubleArrow = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_1);
      _ownedParameters.add(_doubleArrow);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void TestClass_oneParameterOperation(TestClass* const me, uint32 param1);", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test06_OneParameterOperation2() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("singleParameterOperation");
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_1 = (Parameter it_1) -> {
        it_1.setName("arg");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("char");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Parameter _doubleArrow = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_1);
      _ownedParameters.add(_doubleArrow);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("SomeType");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void SomeType_singleParameterOperation(SomeType* const me, char arg);", code);
  }

  /**
   * Mehr Parameter im Modell = mehr Parameter im generierten Code.
   */
  @Test
  public void test07_TwoParameterOperation() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("twoParameterOperation");
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_1 = (Parameter it_1) -> {
        it_1.setName("param1");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("uint32");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Parameter _doubleArrow = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_1);
      _ownedParameters.add(_doubleArrow);
      EList<Parameter> _ownedParameters_1 = it.getOwnedParameters();
      Parameter _createParameter_1 = this.factory.createParameter();
      final Procedure1<Parameter> _function_2 = (Parameter it_1) -> {
        it_1.setName("param2");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_3 = (PrimitiveType it_2) -> {
          it_2.setName("uint8");
        };
        PrimitiveType _doubleArrow_1 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_3);
        it_1.setType(_doubleArrow_1);
      };
      Parameter _doubleArrow_1 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_1, _function_2);
      _ownedParameters_1.add(_doubleArrow_1);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void TestClass_twoParameterOperation(TestClass* const me, uint32 param1, uint8 param2);", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test08_TwoParameterOperation2() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("twoArgsOperation");
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_1 = (Parameter it_1) -> {
        it_1.setName("arg1");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("char");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Parameter _doubleArrow = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_1);
      _ownedParameters.add(_doubleArrow);
      EList<Parameter> _ownedParameters_1 = it.getOwnedParameters();
      Parameter _createParameter_1 = this.factory.createParameter();
      final Procedure1<Parameter> _function_2 = (Parameter it_1) -> {
        it_1.setName("arg2");
        it_1.setDirection(ParameterDirectionKind.INOUT_LITERAL);
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_3 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow_1 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_3);
        it_1.setType(_doubleArrow_1);
      };
      Parameter _doubleArrow_1 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_1, _function_2);
      _ownedParameters_1.add(_doubleArrow_1);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass2");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void TestClass2_twoArgsOperation(TestClass2* const me, char arg1, int* arg2);", code);
  }

  /**
   * Ist bei einer Operation das Attribut "isStatic" auf true gesetzt, soll kein me-Pointer generiert werden.
   * Wenn dadurch die Parameterliste leer ist, soll "void" generiert werden.
   */
  @Test
  public void test09_StaticOperation() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("staticOperation");
      it.setIsStatic(true);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void TestClass_staticOperation(void);", code);
  }

  /**
   * Auch wenn es einen Return Parameter gibt soll in der Parameterliste "void stehen.
   */
  @Test
  public void test10_StaticOperation2() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("staticFunction");
      it.setIsStatic(true);
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_1 = (Parameter it_1) -> {
        it_1.setName("ret");
        it_1.setDirection(ParameterDirectionKind.RETURN_LITERAL);
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Parameter _doubleArrow = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_1);
      _ownedParameters.add(_doubleArrow);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestType");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("int TestType_staticFunction(void);", code);
  }

  /**
   * Ein einzelner Parameter ohne me-Pointer.
   */
  @Test
  public void test11_StaticOperationWithParam() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("staticOperation");
      it.setIsStatic(true);
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_1 = (Parameter it_1) -> {
        it_1.setName("param1");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_2 = (PrimitiveType it_2) -> {
          it_2.setName("uint32");
        };
        PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_2);
        it_1.setType(_doubleArrow);
      };
      Parameter _doubleArrow = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_1);
      _ownedParameters.add(_doubleArrow);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void TestClass_staticOperation(uint32 param1);", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test12_StaticOperationWithParam2() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("staticOperation");
      it.setIsStatic(true);
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_1 = (Parameter it_1) -> {
        it_1.setName("arg");
        org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
        final Procedure1<org.eclipse.uml2.uml.Class> _function_2 = (org.eclipse.uml2.uml.Class it_2) -> {
          it_2.setName("OtherClass");
        };
        org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_2);
        it_1.setType(_doubleArrow);
      };
      Parameter _doubleArrow = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_1);
      _ownedParameters.add(_doubleArrow);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    Assert.assertEquals("void TestClass_staticOperation(OtherClass* arg);", code);
  }

  /**
   * Das Modell enthält eine Implementation der Operation, der Konzext ist zudem "implementation".
   * Es gibt bereits ein OpaqueBehaviorTemplate, welches Ihnen weiterhelfen könnte.
   */
  @Test
  public void test13_ImplementedOperation() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("implementedOperation");
      EList<Behavior> _methods = it.getMethods();
      OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
      final Procedure1<OpaqueBehavior> _function_1 = (OpaqueBehavior it_1) -> {
        EList<String> _languages = it_1.getLanguages();
        _languages.add("C");
        EList<String> _bodies = it_1.getBodies();
        _bodies.add("/* hier koennte Ihre Werbung stehen */");
      };
      OpaqueBehavior _doubleArrow = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_1);
      _methods.add(_doubleArrow);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void TestClass_implementedOperation(TestClass* const me) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/* hier koennte Ihre Werbung stehen */");
    _builder.newLine();
    _builder.append("}");
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Operation mit Implementation und Parametern.
   */
  @Test
  public void test14_ImplementedOperation2() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("fibonacci");
      EList<Behavior> _methods = it.getMethods();
      OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
      final Procedure1<OpaqueBehavior> _function_1 = (OpaqueBehavior it_1) -> {
        EList<String> _languages = it_1.getLanguages();
        _languages.add("C");
        EList<String> _bodies = it_1.getBodies();
        _bodies.add("return 1 < arg ? C_fibonacci(me, arg - 1) + C_fibonacci(me, arg - 2) : 1;");
      };
      OpaqueBehavior _doubleArrow = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_1);
      _methods.add(_doubleArrow);
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_2 = (Parameter it_1) -> {
        it_1.setName("arg");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_3 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow_1 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_3);
        it_1.setType(_doubleArrow_1);
      };
      Parameter _doubleArrow_1 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_2);
      _ownedParameters.add(_doubleArrow_1);
      EList<Parameter> _ownedParameters_1 = it.getOwnedParameters();
      Parameter _createParameter_1 = this.factory.createParameter();
      final Procedure1<Parameter> _function_3 = (Parameter it_1) -> {
        it_1.setName("ret");
        it_1.setDirection(ParameterDirectionKind.RETURN_LITERAL);
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_4 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow_2 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_4);
        it_1.setType(_doubleArrow_2);
      };
      Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_1, _function_3);
      _ownedParameters_1.add(_doubleArrow_2);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("C");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("int C_fibonacci(C* const me, int arg) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return 1 < arg ? C_fibonacci(me, arg - 1) + C_fibonacci(me, arg - 2) : 1;");
    _builder.newLine();
    _builder.append("}");
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Beim Kontext "declaration" soll weiterhin nur die Signatur generiert werden.
   */
  @Test
  public void test15_DeclaredOperation() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("implementedOperation");
      EList<Behavior> _methods = it.getMethods();
      OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
      final Procedure1<OpaqueBehavior> _function_1 = (OpaqueBehavior it_1) -> {
        EList<String> _languages = it_1.getLanguages();
        _languages.add("C");
        EList<String> _bodies = it_1.getBodies();
        _bodies.add("/* hier koennte Ihre Werbung stehen */");
      };
      OpaqueBehavior _doubleArrow = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_1);
      _methods.add(_doubleArrow);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void TestClass_implementedOperation(TestClass* const me);");
    Assert.assertEquals(_builder.toString(), code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test16_DeclaredOperation2() {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName("fibonacci");
      EList<Behavior> _methods = it.getMethods();
      OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
      final Procedure1<OpaqueBehavior> _function_1 = (OpaqueBehavior it_1) -> {
        EList<String> _languages = it_1.getLanguages();
        _languages.add("C");
        EList<String> _bodies = it_1.getBodies();
        _bodies.add("return 1 < arg ? C_fibonacci(me, arg - 1) + C_fibonacci(me, arg - 2) : 1;");
      };
      OpaqueBehavior _doubleArrow = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_1);
      _methods.add(_doubleArrow);
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      Parameter _createParameter = this.factory.createParameter();
      final Procedure1<Parameter> _function_2 = (Parameter it_1) -> {
        it_1.setName("arg");
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_3 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow_1 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_3);
        it_1.setType(_doubleArrow_1);
      };
      Parameter _doubleArrow_1 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_2);
      _ownedParameters.add(_doubleArrow_1);
      EList<Parameter> _ownedParameters_1 = it.getOwnedParameters();
      Parameter _createParameter_1 = this.factory.createParameter();
      final Procedure1<Parameter> _function_3 = (Parameter it_1) -> {
        it_1.setName("ret");
        it_1.setDirection(ParameterDirectionKind.RETURN_LITERAL);
        PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
        final Procedure1<PrimitiveType> _function_4 = (PrimitiveType it_2) -> {
          it_2.setName("int");
        };
        PrimitiveType _doubleArrow_2 = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_4);
        it_1.setType(_doubleArrow_2);
      };
      Parameter _doubleArrow_2 = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter_1, _function_3);
      _ownedParameters_1.add(_doubleArrow_2);
    };
    final Operation operation = ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("C");
      EList<Operation> _ownedOperations = it.getOwnedOperations();
      _ownedOperations.add(operation);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(operation, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("int C_fibonacci(C* const me, int arg);");
    Assert.assertEquals(_builder.toString(), code);
  }
}
