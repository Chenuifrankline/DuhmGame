package codegenerator.test.exercise2;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestParameter2 {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Der Name eines Parameter soll nicht durch die Operation beeinflusst werden.
   */
  @Test
  public void test00_ParameterInOperation() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("testParam");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("int");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function_1 = (Operation it) -> {
      it.setName("testOp");
      EList<Parameter> _ownedParameters = it.getOwnedParameters();
      _ownedParameters.add(parameter);
    };
    ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function_1);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("int testParam", code);
  }

  /**
   * Enumerationen sollen wie PrimitiveTypes gehandhabt werden, was das Generieren von Pointern angeht.
   */
  @Test
  public void test01_EnumParameter() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("testEnum");
      Enumeration _createEnumeration = this.factory.createEnumeration();
      final Procedure1<Enumeration> _function_1 = (Enumeration it_1) -> {
        it_1.setName("AnEnum");
      };
      Enumeration _doubleArrow = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("AnEnum testEnum", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test02_EnumParameter2() {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName("testEnumeration");
      Enumeration _createEnumeration = this.factory.createEnumeration();
      final Procedure1<Enumeration> _function_1 = (Enumeration it_1) -> {
        it_1.setName("Aufzaehlung");
      };
      Enumeration _doubleArrow = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_1);
      it.setType(_doubleArrow);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("Aufzaehlung testEnumeration", code);
  }

  /**
   * Befindet sich ein Enum in einem Namespace, muss dieser im C-Namen auftauchen.
   */
  @Test
  public void test03_EnumInPackage() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("Aufzaehlung");
    };
    final Enumeration enumeration = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("pack");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(enumeration);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function_2 = (Parameter it) -> {
      it.setName("testEnum");
      it.setType(enumeration);
    };
    final Parameter parameter = ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function_2);
    final String code = new Uml2C().generateCode(parameter, "parameter");
    Assert.assertEquals("pack_Aufzaehlung testEnum", code);
  }
}
